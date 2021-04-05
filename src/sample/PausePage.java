//PauseOptionPage

package sample;

import java.io.FileInputStream;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.*;
import java.awt.*;
import javafx.scene.transform.*;
import javafx.animation.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PausePage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Color Switch");

        String cwd = System.getProperty("user.dir");

        //adding resume button to PausePage
        Text t1 = new Text("RESUME");
        t1.setFont(Font.font("Rockwell", FontWeight.BOLD, 40));
        t1.setX(140);
        t1.setY(50);
        t1.setFill(Color.WHITE);
        t1.setStrokeWidth(2);
        t1.setStroke(Color.WHITE);
        t1.resize(70,70);
        String resPath =cwd+ "/Images/resume.jpg";
        Image image1=new Image(new FileInputStream(resPath));
        ImageView res=new ImageView(image1);
        res.setX(300);
        res.setY(150);
        res.setFitHeight(60);
        res.setPreserveRatio(true);
        Button resB = new Button();
        resB.setStyle("-fx-background-radius: 50em; "+"-fx-min-width: 50px; "+"-fx-min-height: 50px; "+"-fx-max-width: 50px; "+"-fx-max-height: 50px;");
        resB.setPadding(Insets.EMPTY);
        resB.setGraphic(res);
        resB.setLayoutX(200);
        resB.setLayoutY(100);
        resB.setPrefSize(300,100);

        //adding save button to PausePage
        Text t2= new Text("SAVE");
        t2.setFont(Font.font("Rockwell", FontWeight.BOLD, 40));
        t2.setX(160);
        t2.setY(250);
        t2.setFill(Color.WHITE);
        t2.setStrokeWidth(2);
        t2.setStroke(Color.WHITE);
        t2.resize(70,70);
        String savePath = cwd + "/Images/save.jpg";
        Image image2 = new Image(new FileInputStream(savePath));
        ImageView save = new ImageView(image2);
        save.setX(100);
        save.setY(300);
        save.setFitHeight(60);
        save.setPreserveRatio(true);
        Button saveB = new Button();
        saveB.setStyle("-fx-background-radius: 50em; "+"-fx-min-width: 50px; "+"-fx-min-height: 50px; "+"-fx-max-width: 50px; "+"-fx-max-height: 50px;");
        saveB.setPadding(Insets.EMPTY);
        saveB.setGraphic(save);
        saveB.setLayoutX(200);
        saveB.setLayoutY(300);
        saveB.setPrefSize(300,100);

        //adding EXIt button to PausePage
        Text t3= new Text("HOME");
        t3.setFont(Font.font("Rockwell", FontWeight.BOLD, 40));
        t3.setX(160);
        t3.setY(450);
        t3.setFill(Color.WHITE);
        t3.setStrokeWidth(2);
        t3.setStroke(Color.WHITE);
        t3.resize(70,70);
        String exitPath = cwd + "/Images/home.jpg";
        Image image3 = new Image(new FileInputStream(exitPath));
        ImageView exit = new ImageView(image3);
        exit.setX(100);
        exit.setY(450);
        exit.setFitHeight(60);
        exit.setPreserveRatio(true);
        Button exitB = new Button();
        exitB.setStyle("-fx-background-radius: 50em; "+"-fx-min-width: 50px; "+"-fx-min-height: 50px; "+"-fx-max-width: 50px; "+"-fx-max-height: 50px;");
        exitB.setPadding(Insets.EMPTY);
        exitB.setGraphic(exit);
        exitB.setLayoutX(200);
        exitB.setLayoutY(500);
        exitB.setPrefSize(300,100);

        //Layout
        Pane layout = new Pane();
        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        layout.getChildren().add(t1);
        //layout.getChildren().add(res);
        layout.getChildren().add(resB);

        layout.getChildren().add(t2);
        //layout.getChildren().add(save);
        layout.getChildren().add(saveB);

        layout.getChildren().add(t3);
        //layout.getChildren().add(exit);
        layout.getChildren().add(exitB);

        //Scene
        Scene PausePage= new Scene(layout, 470, 700);
        primaryStage.setScene(PausePage);
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