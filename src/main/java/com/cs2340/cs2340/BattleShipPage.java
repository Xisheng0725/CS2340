package com.cs2340.cs2340;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BattleShipPage {

    public BattleShipPage() {
    }
    private Scene scene;
    private Stage primaryStage;
    private MainPage mainPage;


    public void formatGameScreen(Scene bsGameScene, Pane bsGamePane, Stage primaryStage, MainPage mainPage) {
        scene = bsGameScene;
        this.primaryStage = primaryStage;
        this.mainPage = mainPage;

        //Background
        ImageView bsBg = MainPage.getImageView("bg.PNG", 800,1200);
        bsGamePane.getChildren().add(bsBg);

        //GridBoard
        GridPane gp = new GridPane();
        Rectangle[][] board = new Rectangle[8][8];
        gp.setMinSize(1000, 1000);
        gp.setHgap(1);
        gp.setVgap(1);
        int rows = 8;
        int cols = 8;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Rectangle rect = new Rectangle();
                rect.setHeight(75);
                rect.setWidth(75);
                board[i][j] = rect;
                rect.setFill(Color.GREY);
                gp.add(board[i][j], i, j);
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
        returnBtn.setOnAction(e -> {
            primaryStage.setScene(mainPage.getSelectScene());
        });
    }
}
