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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.ImageCursor;
import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.effect.DropShadow;

public class BattleShipPage {

    private Scene scene;
    private Stage primaryStage;
    private MainPage mainPage;
    private BattleshipLogic BJLogic;
  
    public BattleShipPage() {
        BJLogic = new BattleshipLogic();
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

        //ShipImages
        ImageView ship2Holes1 = mainPage.getImageView("2HolesShip.png", 100, 200);
        ImageView ship2Holes2 = mainPage.getImageView("2HolesShip.png", 100, 200);
        ImageView ship3Holes1 = mainPage.getImageView("3HolesShip.png", 100, 255);
        ImageView ship3Holes2 = mainPage.getImageView("3HolesShip.png", 100, 255);
        ImageView ship4Holes = mainPage.getImageView("4HolesShip.png", 100, 350);

        //Add ship images to VBox
        VBox ships = new VBox();
        ships.setTranslateX(770);
        ships.setTranslateY(200);
        HBox ship2HolesHbox = new HBox(ship2Holes1, ship2Holes2);
        ship2HolesHbox.setSpacing(-70);
        HBox ship3HolesHbox = new HBox(ship3Holes1, ship3Holes2);
        ship3HolesHbox.setSpacing(-50);
        ships.getChildren().addAll(ship2HolesHbox, ship3HolesHbox, ship4Holes);
        bsGamePane.getChildren().add(ships);

        //counters for game
        String cssStyle = "    -fx-padding: 10;\n" +
                "    -fx-font-size:50;" +
                "    -fx-font:50px Copperplate";
        Label triesLeft = new Label("30");
        triesLeft.setStyle(cssStyle);
        Label triesUsed = new Label("0");
        triesUsed.setStyle(cssStyle);
        Label hit = new Label("0");
        hit.setStyle(cssStyle);

        //Add counters to VBox and display
        VBox counters = new VBox();
        counters.getChildren().addAll(triesLeft, triesUsed, hit);
        counters.setSpacing(-15);
        counters.setTranslateX(1050);
        counters.setTranslateY(500);
        bsGamePane.getChildren().add(counters);

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
                        int check = BJLogic.isHit(gp.getRowIndex(rect), gp.getColumnIndex(rect));
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
                           BSEndPage("won.PNG");
                       } else if (BJLogic.winOrLose() == -1) {
                           BSEndPage("lose.PNG");
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
}
