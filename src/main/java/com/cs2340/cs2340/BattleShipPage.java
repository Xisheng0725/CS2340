package com.cs2340.cs2340;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BattleShipPage {

    public BattleShipPage() {
    }
    private Scene scene;
    private Stage primaryStage;
    private GridPane gp;
    private MainPage mainPage;


    public void formatGameScreen(Scene bsGameScene, Pane bsGamePane, Stage primaryStage, MainPage mainPage) {
        scene = bsGameScene;
        this.primaryStage = primaryStage;
        this.mainPage = mainPage;

        //Background
        ImageView bsBg = MainPage.getImageView("bg.PNG", 800,1200);
        bsGamePane.getChildren().add(bsBg);

    }
}
