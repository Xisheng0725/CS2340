package com.cs2340.cs2340;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Creates the menu and interface for the Blackjack game.
 */
public class BlackjackPage {
    private MainPage main;
    public void formatGameScreen(Scene bjGameScene, StackPane bjGamePane, BorderPane bjGame, Stage primaryStage, MainPage mainPage) {
        main = mainPage;

        Button backBtn = new Button("Return");
        MainPage.setButton(backBtn);
        backBtn.setTranslateX(30);
        backBtn.setTranslateY(-30);
        bjGame.setBottom(backBtn);
        backBtn.setOnAction(e -> {
            primaryStage.setScene(main.getSelectScene());
        });

        ImageView gameBg = MainPage.getImageView("bg_total_placeholder.png", 800, 1200);

        bjGamePane.getChildren().addAll(gameBg, backBtn);
    }
}
