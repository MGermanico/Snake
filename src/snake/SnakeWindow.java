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
    
    public static void main(String[] args) {
        JFrame back = new JFrame();
        back.setBounds(0, 0, 1000, 1000);
        back.setVisible(true);
        SnakeWindow snakeWindow = new SnakeWindow();
        back.add(snakeWindow);
        back.addKeyListener(snakeWindow.getGm().getKeyListener());
    }
    
    GridManager gm;
    
    public SnakeWindow() {
        GridManager gm = new GridManager(80, 60, 1000);
        this.gm = gm;
        this.add(gm.getPanel());
        gm.startGame();
    }

    public GridManager getGm() {
        return gm;
    }
    
    
    
}
