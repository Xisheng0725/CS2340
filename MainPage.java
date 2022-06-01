import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
/**
*Represents MainPage named MainPage.
*@author Xisheng Zhang, Qihui Wang, Porter Zach, Xueqing Li, and Shane Sinnerine.
*@version 1.0.
*/
public class MainPage extends Application {
    /**
    * Creat the main method.
    * Please add commons or JavaDocs description when you add something
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
        Scene scene = new Scene(sp, 1300, 900);
        stage.setScene(scene);

        stage.show();
    }
}
