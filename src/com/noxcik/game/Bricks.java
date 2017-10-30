/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noxcik.game;

import com.noxcik.IO.Input;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author noxcik
 */
public class Bricks {


    private final int height = 50;
    private final int width = 60;
    private final int space = 10;

    private static int count = 0;
    private final int re = 8;
    private static boolean ending = false;
    Brick[] M = null;
    
    Bricks(int startX, int startY, int count){
        ending = false;
        this.count = count;
        M = new Brick[count];
        for(int i = 0; i < count; i++){
            M[i] = new Brick(startX + i * (width + space) - (re * (width + space)) *  (i/re), startY + (i / re) * (height + space));
        }


    }
    
    public float update(float rotation_b){
        int cEmpty = 0;
        for(int i = 0; i < count; i++){
            
            int y = ball.getY();
            int x = ball.getX();
            int radius = ball.getRadius();
            if(M[i] == null) cEmpty++;
            if(M[i] != null && y < M[i].getY() + height && y + radius > M[i].getY() && x + radius/2 < M[i].getX() + width && x + radius/2 > M[i].getX()){ 
                rotation_b = -rotation_b;
                M[i] = null;
            }
            else if(M[i] != null && x < M[i].getX() + width && x + radius > M[i].getX() && y < M[i].getY() + height && y + radius > M[i].getY()){
                rotation_b += 180 - rotation_b * 2;
                M[i] = null;
            }
            
            
            
        }  
        if(cEmpty == count) ending = true;
        return rotation_b;
    }

    
    public void render(Graphics2D graphics){
        graphics.setColor(Color.red);
        for(int i = 0; i < count; i++){
            if(M[i] != null)graphics.fillRect(M[i].getX(), M[i].getY(),width, height);
        }    
    
    }
    
    public int getCount() {
        return count;
    }
    public boolean getLvlEnd(){
        return ending;
    }
    
}
