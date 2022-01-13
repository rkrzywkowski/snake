package SnakePackage;

import java.awt.*;

public class Apple {
    private int x;
    private int y;

//    public Apple(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }

    public void relocate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void drawApple(Graphics g){
        g.fillRect((int)x,(int)y,15,15);
    }

}
