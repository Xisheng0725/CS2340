package com.cs2340.cs2340;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

/**
 *Represents MainPage named MainPage.
 *@author Xisheng Zhang, Qihui Wang, Porter Zach, Xueqing Li, and Shane Sinnerine.
 *@version 1.0.
 */
public class MainPage extends Application {

    private static Scene selectScene;
    private static Button tempBackBtn;
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


        //Create and format homeScreen
        formatHomeScreen(homeStartBtn, homeMain, homeBP, homeTitle, icons);
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
                        primaryStage.setScene(colorGameScene);
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
                "    -fx-padding: 20;";
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
    private void formatHomeScreen(Button btnStart, StackPane homeScreen, BorderPane homeBP, ImageView homeTitle, ImageView icons) {
        //Add elements to home screen scene
        VBox vbox = new VBox(15);
        vbox.getChildren().addAll(homeTitle, btnStart, icons);
        vbox.setAlignment(Pos.CENTER);
        homeBP.setCenter(vbox);
        homeScreen.getChildren().addAll(homeBP);
    }

    private void formatGameScreen(Scene colorGameScene, StackPane colorGamePane, BorderPane colorGame, ImageView colorGameTitle, Stage primaryStage) {
        Button colorBackBtn = new Button("Return");
        setButton(colorBackBtn);
        colorBackBtn.setTranslateX(30);
        colorBackBtn.setTranslateY(-30);
        colorGame.setBottom(colorBackBtn);
        ImageView gamePosition = getImageView("gp.png", 785, 1300);

        VBox imageCheckHead = new VBox();
        ImageView checkHead = getImageView("begin.png", 200, 200);
        imageCheckHead.getChildren().addAll(checkHead);
        imageCheckHead.setTranslateX(850);
        imageCheckHead.setTranslateY(530);

        VBox grey = new VBox(15);
        ImageView grey1 = getImageView("gray.png", 50, 50);
        ImageView grey2 = getImageView("gray.png", 50, 50);
        ImageView grey3 = getImageView("gray.png", 50, 50);
        ImageView grey4 = getImageView("gray.png", 50, 50);
        grey.getChildren().addAll(grey1, grey2, grey3, grey4);
        grey.setTranslateX(950);
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
        int rows = 8;
        int columns = 4;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Circle circle = new Circle(20);
                circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        circle.setFill(Color.GREY);
                    }
                });
                pins[i][j] = circle;
                circle.setFill(Color.GREY);
                eachRound.add(pins[i][j], i, j);
            }
        }
        eachRound.setTranslateX(150);
        eachRound.setTranslateY(180);
        red.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image redImage = new Image("red.png");
                enterPin(redImage, pins);
            }
        });
        orange.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image orangeImage = new Image("orange.png");
                enterPin(orangeImage, pins);
            }
        });
        yellow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image yellowImage = new Image("yellow.png");
                enterPin(yellowImage, pins);
            }
        });
        green.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image greenImage = new Image("green.png");
                enterPin(greenImage, pins);
            }
        });
        blue.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image blueImage = new Image("blue.png");
                enterPin(blueImage, pins);
            }
        });
        purple.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image purpleImage = new Image("purple.png");
                enterPin(purpleImage, pins);
            }
        });
        colorBackBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clearPin(pins);
                primaryStage.setScene(selectScene);
            }
        });
        colorGamePane.getChildren().addAll(colorGameTitle, gamePosition, imageCheckHead, colorGame, eachRound, grey, allColors);
    }
    private void clearPin(Circle[][] pins) {
        for (int i = 0; i < pins.length; i++) {
            for (int j = 0; j < pins[i].length; j++) {
                if (pins[i][j].getFill() != Color.GREY) {
                    pins[i][j].setFill(Color.GREY);
                }
            }
        }
    }
    private void enterPin(Image tempImage, Circle[][] pins) {
        boolean check = false;
        for (int i = 0; i < pins.length; i++) {
            if (!check) {
                for (int j = 0; j < pins[i].length; j++) {
                    if (pins[i][j].getFill() == Color.GREY) {
                        pins[i][j].setFill(new ImagePattern(tempImage));
                        check = true;
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }


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
        Image image = new Image(fileName);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        return imageView;
    }

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
