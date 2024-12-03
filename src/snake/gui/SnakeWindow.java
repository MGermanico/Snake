/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.gui;

import snake.grid.GridManager;
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
    
    public SnakeWindow(Options options) {
        GridManager gm = new GridManager(options.getxPixelSize(), options.getyPixelSize(), options.getDiagonalSize(), options.getPlayers());
        this.gm = gm;
        this.add(gm.getPanel());
        gm.startGame();
    }

    public GridManager getGm() {
        return gm;
    }
}