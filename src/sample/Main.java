package sample;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
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
import javafx.animation.TranslateTransition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

abstract class Page{

    protected String bgcolor;
    protected HashMap<String, Button> options; //=new HashMap<String, Button>();
    protected int id;
    protected String type;
    protected Button butt;

    public Page(){

    }
    //concrete methods
    public void setbgcolor(String bgcolor){

        this.bgcolor=bgcolor;
    }
    public String getbgcolor(){

        return this.bgcolor;
    }
    public Button getOption(String string){
        return butt;
    }
    public HashMap<String, Button> getAllOptions(){

        return options;
    }
    public void addOption(HashMap<String, Button> options){

        this.options=options;
    }
    public boolean equals(Object o) {

        return this.getClass()==o.getClass();
    }

    //abstract methods
    public abstract void setID(int id);
    public abstract int getID();
    public abstract void setType(String type);
    public abstract String getType();
}

//Page1- HOMEPAGE
class HomePage extends Page{
    private int id ;
    private String type;

    public HomePage(int id, String type){
        this.id =id;
        this.type=type;
    }
    @Override
    public void setID(int id){

        this.id=id;
    }
    @Override
    public int getID(){
        return this.id ;
    }
    @Override
    public void setType(String type){
        this.type=type;
    }
    @Override
    public String getType(){
        return this.type;
    }

}

interface Iterator {
    public boolean hasNext();
    public Object next();
}
interface Container {
    public Iterator getIterator();
}

class iteration implements Container {

    transient public static ArrayList<Shape> shapess= new ArrayList<Shape>();
    //@Override
    public  Iterator getIterator() {
        return new NameIterator();
    }

    private  static class NameIterator  implements Iterator{
        int ind;

        @Override
        public boolean hasNext(){
            if(shapess.size()>ind){
                return true;
            }
            else{
                return false;
            }
            //return false;
        }
        @Override
        public Object next() {

            if(this.hasNext()){
                return shapess.get(ind++);
            }
            return null;
        }
    }
}

class GameplayPage extends Page implements detailsinfo{
    private int id ;
    private String type;
    private Text score;
    private int stars ;
    private Circle ball;
    private ScorePage sp;
    private HomePage hp;
    private boolean isPause;
    private Scene homepage;
    private Scene gameover;
    private Stage home;
    private Scene gamepage;
    private Button pauseB;
    private Text Bscore;
//    private Circle ball;
    //private HashMap<String, Button> map=new HashMap<String, Button>();

    public static String cwd = System.getProperty("user.dir");
    transient public static AnimationTimer at1;
    transient public static ArrayList<Shape> shapes=new ArrayList<Shape>(); //obstacle array list1
    transient public static ArrayList<Group> groups=new ArrayList<Group>(); //REAL obstacle array list
    public static ArrayList<Double> ycoords=new ArrayList<Double>(); //y coord list
    public static ArrayList<ImageView> imagev= new ArrayList<ImageView>(); //array list for star
    transient public Timeline tl1; //= new Timeline(new KeyFrame(Duration.millis(20),
    transient public Timeline timeline;
    transient public Timeline animation;
    transient public Timeline gpanimation1;
    transient public Timeline gpanimation2;
    transient public Timeline timeline1;
    transient public Timeline timeline2;
    transient public Timeline timeline3;
    transient public Timeline timeline4;
    transient public Timeline timeline5;
    transient public Timeline timeline6;
    transient public TranslateTransition translate;
    transient public ScaleTransition st;
    transient public TranslateTransition translateTransition1;
    //transient public static Scene gamepage;

    public Pane gpLayout;
    //public Circle ball;
    //public Text score;


    transient public static Group root=new Group(); //main GROUP

    transient public static Group obst1= new Group(); //obstacle group 1 , USED
    transient public static Group obst2= new Group(); //obstacle group 2 , USED
    transient public static Group obst3 =new Group(); //obstacel group3  , USED
    transient public static Group obst4 =new Group(); //obstacle group4 , USED
    transient public static Group obst5 =new Group(); //obstacle group5
    transient public static Group obst6 = new Group(); //obstacle group 6

    transient public static Group star =new Group(); //obstacle star

    public GameplayPage(int id, String type, int stars, Scene homepage,Scene pausepage,Scene gpage, Scene gameover, Stage home, Text intscore, Text intbestscore, Text totalstars, Button pauseButton) {
        this.id = id;
        this.type = type;
        this.stars = stars;
        this.home = home;
        this.homepage = homepage;
        this.gameover = gameover;
        this.home = home;
        //this.ball=ball;
        this.Bscore = intbestscore;

        score = new Text("0"); //SAVE FOR GAME
        score.resize(30, 30);
        score.setFont(Font.font("Rockwell", FontWeight.MEDIUM, 40));
        score.setFill(Color.WHITE);
        score.setStroke(Color.WHITE);
        score.setX(20);
        score.setY(50);

        String pausePath = cwd + "/images/pause.png";
        Image image5 = null;
        try {
            image5 = new Image(new FileInputStream(pausePath));
        } catch (FileNotFoundException e) {
        }
        ImageView pause = new ImageView(image5);
        pauseB = new Button();
        pauseB = pauseButton;
        pause.setFitHeight(52);
        pause.setPreserveRatio(true);
        Button pauseB = new Button(); //SAVE FOR GAME

        pauseB.setStyle(
                "-fx-background-radius: 50em; " +
                        "-fx-min-width: 50px; " +
                        "-fx-min-height: 50px; " +
                        "-fx-max-width: 50px; " +
                        "-fx-max-height: 50px;"
        );
        pauseB.setLayoutX(400);
        pauseB.setLayoutY(10);
        pauseB.setGraphic(pause);

        //Group root =new Group()
        ball = new Circle(); //SAVE FOR GAME
        ball.setCenterX(236);
        ball.setCenterY(550);
        ball.setRadius(12);
        ball.setFill(Color.DEEPPINK);

        Button input = new Button(); //SAVE FOR GAME
        input.setPrefSize(90, 15);
        input.setLayoutX(190);
        input.setLayoutY(665);
        input.setStyle("-fx-background-color: dimgrey;");
        final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";

        input.setStyle(IDLE_BUTTON_STYLE);
        input.setOnMouseEntered(e -> input.setStyle(HOVERED_BUTTON_STYLE));
        input.setOnMouseExited(e -> input.setStyle(IDLE_BUTTON_STYLE));


        timeline = new Timeline(new KeyFrame(Duration.millis(20),
                new EventHandler<ActionEvent>() {

                    double dx = 5; //Step on x or velocity
                    double dy = 4; //Step on y

                    @Override
                    public void handle(ActionEvent t) {
                        //move the ball
                        //ball.setLayoutX(ball.getLayoutX() + dx);
                        if (!isPause) {
                            ball.setLayoutY(ball.getLayoutY() + 3);
                        }
                        if (ball.getLayoutY() > 200) {
                            ball.setLayoutY(ball.getLayoutY() - 100);
                            isPause = true;
                            home.setScene(gameover);
                        }

                        //Node.MiscProperties canvas;
                        //Bounds bounds = canvas.getBoundsInLocal();
                        //If the ball reaches the left or right border make the step negative

                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        iteration itr = new iteration();
        ArrayList<Shape> shapess = new ArrayList<Shape>();
        for(Iterator iter = itr.getIterator(); iter.hasNext();) {
            if (ball.getLayoutY() < 400) {
                //for (int i = 0; i < shapes.size(); i++) {
                //shapes.get(i).setLayoutY(shapes.get(i).getLayoutY() + 5); //onstacles come down with this or you can do with group a  rray list
                Shape shapesss = (Shape)iter.next() ;
                shapesss.setLayoutY(shapesss.getLayoutY() + 3); //obstacles come down
                //System.out.println(shapes.get(i).getLayoutY());

                //bringing star down
            }
        }

        tl1 = new Timeline(new KeyFrame(Duration.millis(20),
                new EventHandler<ActionEvent>() {

                    double dx = -10; //Step on x or velocity
                    double dy = 3; //Step on y

                    @Override
                    public void handle(ActionEvent t) {
                        //move the ball
                        //ball.setLayoutX(ball.getLayoutX() + dx);
                        ball.setLayoutY(ball.getLayoutY() - 6.8);
                        //bringing obstacle down
                        if (ball.getLayoutY() < 400) {
                            //for (int i = 0; i < shapes.size(); i++) {
                            for (int i = 0; i < shapes.size(); i++) {
                                //shapes.get(i).setLayoutY(shapes.get(i).getLayoutY() + 5); //onstacles come down with this or you can do with group array list
                                shapes.get(i).setLayoutY(shapes.get(i).getLayoutY() + 3); //obstacles come down
                                //System.out.println(shapes.get(i).getLayoutY());
                            }
                            //bringing star down
                            for (int i = 0; i < imagev.size(); i++) {
                                imagev.get(i).setLayoutY(imagev.get(i).getLayoutY() + 3); //star list
                            }
                        }
                        //Node.MiscProperties canvas;
                        //Bounds bounds = canvas.getBoundsInLocal();
                        //If the ball reaches the left or right border make the step negative

                    }
                }));
        tl1.setCycleCount(20);
        KeyValue ballup = new KeyValue(ball.centerYProperty(), +10);
        KeyValue balldown = new KeyValue(ball.centerYProperty(), +10);
        EventHandler<ActionEvent> ev1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                at1 = new AnimationTimer() {
                    @Override
                    public void handle(long l) {

                    }
                    //homepage.setOnKeyPressed(keyEvent -> at1.start());
                    //at1.stop();
                };
                tl1.play();
                timeline.play();

            }
            //at1.stop();
        };
        input.setOnAction(ev1);
        Button finalPauseB = pauseB;
        homepage.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.Q) {
                //System.out.println("yo");
                tl1.play(); //copy paste
                timeline.play(); //cop paste in handle
                pauseB.setOnAction(event -> {
                    timeline.pause();
                });
            }
        });
        gpLayout = new Pane(); //SAVE FOR GAME
        gpLayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        int i = 0;
        int v = 0; //for increasing obstacles coordinates
        for (int j = 0; j < 2; j++) {

            //LEVEL-1
            //copy paste everything in new function
            //y coords list 250.0f+ add y coords.get(0) for deserializing in the new function
            //call new function when deserializing

            //obstacle 1- circle
            addCircularObstacle(gpLayout, 235.0f, 250.0f - v, 150.0f, 5 - i, 1);
            addStar(gpLayout, ball, intscore, totalstars,204.0, 200.0 - v);
            addColourChanger(gpLayout, 233.0f, -10.0f - v, 20.0f, 2);


            //obstacle 2- square
            addSquareObstacle(gpLayout, 110.0f, -500.0f - v, 250.0f, 17, 7 - i);
            addStar(gpLayout, ball, intscore, totalstars,204.0, -425.0 - v);
            addColourChanger(gpLayout, 233.0f, -650.0f - v, 20.0f, 2);


            //LEVEL- 2

            //obstacle 3- single windmill
            addFanObstacle(gpLayout, 180.0f, -950.0f - v, 80.0f, 20, 5 - i, -1);
            addStar(gpLayout, ball, intscore, totalstars,204.0, -1050.0 - v);
            addColourChanger(gpLayout, 233.0f, -1250.0f - v, 20.0f, 2);

            //obstacle 4- square fast
            addSquareObstacle(gpLayout, 110.0f, -1750.0f - v, 225.0f, 17, 7 - i);
            addStar(gpLayout, ball, intscore, totalstars,195.0, -1650.0 - v);
            addColourChanger(gpLayout, 233.0f, -1900.0f - v, 20.0f, 2);
            //LEVEL-3

            //obstacle 5- concentric circles
            addCircularObstacle(gpLayout, 235.0f, -2500.0f - v, 200.0f, 5 - i, 1);
            addCircularObstacle(gpLayout, 235.0f, -2500.0f - v, 150.0f, 5 - i, 1);
            addStar(gpLayout, ball, intscore, totalstars,204.0, -2500.0 - v);
            addColourChanger(gpLayout, 233.0f, -2750.0f - v, 20.0f, 2);

            //obstacle 6- concentric square
            addSquareObstacle(gpLayout, 100.0f, -3400.0f - v, 290.0f, 13, 7 - i);
            addSquareObstacle(gpLayout, 140.0f, -3350.0f - v, 175.0f, 13, 7 - i);
            addStar(gpLayout, ball, intscore, totalstars,195.0, -3300.0 - v);
            addColourChanger(gpLayout, 233.0f, -3600.0f - v, 20.0f, 2);

            //LEVEL- 4

            //obstacle 7- double windmill
            addFanObstacle(gpLayout, 160.0f, -3900.0f - v, 80.0f, 20, 5 - i, -1);
            addFanObstacle(gpLayout, 310.0f, -3900.0f - v, 80.0f, 20, 5 - i, 1);
            addStar(gpLayout, ball, intscore, totalstars,204.0, -4050.0 - v);
            addColourChanger(gpLayout, 233.0f, -4250.0f - v, 20.0f, 2);

            //obstacle 8- intersecting circles beside each other
            addCircularObstacle(gpLayout, 125.0f, -4500.0f - v, 100.0f, 5 - i, +1);
            addCircularObstacle(gpLayout, 340.0f, -4500.0f - v, 100.0f, 5 - i, -1);
            addStar(gpLayout, ball, intscore, totalstars,204.0, -4650.0 - v);
            addColourChanger(gpLayout, 233.0f, -4800.0f - v, 20.0f, 2);


            //LEVEL-5

            //obstacle 9- strip
            addStripObstacle(gpLayout, -5200 - v, 15, 1000);
            addStar(gpLayout, ball, intscore, totalstars, 204.0, -5300.0 - v);
            addStripObstacle(gpLayout, -5400.0 - v, 15, 1000);
            addColourChanger(gpLayout, 233.0f, -5550.0f - v, 20.0f, 2);


            //obstacle 10- concentric circles over another
            addCircularObstacle(gpLayout, 233.0f, -5800.0f - v, 100.0f, 5 - i, +1);
            addStar(gpLayout, ball, intscore, totalstars,204.0, -5800.0 - v);
            addCircularObstacle(gpLayout, 233.0f, -6015.0f - v, 100.0f, 5 - i, 2);
            addStar(gpLayout, ball, intscore, totalstars, 204.0, -6015.0 - v);
            addColourChanger(gpLayout, 233.0f, -6150.0f - v, 20.0f, 2);

            i++;
            v = v + 6800;
            //obstacle 8- circle inside square- NOT POSSIBLE
            //obstacle 9 - square inside rectangle- NOT POSSIBLE

        }
        String buttonmusicPath = cwd + "/sound/buttonclick.wav";
        AudioClip bclip = new AudioClip(Paths.get(buttonmusicPath).toUri().toString());
        pauseB.setOnAction(e -> {
            bclip.play();
            isPause = true;
            home.setScene(pausepage);
        });
        gpLayout.getChildren().addAll(score, ball, pauseB, input);
        gamepage = new Scene(gpLayout, 470, 700);
    }
    @Override
    public void setID(int id){
        this.id=id;
    }
    @Override
    public int getID(){
        return this.id ;
    }
    @Override
    public void setType(String type){
        this.type=type;
    }
    @Override
    public String getType(){
        return this.type;
    }

    public void setScore(Text score){
        this.score=score;
    }

    @Override
    public void setScore(int score) {

    }

    @Override
    public Text getScore(){
        return this.score;
    }
    @Override
    public void setStar(int stars){
        this.stars=stars;
    }
    @Override
    public int getStars(){
        return this.stars ;
    }
    //option1
    public void goToScorePage(ScorePage sp){
        this.sp=sp;
    }

    public Scene getScene(){
        return gamepage;
    }

    public Button getPauseButton(){
        return this.pauseB;
    }

    public void setPause(Boolean b){
        isPause = b;
    }

    public  Circle getBall(){
        return this.ball;
    }
    //option2
    public void goToHomePage(HomePage hp){
        this.hp=hp;
    }
    private void addCircularObstacle(Pane layout, double x, double y, double radius, double time, int direction) {

        //Group obst1= new Group(); //obstacle group 1 , USED
        Arc gparc1 = new Arc(); //SAVE FOR GAME
        gparc1.setCenterX(x);
        gparc1.setCenterY(y);
        gparc1.setRadiusX(radius);
        gparc1.setRadiusY(radius);
        gparc1.setStartAngle(0.0f);
        gparc1.setLength(90.0f);
        gparc1.setType(ArcType.OPEN);
        gparc1.setFill(Color.TRANSPARENT);
        if (direction == 2) {
            gparc1.setStroke(Color.DEEPPINK);
        } else if (direction == 1) {
            gparc1.setStroke(Color.BLUEVIOLET);
        } else {
            gparc1.setStroke(Color.YELLOW);
        }

        //gparc1.setStroke(Color.BLUEVIOLET);
        gparc1.setStrokeWidth(15);
        shapes.add(gparc1);
        obst1.getChildren().add(gparc1);


        Arc gparc2 = new Arc(); //SAVE FOR GAME
        gparc2.setCenterX(x);
        gparc2.setCenterY(y);
        gparc2.setRadiusX(radius);
        gparc2.setRadiusY(radius);
        gparc2.setStartAngle(90.0f);
        gparc2.setLength(90.0f);
        gparc2.setType(ArcType.OPEN);
        gparc2.setFill(Color.TRANSPARENT);
        if (direction == 2) {
            gparc2.setStroke(Color.DEEPSKYBLUE);
        } else if (direction == 1) {
            gparc2.setStroke(Color.YELLOW);
        } else {
            gparc2.setStroke(Color.BLUEVIOLET);
        }

        gparc2.setStrokeWidth(15);
        shapes.add(gparc2);
        obst1.getChildren().add(gparc2);

        Arc gparc3 = new Arc(); //SAVE FOR GAME
        gparc3.setCenterX(x);
        gparc3.setCenterY(y);
        gparc3.setRadiusX(radius);
        gparc3.setRadiusY(radius);
        gparc3.setStartAngle(180.0f);
        gparc3.setLength(90.0f);
        gparc3.setType(ArcType.OPEN);
        gparc3.setFill(Color.TRANSPARENT);
        if (direction == 2) {
            gparc3.setStroke(Color.YELLOW);
        } else if (direction == 1) {
            gparc3.setStroke(Color.DEEPSKYBLUE);
        } else {
            gparc3.setStroke(Color.DEEPPINK);
        }
        //gparc3.setStroke(Color.DEEPSKYBLUE);
        gparc3.setStrokeWidth(15);
        shapes.add(gparc3);
        obst1.getChildren().add(gparc3);

        Arc gparc4 = new Arc(); //SAVE FOR GAME
        gparc4.setCenterX(x);
        gparc4.setCenterY(y);
        gparc4.setRadiusX(radius);
        gparc4.setRadiusY(radius);
        gparc4.setStartAngle(270.0f);
        gparc4.setLength(90.0f);
        gparc4.setType(ArcType.OPEN);
        gparc4.setFill(Color.TRANSPARENT);
        if (direction == 2) {
            gparc4.setStroke(Color.BLUEVIOLET);
        } else if (direction == 1) {
            gparc4.setStroke(Color.DEEPPINK);
        } else {
            gparc4.setStroke(Color.DEEPSKYBLUE);
        }
        //gparc4.setStroke(Color.DEEPPINK);
        gparc4.setStrokeWidth(15);
        shapes.add(gparc4);
        obst1.getChildren().add(gparc4);

        //adding circular obstacle group(obst1 group) to group list
        groups.add(obst1);

        if(direction == 2){
            direction -= 3;
        }

        gpanimation1 = new Timeline(
                new KeyFrame(Duration.seconds(time), new KeyValue(gparc1.startAngleProperty(), gparc1.getStartAngle() + direction * 360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(time), new KeyValue(gparc2.startAngleProperty(), gparc2.getStartAngle() + direction * 360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(time), new KeyValue(gparc3.startAngleProperty(), gparc3.getStartAngle() + direction * 360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(time), new KeyValue(gparc4.startAngleProperty(), gparc4.getStartAngle() + direction * 360, Interpolator.LINEAR))

        );
        gpanimation1.setCycleCount(Animation.INDEFINITE);
        gpanimation1.play();

        Timeline ctimeline = new Timeline(new KeyFrame(Duration.millis(20),
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {

                        if (Shape.intersect(ball, gparc1).getBoundsInParent().getHeight() > 0) {
                            if (ball.getFill() != gparc1.getStroke()) {
                                isPause = true;
                                home.setScene(gameover);
                            }
                        }
                        else if (Shape.intersect(ball, gparc2).getBoundsInParent().getHeight() > 0) {
                            if (ball.getFill() != gparc2.getStroke()) {
                                isPause = true;
                                home.setScene(gameover);
                            }
                        }
                        else if (Shape.intersect(ball, gparc3).getBoundsInParent().getHeight() > 0) {
                            if (ball.getFill() != gparc3.getStroke()) {
                                isPause = true;
                                home.setScene(gameover);
                            }
                        }
                        else if (Shape.intersect(ball, gparc4).getBoundsInParent().getHeight() > 0) {
                            if (ball.getFill() != gparc4.getStroke()) {
                                isPause = true;
                                home.setScene(gameover);
                            }
                        }

//
                    }

                }));
        ctimeline.setCycleCount(Animation.INDEFINITE);
        ctimeline.play();
        layout.getChildren().addAll(gparc1, gparc2, gparc3, gparc4);
    }

    private void addColourChanger(Pane layout, double x, double y, double radius, double d){
        Arc gparc1r = new Arc(); //SAVE FOR GAME
        gparc1r.setCenterX(x);
        gparc1r.setCenterY(y);
        gparc1r.setRadiusX(radius);
        gparc1r.setRadiusY(radius);
        gparc1r.setStartAngle(0.0f);
        gparc1r.setLength(90.0f);
        gparc1r.setType(ArcType.ROUND);
        gparc1r.setFill(Color.BLUEVIOLET);
        shapes.add(gparc1r);
        obst2.getChildren().add(gparc1r);

        Arc gparc2r = new Arc(); //SAVE FOR GAME
        gparc2r.setCenterX(x);
        gparc2r.setCenterY(y);
        gparc2r.setRadiusX(radius);
        gparc2r.setRadiusY(radius);
        gparc2r.setStartAngle(90.0f);
        gparc2r.setLength(90.0f);
        gparc2r.setType(ArcType.ROUND);
        gparc2r.setFill(Color.YELLOW);
        shapes.add(gparc2r);
        obst2.getChildren().add(gparc2r);

        Arc gparc3r = new Arc(); //SAVE FOR GAME
        gparc3r.setCenterX(x);
        gparc3r.setCenterY(y);
        gparc3r.setRadiusX(radius);
        gparc3r.setRadiusY(radius);
        gparc3r.setStartAngle(180.0f);
        gparc3r.setLength(90.0f);
        gparc3r.setType(ArcType.ROUND);
        gparc3r.setFill(Color.DEEPSKYBLUE);
        shapes.add(gparc3r);
        obst2.getChildren().add(gparc3r);

        Arc gparc4r = new Arc(); //SAVE FOR GAME
        gparc4r.setCenterX(x);
        gparc4r.setCenterY(y);
        gparc4r.setRadiusX(radius);
        gparc4r.setRadiusY(radius);
        gparc4r.setStartAngle(270.0f);
        gparc4r.setLength(90.0f);
        gparc4r.setType(ArcType.ROUND);
        gparc4r.setFill(Color.DEEPPINK);
        shapes.add(gparc4r);
        obst2.getChildren().add(gparc4r);

        //adding colour changing obstacel group to obstacle group list
        groups.add(obst2);


        gpanimation2 = new Timeline(
                new KeyFrame(Duration.seconds(d), new KeyValue(gparc1r.startAngleProperty(), gparc1r.getStartAngle() +360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(d), new KeyValue(gparc2r.startAngleProperty(), gparc2r.getStartAngle() +360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(d), new KeyValue(gparc3r.startAngleProperty(), gparc3r.getStartAngle() +360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(d), new KeyValue(gparc4r.startAngleProperty(), gparc4r.getStartAngle() +360, Interpolator.LINEAR))


        );
        gpanimation2.setCycleCount(Animation.INDEFINITE);
        gpanimation2.play();

        Timeline ctimeline2 = new Timeline(new KeyFrame(Duration.millis(20),
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {

                        if(gparc1r.getBoundsInParent().intersects(ball.getBoundsInParent())){
                            ball.setFill(gparc1r.getFill());
                            gparc1r.relocate(600,200);
                            gparc2r.relocate(600,200);
                            gparc3r.relocate(600,200);
                            gparc4r.relocate(600,200);
                        }
                        else if(gparc2r.getBoundsInParent().intersects(ball.getBoundsInParent())){
                            ball.setFill(gparc2r.getFill());
                            gparc1r.relocate(600,200);
                            gparc2r.relocate(600,200);
                            gparc3r.relocate(600,200);
                            gparc4r.relocate(600,200);
                        }
                        else if(gparc3r.getBoundsInParent().intersects(ball.getBoundsInParent())){

                            ball.setFill(gparc3r.getFill());
                            gparc1r.relocate(600,200);
                            gparc2r.relocate(600,200);
                            gparc3r.relocate(600,200);
                            gparc4r.relocate(600,200);
                        }
                        else if(gparc4r.getBoundsInParent().intersects(ball.getBoundsInParent())){

                            ball.setFill(gparc4r.getFill());
                            gparc1r.relocate(600,200);
                            gparc2r.relocate(600,200);
                            gparc3r.relocate(600,200);
                            gparc4r.relocate(600,200);
                        }


                    }

                }));
        ctimeline2.setCycleCount(Animation.INDEFINITE);
        ctimeline2.play();

        layout.getChildren().addAll(gparc1r,gparc2r,gparc3r,gparc4r);
    }

    private void addStar(Pane layout, Circle ball, Text intscore,Text totalstars, double x, double y){
        String starPath = cwd + "/images/star.png";
        String musicpath = cwd + "/sound/starcollect.wav";
        AudioClip ac = new AudioClip(Paths.get(musicpath).toUri().toString());
        Image starI = null;
        try {
            starI = new Image(new FileInputStream(starPath));
        }catch(FileNotFoundException e) {
            System.out.println("File Not found");
        }
        ImageView starImage = new ImageView(starI);
        starImage.setX(x);
        starImage.setY(y);
        starImage.setFitHeight(55);
        starImage.setPreserveRatio(true);

        imagev.add(starImage);

        st = new ScaleTransition(Duration.millis(700), starImage);
        st.setByX(0.2f);
        st.setByY(0.2f);
        st.setCycleCount(Animation.INDEFINITE);
        st.setAutoReverse(true);

        st.play();


        timeline4 = new Timeline(new KeyFrame(Duration.millis(100),
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        if (starImage.getBoundsInParent().intersects(ball.getBoundsInParent())) {
                            ac.play();
                            int uScore = Integer.parseInt(score.getText());
                            score.setText(Integer.toString(++uScore));
                            intscore.setText(score.getText());
                            totalstars.setText(Integer.toString(Integer.parseInt(totalstars.getText())+1));
                            int bScore = Integer.parseInt(Bscore.getText());
                            if(bScore < uScore){
                                Bscore.setText(score.getText());
                            }
                            starImage.relocate(300,1000);
                        }

                    }
                }));
        timeline4.setCycleCount(Animation.INDEFINITE);
        timeline4.play();
        layout.getChildren().add(starImage);
    }
    private void addSquareObstacle(Pane layout, double spX, double spY, double size, double width, int time){
        Line line1 = new Line(spX,spY,spX,spY+size);
        line1.setStrokeWidth(width);
        line1.setStroke(Color.BLUEVIOLET);
        obst3.getChildren().add(line1);
        shapes.add(line1);

        Line line2 = new Line(spX,spY+size,spX+size,spY+size);
        line2.setStrokeWidth(width);
        line2.setStroke(Color.YELLOW);
        obst3.getChildren().add(line2);
        shapes.add(line2);

        Line line3 = new Line(spX+size,spY+size,spX+size,spY);
        line3.setStrokeWidth(width);
        line3.setStroke(Color.DEEPSKYBLUE);
        obst3.getChildren().add(line3);
        shapes.add(line3);

        Line line4 = new Line(spX+size,spY,spX,spY);
        line4.setStrokeWidth(width);
        line4.setStroke(Color.DEEPPINK);
        obst3.getChildren().add(line4);
        shapes.add(line4);

        //adding square obstacle group to group list
        groups.add(obst3);


        Rotate rotate = new Rotate(0, spX+(size/2), spY+(size/2));

        line1.getTransforms().add(rotate);
        line2.getTransforms().add(rotate);
        line3.getTransforms().add(rotate);
        line4.getTransforms().add(rotate);

        timeline5 = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0d)),
                new KeyFrame(Duration.seconds(time), new KeyValue(rotate.angleProperty(), 360d)));

        timeline5.setCycleCount(Animation.INDEFINITE);

        timeline5.play();

        Timeline ctimeline3 = new Timeline(new KeyFrame(Duration.millis(20),
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {

                        if(Shape.intersect(ball,line1).getBoundsInParent().getHeight() > 0){
                            if(ball.getFill() != line1.getStroke()){
                                isPause = true;
                                home.setScene(gameover);
                            }
                        }
                        else if(Shape.intersect(ball,line2).getBoundsInParent().getHeight() > 0){
                            if(ball.getFill() != line2.getStroke()){
                                isPause = true;
                                home.setScene(gameover);
                            }
                        }
                        else if(Shape.intersect(ball,line3).getBoundsInParent().getHeight() > 0){
                            if(ball.getFill() != line3.getStroke()){
                                isPause = true;
                                home.setScene(gameover);
                            }
                        }
                        else if(Shape.intersect(ball,line4).getBoundsInParent().getHeight() > 0){
                            if(ball.getFill() != line4.getStroke()){
                                isPause = true;
                                home.setScene(gameover);
                            }
                        }

//
                    }

                }));
        ctimeline3.setCycleCount(Animation.INDEFINITE);
        ctimeline3.play();

        layout.getChildren().addAll(line1,line2,line3,line4);
    }
    private void addFanObstacle(Pane layout, double spX, double spY, double size, double width, int time, int direction){
        Line line11 = new Line(spX,spY,spX,spY-size);
        line11.setStrokeWidth(width);
        line11.setStroke(Color.BLUEVIOLET);
        obst4.getChildren().add(line11);
        shapes.add(line11);

        Line line22 = new Line(spX,spY,spX-size,spY);
        line22.setStrokeWidth(width);
        if(direction == 1) {
            line22.setStroke(Color.YELLOW);
        }else{
            line22.setStroke(Color.DEEPPINK);
        }
        obst4.getChildren().add(line22);
        shapes.add(line22);

        Line line33 = new Line(spX,spY,spX,spY+size);
        line33.setStrokeWidth(width);
        line33.setStroke(Color.DEEPSKYBLUE);
        obst4.getChildren().add(line33);
        shapes.add(line33);

        Line line44 = new Line(spX,spY,spX+size,spY);
        line44.setStrokeWidth(width);
        if(direction == -1) {
            line44.setStroke(Color.YELLOW);
        }else{
            line44.setStroke(Color.DEEPPINK);
        }
        obst4.getChildren().add(line44);
        shapes.add(line44);


        Rotate rotate = new Rotate(0, spX, spY);

        line11.getTransforms().add(rotate);
        line22.getTransforms().add(rotate);
        line33.getTransforms().add(rotate);
        line44.getTransforms().add(rotate);

        timeline6 = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0d)),
                new KeyFrame(Duration.seconds(time), new KeyValue(rotate.angleProperty(), (direction)*360d)));

        timeline6.setCycleCount(Animation.INDEFINITE);

        timeline6.play();

        Timeline ctimeline4 = new Timeline(new KeyFrame(Duration.millis(20),
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {

                        if(Shape.intersect(ball,line11).getBoundsInParent().getHeight() > 0){
                            if(ball.getFill() != line11.getStroke()){
                                isPause = true;
                                home.setScene(gameover);
                            }
                        }
                        else if(Shape.intersect(ball,line22).getBoundsInParent().getHeight() > 0){
                            if(ball.getFill() != line22.getStroke()){
                                isPause = true;
                                home.setScene(gameover);
                            }
                        }
                        else if(Shape.intersect(ball,line33).getBoundsInParent().getHeight() > 0){
                            if(ball.getFill() != line33.getStroke()){
                                isPause = true;
                                home.setScene(gameover);
                            }
                        }
                        else if(Shape.intersect(ball,line44).getBoundsInParent().getHeight() > 0){
                            if(ball.getFill() != line44.getStroke()){
                                isPause = true;
                                home.setScene(gameover);
                            }
                        }

//
                    }

                }));
        ctimeline4.setCycleCount(Animation.INDEFINITE);
        ctimeline4.play();

        layout.getChildren().addAll(line11,line22,line33,line44);
    }
    private void updateScore(Text score, Circle ball, Rectangle rect) {
        //    Shape intersect = Shape.intersect(ball, rect);
        System.out.println("Entered");
        if (rect.getBoundsInParent().intersects(ball.getBoundsInParent())) {
            int uScore = Integer.parseInt(score.getText());
            System.out.println(uScore);
            score.setText(Integer.toString(++uScore));
        }
    }
    private void addStripObstacle(Pane layout, double Y,int width, int time){

        addStripFragment(layout,(-3)*117.5f,Y,(-2)*117.5f,Y,0.0f ,470.0f, width,Color.YELLOW,time);
        addStripFragment(layout,(-2)*117.5f,Y,(-1)*117.5f,Y,0.0f ,470.0f, width,Color.DEEPSKYBLUE,time);
        addStripFragment(layout,(-1)*117.5f,Y,(0)*117.5f,Y,0.0f ,470.0f, width,Color.DEEPPINK,time);
        addStripFragment(layout,0.0f,Y,0.0f + 1*117.5f,Y,0.0f,470.0f,width,Color.BLUEVIOLET,time);
        addStripFragment(layout,117.5f,Y,0.0f + 2*117.5f,Y,0.0f ,470.0f, width,Color.YELLOW,time);
        addStripFragment(layout,0.0f + 2*117.5f,Y,0.0f + 3*117.5f,Y,0.0f ,470.0f, width,Color.DEEPSKYBLUE,time);
        addStripFragment(layout,0.0f + 3*117.5f,Y,0.0f + 4*117.5f,Y,0.0f ,470.0f, width,Color.DEEPPINK,time);


    }
    private void addStripFragment(Pane layout, double sX, double sY, double eX, double eY,double startPos, double endPos, int width, Color color, int time){
        Line strip1 = new Line(sX,sY,eX,eY);
        strip1.setStrokeWidth(width);
        strip1.setStroke(color);

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(time), strip1);
        translateTransition1.setFromX(startPos);
        translateTransition1.setToX(endPos-117.5f);
        translateTransition1.setCycleCount(Animation.INDEFINITE);
        translateTransition1.setAutoReverse(true);
        translateTransition1.play();

        Timeline ctimeline5 = new Timeline(new KeyFrame(Duration.millis(10),
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {

                        if(Shape.intersect(ball,strip1).getBoundsInParent().getHeight() > 0){
                            if(strip1.getStroke() != ball.getFill()){
                                isPause = true;
                                home.setScene(gameover);
                            }
                        }
                    }

                }));
        ctimeline5.setCycleCount(Animation.INDEFINITE);
        ctimeline5.play();
        shapes.add(strip1);

        layout.getChildren().addAll(strip1);

    }

}

class ResumePage extends Page{
    private int id;
    private String type;
    private int currscore; //current score of current game
    private int bestscore ; //overall best score
    private int totstars; //total stars
    private HomePage hp;
    private GameplayPage gp;
    private Player p;
    private ArrayList<Player> savedGames;

    public ResumePage(int id, String type , int currscore, int bestscore, int totstars ){
        this.id=id;
        this.type=type;
        this.currscore=currscore;
        this.bestscore=bestscore;
        this.totstars=totstars ;
    }
    @Override
    public void setID(int id){
        this.id =id;
    }
    @Override
    public int getID(){
        return this.id ;
    }
    @Override
    public void setType(String type){
        this.type=type;
    }
    @Override
    public String getType(){
        return this.type;
    }
    public Player getSavedGame(String name){
        return p;
    }
    public ArrayList<Player> getAllSavedGames(){
        return savedGames;
    }
    public void addSavedGame(Player p){
        this.p=p;
    }
    public void goToGameplayPage(GameplayPage gp){
        this.gp=gp;
    }
}

//PAGE4- SCOREPAGE
class ScorePage extends Page{
    private int id;
    private String type;
    private int currscore;
    private int bestscore;
    private int totstars;
    private HomePage hp;
    private GameplayPage gp;

    public ScorePage(int id, String type, int currscore, int bestscore, int totstars){
        this.id=id;
        this.type=type;
        this.currscore=currscore;
        this.bestscore=bestscore;
        this.totstars=totstars ;
    }
    @Override
    public void setID(int id ){
        this.id =id;
    }
    @Override
    public int getID(){
        return this.id;
    }
    @Override
    public void setType(String type){
        this.type=type;
    }
    @Override
    public String getType(){
        return this.type;
    }
    public void setCurrScore(int currscore){
        this.currscore=currscore;
    }
    public int getCurrScore(){
        return this.currscore;
    }
    public void setBestScore(int bestscore){
        this.bestscore=bestscore;
    }
    public int getBestScore(){
        return this.bestscore;
    }
    public void setTotalStars(int totstars){
        this.totstars=totstars;
    }
    public int getTotalStars(){
        return this.totstars ;
    }
    public void goToHomePage(HomePage hp){
        this.hp=hp;
    }
    public void RestartGame(GameplayPage gp){
        this.gp=gp;
    }
}
interface Clickable{
    public void click();

}

interface detailsinfo{
    public void setScore(int score);
    public Text getScore();
    public void setStar(int stars);
    public int getStars();

}

class button implements Clickable{
    private String color;
    private String shape ;
    private String text;

    public button(String color, String shape, String text ){
        this.color=color;
        this.shape=shape;
        this.text=text ;
    }
    public void setColor(String color){
        this.color=color;
    }
    public String getColor(){
        return this.color;
    }
    public void setShape(String shape){
        this.shape=shape;
    }
    public String getShape(){
        return this.shape;
    }
    public void setText(String text){
        this.text=text;
    }
    public String getText(){
        return this.text ;
    }
    @Override
    public void click(){

    }
}

class Player implements java.io.Serializable{
    private String name ;
    private int score;
    private int stars ;

    public Player(String name, int score, int stars){
        this.name=name ;
        this.score=score;
        this.stars=stars;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name ;
    }
    public void setScore(int score){
        this.score=score;
    }
    public int getScore(){
        return this.score ;
    }
    public void setStars(int stars){
        this.stars=stars;
    }
    public int getStars(){
        return this.stars ;
    }
}

class Obstacle implements java.io.Serializable{
    private int diff;
    private String shape;

    public Obstacle(int diff, String shape){
        this.diff=diff;
        this.shape=shape;
    }
    public void setDiff(int diff){
        this.diff=diff;
    }
    public int getDiff(){
        return this.diff;
    }
    public void setShape(String shape){
        this.shape=shape;
    }
    public String getShape(){
        return this.shape;
    }
}

class Ball implements java.io.Serializable{
    private String color;

    public Ball(String color){
        this.color=color;
    }

    public void setColor(String color){
        this.color=color;
    }
    public String getColor(){
        return this.color;
    }
    public void jump(){

    }
}

class genlist <T> {
    private ArrayList <T> mylist;
    public genlist() {
        mylist = new ArrayList<T>();
    }
    public void add(T o) {
        mylist.add(o);
    }
    public T get(int i) {
        return mylist.get(i);
    }
    public int size(){
        return mylist.size();
    }
    public void remove(int i){
        mylist.remove(i);
    }

}

//generic hashmap
class genmap <T, K> {
    private HashMap <T, K> mymap;

    public genmap() {
        mymap = new HashMap<T, K>();
    }
    public boolean isEmpty(){
        return mymap.isEmpty();
    }
    public void put(T key, K value){
        mymap.put(key, value);
    }
    public K get(T i){
        return mymap.get(i);
    }
    public void remove(T i){
        mymap.remove(i);
    }
    public void clear(){
        mymap.clear();
    }
    public void putAll(HashMap<T, K> mymap ){
        mymap.putAll(mymap);
    }
    public boolean containsKey(T o){
        return mymap.containsKey(o);
    }
    public boolean containsValue(K o){
        return mymap.containsValue(o);
    }
}

class Save_Game implements java.io.Serializable {
    //GAMEPLAY PAGE button and texts saving
    private static final long serialVersionUID = 1L;
    private int cs; //current score
    private Button pb; //pause button
    private Arc a1; //arc 1
    private Arc a2; //arc2
    private Arc a3; //arc3
    private Arc a4; //arc4
    private Arc a5; //arc5
    private Arc a6; //arc6
    private Arc a7; //arc7
    private Arc a8; //arc8
    private Circle c; //Ball
    private Rectangle r; //star inside arc
    //yet to add obstacles here

}
class serialize {
    //serialising saving data i.e. game using output stream
    public static void save(Serializable data, String filename) throws Exception {
        try (ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
            os.writeObject(data);
        }
    }
    //serialising loading data i.e. game using input stream
    public static Object load(String filename) throws Exception {
        try (ObjectInputStream is = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
            return is.readObject();
        }
    }
}

public class Main extends Application{

    public static String cwd = System.getProperty("user.dir");
    public static AnimationTimer at1;
    public static ArrayList<Shape> shapes=new ArrayList<Shape>(); //obstacle array list1
    public static ArrayList<Group> groups=new ArrayList<Group>(); //REAL obstacle array list
    public static ArrayList<ImageView> imagev= new ArrayList<ImageView>(); //array list for star
    public TranslateTransition translate;
    public Timeline animation;

    public static Group root=new Group(); //main GROUP

    public static Group obst1= new Group(); //obstacle group 1 , USED
    public static Group obst2= new Group(); //obstacle group 2 , USED
    public static Group obst3 =new Group(); //obstacel group3  , USED
    public static Group obst4 =new Group(); //obstacle group4 , USED
    public static Group obst5 =new Group(); //obstacle group5
    public static Group obst6 = new Group(); //obstacle group 6

    public static Group star =new Group(); //obstacle star
    GameplayPage gameplayPage;
    Stage home;
    boolean isPause;
    Scene gameoverCopy;
    Scene gamepage;
    Scene homepageCopy;
    Text intscore = new Text("0");

    Circle ball;
    Text score;
    Button pauseB = new Button();

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Color Switch");
        home=primaryStage;

        String cwd = System.getProperty("user.dir");


        String musicpath = cwd + "/sound/colorswitch.mp3";
        String buttonmusicPath = cwd + "/sound/buttonclick.wav";
        AudioClip ac = new AudioClip(Paths.get(musicpath).toUri().toString());
        ac.setCycleCount(AudioClip.INDEFINITE);
        ac.play();

        AudioClip bclip = new AudioClip(Paths.get(buttonmusicPath).toUri().toString());
        //Adding background Image
        String homeBgPath = cwd + "/images/Bg.jpg";
        Image image1 = new Image(new FileInputStream(homeBgPath));
        ImageView bg = new ImageView(image1);
        bg.setFitHeight(705);
        bg.setPreserveRatio(true);

        translate = new TranslateTransition();
        translate.setByX(400);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(500);
        translate.setAutoReverse(true);

        /*String path = cwd+ "/images/scam.wav";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        //by setting this property to true, the audio will be played
        //mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();*/

        //HomePage Buttons
        String ngPath = cwd + "/images/newgame.jpeg";
        Image image2 = new Image(new FileInputStream(ngPath));
        ImageView ng = new ImageView(image2);
        ng.setFitHeight(60);
        ng.setPreserveRatio(true);
        Button newGameB = new Button();
        newGameB.setPadding(Insets.EMPTY);
        newGameB.setGraphic(ng);
        newGameB.setLayoutX(120);
        newGameB.setLayoutY(250);
        newGameB.setPrefSize(250,70);

        String lgPath = cwd + "/images/loadgame.jpeg";
        Image image3 = new Image(new FileInputStream(lgPath));
        ImageView lg = new ImageView(image3);
        lg.setFitHeight(60);
        lg.setPreserveRatio(true);
        Button loadGameB = new Button();
        loadGameB.setPadding(Insets.EMPTY);
        loadGameB.setGraphic(lg);
        loadGameB.setLayoutX(120);
        loadGameB.setLayoutY(400);
        loadGameB.setPrefSize(252,73);

        String exitPath = cwd + "/images/exit.jpeg";
        Image image4 = new Image(new FileInputStream(exitPath));
        ImageView exit = new ImageView(image4);
        exit.setFitHeight(60);
        exit.setPreserveRatio(true);
        Button exitB = new Button();
        exitB.setPadding(Insets.EMPTY);
        exitB.setGraphic(exit);
        exitB.setLayoutX(122);
        exitB.setLayoutY(550);
        exitB.setPrefSize(252,73);

        //rotating circle
        Arc arc1 = new Arc();
        arc1.setCenterX(170.0f);
        arc1.setCenterY(53.0f);
        arc1.setRadiusX(24.0f);
        arc1.setRadiusY(24.0f);
        arc1.setStartAngle(0.0f);
        arc1.setLength(90.0f);
        arc1.setType(ArcType.OPEN);
        arc1.setFill(Color.TRANSPARENT);
        arc1.setStroke(Color.BLUEVIOLET);
        arc1.setStrokeWidth(12);
        //shapes.add(arc1);

        Arc arc2 = new Arc();
        arc2.setCenterX(170.0f);
        arc2.setCenterY(53.0f);
        arc2.setRadiusX(24.0f);
        arc2.setRadiusY(24.0f);
        arc2.setStartAngle(90.0f);
        arc2.setLength(90.0f);
        arc2.setType(ArcType.OPEN);
        arc2.setFill(Color.TRANSPARENT);
        arc2.setStroke(Color.YELLOW);
        arc2.setStrokeWidth(12);
        //shapes.add(arc2);

        Arc arc3 = new Arc();
        arc3.setCenterX(170.0f);
        arc3.setCenterY(53.0f);
        arc3.setRadiusX(24.0f);
        arc3.setRadiusY(24.0f);
        arc3.setStartAngle(180.0f);
        arc3.setLength(90.0f);
        arc3.setType(ArcType.OPEN);
        arc3.setFill(Color.TRANSPARENT);
        arc3.setStroke(Color.DEEPSKYBLUE);
        arc3.setStrokeWidth(12);
        //shapes.add(arc3);

        Arc arc4 = new Arc();
        arc4.setCenterX(170.0f);
        arc4.setCenterY(53.0f);
        arc4.setRadiusX(24.0f);
        arc4.setRadiusY(24.0f);
        arc4.setStartAngle(270.0f);
        arc4.setLength(90.0f);
        arc4.setType(ArcType.OPEN);
        arc4.setFill(Color.TRANSPARENT);
        arc4.setStroke(Color.DEEPPINK);
        arc4.setStrokeWidth(12);

        ////////////////////////////////////////
        Arc arc5 = new Arc();
        arc5.setCenterX(290.0f);
        arc5.setCenterY(53.0f);
        arc5.setRadiusX(24.0f);
        arc5.setRadiusY(24.0f);
        arc5.setStartAngle(0.0f);
        arc5.setLength(90.0f);
        arc5.setType(ArcType.OPEN);
        arc5.setFill(Color.TRANSPARENT);
        arc5.setStroke(Color.BLUEVIOLET);
        arc5.setStrokeWidth(12);

        Arc arc6 = new Arc();
        arc6.setCenterX(290.0f);
        arc6.setCenterY(53.0f);
        arc6.setRadiusX(24.0f);
        arc6.setRadiusY(24.0f);
        arc6.setStartAngle(90.0f);
        arc6.setLength(90.0f);
        arc6.setType(ArcType.OPEN);
        arc6.setFill(Color.TRANSPARENT);
        arc6.setStroke(Color.YELLOW);
        arc6.setStrokeWidth(12);

        Arc arc7 = new Arc();
        arc7.setCenterX(290.0f);
        arc7.setCenterY(53.0f);
        arc7.setRadiusX(24.0f);
        arc7.setRadiusY(24.0f);
        arc7.setStartAngle(180.0f);
        arc7.setLength(90.0f);
        arc7.setType(ArcType.OPEN);
        arc7.setFill(Color.TRANSPARENT);
        arc7.setStroke(Color.DEEPSKYBLUE);
        arc7.setStrokeWidth(12);

        Arc arc8 = new Arc();
        arc8.setCenterX(290.0f);
        arc8.setCenterY(53.0f);
        arc8.setRadiusX(24.0f);
        arc8.setRadiusY(24.0f);
        arc8.setStartAngle(270.0f);
        arc8.setLength(90.0f);
        arc8.setType(ArcType.OPEN);
        arc8.setFill(Color.TRANSPARENT);
        arc8.setStroke(Color.DEEPPINK);
        arc8.setStrokeWidth(12);

        animation = new Timeline(
                new KeyFrame(Duration.seconds(1.7), new KeyValue(arc1.startAngleProperty(), arc1.getStartAngle()+360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1.7), new KeyValue(arc2.startAngleProperty(), arc2.getStartAngle() +360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1.7), new KeyValue(arc3.startAngleProperty(), arc3.getStartAngle() + 360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1.7), new KeyValue(arc4.startAngleProperty(), arc4.getStartAngle() +360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1.7), new KeyValue(arc5.startAngleProperty(), arc5.getStartAngle() -360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1.7), new KeyValue(arc6.startAngleProperty(), arc6.getStartAngle() -360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1.7), new KeyValue(arc7.startAngleProperty(), arc7.getStartAngle() -360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1.7), new KeyValue(arc8.startAngleProperty(), arc8.getStartAngle() -360, Interpolator.LINEAR))
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();


        Hyperlink hp = new Hyperlink("colorswitch.co");
        hp.setLayoutX(360);
        hp.setLayoutY(670);
        hp.setPrefHeight(20);
        hp.setTextFill(Color.BLACK);


        hp.setOnAction(e -> {bclip.play();getHostServices().showDocument("https://colorswitch.co");});

        Pane layout = new Pane();

        layout.getChildren().add(bg);
        layout.getChildren().add(newGameB);
        layout.getChildren().add(loadGameB);
        layout.getChildren().add(exitB);
        layout.getChildren().addAll(arc1,arc2,arc3,arc4,arc5,arc6,arc7,arc8,hp);

        Scene homepage = new Scene(layout, 470, 700);
        homepageCopy = homepage;
        primaryStage.setScene(homepage);

        //GameoverPage
        String goPath = cwd + "/images/gameover.jpeg";
        Image goImage = new Image(new FileInputStream(goPath));
        ImageView go = new ImageView(goImage);
        go.setFitHeight(705);
        go.setPreserveRatio(true);

        Button goContinue = new Button();
        goContinue.setPrefSize(350,50);
        goContinue.setText("CONTINUE USING STARS");
        goContinue.setTextFill(Color.DEEPPINK);
        goContinue.setLayoutX(60);
        goContinue.setLayoutY(400);

        Button goExit = new Button();
        goExit.setPrefSize(350,50);
        goExit.setText("EXIT");
        goExit.setTextFill(Color.DEEPPINK);
        goExit.setLayoutX(60);
        goExit.setLayoutY(500);

        Pane golayout = new Pane();
        golayout.getChildren().add(go);
        golayout.getChildren().addAll(goContinue,goExit);
        Scene gameoverPage = new Scene(golayout,470,700);
        gameoverCopy = gameoverPage;

        //Score Page
        String scoreBgPath = cwd + "/images/scorepage.jpeg";
        Image image6 = new Image(new FileInputStream(scoreBgPath));
        ImageView scorebg = new ImageView(image6);
        scorebg.setFitHeight(700);
        scorebg.setPreserveRatio(true);

        Text intscore = new Text("0");
        intscore.resize(30,30);
        intscore.setFont(Font.font("Rockwell", FontWeight.MEDIUM, 50));
        intscore.setFill(Color.WHITE);
        intscore.setStroke(Color.WHITE);
        intscore.setX(210);
        intscore.setY(314);

        Text score1 = new Text("SCORE");
        score1.resize(30,30);
        score1.setFont(Font.font("Rockwell", FontWeight.MEDIUM, 40));
        score1.setFill(Color.WHITE);
        score1.setStroke(Color.WHITE);
        score1.setX(155);
        score1.setY(254);

        Text bestscore = new Text("BEST SCORE");
        bestscore.resize(30,30);
        bestscore.setFont(Font.font("Rockwell", FontWeight.MEDIUM, 40));
        bestscore.setFill(Color.WHITE);
        bestscore.setStroke(Color.WHITE);
        bestscore.setX(100);
        bestscore.setY(395);

        Text intbestscore = new Text("0");
        intbestscore.resize(30,30);
        intbestscore.setFont(Font.font("Rockwell", FontWeight.MEDIUM, 50));
        intbestscore.setFill(Color.WHITE);
        intbestscore.setStroke(Color.WHITE);
        intbestscore.setX(210);
        intbestscore.setY(455);

        Text totalstars = new Text("0");
        totalstars.resize(30,30);
        totalstars.setFont(Font.font("Rockwell", FontWeight.MEDIUM, 40));
        totalstars.setFill(Color.WHITE);
        totalstars.setStroke(Color.WHITE);
        totalstars.setX(54);
        totalstars.setY(590);

        //rotating circle
        Arc arcs1 = new Arc();
        arcs1.setCenterX(185.0f);
        arcs1.setCenterY(86.0f);
        arcs1.setRadiusX(18.0f);
        arcs1.setRadiusY(18.0f);
        arcs1.setStartAngle(0.0f);
        arcs1.setLength(90.0f);
        arcs1.setType(ArcType.OPEN);
        arcs1.setFill(Color.TRANSPARENT);
        arcs1.setStroke(Color.BLUEVIOLET);
        arcs1.setStrokeWidth(10);
        //shapes.add(arcs1);

        Arc arcs2 = new Arc();
        arcs2.setCenterX(185.0f);
        arcs2.setCenterY(86.0f);
        arcs2.setRadiusX(18.0f);
        arcs2.setRadiusY(18.0f);
        arcs2.setStartAngle(90.0f);
        arcs2.setLength(90.0f);
        arcs2.setType(ArcType.OPEN);
        arcs2.setFill(Color.TRANSPARENT);
        arcs2.setStroke(Color.YELLOW);
        arcs2.setStrokeWidth(10);
        //shapes.add(arcs2);

        Arc arcs3 = new Arc();
        arcs3.setCenterX(185.0f);
        arcs3.setCenterY(86.0f);
        arcs3.setRadiusX(18.0f);
        arcs3.setRadiusY(18.0f);
        arcs3.setStartAngle(180.0f);
        arcs3.setLength(90.0f);
        arcs3.setType(ArcType.OPEN);
        arcs3.setFill(Color.TRANSPARENT);
        arcs3.setStroke(Color.DEEPSKYBLUE);
        arcs3.setStrokeWidth(10);
        //shapes.add(arcs3);

        Arc arcs4 = new Arc();
        arcs4.setCenterX(185.0f);
        arcs4.setCenterY(86.0f);
        arcs4.setRadiusX(18.0f);
        arcs4.setRadiusY(18.0f);
        arcs4.setStartAngle(270.0f);
        arcs4.setLength(90.0f);
        arcs4.setType(ArcType.OPEN);
        arcs4.setFill(Color.TRANSPARENT);
        arcs4.setStroke(Color.DEEPPINK);
        arcs4.setStrokeWidth(10);
        //shapes.add(arcs4);

        Arc arcs5 = new Arc();
        arcs5.setCenterX(275.0f);
        arcs5.setCenterY(86.0f);
        arcs5.setRadiusX(18.0f);
        arcs5.setRadiusY(18.0f);
        arcs5.setStartAngle(0.0f);
        arcs5.setLength(90.0f);
        arcs5.setType(ArcType.OPEN);
        arcs5.setFill(Color.TRANSPARENT);
        arcs5.setStroke(Color.BLUEVIOLET);
        arcs5.setStrokeWidth(10);
        //shapes.add(arcs5);

        Arc arcs6 = new Arc();
        arcs6.setCenterX(275.0f);
        arcs6.setCenterY(86.0f);
        arcs6.setRadiusX(18.0f);
        arcs6.setRadiusY(18.0f);
        arcs6.setStartAngle(90.0f);
        arcs6.setLength(90.0f);
        arcs6.setType(ArcType.OPEN);
        arcs6.setFill(Color.TRANSPARENT);
        arcs6.setStroke(Color.YELLOW);
        arcs6.setStrokeWidth(10);
        //shapes.add(arcs6);

        Arc arcs7 = new Arc();
        arcs7.setCenterX(275.0f);
        arcs7.setCenterY(86.0f);
        arcs7.setRadiusX(18.0f);
        arcs7.setRadiusY(18.0f);
        arcs7.setStartAngle(180.0f);
        arcs7.setLength(90.0f);
        arcs7.setType(ArcType.OPEN);
        arcs7.setFill(Color.TRANSPARENT);
        arcs7.setStroke(Color.DEEPSKYBLUE);
        arcs7.setStrokeWidth(10);
        //shapes.add(arcs7);

        Arc arcs8 = new Arc();
        arcs8.setCenterX(275.0);
        arcs8.setCenterY(86.0f);
        arcs8.setRadiusX(18.0f);
        arcs8.setRadiusY(18.0f);
        arcs8.setStartAngle(270.0f);
        arcs8.setLength(90.0f);
        arcs8.setType(ArcType.OPEN);
        arcs8.setFill(Color.TRANSPARENT);
        arcs8.setStroke(Color.DEEPPINK);
        arcs8.setStrokeWidth(10);
        //shapes.add(arcs8);

        Timeline spanimation = new Timeline(
                new KeyFrame(Duration.seconds(1.7), new KeyValue(arcs1.startAngleProperty(), arcs1.getStartAngle()+360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1.7), new KeyValue(arcs2.startAngleProperty(), arcs2.getStartAngle() +360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1.7), new KeyValue(arcs3.startAngleProperty(), arcs3.getStartAngle() + 360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1.7), new KeyValue(arcs4.startAngleProperty(), arcs4.getStartAngle() +360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1.7), new KeyValue(arcs5.startAngleProperty(), arcs5.getStartAngle() -360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1.7), new KeyValue(arcs6.startAngleProperty(), arcs6.getStartAngle() -360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1.7), new KeyValue(arcs7.startAngleProperty(), arcs7.getStartAngle() -360, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1.7), new KeyValue(arcs8.startAngleProperty(), arcs8.getStartAngle() -360, Interpolator.LINEAR))
        );
        spanimation.setCycleCount(Animation.INDEFINITE);
        spanimation.play();

        String backPath = cwd + "/images/back.png";
        Image image7 = new Image(new FileInputStream(backPath));
        ImageView spbackI = new ImageView(image7);
        spbackI.setFitHeight(70);
        spbackI.setPreserveRatio(true);
        Button spback = new Button();
        spback.setStyle(
                "-fx-background-radius: 70em; " +
                        "-fx-min-width: 70px; " +
                        "-fx-min-height: 70px; " +
                        "-fx-max-width: 70px; " +
                        "-fx-max-height: 70px;"
        );
        spback.setLayoutX(25);
        spback.setLayoutY(20);
        spback.setGraphic(spbackI);

        String restartPath = cwd + "/images/restart.png";
        Image image8 = new Image(new FileInputStream(restartPath));
        ImageView sprestartI = new ImageView(image8);
        sprestartI.setFitHeight(110);
        sprestartI.setPreserveRatio(true);
        Button sprestart = new Button();
        sprestart.setStyle(
                "-fx-background-radius: 100em; " +
                        "-fx-min-width: 100px; " +
                        "-fx-min-height: 100px; " +
                        "-fx-max-width: 100px; " +
                        "-fx-max-height: 100px;"
        );
        sprestart.setLayoutX(175);
        sprestart.setLayoutY(500);
        sprestart.setGraphic(sprestartI);


        Button fbB = new Button();
        fbB.setLayoutX(360);
        fbB.setLayoutY(475);
        fbB.setPrefSize(70,70);
        String fbPath = cwd + "/images/fb.png";
        Image imagefb = new Image(new FileInputStream(fbPath));
        ImageView fbI = new ImageView(imagefb);
        fbI.setFitHeight(60);
        fbI.setPreserveRatio(true);
        fbB.setGraphic(fbI);

        Button instaB = new Button();
        instaB.setLayoutX(360);
        instaB.setLayoutY(570);
        instaB.setPrefSize(70,70);
        String instaPath = cwd + "/images/insta.png";
        Image imageinsta = new Image(new FileInputStream(instaPath));
        ImageView instaI = new ImageView(imageinsta);
        instaI.setFitHeight(60);
        instaI.setPreserveRatio(true);
        instaB.setGraphic(instaI);

        fbB.setOnAction(e -> {
            bclip.play();
            getHostServices().showDocument("https://www.facebook.com");
        });
        instaB.setOnAction(e -> {
            bclip.play();
            getHostServices().showDocument("https://www.instagram.com");
        });

        Text share = new Text("SHARE!");
        share.resize(10,10);
        share.setFont(Font.font("Gill Sans", FontWeight.MEDIUM, 30));
        share.setFill(Color.WHITE);
        share.setStroke(Color.WHITE);
        share.setX(355);
        share.setY(455);

        //Hyperlink fb = new Hyperlink("https://www.facebook.com");  fb.setOnAction(e -> {getHostServices().showDocument(fb.getText());});



        Pane spLayout = new Pane();
        spLayout.getChildren().addAll(scorebg,score1,bestscore, intscore, intbestscore, totalstars, arcs1, arcs2, arcs3, arcs4, arcs5, arcs6, arcs7, arcs8);
        spLayout.getChildren().addAll(spback, sprestart,fbB,instaB,share);
        Scene scorepage = new Scene(spLayout,470,700);

        //Pausepage
        //addidng resume button
        Text t1 = new Text("Resume");
        t1.setFont(Font.font("Rockwell", FontWeight.MEDIUM, 30));
        t1.setX(180);
        t1.setY(80);
        t1.setFill(Color.WHITE);
        t1.setStroke(Color.WHITE);
        t1.resize(70,70);
        String resPath =cwd+ "/images/resume.jpeg";
        Image image9 =new Image(new FileInputStream(resPath));
        ImageView res=new ImageView(image9);
        res.setFitHeight(122);
        res.setPreserveRatio(true);
        Button resB = new Button();
        resB.setStyle("-fx-background-radius: 120em; "+"-fx-min-width: 120px; "+"-fx-min-height: 120px; "+"-fx-max-width: 120px; "+"-fx-max-height: 120px;");
        resB.setPadding(Insets.EMPTY);
        resB.setGraphic(res);
        resB.setLayoutX(180);
        resB.setLayoutY(90);
        resB.setPrefSize(300,100);

        //adding save button to PausePage
        Text t2= new Text("Save");
        t2.setFont(Font.font("Rockwell", FontWeight.MEDIUM, 30));
        t2.setX(200);
        t2.setY(290);
        t2.setFill(Color.WHITE);
        t2.setStroke(Color.WHITE);
        t2.resize(70,70);
        String savePath = cwd + "/images/save.jpeg";
        Image image10 = new Image(new FileInputStream(savePath));
        ImageView save = new ImageView(image10);
        save.setFitHeight(122);
        save.setPreserveRatio(true);

        //Saving Game, changes made by Shabeg
        Button saveB = new Button();
        saveB.setStyle("-fx-background-radius: 120em; "+"-fx-min-width: 120px; "+"-fx-min-height: 120px; "+"-fx-max-width: 120px; "+"-fx-max-height: 120px;");
        saveB.setPadding(Insets.EMPTY);
        saveB.setGraphic(save);
        saveB.setLayoutX(180);
        saveB.setLayoutY(300);
        saveB.setPrefSize(300,100);

        Label lb = new Label("  Game Saved!");
        Popup popup = new Popup();
        lb.setStyle(" -fx-background-color: darkgray");
        popup.getContent().add(lb);
        popup.setAutoHide(true);
        lb.setMinWidth(90);
        lb.setMinHeight(60);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e)
            {
                bclip.play();
                popup.show(primaryStage);
                try{
                    //Main.Save_Game sv2=(Main.Save_Game) Main.serialize.load("Save_1");
                    //score.setText(Integer.toString(sv2.cs));
                    //save();

                    //to load -pause button
                    //to load- arcs
                    //to load- ball
                    //to load- rectangle/star
                }
                catch(Exception ef ){
                    System.out.println("Couldn't load saved game"+ ef.getMessage());
                    ef.printStackTrace();
                }
            }
        };
        saveB.setOnAction(event);
        Save_Game sv= new Save_Game();
        //sv.cs=Integer.parseInt(score.getText());
        try{
            //Main.serialize.save(sv, "Save_1");

        }
        catch(Exception e ){
            System.out.print("Not saved" +e.getMessage());
        }

        //adding EXIt button to PausePage
        Text t3= new Text("Exit");
        t3.setFont(Font.font("Rockwell", FontWeight.MEDIUM, 30));
        t3.setX(210);
        t3.setY(500);
        t3.setFill(Color.WHITE);
        t3.setStroke(Color.WHITE);
        t3.resize(70,70);
        String ppexitPath = cwd + "/Images/home.jpeg";
        Image image11 = new Image(new FileInputStream(ppexitPath));
        ImageView ppexit = new ImageView(image11);
        ppexit.setFitHeight(122);
        ppexit.setPreserveRatio(true);
        Button ppexitB = new Button();
        ppexitB.setStyle("-fx-background-radius: 120em; "+"-fx-min-width: 120px; "+"-fx-min-height: 120px; "+"-fx-max-width: 120px; "+"-fx-max-height: 120px;");
        ppexitB.setPadding(Insets.EMPTY);
        ppexitB.setGraphic(ppexit);
        ppexitB.setLayoutX(180);
        ppexitB.setLayoutY(510);
        ppexitB.setPrefSize(300,100);

        Pane ppLayout = new Pane();
        ppLayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        ppLayout.getChildren().addAll(t1,t2,t3,resB,saveB,ppexitB);
        Scene pausepage = new Scene(ppLayout,470,700);


        //LoadPage
        Text savedG= new Text("SAVED GAMES");
        savedG.setFont(Font.font("Gill Sans", FontWeight.MEDIUM, 46));
        savedG.setX(35);
        savedG.setY(70);
        savedG.setFill(Color.WHITE);
        savedG.setStroke(Color.WHITE);
        savedG.resize(70,70);

        String lgpPath = cwd + "/images/home.jpeg";
        Image image12 = new Image(new FileInputStream(lgpPath));
        ImageView lgphome = new ImageView(image12);
        lgphome.setFitHeight(82);
        lgphome.setPreserveRatio(true);
        Button lgphomeB = new Button();
        lgphomeB.setStyle(
                "-fx-background-radius: 80em; " +
                        "-fx-min-width: 80px; " +
                        "-fx-min-height: 80px; " +
                        "-fx-max-width: 80px; " +
                        "-fx-max-height: 80px;"
        );
        lgphomeB.setLayoutX(370);
        lgphomeB.setLayoutY(13);
        lgphomeB.setGraphic(lgphome);

        Line line = new Line();
        line.setStartX(30);
        line.setStartY(130);
        line.setEndX(430);
        line.setEndY(130);
        line.setFill(Color.WHITE);
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(4);

        Button g1 = new Button();
        g1.setPrefSize(350,70);
        g1.setText("Saved Game 1");
        g1.setLayoutX(60);
        g1.setLayoutY(200);

        g1.setOnAction(event);
        try{
            Save_Game sv2=(Save_Game) serialize.load("Save_1");
//            score.setText(Integer.toString(sv2.cs));
            //to load -pause button
            //to load- arcs
            //to load- ball
            //to load- rectangle/star
        }
        catch(Exception e ){
            System.out.println("Couldn't load saved game"+ e.getMessage());
        }

        Button g2 = new Button();
        g2.setPrefSize(350,70);
        g2.setText("Saved Game 2");
        g2.setLayoutX(60);
        g2.setLayoutY(300);

        Button g3 = new Button();
        g3.setPrefSize(350,70);
        g3.setText("Saved Game 3");
        g3.setLayoutX(60);
        g3.setLayoutY(400);

        Button g4 = new Button();
        g4.setPrefSize(350,70);
        g4.setText("Saved Game 4");
        g4.setLayoutX(60);
        g4.setLayoutY(500);

        Pane lpLayout = new Pane();
        lpLayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        lpLayout.getChildren().addAll(savedG, lgphomeB,line,g1,g2,g3,g4);
        Scene loadpage = new Scene(lpLayout,470,700);

        //EnternamePage- For NewGame

        Line l1 = new Line();
        l1.setStartX(30);
        l1.setStartY(200);
        l1.setEndX(430);
        l1.setEndY(200);
        l1.setFill(Color.WHITE);
        l1.setStroke(Color.ORANGE);
        l1.setStrokeWidth(5);

        Line l2 = new Line();
        l2.setStartX(30);
        l2.setStartY(435);
        l2.setEndX(430);
        l2.setEndY(435);
        l2.setFill(Color.WHITE);
        l2.setStroke(Color.ORANGE);
        l2.setStrokeWidth(5);

        Text name1= new Text("ENTER NAME");
        name1.setFont(Font.font("Gill Sans", FontWeight.MEDIUM, 35));
        name1.setX(130);
        name1.setY(265);
        name1.setFill(Color.WHITE);
        name1.setStroke(Color.WHITE);
        name1.resize(70,70);

        TextField tf1=new TextField();
        tf1.setLayoutX(160);
        tf1.setLayoutY(300);
        tf1.setMaxWidth(400);
        Button enterB1 = new Button("Submit");
        enterB1.setPrefSize(100,40);
        enterB1.setLayoutX(190);
        enterB1.setLayoutY(360);

        Pane nameLayout1 = new Pane();
        nameLayout1.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        nameLayout1.getChildren().addAll(name1,tf1,enterB1, l1, l2);
        Scene namepage1 = new Scene(nameLayout1,470,700);


        //Namepage - For loadgames

        Text name2= new Text("ENTER NAME           OR");
        name2.setFont(Font.font("Gill Sans", FontWeight.MEDIUM, 35));
        name2.setX(35);
        name2.setY(265);
        name2.setFill(Color.WHITE);
        name2.setStroke(Color.WHITE);
        name2.resize(70,70);
        TextField tf2=new TextField();
        tf2.setLayoutX(65);
        tf2.setLayoutY(300);
        tf2.setMaxWidth(400);
        Button enterB2 = new Button("Show Saved Games");
        enterB2.setPrefSize(150,40);
        enterB2.setLayoutX(70);
        enterB2.setLayoutY(360);

        Button enterB3 = new Button("   Show All\nSaved Games");
        enterB3.setPrefSize(120,60);
        enterB3.setLayoutX(315);
        enterB3.setLayoutY(300);

        Line l3 = new Line();
        l3.setStartX(30);
        l3.setStartY(200);
        l3.setEndX(430);
        l3.setEndY(200);
        l3.setFill(Color.WHITE);
        l3.setStroke(Color.ORANGE);
        l3.setStrokeWidth(5);

        Line l4 = new Line();
        l4.setStartX(30);
        l4.setStartY(435);
        l4.setEndX(430);
        l4.setEndY(435);
        l4.setFill(Color.WHITE);
        l4.setStroke(Color.ORANGE);
        l4.setStrokeWidth(5);

        Line l5 = new Line();
        l5.setStartX(280);
        l5.setStartY(215);
        l5.setEndX(280);
        l5.setEndY(420);
        l5.setFill(Color.WHITE);
        l5.setStroke(Color.LIGHTGREY);
        l5.setStrokeWidth(5);

        Pane nameLayout2 = new Pane();
        nameLayout2.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        nameLayout2.getChildren().addAll(name2,tf2,enterB2,l3,l4,enterB3,l5);
        Scene namepage2 = new Scene(nameLayout2,470,700);


        newGameB.setOnAction(e -> {bclip.play();primaryStage.setScene(namepage1);});
        loadGameB.setOnAction(e -> {bclip.play();primaryStage.setScene(namepage2);});
        enterB1.setOnAction(e -> {bclip.play();if(gameplayPage != null){gameplayPage.getBall().relocate(1000,800);}intscore.setText("0");gameplayPage = new GameplayPage(2,"Gameplay",0,homepage,pausepage,gamepage,gameoverPage,primaryStage,intscore,intbestscore,totalstars, pauseB);
            this.gamepage = gameplayPage.getScene();
            this.ball = gameplayPage.getBall();primaryStage.setScene(gamepage);});
        lgphomeB.setOnAction(e -> {bclip.play();primaryStage.setScene(homepage);});
        enterB2.setOnAction(e -> {bclip.play();primaryStage.setScene(loadpage);});
        enterB3.setOnAction(e -> {bclip.play();primaryStage.setScene(loadpage);});
        spback.setOnAction(e -> primaryStage.setScene(homepage));
        sprestart.setOnAction(e -> {bclip.play();ball.setLayoutX(600);gameplayPage.getBall().relocate(1000,800);isPause = true;intscore.setText("0");gameplayPage.setPause(true);gameplayPage = new GameplayPage(2,"Gameplay",0,homepage,pausepage,gamepage,gameoverPage,primaryStage,intscore,intbestscore, totalstars,pauseB);gamepage = gameplayPage.getScene();primaryStage.setScene(gamepage);});
        pauseB.setOnAction(e -> {bclip.play();isPause = true;gameplayPage.setPause(true);primaryStage.setScene(pausepage);});
        resB.setOnAction(e -> {bclip.play();primaryStage.setScene(gamepage);gameplayPage.setPause(false);isPause=false;});
        ppexitB.setOnAction(e -> {bclip.play();primaryStage.setScene(scorepage);});

        goContinue.setOnAction(e -> {bclip.play();
            if(Integer.parseInt(totalstars.getText())>=5){
                totalstars.setText(Integer.toString(Integer.parseInt(totalstars.getText()) - 5));
                ball.setLayoutY(ball.getLayoutY()-30);
                primaryStage.setScene(gameplayPage.getScene());
                gameplayPage.setPause(false);isPause=false;}});
        goExit.setOnAction(e -> {bclip.play();ball.setLayoutX(600);isPause = true;gameplayPage.setPause(true);primaryStage.setScene(scorepage);});

        primaryStage.setResizable(true);
        primaryStage.show();

        EventHandler<ActionEvent> closeevent = new EventHandler<ActionEvent>(){

            public void handle(ActionEvent e)
            {
                primaryStage.close();
            }
        };

        exitB.setOnAction(closeevent);


    }
    public static void main(String[] args) {

        launch(args);
    }
}



