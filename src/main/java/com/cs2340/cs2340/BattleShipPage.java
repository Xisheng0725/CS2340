package com.cs2340.cs2340;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.ImageCursor;
import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.effect.DropShadow;
import static java.lang.Character.getNumericValue;

public class BattleShipPage {

    private Scene scene;
    private Stage primaryStage;
    private MainPage mainPage;
    private BattleshipLogic BJLogic;

    private final int startingGuessCount;
    private final int startingTargetCount;

    private final ImageView[] ships = new ImageView[5];
    private int[] damages = new int[5];
  
    public BattleShipPage() {
        BJLogic = new BattleshipLogic();
        startingGuessCount = BJLogic.getMaxGuess();
        startingTargetCount = BJLogic.getNumRemain();
    }
  
    public void formatGameScreen(Scene bsGameScene, Pane bsGamePane, Stage primaryStage, MainPage mainPage) {
        BJLogic.addPattern();
        scene = bsGameScene;
        this.primaryStage = primaryStage;
        this.mainPage = mainPage;

        //Set Cursor
        Image image = new Image("cursor.png");
        bsGameScene.setCursor(new ImageCursor(image));

        //Background
        ImageView bsBg = MainPage.getImageView("bg.PNG", 800,1200);
        bsGamePane.getChildren().add(bsBg);

        // Ships
        initShips();
        bsGamePane.getChildren().addAll(ships);

        // Remaining guesses text
        Font valueFont = new Font("verdana", 56);
        Color textColor = new Color(.3, .3, .3, 1);
        Text remainingGuesses = new Text(1035, 568, ""+BJLogic.getMaxGuess());
        remainingGuesses.setFont(valueFont);
        remainingGuesses.setFill(textColor);
        Text guessesUsed = new Text(1035, 648, ""+(startingGuessCount - BJLogic.getMaxGuess()));
        guessesUsed.setFont(valueFont);
        guessesUsed.setFill(textColor);
        Text hits = new Text(1035, 725, ""+(startingTargetCount - BJLogic.getNumRemain()));
        hits.setFont(valueFont);
        hits.setFill(textColor);
        bsGamePane.getChildren().addAll(remainingGuesses, guessesUsed, hits);

        //GridBoard
        GridPane gp = new GridPane();
        Rectangle[][] board = new Rectangle[8][8];
        gp.setMinSize(1000, 1000);
        gp.setHgap(2);
        gp.setVgap(2);
        int rows = 8;
        int cols = 8;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Rectangle rect = new Rectangle();
                rect.setHeight(75);
                rect.setWidth(75);
                board[i][j] = rect;
                Color color = Color.rgb(40, 116, 166);
                rect.setFill(color);
                gp.add(board[i][j], i, j);
                shadow(rect);

                //if hit the ship, grid turns red; else turns white
                rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {

                        //change ship pattern
                        char shipChar = BJLogic.getPattern()[gp.getRowIndex(rect)][gp.getColumnIndex(rect)];
                        if (shipChar == '0' || shipChar == '1' ||shipChar == '2' || shipChar == '3' || shipChar == '4') {
                            int shipNum = getNumericValue(shipChar);
                            String fileName = hit(shipNum);
                            ImageView newImageView = MainPage.getImageView(fileName, 95, 400);
                            newImageView.setTranslateY(180 + shipNum * 60);
                            newImageView.setTranslateX(790);
                            bsGamePane.getChildren().add(newImageView);
                        }

                        //change grid color
                        int check = BJLogic.isHit(gp.getRowIndex(rect), gp.getColumnIndex(rect));
                        remainingGuesses.setText(""+BJLogic.getMaxGuess());
                        guessesUsed.setText(""+(startingGuessCount - BJLogic.getMaxGuess()));
                        hits.setText(""+(startingTargetCount - BJLogic.getNumRemain()));
                        if (check == 1) {
                            Color right = Color.rgb(241, 148, 138);
                            rect.setFill(right);
                        } else if (check == -1) {
                            Color wrong = Color.rgb(189, 195, 199);
                            rect.setFill(wrong);
                        } else if (check == 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "This box has Been boomed, Please try another one");
                            alert.showAndWait();
                        }

                       if (BJLogic.winOrLose() == 1) {
                           BSEndPage("bs_win.PNG");
                       } else if (BJLogic.winOrLose() == -1) {
                           BSEndPage("bs_lose.PNG");
                       }
                    }
                });
            }
        }
        gp.setTranslateX(100);
        gp.setTranslateY(140);
        bsGamePane.getChildren().add(gp);

        //Return Button
        Button returnBtn = new Button("Return");
        MainPage.setButton(returnBtn);
        returnBtn.setTranslateX(30);
        returnBtn.setTranslateY(750);
        bsGamePane.getChildren().add(returnBtn);
        ColorPage.glow((returnBtn));
        returnBtn.setOnAction(e -> {
            primaryStage.setScene(mainPage.getSelectScene());
        });
    }
  
    public void BSEndPage(String indicator) {
        String cssStyle = " -fx-text-fill: #006464;\n" +
                "    -fx-background-color: #DFB951;\n" +
                "    -fx-border-radius: 30;\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-padding: 10;\n" +
                "    -fx-font-size:40;";
        ImageView BJOutcome = mainPage.getImageView(indicator, 400, 500);;
        Button BJReturnBtn = new Button("Return");
        makeGlow(BJReturnBtn);
        BJReturnBtn.setStyle(cssStyle);
        BJReturnBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                reset();
                primaryStage.setScene(mainPage.getSelectScene());
            }
        });

        HBox BJOptions = new HBox();

        //Setup replay button
        Button replayBtn = new Button("Replay");
        makeGlow(replayBtn);
        replayBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                formatGameScreen(mainPage.bsGameScene, mainPage.bsGamePane, primaryStage, mainPage);
                reset();
            }
        });
        replayBtn.setStyle(cssStyle);
        BJOptions.getChildren().addAll(replayBtn, BJReturnBtn);
        BJOptions.setSpacing(40);
        BJOptions.setTranslateX(475);
        BJOptions.setTranslateY(500);
        BJOutcome.setTranslateY(70);
        BJOutcome.setTranslateX(300);

        for(Node child : mainPage.bsGamePane.getChildren()) {
            child.setOpacity(0.5);
        }

        mainPage.bsGamePane.getChildren().addAll(BJOutcome, BJOptions);
    }

    public void reset() {
        mainPage.bsGamePane = new Pane();
        scene.setRoot(mainPage.bsGamePane);
        BJLogic = new BattleshipLogic();
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // reset the blackjacklogic variable here

        formatGameScreen(mainPage.bsGameScene, mainPage.bsGamePane, primaryStage, mainPage);
    }

    public void makeGlow(Node node) {
        double glowAmount = 0.5;
        Glow glow = new Glow();
        glow.setLevel(0);
        node.setEffect(glow);
        node.setOnMouseEntered(e -> {
            glow.setLevel(glowAmount);
        });
        node.setOnMouseExited(e -> {
            glow.setLevel(0);
        });
    }
  
    public void shadow(Node node) {
        node.setOnMouseEntered(e ->{
            node.setEffect(new DropShadow(45, Color.PINK));
        });
        node.setOnMouseExited(e -> {
            node.setEffect(new DropShadow(0, Color.PINK));
        });
    }

    private void initShips() {
        ships[0] = MainPage.getImageView("2holes_ship.PNG", 95, 400);
        ships[1] = MainPage.getImageView("2holes_ship.PNG", 95, 400);

        ships[2] = MainPage.getImageView("3holes_ship.PNG", 95, 400);
        ships[3] = MainPage.getImageView("3holes_ship.PNG", 95, 400);

        ships[4] = MainPage.getImageView("4holes_ship.PNG", 95, 400);

        for (int i = 0; i < 5; i++) {
            ships[i].setTranslateY(180 + i * 60);
            ships[i].setTranslateX(790);
        }
        damages = new int[5];
    }

    private String hit(int index) {
        int max = 0;
        String prefix = "";
        String suffix;
        switch (index) {
            case 0:
            case 1:
                prefix = "2holes_";
                max = 2;
                break;
            case 2:
            case 3:
                prefix = "3holes_";
                max = 3;
                break;
            case 4:
                prefix = "4holes_";
                max = 4;
                break;
        }

        damages[index] += 1;
        if (damages[index] >= max) {
            suffix = "sunk.png";
        } else {
            suffix = "damage" + damages[index] + ".png";
        }
        return prefix + suffix;
    }
}
