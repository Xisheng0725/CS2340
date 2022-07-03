package com.cs2340.cs2340;

import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
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
    private BlackjackPage BJPage = new BlackjackPage();
    private Scene scene;
    private Stage primaryStage;
    private MainPage main;

    public void formatInfoScreen(Scene scene, Stage primaryStage, MainPage main, Scene bjGameScene) {

        this.primaryStage = primaryStage;
        this.main = main;

        Pane bjInfoPane = new Pane();
        BorderPane blackjackBP = new BorderPane();
        this.scene = scene;
        ImageView infoBg = MainPage.getImageView("bj_info.png", 800, 1200);
        bjInfoPane.getChildren().add(infoBg);

        Button blackjackBackBtn = new Button("Return");
        ColorPage.glow((blackjackBackBtn));
        MainPage.setButton(blackjackBackBtn);
        blackjackBackBtn.setOnAction(e -> primaryStage.setScene(main.getSelectScene()));
        Button enterBlackjackGame = new Button("Start Game");
        ColorPage.glow((enterBlackjackGame));
        MainPage.setButton(enterBlackjackGame);
        enterBlackjackGame.setOnAction(e -> primaryStage.setScene(bjGameScene));

        HBox buttonHbox = new HBox(1030);
        buttonHbox.getChildren().addAll(blackjackBackBtn, enterBlackjackGame);
        buttonHbox.setTranslateX(30);
        buttonHbox.setTranslateY(-30);
        blackjackBP.setBottom(buttonHbox);

        StackPane blackjackPane = new StackPane();
        blackjackPane.getChildren().addAll(bjInfoPane, blackjackBP);
        scene.setRoot(blackjackPane);

    }
}
