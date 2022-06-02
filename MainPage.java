import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;


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
        StackPane firstMain = new StackPane();
        BorderPane firstBP = new BorderPane();
        Scene scene = new Scene(firstMain, 1300, 900);
        stage.setScene(scene);

        Text tx1 = new Text("Game Name"); // when can decide later
        tx1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));

        Button bt = new Button("Start game");
        VBox vbox = new VBox(400);
        vbox.getChildren().addAll(tx1, bt);
        vbox.setAlignment(Pos.CENTER);
        firstBP.setCenter(vbox);
        firstMain.setAlignment(firstBP, Pos.CENTER);
        firstMain.getChildren().addAll(firstBP);


        // second main page which show 3 games.
        StackPane secondMain = new StackPane();
        BorderPane secondBP = new BorderPane();
        Scene scene2 = new Scene(secondMain, 1300, 900);
        HBox hbox = new HBox(100);

        Rectangle game1 = new Rectangle(250, 250);
        Rectangle game2 = new Rectangle(250, 250);
        Rectangle game3 = new Rectangle(250, 250);
        hbox.getChildren().addAll(game1, game2, game3);
        hbox.setAlignment(Pos.CENTER);
        secondBP.setCenter(hbox);
        secondMain.setAlignment(secondBP, Pos.CENTER);
        secondMain.getChildren().addAll(secondBP);

        bt.setOnAction(e -> stage.setScene(scene2));



        stage.show();
    }
}
