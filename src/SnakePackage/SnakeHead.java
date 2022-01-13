package SnakePackage;

import java.awt.*;

public class SnakeHead {


    private double x;
    private double y;
    private double lastX;
    private double lastY;
    private double vx;
    private double vy;
    private double height;
    private Color color;

    public SnakeHead(double x, double y, double vx, double vy, double height) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.height = height;
        color = Color.green;
    }

    public void drawSnakeHead(Graphics g){
        g.fillRect((int)x,(int)y,(int)height,(int)height);
    }

    public Color getColor() {
        return color;
    }

    public void moveSnakeHead(){
        x += vx;
        y += vy;
    }

    public void checkBoundaries(int widthAnimation, int heightAnimation) {
        if (x >= widthAnimation) {
            x = 0;
        }
        if (y >= heightAnimation) {
            y = 0;
        }
        if (x < 0) {
            x = widthAnimation;
        }
        if (y < 0) {
            y = heightAnimation;
        }
    }

    public void increaseVw(double i, double i1) {
        vx = i;
        vy = i1;
    }

    public void increaseVs(double i, double i1) {
        vx = i;
        vy = i1;
    }
    public void increaseVa(double i, double i1) {
        vx = i;
        vy = i1;
    }
    public void increaseVd(double i, double i1) {
        vx = i;
        vy = i1;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getLastX() {
        return lastX;
    }

    public double getLastY() {
        return lastY;
    }
}
