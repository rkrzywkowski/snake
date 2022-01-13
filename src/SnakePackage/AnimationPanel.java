package SnakePackage;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.awt.Color.*;
import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;


public class AnimationPanel extends JPanel {

    double speed = 15;
    private Animation animation;
    private List<BodySnake> bodySnakeList = new ArrayList<>();

    SnakeHead sh;
    Apple apple;
    Random generate = new Random();
    BodySnake bodySnake;
    BodySnake bodySnake1;
    boolean snakeIsAlive = true;


    public AnimationPanel(){

        sh = new SnakeHead(30,0,speed,0,15);

        apple = new Apple();
        apple.relocate(generate.nextInt(570/15)*15, generate.nextInt(570/15)*15);

        bodySnake = new BodySnake();
        bodySnake.setXYBody(15,0, sh.getColor());
        bodySnakeList.add(bodySnake);

        bodySnake1 = new BodySnake();
        bodySnake1.setXYBody(0,0, sh.getColor());
        bodySnakeList.add(bodySnake1);


        animation = new Animation();
        addKeyListener(new PanelKeyListener());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(sh.getColor());
        sh.drawSnakeHead(g);

        g.setColor(red);
        apple.drawApple(g);

        for(BodySnake bodySnake : bodySnakeList){
            g.setColor(bodySnake.getColor());
            bodySnake.drawSnakeBody(g);
        }

    }

    public void start(){
        Thread t = new Thread(animation);
        t.start();

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        AnimationPanel aPanel = new AnimationPanel();
        frame.setContentPane(aPanel);
        frame.addKeyListener(aPanel.new PanelKeyListener());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aPanel.setSize(600,600);
        frame.setSize(600,600);


        //frame.pack();
        frame.setVisible(true);
        aPanel.start();
    }

    class Animation implements Runnable{

        @Override
        public void run() {
            while (true) {

                moveSnake();
                sh.moveSnakeHead();
                sh.checkBoundaries(600, 600);
                //System.out.println("width: " + getWidth() + " height: " + getHeight());
                checkCollision();

                if(!snakeIsAlive) break;

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("apple " + apple.getX() + " " + apple.getY());
//                System.out.println("glowa " + sh.getX() + " " + sh.getY());
                repaint();
            }
        }

        private void checkCollision(){

            if(sh.getX() == apple.getX() && sh.getY() == apple.getY()){
                relocateApple();
                bodySnake = new BodySnake(sh.getX(),sh.getY(), sh.getColor());
                bodySnakeList.add(bodySnake);

            }
        }
        private void relocateApple(){
            apple.relocate(generate.nextInt(600/15)*15, generate.nextInt(600/15)*15);
        }

        private void checkSelfCollision() {
            for (int i = 1; i < bodySnakeList.size(); i++) {
                if (sh.getX() == bodySnakeList.get(i).getX() && sh.getY() == bodySnakeList.get(i).getY()) {
//                    System.out.println("sh: " + sh.getX() + " " + sh.getY());
//                    System.out.println(i + " bs: " + bodySnake.getX() + " " + bodySnake.getY());
//                    JOptionPane.showMessageDialog(null, "GAME OVER", "YOU LOST", JOptionPane.WARNING_MESSAGE);
                      final JOptionPane pane = new JOptionPane("GAME OVER");
                      final JDialog d = pane.createDialog((JFrame)null, "YOU LOST!");

                      d.setLocation(190,250);
                      d.setVisible(true);
                      snakeIsAlive = false;
                }
            }
        }


        private void moveSnake() {
            for (int i = bodySnakeList.size() - 1; i > 0; i--) {
                bodySnakeList.get(i).setXYBody(bodySnakeList.get(i - 1).getX(), bodySnakeList.get(i - 1).getY(), sh.getColor());
            }
            bodySnakeList.get(0).setXYBody(sh.getX(), sh.getY(), sh.getColor());
            checkSelfCollision();
        }
    }

    private class PanelKeyListener extends KeyAdapter {
        String destination = "D";
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            System.out.println("Key Pressed");

            switch(e.getKeyCode()){

                case KeyEvent.VK_W :
                    if(destination != "S") {
                        System.out.println(destination);
                        sh.increaseVw(0, -speed);
                        destination = "W";
                        System.out.println(destination);

                    }  break;
                case KeyEvent.VK_S :
                    if(destination != "W") {
                        System.out.println(destination);
                        sh.increaseVs(0, speed);
                        destination = "S";
                        System.out.println(destination);

                    } break;
                case KeyEvent.VK_A :
                    if(destination != "D") {
                        System.out.println(destination);
                        sh.increaseVa(-speed, 0);
                        destination = "A";
                        System.out.println(destination);
                    } break;

                case KeyEvent.VK_D :
                    if(destination != "A") {
                        System.out.println(destination);
                        sh.increaseVd(+speed, 0);
                        destination = "D";
                        System.out.println(destination);
                    }  break;
            }
        }
    }
}