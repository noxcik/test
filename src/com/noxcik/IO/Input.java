/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noxcik.IO;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author noxcik
 */

public class Input extends JComponent{
    
    private boolean[] map;
    public Input(){
        map = new boolean[256];
        for(int i = 0; i < map.length; i++){
            final int KEY_CODE = i;
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i, 0, false), i*2);//нажата
            getActionMap().put(i * 2, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    map[KEY_CODE] = true;
                }
            });
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i, 0, true), i*2 + 1);//не нажата
            getActionMap().put(i * 2 + 1, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    map[KEY_CODE] = false;
                }
            });
        }
    }
    public boolean[] getMap(){
        return Arrays.copyOf(map, map.length);
    }
    public boolean getKey(int keyCode){
        return map[keyCode];
    }

    
}
