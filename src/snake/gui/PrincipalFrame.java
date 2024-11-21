/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.gui;

import java.awt.HeadlessException;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author tarde
 */
public class PrincipalFrame extends JFrame{

    public static final int MENU = 0;
    public static final int SETUP_SNAKE_GAME = 1;
    
    JPanel back;
    
    public PrincipalFrame() throws HeadlessException {
        this.setBounds(0, 0, 1000, 1000);
        this.setVisible(true);
        back = new JPanel();
        setUp(SETUP_SNAKE_GAME);
        this.add(back);
    }

    private void setUp(int type) {
        back.removeAll();
        if (type == MENU) {
            
        }else if(type == SETUP_SNAKE_GAME){
            SnakeWindow snakeWindow = new SnakeWindow();
            back.add(snakeWindow);
            
            for (KeyListener keyListener : snakeWindow.getGm().getKeyListeners()) {
                this.addKeyListener(keyListener);
            }
        }
    }
    
    
}
