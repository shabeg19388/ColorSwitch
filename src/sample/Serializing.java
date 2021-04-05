package sample;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.*;
import javafx.animation.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.nio.file.Files;
import java.util.Scanner ;
import java.util.*;
import java.io.*;
import java.util.Map;
import java.util.HashMap;
public class Serializing {

    //serialising saving data i.e. game using output stream
    public static void save(Serializable data, String filename) throws Exception{
        try(ObjectOutputStream os= new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))){
            os.writeObject(data);
        }
    }

    //serialising loading data i.e. game using input stream
    public static Object load(String filename) throws Exception{
        try(ObjectInputStream is= new ObjectInputStream(Files.newInputStream(Paths.get(filename)))){
            return is.readObject();
        }

    }


}
