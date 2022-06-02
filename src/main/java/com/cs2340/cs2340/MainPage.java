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
     * @param stage take in
     */
    public void start(Stage stage) {
        stage.setTitle("Game Suit"); // When can change when we find a better name.
        StackPane firstMain = new StackPane();
        BorderPane firstBP = new BorderPane();
        Scene scene = new Scene(firstMain, 1300, 900);
        stage.setScene(scene);

        Text tx1 = new Text("Game Name"); // when can decide later
        tx1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));

        Button bt = new Button("Start game");
        VBox vbox = new VBox(400);
        vbox.getChildren().addAll(tx1, bt);
        vbox.setAlignment(Pos.CENTER);
        firstBP.setCenter(vbox);
        firstMain.setAlignment(firstBP, Pos.CENTER);
        firstMain.getChildren().addAll(firstBP);


        // second main page which show 3 games.
        StackPane secondMain = new StackPane();
        BorderPane secondBP = new BorderPane();
        Scene scene2 = new Scene(secondMain, 1300, 900);
        // return Button
        Button returnTo = new Button("Return to Main Page");

        // Three game place holder.
        HBox hbox = new HBox(100);
        ImageView battleshipImageView = getImageView("/battleship.JPG", 200, 250);
        ImageView blackjackImageView = getImageView("/blackjack.JPG", 200, 250);
        ImageView colorImageView = getImageView("/color.JPG", 200, 250);
        hbox.getChildren().addAll(battleshipImageView, blackjackImageView, colorImageView);
        hbox.setAlignment(Pos.CENTER);

        secondBP.setCenter(hbox);
        secondBP.setLeft(returnTo);
        returnTo.setTranslateX(30);
        returnTo.setTranslateY(30);
        secondMain.setAlignment(secondBP, Pos.CENTER);
        secondMain.getChildren().addAll(secondBP);


        bt.setOnAction(e -> stage.setScene(scene2));

        returnTo.setOnAction(e -> stage.setScene(scene));


        stage.show();
    }

    private ImageView getImageView(String fileName, int height, int width) {
        Image image = new Image(fileName);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        return imageView;
    }
}
