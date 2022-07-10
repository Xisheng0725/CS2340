package com.cs2340.cs2340;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
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

import static javafx.application.Platform.exit;

/**
 *Represents MainPage named MainPage.
 *@author Xisheng Zhang, Qihui Wang, Porter Zach, Xueqing Li, and Shane Sinnerine.
 *@version 1.0.
 */
public class MainPage extends Application {

    private static Scene selectScene;
    private static Button tempBackBtn;

    private static ColorPage colorPage = new ColorPage();
    private static BlackjackPage bjPage = new BlackjackPage();
    private static BattleShipPage bsPage = new BattleShipPage();
    private static BlackjackInfoPage bjInfoPage = new BlackjackInfoPage();
    private static BattleShipInfoPage bsInfoPage = new BattleShipInfoPage();

    public Pane bjGamePane = new Pane();
    public Pane bjInfoPane = new Pane();
    public Scene bjGameScene;
    public Scene bjInfoScene;
    public Pane bsGamePane = new Pane();
    public Pane bsInfoPane = new Pane();
    public Scene bsInfoScene;
    public Scene bsGameScene;


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
        Image image = new Image("cursor.png");
        homeScene.setCursor(new ImageCursor(image));

        
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
        selectScene.setCursor(new ImageCursor(image));


        //Create temporary construction scene



        // Create the Color Game game Page
        BorderPane colorGame = new BorderPane();
        StackPane colorGamePane = new StackPane();
        Scene colorGameScene = new Scene(colorGamePane, 1200, 800);
        colorGameScene.setCursor(new ImageCursor(image));
        Button gameReturnButton = new Button("Return");
        gameReturnButton.setOnAction(e -> primaryStage.setScene(selectScene));
        ImageView colorGameTitle = getImageView("colorBack.PNG", 800, 1200);
        colorPage.formatGameScreen(colorGameScene, colorGamePane, colorGame, colorGameTitle, primaryStage);

        //Create the color game instruction page
        BorderPane colorBP = new BorderPane();
        StackPane colorPane = new StackPane();
        Scene colorScene = new Scene(colorPane, 1200, 800);
        colorScene.setCursor(new ImageCursor(image));
        ImageView colorTitle = getImageView("color.PNG", 800, 1200);
        Button colorBackBtn = new Button("Return");
        colorBackBtn.setOnAction(e -> primaryStage.setScene(selectScene));

        Button enterGame = new Button("Start Game");
        colorPage.formatColorScreen(colorScene, gameReturnButton, colorPane, colorBP, colorTitle, enterGame, primaryStage, colorGameScene);

        //Create Battleship game page
        bsGameScene = new Scene(bsGamePane, 1200, 800);
        bsPage.formatGameScreen(bsGameScene, bsGamePane, primaryStage, this);

        //Create Battleship instruction page
        bsInfoScene = new Scene(bsInfoPane, 1200, 800);
        bsInfoPage.formatInfoScreen(bsInfoScene, primaryStage, this, bsGameScene);

        //Create Blackjack game page
        bjGameScene = new Scene(bjGamePane, 1200, 800);
        bjPage.formatGameScreen(bjGameScene, bjGamePane, primaryStage, this);

        //Create the blackjack instruction page
        bjInfoScene = new Scene(bjInfoPane, 1200, 800);
        bjInfoPage.formatInfoScreen(bjInfoScene, primaryStage, this, bjGameScene);

        // setButton Style
        gameButtonStyle(selectReturn, selectBsInfo, selectBjInfo, selectClrInfo, homeStartBtn);

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
                        primaryStage.setScene(bjGameScene);
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
                        primaryStage.setScene(bsGameScene);
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
                        colorPage.resetClrGame(colorGameScene, primaryStage);
                    }
                });
            }
        });
        selectBjInfo.setOnAction(e -> primaryStage.setScene(bjInfoScene));
        selectBsInfo.setOnAction(e -> primaryStage.setScene(bsInfoScene));
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
     */
    private void gameButtonStyle(Button selectReturn, Button selectBsInfo, Button selectBjInfo, Button selectClrInfo, Button homeStartBtn) {
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
        //tempBackBtn.setStyle((cssStyle));
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

        //Add glow effects to selection screen
        colorPage.glow(retBtn);
        colorPage.glow(bsIV);
        colorPage.glow(bsInfo);
        colorPage.glow(bjIV);
        colorPage.glow(bjInfo);
        colorPage.glow(clrIV);
        colorPage.glow(clrInfo);

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

        HBox buttonHBox = new HBox(40);
        buttonHBox.getChildren().addAll(btnStart, exitBtn);

        buttonHBox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(homeTitle, buttonHBox, icons);
        vbox.setAlignment(Pos.CENTER);
        homeBP.setCenter(vbox);
        exitBtn.setStyle("-fx-background-color: #DFB951;\n" +
        "    -fx-border-radius: 30;\n" +
                "    -fx-background-radius: 90;\n" +
                "    -fx-padding: 20;\n" +
                "    -fx-font-size: 20");
        exitBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exit();
            }
        });

        //Add glow effects to home screen
        colorPage.glow(btnStart);
        colorPage.glow(exitBtn);
        homeScreen.getChildren().addAll(homeBP);
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





    // enter pin loop
    public static void enterPin(Image tempImage, Circle[][] pins, GTCColor[][] pinColors, GTCColor tempColor) {
        boolean check = false;
        for (int i = 0; i < pins.length; i++) {
            if (!check) {
                for (int j = 0; j < pins[i].length; j++) {
                    if (pins[i][j].getFill() == Color.GREY) {
                        ColorPage.glow(pins[i][j]);
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


    /**
     *
     * @param fileName name of image file to be loaded
     * @param height height of image file for display
     * @param width width of image file for display
     * @return ImageView of image passed in with requested height and width
     */
    public static ImageView getImageView(String fileName, int height, int width) {
        Image image = new Image(fileName);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        return imageView;
    }

    // set button helper
    public static void setButton(Button bt) {
        String cssStyle = " -fx-text-fill: #006464;\n" +
                "    -fx-background-color: #DFB951;\n" +
                "    -fx-border-radius: 20;\n" +
                "    -fx-background-radius: 20;\n" +
                "    -fx-padding: 5;";
        bt.setStyle(cssStyle);
    }
    public static void setText(Text tx) {
        String cssStyle = " -fx-text-fill: #006464;\n" +
                "        -fx-font-family: Comic Sans MS;\n" +
                "        -fx-font-size: 25px;";
        tx.setStyle(cssStyle);
        Color c = Color.web("#006464");
        tx.setFill(c);
    }

    public Scene getSelectScene(){
        return selectScene;
    }



}
