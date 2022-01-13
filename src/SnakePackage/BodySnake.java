package SnakePackage;


import java.awt.*;

public class BodySnake {
    private double x;
    private double y;
    private Color color;

    public BodySnake(){}

    public BodySnake(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setXYBody(double x, double y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    public void drawSnakeBody(Graphics g){
        g.fillRect((int)x,(int)y,15,15);
    }
}
