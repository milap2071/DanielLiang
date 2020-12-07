package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {

    public double x = 1;
    public double y = 1;

    public Ball(double x, double y, double radius, Color color){
        super(x, y, radius);
        setFill(color);
    }
}
