/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noxcik.game;

/**
 *
 * @author noxcik
 */
public class Brick {
    private static int count = 0;
    private int x = 0;
    private int y = 0;

    Brick(int x, int y) {
        count++;
        this.x = x;
        this.y = y;
        System.out.println(x + " " + y);
    }
    public void getCount(){
        
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }    
}
