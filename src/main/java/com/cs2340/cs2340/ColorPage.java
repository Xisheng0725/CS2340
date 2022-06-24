package com.cs2340.cs2340;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The class that will create the menu and interface for the GTC game.
 */
public class ColorPage {

    private static ColorGame colorLogic;
    private static int gtcCounter;

    private static ColorGame colorGameLogic = new ColorGame();

    private static MainPage main = new MainPage();
    public static void formatColorScreen(Scene colorScene, Button colorBackReturn, StackPane colorPane, BorderPane colorBP, ImageView colorTitle, Button enterGame) {
        MainPage.setButton(colorBackReturn);

        MainPage.setButton(enterGame);

        HBox buttonHbox = new HBox(1030);
        buttonHbox.getChildren().addAll(colorBackReturn, enterGame);
        buttonHbox.setTranslateX(30);
        buttonHbox.setTranslateY(-30);
        colorBP.setBottom(buttonHbox);

        Text instruction1 = new Text("1. Players should try to correctly guess the color and its corresponding position\n " +
                "   in the blind box in 8 rounds of guessing.");
        main.setText(instruction1);
        Text instruction2 = new Text("2. At the end of a round of guessing, the result will be displayed.");
        main.setText(instruction2);
        Text instruction3 = new Text("3. Green means the color and position are correct");
        main.setText(instruction3);
        Text instruction4 = new Text("4. Red means the color is right but the position is wrong.");
        main.setText(instruction4);
        Text instruction5 = new Text("5. No color means the color is not in the blind box");
        main.setText(instruction5);
        Text instruction6 = new Text("Examples are shown below.");
        main.setText(instruction6);
        VBox vText = new VBox(17);
        vText.getChildren().addAll(instruction1, instruction2, instruction3, instruction4, instruction5, instruction6);
        vText.setTranslateX(180);
        vText.setTranslateY(178);


        VBox example1 = new VBox(1);
        ImageView instructionImage1 =  main.getImageView("2half2not.png", 135, 135);
        Text explain1 = new Text("Red: correct color but wrong position. \n" + "White: wrong color and wrong position.");
        main.setText(explain1);
        explain1.setStyle("-fx-font-size: 15px");
        example1.getChildren().addAll(explain1, instructionImage1);
        VBox example2 = new VBox(1);
        ImageView instructionImage2 =  main.getImageView("2full2not.png", 135, 135);
        Text explain2 = new Text("Green: correct color and correct position. \n" + "White: wrong color and wrong position.");
        main.setText(explain2);
        explain2.setStyle("-fx-font-size: 15px");
        example2.getChildren().addAll(explain2, instructionImage2);
        HBox instruction = new HBox(21);
        instruction.getChildren().addAll(example1, example2);
        instruction.setTranslateX(150);
        instruction.setTranslateY(563);


        colorPane.getChildren().addAll(colorTitle, instruction, vText, colorBP);

    }

    public static void glow(Node color) {
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
                if(colorGameLogic.hasWon() ||colorGameLogic.hasLost()) {
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
    public void colorGameEnd(ColorGame colorGameLogic, ImageView checkHead, StackPane colorGamePane, Stage primaryStage,
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
            GTCOutcome = main.getImageView("SubmitWin.png", 500, 500);
        } else {
            GTCOutcome = main.getImageView("SubmitLoss.png", 500, 500);
        }

        //Setup return button
        Button GTCReturnBtn = new Button("Return");
        GTCReturnBtn.setStyle(cssStyle);
        GTCReturnBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(main.getSelectScene());
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

    public void resetClrGame(Scene colorGameScene, Stage primaryStage) {
        colorGameLogic = new ColorGame();
        BorderPane colorGame = new BorderPane();
        StackPane colorGamePane = new StackPane();
        colorGameScene = new Scene(colorGamePane, 1200, 800);
        Button gameReturnButton = new Button("Return");
        gameReturnButton.setOnAction(e -> primaryStage.setScene(main.getSelectScene()));
        ImageView colorGameTitle = main.getImageView("colorBack.PNG", 800, 1200);
        formatGameScreen(colorGameScene, colorGamePane, colorGame, colorGameTitle, primaryStage);
        primaryStage.setScene(colorGameScene);
    }

    public void formatGameScreen(Scene colorGameScene, StackPane colorGamePane, BorderPane colorGame, ImageView colorGameTitle, Stage primaryStage) {
        gtcCounter = 0;
        Button colorBackBtn = new Button("Return");
        main.setButton(colorBackBtn);
        colorBackBtn.setTranslateX(30);
        colorBackBtn.setTranslateY(-30);
        colorGame.setBottom(colorBackBtn);
        ImageView gamePosition = main.getImageView("gp.png", 785, 1300);

        VBox imageCheckHead = new VBox();
        ImageView checkHead = main.getImageView("submit1.png", 300, 300);
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
                glow.setLevel(0.8);
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
        ImageView title1 = main.getImageView("GTC Title.png", 100, 500);
        title.getChildren().addAll(title1);
        title.setTranslateX(350);
        title.setTranslateY(20);

        VBox grey = new VBox(15);
        ImageView grey1 = main.getImageView("gray.png", 50, 50);
        ImageView grey2 = main.getImageView("gray.png", 50, 50);
        ImageView grey3 = main.getImageView("gray.png", 50, 50);
        ImageView grey4 = main.getImageView("gray.png", 50, 50);
        grey.getChildren().addAll(grey1, grey2, grey3, grey4);
        grey.setTranslateX(1025);
        grey.setTranslateY(175);


        HBox allColors = new HBox(40);
        ImageView red = main.getImageView("red.png", 50, 50);
        Image redImage = new Image("red.png");
        ImageView orange = main.getImageView("orange.png", 50, 50);
        Image orangeImage = new Image("orange.png");
        ImageView yellow = main.getImageView("yellow.png", 50, 50);
        Image yellowImage = new Image("yellow.png");
        ImageView green = main.getImageView("green.png", 50, 50);
        Image greenImage = new Image("green.png");
        ImageView blue = main.getImageView("blue.png", 50, 50);
        Image blueImage = new Image("blue.png");
        ImageView purple = main.getImageView("purple.png", 50, 50);
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
                    }
                });
                pins[i][j] = circle;
                circle.setFill(Color.GREY);
                eachRound.add(pins[i][j], i, j);
            }
            ImageView fourNotIV = main.getImageView("4notoriginal.png", 50 ,50);
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
                main.enterPin(redImage, pins, pinColors, GTCColor.Red);
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
                main.enterPin(orangeImage, pins, pinColors, GTCColor.Orange);
            }
        });
        yellow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(gtcCounter == 4 ||colorGameLogic.hasWon()) {
                    return;
                }
                gtcCounter++;
                Image yellowImage = new Image("yellow.png");
                main.enterPin(yellowImage, pins, pinColors, GTCColor.Yellow);
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
                main.enterPin(greenImage, pins, pinColors, GTCColor.Green);
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
                main.enterPin(blueImage, pins, pinColors, GTCColor.Blue);
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
                main.enterPin(purpleImage, pins, pinColors, GTCColor.Purple);
            }
        });
        colorBackBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                main.clearPin(pins);
                primaryStage.setScene(main.getSelectScene());
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

    /**
     * Gets the corresponding image for a hint and returns the associated ImageView
     * @param hint latest hint
     * @return IV of hint
     */
    public ImageView hintToIV(GTCColor[] hint) {
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
        return main.getImageView(imageName, 50, 50);
    }

    //Gets users guess
    public GTCColor[] getGuess(Circle[][] pins, GTCColor[][] pinColors) {
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
}
