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

/**
 *
 * @author noxcik
 */
public class ball {
    
    public static float add_rotation = 5f;
    private static boolean starting = false;    
    private static float rotation_b = 0;
    
    public static float update(float rotation_b){
        if(starting){
            if(x_b < 0){
                rotation_b += 180 - rotation_b * 2;
            }
            if(y_b < 0){
                rotation_b = -rotation_b;
            }
            if(x_b > WIDTH - radius_b){
                rotation_b += 180 - rotation_b * 2;
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

    static void start() {
        starting = true;
    }
    public static boolean getStarting(){
        return starting;
    }
    public static int getX(){
        return x_b;
    }
    public static int getRadius(){
        return radius_b;
    }    
    public static int getY(){
        return y_b;
    }  
    public void reboundV(){
        rotation_b = -rotation_b;
    }
    public void reboundG(){
        rotation_b += 180 - rotation_b * 2;
    }    
}
