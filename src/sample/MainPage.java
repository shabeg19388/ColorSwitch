
//mainpage
package sample;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;

public class MainPage extends Application {
    //Button button1;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Color Switch");
        /*button1 = new Button();
        button1.setText("TAP TO PLAY");
        button1.setOnAction(this);
        StackPane layout = new StackPane();
        layout.getChildren().add(button1);
        Scene scene1 = new Scene(layout, 400, 400);
        primaryStage.setScene(scene1);
        primaryStage.show();*/
        String cwd = System.getProperty("user.dir");

        //Adding background Image to mainpage
        String homeBgPath = cwd + "/images/CS2.jpg";
        Image image1 = new Image(new FileInputStream(homeBgPath));
        ImageView bg = new ImageView(image1);
        bg.setFitHeight(800);
        bg.setPreserveRatio(true);

        //adding colour switch image to main page
        String csPath =cwd+ "/images/CS4.jpg";
        Image image2=new Image(new FileInputStream(csPath));
        ImageView cs=new ImageView(image2);
        cs.setFitHeight(60);
        cs.setPreserveRatio(true);

        //Mainpage(tap to play) image
        String ttpPath = cwd + "/Images/CS3.jpg";
        Image image3 = new Image(new FileInputStream(ttpPath));
        ImageView ttp = new ImageView(image3);
        ttp.setFitHeight(60);
        ttp.setPreserveRatio(true);
        Button ttpB = new Button();
        ttpB.setPadding(Insets.EMPTY);
        ttpB.setGraphic(ttp);
        ttpB.setLayoutX(120);
        ttpB.setLayoutY(150);
        ttpB.setPrefSize(250,70);

        //Layout
        Pane layout = new Pane();
        layout.getChildren().add(bg);
        layout.getChildren().add(cs);
        layout.getChildren().add(ttpB);


        //Scene
        Scene homepage = new Scene(layout, 470, 700);

        primaryStage.setScene(homepage);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    //button functionality
    /*@Override
    public void handle(ActionEvent event) {
    }*/
    public static void main(String[] args) {
        launch(args);
    }
}
