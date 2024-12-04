/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import snake.grid.GridManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
public class SnakeWindow extends JPanel{
    
    private Box verticalBox = Box.createVerticalBox();
    
    PrincipalFrame owner;
    
    GridManager gm;
    
    Score score;
    
    public SnakeWindow(PrincipalFrame owner, Options options) {
        this.owner = owner;
        
        initComponents(options);
        
        setUpComponents(options);
        
        gm.startGame(options);
    }
    
    private void initComponents(Options options){
        score = new Score(options.getPlayers());
        
        gm = new GridManager(options.getxPixelSize(), options.getyPixelSize(), options.getDiagonalSize(), options.getPlayers(), score);
    }
    
    private void setUpComponents(Options options){
        verticalBox.add(getTittlePanel(options.getPlayers().size() > 1));
        verticalBox.add(score.getPanel());
        verticalBox.add(getCancelPanel());
        verticalBox.add(gm.getPanel());
        this.add(verticalBox);
    }
    
    private JPanel getTittlePanel(boolean isMultiplayer){
        JPanel horizontal = new JPanel();
        JLabel text;
        horizontal = new JPanel();
        if (isMultiplayer) {
            text = new JLabel("MODO MULTIJUGADOR");
        }else{
            text = new JLabel("MODO INDIVIDUAL");
        }
        text.setFont(new java.awt.Font("Liberation Sans", 1, 60));
        horizontal.add(text);
        return horizontal;
    }

    private JPanel getCancelPanel(){
        JPanel horizontal = new JPanel();
        JButton cancelButton = new JButton("Salir");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gm.endGame();
                owner.setUp(PrincipalFrame.SETUP_MENU);
            }
        });
        cancelButton.setBackground(Color.WHITE);
        horizontal.add(cancelButton);
        return horizontal;
    }
    
    public GridManager getGm() {
        return gm;
    }
}