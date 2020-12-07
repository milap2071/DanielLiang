package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public AnchorPane rootAnchor;
    public ScrollBar scrollBar;
    public AnchorPane bounceAnchor;
    public AnchorPane buttonAnchor;
    public Button btnSub;
    public Button btnAdd;

    private Timeline timeLine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeLine = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(50), event -> moveBall());
        timeLine.getKeyFrames().add(kf);
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();

        scrollBar.setMax(20);
        scrollBar.setValue(10);
        rateProperty().bind(scrollBar.valueProperty());

        bounceAnchor.setOnMousePressed(e->pause());
        bounceAnchor.setOnMouseReleased(e->play());

        btnAdd.setOnAction(e->addBall());
        btnSub.setOnAction(e->removeBall());


    }

    public void addBall(){
        Color ballColor = new Color(Math.random(), Math.random(), Math.random(), 0.5);
        bounceAnchor.getChildren().add(new Ball(30, 30, 20, ballColor));
    }

    public void removeBall(){
        List ballList = bounceAnchor.getChildren();
        ballList.remove(ballList.size() -1);
    }

    public void play(){
        timeLine.play();

    }

    public void pause(){
        timeLine.pause();
    }

    public void increaseSpeed(){
        timeLine.setRate(timeLine.getRate() + 0.1);
    }

    public void decreaseSpeed(){
        timeLine.setRate(timeLine.getRate() - 0.1);
    }

    public DoubleProperty rateProperty(){
        return timeLine.rateProperty();
    }

    public void moveBall(){
        for (Node node: bounceAnchor.getChildren()){
            Ball ball = (Ball) node;

            //Check boundaries
            if(ball.getCenterX()< ball.getRadius() || ball.getCenterX()> bounceAnchor.getWidth() - ball.getRadius()){
                ball.x *= -1;
            }
            if(ball.getCenterY() < ball.getRadius() || ball.getCenterY() > bounceAnchor.getHeight() - ball.getRadius()){
                ball.y *= -1;
            }

            //Adjust ball position
            ball.setCenterX(ball.x + ball.getCenterX());
            ball.setCenterY(ball.y + ball.getCenterY());
        }
    }
}
