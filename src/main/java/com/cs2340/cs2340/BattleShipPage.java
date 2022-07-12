package com.cs2340.cs2340;


import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.ImageCursor;

public class BattleShipPage {


    private Scene scene;
    private Stage primaryStage;
    private MainPage mainPage;
    private BattleshipLogic BJLogic;

    public BattleShipPage() {
        BJLogic = new BattleshipLogic();
    }

    public void formatGameScreen(Scene bsGameScene, Pane bsGamePane, Stage primaryStage, MainPage mainPage) {
        BJLogic.addPattern();
        scene = bsGameScene;
        this.primaryStage = primaryStage;
        this.mainPage = mainPage;

        //Set Cursor
        Image image = new Image("cursor.png");
        bsGameScene.setCursor(new ImageCursor(image));

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
                rect.setFill(Color.MIDNIGHTBLUE);
                gp.add(board[i][j], i, j);

                //if hit the ship, grid turns red; else turns white
                rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (BJLogic.isHit(gp.getRowIndex(rect), gp.getColumnIndex(rect)) == true) {
                            rect.setFill(Color.DARKRED);
                        } else {
                            rect.setFill(Color.WHITE);
                        }

                        //for result page checking
                       /* if (BJLogic.winOrLose() == 1) {

                        } else if (BJLogic.winOrLose() == -1) {

                        }*/
                    }
                });
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
        ColorPage.glow((returnBtn));
        returnBtn.setOnAction(e -> {
            primaryStage.setScene(mainPage.getSelectScene());
        });
    }
}
