/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noxcik.game;

import com.noxcik.IO.Input;
import static com.noxcik.game.game.HEIGHT;
import static com.noxcik.game.game.PLAYER_W;
import static com.noxcik.game.game.WIDTH;
import static com.noxcik.game.game.speed;
import static com.noxcik.game.game.x;
import static com.noxcik.game.game.y_b;
import java.awt.event.KeyEvent;

/**
 *
 * @author noxcik
 * на 21.10 _ перенести проверку нажатий в отдельный класс.
 */
public class platform {
    public static boolean right = false;
    public static boolean left = false;
    public static void update(Input input){
        if(y_b > HEIGHT){
            game.lvlEnd();
        }
        if(input.getKey(KeyEvent.VK_LEFT) && x > speed) {
            left = true;
            x -= speed;
        } else left = false;
        if(input.getKey(KeyEvent.VK_RIGHT) && x < WIDTH - PLAYER_W - speed) {
            right = true;
           x += speed;
        } else right = false;
        if(input.getKey(KeyEvent.VK_SPACE)) {
           ball.start();
        } 
    }
    public static boolean getRight(){
        return right;
    }
    public static boolean getLeft(){
        return left;
    }
    
}
