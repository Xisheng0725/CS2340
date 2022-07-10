package com.cs2340.cs2340;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BattleShipInfoPage {
    private BattleShipPage BSPage = new BattleShipPage();
    private Scene scene;
    private Stage primaryStage;
    private MainPage main;
    private int currentPage;

    public void formatInfoScreen(Scene scene, Stage primaryStage, MainPage main, Scene bsGameScene) {

        this.primaryStage = primaryStage;
        this.main = main;
        currentPage = 1;
        Pane bsInfoPane = new Pane();
        BorderPane battleshipBP = new BorderPane();
        this.scene = scene;
        ImageView infoBg = MainPage.getImageView("bs_info_page1.png", 800, 1200);
        bsInfoPane.getChildren().add(infoBg);

        Button battleshipBackBtn = new Button("Return");
        ColorPage.glow((battleshipBackBtn));
        MainPage.setButton(battleshipBackBtn);
        battleshipBackBtn.setOnAction(e -> primaryStage.setScene(main.getSelectScene()));
        Button enterBattleshipGame = new Button("Start Game");
        ColorPage.glow((enterBattleshipGame));
        MainPage.setButton(enterBattleshipGame);
        enterBattleshipGame.setOnAction(e -> {
            if(currentPage == 1) {
                currentPage = 2;
                infoBg.setImage(new Image("bs_info_page2.png"));
            } else {
                primaryStage.setScene(bsGameScene);
            }
        });

        HBox buttonHbox = new HBox(1030);
        buttonHbox.getChildren().addAll(battleshipBackBtn, enterBattleshipGame);
        buttonHbox.setTranslateX(30);
        buttonHbox.setTranslateY(-30);
        battleshipBP.setBottom(buttonHbox);

        StackPane battleshipPane = new StackPane();
        battleshipPane.getChildren().addAll(bsInfoPane, battleshipBP);
        scene.setRoot(battleshipPane);
    }
}
