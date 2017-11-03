/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noxcik.display;

import com.noxcik.IO.Input;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author noxcik
 */
public abstract class Display{

    /**
     * @param args the command line arguments
     */
    private static boolean created = false;
    private static JFrame window;
    private static Canvas content;
    
    private static BufferedImage buffer;
    private static int[] bufferData;
    private static Graphics bufferGraphics;
    private static int clearColor;
    public static float delta = 0;
    private static BufferStrategy bufferStrategy;
    
    public static void create(int width, int height, String title, int cC, int numBuffers){
        if(created)
            return;
        window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = new Canvas();
       // Image im=Toolkit.getDefaultToolkit().getImage("Icon.png");
       //window.setIconImage(im);
        
        Dimension size = new Dimension(width, height);
        content.setPreferredSize(size);
        window.setResizable(false);
        window.getContentPane().add(content);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt)buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        clearColor = cC;
        
        content.createBufferStrategy(numBuffers);
        bufferStrategy = content.getBufferStrategy();
        created = true;
    }

    public static void clear() {
        Arrays.fill(bufferData, clearColor);
    }
    public static void Render(){
        

    
    
    }
    public static void swapBuffers(){
        Graphics h = bufferStrategy.getDrawGraphics();
        h.drawImage(buffer, 0, 0, null);
        bufferStrategy.show();
                            
    }
    
    public static void destroy(){
    if(!created)
        return;  
    window.dispose();
    }
    public static void setTitle(String title){
        window.setTitle(title);
    }
    public static Graphics2D getGraphics(){
        return (Graphics2D) bufferGraphics;
    }
    public static void addInputListener(Input inputListener){
        window.add(inputListener);
        
    }



    
}
