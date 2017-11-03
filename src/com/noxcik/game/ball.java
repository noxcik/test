/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noxcik.game;

import static com.noxcik.game.game.HEIGHT;
import static com.noxcik.game.game.PLAYER_W;
import static com.noxcik.game.game.WIDTH;
import static com.noxcik.game.game.lvlEnd;
import static com.noxcik.game.game.radius_b;
import static com.noxcik.game.game.speed_b;
import static com.noxcik.game.game.x;
import static com.noxcik.game.game.x_b;
import static com.noxcik.game.game.y;
import static com.noxcik.game.game.y_b;
        
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author noxcik
 */
public class ball {
    
    public static float add_rotation = 5f;
    private static boolean starting = false;    
    private static double rotation_b = 0;
    private static int t_size = 28;
    private static int t_y = HEIGHT / 2 - t_size;
    private static int t_x = 100;

    public static double update(double rotation_b){
        if(starting){
            if(x_b < 0){
                System.out.println(x_b + " " + y_b);
                x_b = 1;
                rotation_b += 180 - rotation_b * 2.0;
            }
            if(y_b < 0){
                System.out.println(x_b + " " + y_b);
                y_b = 1;
                rotation_b = -rotation_b;
            }
            if(x_b > WIDTH - radius_b){
                System.out.println(x_b + " " + y_b);
                x_b = WIDTH - radius_b - 1;
                rotation_b += 180 - rotation_b * 2.0;
            }
            if(y_b >= y - radius_b - Math.sin(Math.toRadians(rotation_b)) * speed_b && x_b >= x - radius_b/2 && x_b < x + PLAYER_W + radius_b/2 ){
                y_b = y - radius_b - 1;
                rotation_b = -rotation_b;
                if(platform.left)  rotation_b -= add_rotation;
                if(platform.right) rotation_b += add_rotation;
            }
            if(y_b > HEIGHT){
                lvlEnd();
            }
        }
        ball.rotation_b = rotation_b;
        return rotation_b;        
    }

    public static void render(Graphics2D graphics){
        if(!starting){
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Font f = new Font("Monospaced", Font.ITALIC, t_size);
            graphics.setFont(f);
            graphics.drawString("для запуска используй: пробел", t_x, t_y);
            graphics.drawString("влево/вправо - стрелки влево/вправо", t_x, t_y + t_size);
        }
    }
    static void start() {
        starting = true;
    }
    public static boolean getStarting(){
        return starting;
    }
    public static double getX(){
        return x_b;
    }
    public static int getRadius(){
        return radius_b;
    }    
    public static double getY(){
        return y_b;
    }  
    public void reboundV(){
        rotation_b = -rotation_b;
    }
    public void reboundG(){
        rotation_b += 180 - rotation_b * 2;
    }    
}
