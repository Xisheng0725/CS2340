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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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

        //Create the color game instruction page
        BorderPane colorBP = new BorderPane();
        StackPane colorPane = new StackPane();
        Scene colorScene = new Scene(colorPane, 1200, 800);;
        ImageView colorTitle = getImageView("color.PNG", 800, 1200);
        Button colorBackBtn = new Button("Return");
        colorBackBtn.setOnAction(e -> primaryStage.setScene(selectScene));
        formatColorScreen(colorScene, colorBackBtn, colorPane, colorBP, colorTitle);


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
                        primaryStage.setScene(tempScene);
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
        main.setAlignment(bp, Pos.CENTER);
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
        homeScreen.setAlignment(homeBP, Pos.CENTER);
        homeScreen.getChildren().addAll(homeBP);
    }

    private void formatColorScreen(Scene colorScene, Button colorBackReturn, StackPane colorPane, BorderPane colorBP, ImageView colorTitle) {
        setButton(colorBackReturn);
        colorBackReturn.setTranslateX(30);
        colorBackReturn.setTranslateY(-30);

        colorBP.setBottom(colorBackReturn);

        Text instruction1 = new Text("1. Players should try to correctly guess the color and its corresponding position in the blind box in 8 rounds of guessing.");
        Text instruction2 = new Text("2. At the end of a round of guessing, the result will be displayed.");
        Text instruction3 = new Text("3. Green means the color and position are correct");
        Text instruction4 = new Text("4. Red means the color is right but the position is wrong.");
        Text instruction5 = new Text("5. No color means the color is not in the blind box");
        VBox vText = new VBox(5);
        vText.getChildren().addAll(instruction1, instruction2, instruction3, instruction4, instruction5);
        vText.setTranslateX(200);
        vText.setTranslateY(300);
        colorBP.setCenter(vText);


        colorPane.setAlignment(colorBP, Pos.CENTER);
        colorPane.getChildren().addAll(colorTitle, colorBP);
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



}
