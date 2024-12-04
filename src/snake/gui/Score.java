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
public class Score {
    
    private ArrayList<Player> players;
    
    public Score(ArrayList<Player> players) {
        this.players = players;
    }
    
    public JPanel getPanel(){
        JPanel panel = new JPanel(new GridLayout(Math.ceilDiv(players.size(), 3), 3));
        Player actualPlayer;
        for (int i = 0; i < players.size(); i++) {
            actualPlayer = players.get(i);
            panel.add(new JLabel("Player " + actualPlayer.getId_Player() + " : " + actualPlayer.getPoints()));
        }
        return panel;
    }
    
}
