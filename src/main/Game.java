/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import input.Controller;
import java.awt.event.KeyEvent;

/**
 *
 * @author Senai
 */
public class Game {
    public static int time;
    public Controller controls;
    
    

    public Game() {
        controls = new Controller();
    }

    public void tick(boolean[] key) {
        time++;
        boolean left = key[KeyEvent.VK_A];
        boolean right = key[KeyEvent.VK_D];       
        
        controls.tick(left, right);
    }
}
