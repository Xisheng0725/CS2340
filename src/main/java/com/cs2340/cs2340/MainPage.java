package com.cs2340.cs2340;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;

/**
 *Represents MainPage named MainPage.
 *@author Xisheng Zhang, Qihui Wang, Porter Zach, Xueqing Li, and Shane Sinnerine.
 *@version 1.0.
 */
public class MainPage extends Application {
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
        String css = "-fx-background-color:  #3c7fb1, linear-gradient(#fafdfe, #e8f5fc), linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%);" +
                "-fx-background-insets: 0,1,2;" + " -fx-background-radius: 3,2,1;" + "-fx-padding: 3 30 3 30;" + "-fx-text-fill: black;" +  "-fx-font-size: 14px;";
        homeStartBtn.setStyle(css);
        StackPane homeMain = new StackPane();
        homeMain.setStyle("-fx-background-color: azure");
        BorderPane homeBP = new BorderPane();
        ImageView homeTitle = getImageView("/royale.PNG", 300, 800);
        ImageView icons = getImageView("/icons.PNG", 300, 900);


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

        //create and format selection scene
        formatSelectScene(selectMain, selectBP, selectReturn, hbox, selectBsImageView, selectBjImageView, selectClrImageView, selectBsInfo, selectBjInfo, selectClrInfo);
        Scene selectScene = new Scene(selectMain, 1200, 800);

        //Create temporary construction scene
        Text tempTxt = new Text("This page is under construction...");
        Button tempBackBtn = new Button("Return");
        BorderPane tempBP = new BorderPane();
        tempBP.setCenter(tempTxt);
        tempBP.setBottom(tempBackBtn);
        tempBackBtn.setOnAction(e -> primaryStage.setScene(selectScene));
        tempBP.setPadding(new Insets(20, 20, 20, 20));
        Scene tempScene = new Scene(tempBP, 1200, 800);

        // setButton Style
        gameButtonStyle(selectReturn, selectBsInfo, selectBjInfo, selectClrInfo, tempBackBtn);

        //Home Screen event mapping
        homeStartBtn.setOnAction(e -> primaryStage.setScene(selectScene));

        //Selection screen event mapping
        selectReturn.setOnAction(e -> primaryStage.setScene(homeScene));
        selectBjImageView.setOnMouseClicked(e -> primaryStage.setScene(tempScene));
        selectBsImageView.setOnMouseClicked(e -> primaryStage.setScene(tempScene));
        selectClrImageView.setOnMouseClicked(e -> primaryStage.setScene(tempScene));
        selectBjInfo.setOnAction(e -> primaryStage.setScene(tempScene));
        selectBsInfo.setOnAction(e -> primaryStage.setScene(tempScene));
        selectClrInfo.setOnAction(e -> primaryStage.setScene(tempScene));

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
    private void gameButtonStyle(Button selectReturn, Button selectBsInfo, Button selectBjInfo, Button selectClrInfo, Button tempBackBtn) {
        String cssStyle = " -fx-text-fill: #006464;\n" +
                "    -fx-background-color: #DFB951;\n" +
                "    -fx-border-radius: 20;\n" +
                "    -fx-background-radius: 20;\n" +
                "    -fx-padding: 5;";
        selectReturn.setStyle((cssStyle));
        selectBsInfo.setStyle((cssStyle));
        selectBjInfo.setStyle((cssStyle));
        selectClrInfo.setStyle((cssStyle));
        tempBackBtn.setStyle((cssStyle));
    }

    private void formatSelectScene(StackPane main, BorderPane bp, Button retBtn, HBox hbox, ImageView bsIV, ImageView bjIV,
                                   ImageView clrIV, Button bsInfo, Button bjInfo, Button clrInfo) {
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
        bp.setLeft(retBtn);
        retBtn.setTranslateX(30);
        retBtn.setTranslateY(30);
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
        VBox vbox = new VBox(80);
        vbox.getChildren().addAll(homeTitle, btnStart, icons);
        vbox.setAlignment(Pos.CENTER);
        homeBP.setCenter(vbox);
        homeScreen.setAlignment(homeBP, Pos.CENTER);
        homeScreen.getChildren().addAll(homeBP);
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
}
