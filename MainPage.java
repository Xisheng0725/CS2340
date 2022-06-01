import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;


/**
*Represents MainPage named MainPage.
* Note: Please add commons or JavaDocs description when you add something
*@author Xisheng Zhang, Qihui Wang, Porter Zach, Xueqing Li, and Shane Sinnerine.
*@version 1.0.
*/
public class MainPage extends Application {
    /**
    * Creat the main method.
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
        StackPane sp = new StackPane();
        BorderPane bp = new BorderPane();
        Scene scene = new Scene(sp, 1300, 900);
        stage.setScene(scene);

        Text tx1 = new Text("Game Name"); // when can decide later
        tx1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));

        Button bt = new Button("Start game");
        VBox vbox = new VBox(400);
        vbox.getChildren().addAll(tx1, bt);  // I made a VBox to hold the game name and button for start the game.
        vbox.setAlignment(Pos.CENTER);
        bp.setCenter(vbox);
        sp.setAlignment(bp, Pos.CENTER);  // set center
        sp.getChildren().addAll(bp); // the stackPane hod the Boderpane.

        stage.show();
    }
}
