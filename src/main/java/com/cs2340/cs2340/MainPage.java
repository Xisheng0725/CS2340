package com.cs2340.cs2340;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;

/**
 *Represents MainPage named MainPage.
 *@author Xisheng Zhang, Qihui Wang, Porter Zach, Xueqing Li, and Shane Sinnerine.
 *@version 1.0.
 */
public class MainPage extends Application {
    /**
     * Creat the main method.
     * Please add commons or JavaDocs description when you add something
     * @param args take in
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * Creat the start method that using for main page GUI.
     * @param primaryStage take in
     */
    public void start(Stage primaryStage) {

        //Create all objects to be placed on home screen.
        Button homeStartBtn = new Button("Start game");
        StackPane homeMain = new StackPane();
        BorderPane homeBP = new BorderPane();
        Text homeTitleTxt = new Text("Casino Royale Deluxe");

        //Format all objects on homeScreen and set up primaryStage
        createHomeScene(homeStartBtn, homeMain, homeBP, homeTitleTxt);
        Scene homeScene = new Scene(homeMain, 1300, 900);
        primaryStage.setTitle("Casino Royale Deluxe");
        primaryStage.setScene(homeScene);

        // second main page which show 3 games.
        StackPane secondMain = new StackPane();
        BorderPane secondBP = new BorderPane();
        Scene scene2 = new Scene(secondMain, 1300, 900);

        // return Button
        Button returnTo = new Button("Return to Main Page");

        // Load icons for all three games.
        HBox hbox = new HBox(100);
        ImageView battleshipImageView = getImageView("/battleship.JPG", 200, 250);
        ImageView blackjackImageView = getImageView("/blackjack.JPG", 200, 250);
        ImageView colorImageView = getImageView("/color.JPG", 200, 250);
        hbox.getChildren().addAll(battleshipImageView, blackjackImageView, colorImageView);
        hbox.setAlignment(Pos.CENTER);

        //Format "game selection" scene
        secondBP.setCenter(hbox);
        secondBP.setLeft(returnTo);
        returnTo.setTranslateX(30);
        returnTo.setTranslateY(30);
        secondMain.setAlignment(secondBP, Pos.CENTER);
        secondMain.getChildren().addAll(secondBP);

        //Map what should happen when start and return buttons are clicked
        homeStartBtn.setOnAction(e -> primaryStage.setScene(scene2));
        returnTo.setOnAction(e -> primaryStage.setScene(homeScene));

        //Show primary stage
        primaryStage.show();
    }

    /**
     *
     * @param btnStart Start button
     * @param homeScreen stackPane to create scene with
     * @param homeBP borderpane to create home scene with
     * @param homeTitleTxt game title
     */
    private void createHomeScene(Button btnStart, StackPane homeScreen, BorderPane homeBP, Text homeTitleTxt) {
        //Add elements to home screen scene
        VBox vbox = new VBox(400);
        vbox.getChildren().addAll(homeTitleTxt, btnStart);
        vbox.setAlignment(Pos.CENTER);
        homeBP.setCenter(vbox);
        homeScreen.setAlignment(homeBP, Pos.CENTER);
        homeScreen.getChildren().addAll(homeBP);
        homeTitleTxt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
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
