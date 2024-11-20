/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
public class SnakeWindow extends JPanel{
    
    GridManager gm;
    
    public SnakeWindow() {
        GridManager gm = new GridManager(105, 105, 500, 2);
        this.gm = gm;
        this.add(gm.getPanel());
        gm.startGame();
    }

    public GridManager getGm() {
        return gm;
    }
}