package com.noxcik.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
//...
import java.util.Scanner;
//...

import com.noxcik.IO.Input;
import com.noxcik.display.Display;
import com.noxcik.utils.time;
import com.sun.java.swing.plaf.windows.resources.windows;




/**
 * @author noxcik
 */
public class game implements Runnable  {//
    //Scanner s = new Scanner(System.in);
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "arcID";
    public static final int CLEAR_COLOR= 0xfc00ccaa;
    public static final int MEM_BUFFEERS = 2;
    public static final float UPDATE_RATE = 60.0f;
    public static final float UPDATE_INTERVAL = time.SECOND / UPDATE_RATE;
    public static final long IDLE_TIME = 1;
    //public static float delta = 0;
    //public static final String ATLAS_FILE_NAME = "img.png";

    public static final int PLAYER_H = 10;
    public static final int PLAYER_W = 150;
    public static int x = (int) (Math.random() * (WIDTH - PLAYER_W - 20) + 20);
    public static int y = HEIGHT - PLAYER_H;
    public static int level = 0;
    public static int speed = 10;

    public static double x_b = 0;
    public static double y_b = 0;
    public static int speed_b_min= 0;
    public static int speed_b_max = 0;
    public double rotation_b = 87;// (double) (Math.random() * 45.0f + randChar());
    public static int speed_b = 20;
    public static int radius_b = 30;

    private boolean running;
    private Thread gameThread;
    private Graphics2D graphics;
    public Input input;


    public game(){
        running = false;
        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, MEM_BUFFEERS);
        graphics = Display.getGraphics();
        input = new Input();
        Display.addInputListener(input);
    }


    public void run() {
        float delt= 0;
        int fps = 0;
        int upd = 0;
        int upd1 = 0;
        long count = 0;
        long lastTime = time.get();
        while(running){
            long now = time.get();
            long elapsedTime = now - lastTime;
            lastTime = now;

            count += elapsedTime;

            boolean render = false;
            delt += (elapsedTime / UPDATE_INTERVAL);
            while(delt > 1){
                update();
                delt --;
                if(render){
                    upd1 ++;
                }else{
                    render = true;
                }
            }
            if(render){
                render();
                fps++;
            }else{
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException ex) {
                    Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(count >= time.SECOND){
                Display.setTitle(TITLE + " fps:" + fps);
                fps = 0;
                upd = 0;
                upd1 = 0;
                count = 0;
            }

        }
    }
    public synchronized void start(){
        //synchronized -"программа может вызывать функцию одновременно только через один процесс" @noxcik
        if(running)
            return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
    public synchronized void stop() {
        if(!running)
            return;
        running = false;
        try{
            gameThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
    public static void lvlEnd(){
        x_b = 30;
        y_b = 50;
        speed_b -= speedMove();

    }
    Bricks lvl = getLvl(level);
    private  void update(){
        platform.update(input);
        rotation_b = ball.update(rotation_b);
        rotation_b = lvl.update(rotation_b);
        if(lvl.getLvlEnd()){
            speed_b += speedMove();
            level++;
            lvl = getLvl(level);
        }
    }
    public Bricks getLvl(int lvl){
        if(lvl == 0) return new Bricks(200, 300, 35);
        if(lvl == 1) return new Bricks(200, 100, 10);
        if(lvl == 2) return new Bricks(100, 100, 20);
        else return null;
    }
    private  void render(){
        Display.clear();
        ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        lvl.render(graphics);

        ball.render(graphics);
        graphics.setColor(Color.red);
        graphics.fillRect(x, y - 2, PLAYER_W, PLAYER_H);
        graphics.setColor(Color.black);
        if(ball.getStarting()) graphics.fillOval((int) (x_b += Math.cos(Math.toRadians(rotation_b)) * speed_b), (int) (y_b += Math.sin(Math.toRadians(rotation_b)) * speed_b), radius_b, radius_b);
        else graphics.fillOval((int) (x_b = x + PLAYER_W/2 - radius_b/2), (int) (y_b = y - radius_b), radius_b, radius_b);
        Display.swapBuffers();
    }
    private void cleanUp(){
        Display.destroy();
    }
    private int randChar(){
        if(Math.random() < 0.5) return 0;
        else return 90;
    }
    private static int speedMove(){
        if(speed_b - 3 > speed_b_min && speed_b + 3 < speed_b_max)
            return 3;
        else return 0;
    }
}
