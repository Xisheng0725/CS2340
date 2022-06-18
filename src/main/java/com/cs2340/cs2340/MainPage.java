package com.cs2340.cs2340;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;

import static javafx.application.Platform.exit;

/**
 *Represents MainPage named MainPage.
 *@author Xisheng Zhang, Qihui Wang, Porter Zach, Xueqing Li, and Shane Sinnerine.
 *@version 1.0.
 */
public class MainPage extends Application {

    private static Scene selectScene;
    private static Button tempBackBtn;
    private static int gtcCounter;
    private static ColorGame colorGameLogic;
    /**
     * Create the main method.
     * Please add commons or JavaDocs description when you add something
     * @param args take in
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * Create the start method that using for main page GUI.
     * @param primaryStage take in
     */
    public void start(Stage primaryStage) {

        //Create all objects to be placed on home screen.
        Button homeStartBtn = new Button("Start game");
        StackPane homeMain = new StackPane();
        homeMain.setStyle("-fx-background-color: azure");
        BorderPane homeBP = new BorderPane();
        ImageView homeTitle = getImageView("/royale2.PNG", 350, 700);
        ImageView icons = getImageView("/icons.PNG", 400, 900);
        Button exitBtn = new Button("Exit");


        //Create and format homeScreen
        formatHomeScreen(homeStartBtn, homeMain, homeBP, homeTitle, icons, exitBtn);
        Scene homeScene = new Scene(homeMain, 1200, 800);

        
        //Set up primary stage
        primaryStage.setTitle("Casino Royale Deluxe");
        primaryStage.setScene(homeScene);

        //Create objects needed for selection page.
        StackPane selectMain = new StackPane();
        selectMain.setStyle("-fx-background-color: azure");
        BorderPane selectBP = new BorderPane();
        Button selectReturn = new Button("Return to Main Page");
        Button selectBsInfo = new Button("Instructions");
        Button selectBjInfo = new Button("Instructions");
        Button selectClrInfo = new Button("Instructions");
        HBox hbox = new HBox(100);
        ImageView selectBsImageView = getImageView("/battleship.JPG", 200, 250);
        ImageView selectBjImageView = getImageView("/blackjack.JPG", 200, 250);
        ImageView selectClrImageView = getImageView("/color.JPG", 200, 250);
        ImageView selectionTitle = getImageView("/royale.PNG", 250, 800);

        //create and format selection scene
        formatSelectScene(selectMain, selectBP, selectReturn, hbox, selectBsImageView, selectBjImageView, selectClrImageView, selectBsInfo, selectBjInfo, selectClrInfo, selectionTitle);
        selectScene = new Scene(selectMain, 1200, 800);

        //Create temporary construction scene
        Text tempTxt = new Text("This page is under construction...");
        tempBackBtn = new Button("Return");
        BorderPane tempBP = new BorderPane();
        tempBP.setCenter(tempTxt);
        tempBP.setBottom(tempBackBtn);
        tempBackBtn.setOnAction(e -> primaryStage.setScene(selectScene));
        tempBP.setPadding(new Insets(20, 20, 20, 20));
        Scene tempScene = new Scene(tempBP, 1200, 800);


        // Create the Color Game game Page
        colorGameLogic = new ColorGame();
        BorderPane colorGame = new BorderPane();
        StackPane colorGamePane = new StackPane();
        Scene colorGameScene = new Scene(colorGamePane, 1200, 800);
        Button gameReturnButton = new Button("Return");
        gameReturnButton.setOnAction(e -> primaryStage.setScene(selectScene));
        ImageView colorGameTitle = getImageView("colorBack.PNG", 800, 1200);
        formatGameScreen(colorGameScene, colorGamePane, colorGame, colorGameTitle, primaryStage);

        //Create the color game instruction page
        BorderPane colorBP = new BorderPane();
        StackPane colorPane = new StackPane();
        Scene colorScene = new Scene(colorPane, 1200, 800);
        ImageView colorTitle = getImageView("color.PNG", 800, 1200);
        Button colorBackBtn = new Button("Return");
        colorBackBtn.setOnAction(e -> primaryStage.setScene(selectScene));

        Button enterGame = new Button("Start Game");
        enterGame.setOnAction(e -> primaryStage.setScene(colorGameScene));
        formatColorScreen(colorScene, gameReturnButton, colorPane, colorBP, colorTitle, enterGame);

        // setButton Style
        gameButtonStyle(selectReturn, selectBsInfo, selectBjInfo, selectClrInfo, tempBackBtn, homeStartBtn);

        //Home Screen event mapping
        homeStartBtn.setOnAction(e -> primaryStage.setScene(selectScene));

        //Selection screen event mapping
        selectReturn.setOnAction(e -> primaryStage.setScene(homeScene));


        // set animation and MouseClickedAction
        selectBjImageView.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                FadeTransition fadeBj = new FadeTransition(Duration.millis(1500), selectBjImageView);
                FadeTransition fadeClr = new FadeTransition(Duration.millis(1500), selectClrImageView);
                fadeBj.setFromValue(10);
                fadeBj.setToValue(0.1);
                fadeBj.setCycleCount(2);
                fadeBj.play();

                fadeClr.setFromValue(10);
                fadeClr.setToValue(1);
                fadeClr.setCycleCount(1);
                fadeClr.play();

                fadeClr.setOnFinished(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        fadeBj.stop();
                        fadeClr.stop();
                        primaryStage.setScene(tempScene);
                    }
                }) ;
            }
        });

        // set animation and MouseClickedAction
        selectBsImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event2) {
                FadeTransition fadeBS = new FadeTransition(Duration.millis(1500), selectBsImageView);
                FadeTransition fadeClr = new FadeTransition(Duration.millis(1500), selectClrImageView);
                fadeBS.setFromValue(10);
                fadeBS.setToValue(0.1);
                fadeBS.setCycleCount(2);
                fadeBS.play();

                fadeClr.setFromValue(10);
                fadeClr.setToValue(1);
                fadeClr.setCycleCount(1);
                fadeClr.play();

                fadeClr.setOnFinished(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        fadeBS.stop();
                        fadeClr.stop();
                        primaryStage.setScene(tempScene);
                    }
                }) ;
            }
        });

        // set animation and MouseClickedAction
        selectClrImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event3) {
                FadeTransition fadeBj = new FadeTransition(Duration.millis(1500), selectBjImageView);
                FadeTransition fadeClr = new FadeTransition(Duration.millis(1500), selectClrImageView);
                fadeClr.setFromValue(10);
                fadeClr.setToValue(0.1);
                fadeClr.setCycleCount(2);
                fadeClr.play();

                fadeBj.setFromValue(10);
                fadeBj.setToValue(1);
                fadeBj.setCycleCount(1);
                fadeBj.play();

                fadeBj.setOnFinished(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        fadeBj.stop();
                        fadeClr.stop();
                        resetClrGame(colorGameScene, primaryStage);
                    }
                });
            }
        });
        selectBjInfo.setOnAction(e -> primaryStage.setScene(tempScene));
        selectBsInfo.setOnAction(e -> primaryStage.setScene(tempScene));
        selectClrInfo.setOnAction(e -> primaryStage.setScene(colorScene));

        //Show primary stage
        primaryStage.show();
    }
    /**
     * change the button style.
     * @param selectReturn Return button
     * @param selectBsInfo Info button
     * @param selectBjInfo Info Button
     * @param selectClrInfo Info Button
     * @param tempBackBtn Back Button
     */
    private void gameButtonStyle(Button selectReturn, Button selectBsInfo, Button selectBjInfo, Button selectClrInfo, Button tempBackBtn, Button homeStartBtn) {
        String cssStyle = " -fx-text-fill: #006464;\n" +
                "    -fx-background-color: #DFB951;\n" +
                "    -fx-border-radius: 20;\n" +
                "    -fx-background-radius: 20;\n" +
                "    -fx-padding: 5;";
        String cssForHome = " -fx-text-fill: #006464;\n" +
                "    -fx-background-color: #DFB951;\n" +
                "    -fx-border-radius: 30;\n" +
                "    -fx-background-radius: 90;\n" +
                "    -fx-padding: 20;\n" +
                "    -fx-font-size: 20";
        homeStartBtn.setStyle(cssForHome);
        selectReturn.setStyle((cssStyle));
        selectBsInfo.setStyle((cssStyle));
        selectBjInfo.setStyle((cssStyle));
        selectClrInfo.setStyle((cssStyle));
        tempBackBtn.setStyle((cssStyle));
    }
    /**
     * change the layout of main page.
     * @param main is a stackpane for main
     * @param bp BoardPane
     * @param retBtn return button
     * @param bsInfo info button
     * @param clrInfo info button
     * @param hbox hbox
     * @param bsIV image
     * @param bjIV image
     * @param clrIV image
     * @param selectionTitle image
     */
    private void formatSelectScene(StackPane main, BorderPane bp, Button retBtn, HBox hbox, ImageView bsIV, ImageView bjIV,
                                   ImageView clrIV, Button bsInfo, Button bjInfo, Button clrInfo, ImageView selectionTitle) {
        VBox bsVBox = new VBox();
        bsVBox.setSpacing(10);
        bsVBox.getChildren().addAll(bsIV, bsInfo);
        bsVBox.setAlignment(Pos.CENTER);

        VBox bjVBox = new VBox();
        bjVBox.setSpacing(10);
        bjVBox.getChildren().addAll(bjIV, bjInfo);
        bjVBox.setAlignment(Pos.CENTER);

        VBox clrVBox = new VBox();
        clrVBox.setSpacing(10);
        clrVBox.getChildren().addAll(clrIV, clrInfo);
        clrVBox.setAlignment(Pos.CENTER);

        hbox.getChildren().addAll(bsVBox, bjVBox, clrVBox);
        hbox.setAlignment(Pos.CENTER);

        bp.setCenter(hbox);
        bp.setTop(selectionTitle);
        bp.setBottom(retBtn);
        selectionTitle.setTranslateX(200);
        selectionTitle.setTranslateY(25);
        hbox.setTranslateY(-90);
        retBtn.setTranslateX(30);
        retBtn.setTranslateY(-30);
        main.getChildren().addAll(bp);
    }

    /**
     *
     * @param btnStart Start button
     * @param homeScreen stackPane to create scene with
     * @param homeBP borderpane to create home scene with
     */
    private void formatHomeScreen(Button btnStart, StackPane homeScreen, BorderPane homeBP, ImageView homeTitle, ImageView icons, Button exitBtn) {
        //Add elements to home screen scene
        VBox vbox = new VBox(15);
        vbox.getChildren().addAll(homeTitle, btnStart, icons);
        vbox.setAlignment(Pos.CENTER);
        homeBP.setCenter(vbox);
        exitBtn.setStyle(" -fx-text-fill: #006464;\n" +
                "    -fx-background-color: #DFB951;\n" +
                "    -fx-border-radius: 30;\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-padding: 10;\n" +
                "    -fx-font-size:20;");
        exitBtn.setTranslateX(-520);
        exitBtn.setTranslateY(-350);
        exitBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exit();
            }
        });
        homeScreen.getChildren().addAll(homeBP, exitBtn);
    }

    //format of color game page itself

    private void formatGameScreen(Scene colorGameScene, StackPane colorGamePane, BorderPane colorGame, ImageView colorGameTitle, Stage primaryStage) {
        gtcCounter = 0;
        Button colorBackBtn = new Button("Return");
        setButton(colorBackBtn);
        colorBackBtn.setTranslateX(30);
        colorBackBtn.setTranslateY(-30);
        colorGame.setBottom(colorBackBtn);
        ImageView gamePosition = getImageView("gp.png", 785, 1300);

        VBox imageCheckHead = new VBox();
        ImageView checkHead = getImageView("submit1.png", 300, 300);
        imageCheckHead.getChildren().addAll(checkHead);
        imageCheckHead.setTranslateX(770);
        imageCheckHead.setTranslateY(500);

        //Glow effect for image on mouse hover
        Glow glow = new Glow();
        glow.setLevel(0);
        imageCheckHead.setEffect(glow);
        imageCheckHead.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(colorGameLogic.hasWon() || colorGameLogic.hasLost()) {
                    return;
                }
                glow.setLevel(0.5);
            }
        });
        imageCheckHead.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(colorGameLogic.hasWon() || colorGameLogic.hasLost()) {
                    return;
                }
                glow.setLevel(0);
            }
        });

        HBox title = new HBox();
        ImageView title1 = getImageView("GTC Title.png", 100, 500);
        title.getChildren().addAll(title1);
        title.setTranslateX(350);
        title.setTranslateY(20);

        VBox grey = new VBox(15);
        ImageView grey1 = getImageView("gray.png", 50, 50);
        ImageView grey2 = getImageView("gray.png", 50, 50);
        ImageView grey3 = getImageView("gray.png", 50, 50);
        ImageView grey4 = getImageView("gray.png", 50, 50);
        grey.getChildren().addAll(grey1, grey2, grey3, grey4);
        grey.setTranslateX(1025);
        grey.setTranslateY(175);


        HBox allColors = new HBox(40);
        ImageView red = getImageView("red.png", 50, 50);
        Image redImage = new Image("red.png");
        ImageView orange = getImageView("orange.png", 50, 50);
        Image orangeImage = new Image("orange.png");
        ImageView yellow = getImageView("yellow.png", 50, 50);
        Image yellowImage = new Image("yellow.png");
        ImageView green = getImageView("green.png", 50, 50);
        Image greenImage = new Image("green.png");
        ImageView blue = getImageView("blue.png", 50, 50);
        Image blueImage = new Image("blue.png");
        ImageView purple = getImageView("purple.png", 50, 50);
        Image purpleImage = new Image("purple.png");
        allColors.getChildren().addAll(red, orange, yellow, green, blue, purple);
        allColors.setTranslateX(140);
        allColors.setTranslateY(610);



        GridPane eachRound = new GridPane();
        eachRound.setMinSize(700, 500);
        eachRound.setHgap(60);
        eachRound.setVgap(25);
        Circle[][] pins = new Circle[8][4];
        GTCColor[][] pinColors = new GTCColor[8][4];
        int rows = 8;
        int columns = 4;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                pinColors[i][j] = GTCColor.Empty;
                Circle circle = new Circle(20);
                circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(circle.getFill() != Color.GREY) {
                            gtcCounter--;
                        }
                        circle.setFill(Color.GREY);
                        circle.setEffect(null);
                    }
                });
                pins[i][j] = circle;
                circle.setFill(Color.GREY);
                eachRound.add(pins[i][j], i, j);
            }
            ImageView fourNotIV = getImageView("4not.png", 50 ,50);
            fourNotIV.setTranslateX(-5);
            eachRound.add(fourNotIV, i, 4);
        }

        eachRound.setTranslateX(150);
        eachRound.setTranslateY(180);
        red.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(gtcCounter == 4 || colorGameLogic.hasWon()) {
                    return;
                }
                gtcCounter++;
                Image redImage = new Image("red.png");
                enterPin(redImage, pins, pinColors, GTCColor.Red);
            }
        });
        orange.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(gtcCounter == 4 || colorGameLogic.hasWon()) {
                    return;
                }
                gtcCounter++;
                Image orangeImage = new Image("orange.png");
                enterPin(orangeImage, pins, pinColors, GTCColor.Orange);
            }
        });
        yellow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(gtcCounter == 4 || colorGameLogic.hasWon()) {
                    return;
                }
                gtcCounter++;
                Image yellowImage = new Image("yellow.png");
                enterPin(yellowImage, pins, pinColors, GTCColor.Yellow);
            }
        });
        green.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(gtcCounter == 4 || colorGameLogic.hasWon()) {
                    return;
                }
                gtcCounter++;
                Image greenImage = new Image("green.png");
                enterPin(greenImage, pins, pinColors, GTCColor.Green);
            }
        });
        blue.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(gtcCounter == 4 || colorGameLogic.hasWon()) {
                    return;
                }
                gtcCounter++;
                Image blueImage = new Image("blue.png");
                enterPin(blueImage, pins, pinColors, GTCColor.Blue);
            }
        });
        purple.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(gtcCounter == 4 || colorGameLogic.hasWon()) {
                    return;
                }
                gtcCounter++;
                Image purpleImage = new Image("purple.png");
                enterPin(purpleImage, pins, pinColors, GTCColor.Purple);
            }
        });

        //Adding glow to color selection
        glow(red);
        glow(blue);
        glow(green);
        glow(yellow);
        glow(orange);
        glow(purple);

        colorBackBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clearPin(pins);
                primaryStage.setScene(selectScene);
            }
        });

        //Submit button logic
        imageCheckHead.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(gtcCounter != 4) {
                    return;
                }
                if(colorGameLogic.getNumGuesses() >= 8 || colorGameLogic.hasWon()) {
                    return;
                }
                gtcCounter = 0;
                GTCColor[] guess = getGuess(pins, pinColors);
                colorGameLogic.guess(guess);
                ImageView hint = hintToIV(colorGameLogic.getHint());
                hint.setTranslateX(-5);
                eachRound.add(hint, colorGameLogic.getNumGuesses() - 1, 4);
                if(colorGameLogic.hasLost() || colorGameLogic.hasWon()){
                    colorGameEnd(colorGameLogic, checkHead, colorGamePane, primaryStage, pins,
                            pinColors, colorGameScene, grey);
                    return;
                }
            }
        });
        colorGamePane.getChildren().addAll(colorGameTitle, gamePosition, title, colorGame, eachRound,
                grey, allColors, imageCheckHead);
    }

    private void glow(Node color) {
        Glow glow = new Glow();
        color.setEffect(glow);
        glow.setLevel(0);
        color.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(colorGameLogic.hasWon() || colorGameLogic.hasLost()) {
                    return;
                }
                glow.setLevel(0.5);
            }
        });
        color.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(colorGameLogic.hasWon() || colorGameLogic.hasLost()) {
                    return;
                }
                glow.setLevel(0);
            }
        });
    }

    /**
     * Logic for when game ends
     * @param colorGameLogic statistics of game
     */
    private void colorGameEnd(ColorGame colorGameLogic, ImageView checkHead, StackPane colorGamePane, Stage primaryStage,
                              Circle[][] pins, GTCColor[][] pinColors, Scene colorGameScene, VBox grey) {

        //Styling for buttons
        String cssStyle = " -fx-text-fill: #006464;\n" +
                "    -fx-background-color: #DFB951;\n" +
                "    -fx-border-radius: 30;\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-padding: 10;\n" +
                "    -fx-font-size:40;";

        //Decide image based on outcome of game
        ImageView GTCOutcome;
        if(colorGameLogic.hasWon()) {
            GTCOutcome = getImageView("SubmitWin.png", 500, 500);
        } else {
            GTCOutcome = getImageView("SubmitLoss.png", 500, 500);
        }

        //Setup return button
        Button GTCReturnBtn = new Button("Return");
        GTCReturnBtn.setStyle(cssStyle);
        GTCReturnBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(selectScene);
            }
        });

        HBox GTCOptions = new HBox();

        //Setup replay button
        Button replayBtn = new Button("Replay");
        replayBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                resetClrGame(colorGameScene, primaryStage);
            }
        });

        //Format buttons
        replayBtn.setStyle(cssStyle);
        GTCOptions.getChildren().addAll(replayBtn, GTCReturnBtn);
        GTCOptions.setSpacing(40);
        GTCOptions.setTranslateX(475);
        GTCOptions.setTranslateY(500);
        GTCOutcome.setTranslateY(-150);
        GTCOutcome.setTranslateX(-50);

        //Display answer
        GTCColor[] blind = colorGameLogic.getBlind();
        int i = 0;
        for(Node child :grey.getChildren()) {
            ImageView greyImage = (ImageView) child;
            String imageName = blind[i].getColor() + ".png";
            greyImage.setImage(new Image(imageName));
            i++;
        }
        //Fade out background when game ends
        for(Node child : colorGamePane.getChildren()) {
            child.setOpacity(0.5);
        }
        colorGamePane.getChildren().addAll(GTCOutcome, GTCOptions);
    }

    /**
     * Resets the colour game
     * @param colorGameScene previous color game scene used
     * @param primaryStage primary stage of application
     */
    private void resetClrGame(Scene colorGameScene, Stage primaryStage) {
        colorGameLogic = new ColorGame();
        BorderPane colorGame = new BorderPane();
        StackPane colorGamePane = new StackPane();
        colorGameScene = new Scene(colorGamePane, 1200, 800);
        Button gameReturnButton = new Button("Return");
        gameReturnButton.setOnAction(e -> primaryStage.setScene(selectScene));
        ImageView colorGameTitle = getImageView("colorBack.PNG", 800, 1200);
        formatGameScreen(colorGameScene, colorGamePane, colorGame, colorGameTitle, primaryStage);
        primaryStage.setScene(colorGameScene);
    }

    /**
     * Clears pinCOlors array
     * @param pinColors array of current colors of all pins
     */
    private void clearPinColors(GTCColor[][] pinColors) {
            pinColors = new GTCColor[8][4];
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j <4; j++) {
                    pinColors[i][j] = GTCColor.Empty;
                }
            }
        }

    /**
     * Gets the corresponding image for a hint and returns the associated ImageView
     * @param hint latest hint
     * @return IV of hint
     */
    private ImageView hintToIV(GTCColor[] hint) {
        int full = 0;
        int half = 0;
        int not = 0;
        for(int i = 0; i < hint.length; i++) {
            if(hint[i].toString().equals(GTCColor.Hint_Correct.toString())) {
                full++;
            } else if(hint[i].toString().equals(GTCColor.Hint_WrongSpot.toString())) {
                half++;
            } else if(hint[i].toString().equals(GTCColor.Hint_Wrong.toString())) {
                not++;
            }
        }
        String imageName = "";
        if(full != 0) {
            imageName += String.valueOf(full) + "full";
        }
        if(half != 0) {
            imageName += String.valueOf(half) + "half";
        }
        if(not != 0) {
            imageName += String.valueOf(not) + "not";
        }
        imageName += ".png";
        return getImageView(imageName, 50, 50);
    }

    //Gets users guess
    private GTCColor[] getGuess(Circle[][] pins, GTCColor[][] pinColors) {
        int col = 0;
        boolean found = false;
        for(int i = 0; i < 8; i++) {
            if(pins[i][3].getFill() == Color.GREY) {
                col = i - 1;
                found = true;
                break;
            }
        }
        if(found == false) {
            col = 7;
        }
        GTCColor[] guess = new GTCColor[4];
        for(int i = 0; i < 4; i++) {
            guess[i] = pinColors[col][i];
        }
        return guess;
    }


    // helper for when user click return button, all the pin are clear
    private void clearPin(Circle[][] pins) {
        for (int i = 0; i < pins.length; i++) {
            for (int j = 0; j < pins[i].length; j++) {
                if (pins[i][j].getFill() != Color.GREY) {
                    pins[i][j].setFill(Color.GREY);
                }
            }
        }
    }

    // enter pin loop
    private void enterPin(Image tempImage, Circle[][] pins, GTCColor[][] pinColors, GTCColor tempColor) {
        boolean check = false;
        for (int i = 0; i < pins.length; i++) {
            if (!check) {
                for (int j = 0; j < pins[i].length; j++) {
                    if (pins[i][j].getFill() == Color.GREY) {
                        glow(pins[i][j]);
                        pins[i][j].setFill(new ImagePattern(tempImage));
                        pinColors[i][j] = tempColor;
                        check = true;
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }

    // format of instruction page
    private void formatColorScreen(Scene colorScene, Button colorBackReturn, StackPane colorPane, BorderPane colorBP, ImageView colorTitle, Button enterGame) {
        setButton(colorBackReturn);

        setButton(enterGame);

        HBox buttonHbox = new HBox(1030);
        buttonHbox.getChildren().addAll(colorBackReturn, enterGame);
        buttonHbox.setTranslateX(30);
        buttonHbox.setTranslateY(-30);
        colorBP.setBottom(buttonHbox);

        Text instruction1 = new Text("1. Players should try to correctly guess the color and its corresponding position\n " +
                "   in the blind box in 8 rounds of guessing.");
        setText(instruction1);
        Text instruction2 = new Text("2. At the end of a round of guessing, the result will be displayed.");
        setText(instruction2);
        Text instruction3 = new Text("3. Green means the color and position are correct");
        setText(instruction3);
        Text instruction4 = new Text("4. Red means the color is right but the position is wrong.");
        setText(instruction4);
        Text instruction5 = new Text("5. No color means the color is not in the blind box");
        setText(instruction5);
        Text instruction6 = new Text("Examples are shown below.");
        setText(instruction6);
        VBox vText = new VBox(17);
        vText.getChildren().addAll(instruction1, instruction2, instruction3, instruction4, instruction5, instruction6);
        vText.setTranslateX(180);
        vText.setTranslateY(178);


        VBox example1 = new VBox(1);
        ImageView instructionImage1 = getImageView("2half2not.png", 135, 135);
        Text explain1 = new Text("Red: correct color but wrong position. \n" + "White: wrong color and wrong position.");
        setText(explain1);
        explain1.setStyle("-fx-font-size: 15px");
        example1.getChildren().addAll(explain1, instructionImage1);
        VBox example2 = new VBox(1);
        ImageView instructionImage2 = getImageView("2full2not.png", 135, 135);
        Text explain2 = new Text("Green: correct color and correct position. \n" + "White: wrong color and wrong position.");
        setText(explain2);
        explain2.setStyle("-fx-font-size: 15px");
        example2.getChildren().addAll(explain2, instructionImage2);
        HBox instruction = new HBox(21);
        instruction.getChildren().addAll(example1, example2);
        instruction.setTranslateX(150);
        instruction.setTranslateY(563);


        colorPane.getChildren().addAll(colorTitle, instruction, vText, colorBP);

    }

    /**
     *
     * @param fileName name of image file to be loaded
     * @param height height of image file for display
     * @param width width of image file for display
     * @return ImageView of image passed in with requested height and width
     */
    private ImageView getImageView(String fileName, int height, int width) {
        System.out.println(fileName);
        Image image = new Image(fileName);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        return imageView;
    }

    // set button helper
    private static void setButton(Button bt) {
        String cssStyle = " -fx-text-fill: #006464;\n" +
                "    -fx-background-color: #DFB951;\n" +
                "    -fx-border-radius: 20;\n" +
                "    -fx-background-radius: 20;\n" +
                "    -fx-padding: 5;";
        bt.setStyle(cssStyle);
    }
    private static void setText(Text tx) {
        String cssStyle = " -fx-text-fill: #006464;\n" +
                "        -fx-font-family: Comic Sans MS;\n" +
                "        -fx-font-size: 25px;";
        tx.setStyle(cssStyle);
        Color c = Color.web("#006464");
        tx.setFill(c);
    }
}
