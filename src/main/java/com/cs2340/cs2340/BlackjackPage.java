package com.cs2340.cs2340;

import javafx.collections.ObservableList;
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
public class BlackjackPage {
    private static MainPage main= new MainPage();
    public void formatBlackjackScreen(Button enterBlackjackGame, Button blackjackBackReturn, StackPane blackjackPane, BorderPane blackjackBP,
                                      ImageView blackjackTitle, Stage primaryStage, Scene colorGameScene) {



        //Set the style of the return button.
        MainPage.setButton(blackjackBackReturn);
        blackjackBackReturn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(main.getSelectScene());
            }
        });

        //set the style of the start game button.
        MainPage.setButton(enterBlackjackGame);
        enterBlackjackGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(main.getSelectScene());
            }
        });

        HBox buttonHbox = new HBox(1030);
        buttonHbox.getChildren().addAll(blackjackBackReturn, enterBlackjackGame);
        buttonHbox.setTranslateX(30);
        buttonHbox.setTranslateY(-30);
        blackjackBP.setBottom(buttonHbox);

        blackjackPane.getChildren().addAll(blackjackTitle, blackjackBP);

    }
}
