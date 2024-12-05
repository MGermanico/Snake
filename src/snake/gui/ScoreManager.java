/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import snake.grid.gridObjects.Player;

/**
 *
 * @author tarde
 */
public class ScoreManager{
    
    private ArrayList<Player> players;
    
    public ScoreManager(ArrayList<Player> players) {
        this.players = players;
        panel = new ScorePanel(players);
    }
    
    public ScorePanel panel;
    
    public void updatePanel(){
        panel.updatePanel(players);
        panel.revalidate();
    }
    
    public JPanel getPanel(){
        updatePanel();
        return panel;
    }
}
