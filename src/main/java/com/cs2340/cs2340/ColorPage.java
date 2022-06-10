package com.cs2340.cs2340;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * The class that will create the menu and interface for the GTC game.
 */
public class ColorPage {

    public static Scene getScene() {
        StackPane colorPane = new StackPane();
        BorderPane colorBP = new BorderPane();
        Scene colorScene;
        ImageView colorTitle = getImageView("color.PNG", 800, 1200);


        colorBP.setBottom(MainPage.getReturnButton());
        MainPage.getReturnButton().setTranslateX(30);
        MainPage.getReturnButton().setTranslateY(-30);


        colorPane.setAlignment(colorBP, Pos.CENTER);
        colorPane.getChildren().addAll(colorTitle, colorBP);
        colorScene = new Scene(colorPane, 1200, 800);

        return colorScene;
    }

    private static ImageView getImageView(String fileName, int height, int width) {
        Image image = new Image(fileName);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        return imageView;
    }

    private static void setButton(Button bt) {
        String cssStyle = " -fx-text-fill: #006464;\n" +
                "    -fx-background-color: #DFB951;\n" +
                "    -fx-border-radius: 20;\n" +
                "    -fx-background-radius: 20;\n" +
                "    -fx-padding: 5;";
        bt.setStyle(cssStyle);
    }

}
