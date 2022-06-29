package com.cs2340.cs2340;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * The class that will create the menu and interface for the GTC game.
 */
public class BlackjackInfoPage {
    private static MainPage main= new MainPage();

    private BlackjackPage BJPage = new BlackjackPage();
    public void formatBlackjackScreen(Button enterBlackjackGame, Button blackjackBackReturn, StackPane blackjackPane, BorderPane blackjackBP,
                                      ImageView blackjackTitle, Stage primaryStage, Scene colorGameScene) {



        //Set the style of the return button.
        MainPage.setButton(blackjackBackReturn);
        BJPage.makeGlow(blackjackBackReturn);
        blackjackBackReturn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(main.getSelectScene());
            }
        });

        //set the style of the start game button.
        MainPage.setButton(enterBlackjackGame);
        BJPage.makeGlow(enterBlackjackGame);
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
