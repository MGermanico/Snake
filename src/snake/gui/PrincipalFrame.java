/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.gui;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author tarde
 */
public class PrincipalFrame extends JFrame{

    public static final int SETUP_MENU = 0;
    public static final int SETUP_SNAKE_GAME = 1;
    public static final int SETUP_MULTIPLAYER_OPTIONS = 2;
    public static final int SETUP_INDIVIDUAL_OPTIONS = 3;
    
    JPanel back;
    
    public PrincipalFrame() {
        this.setFocusable(true);
        this.setBounds(0, 0, 1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        back = new JPanel();
        this.add(back);
        Options op = new Options();
//        setUp(SETUP_SNAKE_GAME, op);
        setUp(SETUP_MENU);
        
    }

    public void setUp(int type, Options options) {
        back.removeAll();
        if (type == SETUP_MENU) {
            MenuPanel menuPanel = new MenuPanel(this);
            back.add(menuPanel);
            
        }else if(type == SETUP_SNAKE_GAME && options != null){
            SnakeWindow snakeWindow = new SnakeWindow(this, options);
            back.add(snakeWindow);
            
            for (KeyListener keyListener : snakeWindow.getGm().getKeyListeners()) {
                this.addKeyListener(keyListener);
            }
        }else if (type == SETUP_INDIVIDUAL_OPTIONS) {
            IndividualOptionPanel indivOptionPanel = new IndividualOptionPanel(this, options);
            back.add(indivOptionPanel);
        }else if (type == SETUP_MULTIPLAYER_OPTIONS) {
            MultiplayerOptionPanel multiplayerOptionPanel = new MultiplayerOptionPanel(this, options);
            back.add(multiplayerOptionPanel);
        }
        this.validate();
        this.revalidate();
        this.repaint();
    }
    
    public void setUp(int type){
        setUp(type, null);
    }
    
}
