/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import snake.grid.gridObjects.Player;

/**
 *
 * @author migue
 */
public class ScorePanel extends JPanel{
    
    public ScorePanel(ArrayList<Player> players) {
        int columns;
        int playersSize = players.size();
        if (playersSize <= 3) {
            columns = playersSize;
        }else{
            columns = playersSize;
        }
        this.setLayout(new GridLayout(Math.ceilDiv(playersSize, 3), columns));
        updatePanel(players);
    }
    
    public void updatePanel(ArrayList<Player> players){
        Player actualPlayer;
        JLabel labelText;
        for (int i = 0; i < players.size(); i++) {
            if (this.getComponentCount() - 1 < i) {
                this.add(new JLabel());
            }else{
                actualPlayer = players.get(i);
                this.remove(i);
                labelText = new JLabel("Player " + actualPlayer.getId_Player() + " : " + actualPlayer.getPoints() + " puntos");
                labelText.setFont(new java.awt.Font("Liberation Sans", 1, 20));
                this.add(labelText, i);
            }
        }
    }
    
}
