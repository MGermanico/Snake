/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import snake.grid.gridObjects.Player;

/**
 *
 * @author migue
 */
public class KeysSelecter extends JDialog{
    
    public KeysSelecter(JFrame owner, Player player) {
        super(owner, true);
        
        char[] keys = player.getKeys();
        
        JPanel gridUp = new JPanel(new GridLayout(2, 3));
        gridUp.setPreferredSize(new Dimension(300, 200));
        gridUp.add(new JLabel());
        JButton wKey = new JButton(keys[1] + "");
        wKey.setBackground(Color.LIGHT_GRAY);
        wKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keys[1] = verifyKey(JOptionPane.showInputDialog("Elige tecla para arriba"), keys[1]);
                wKey.setText(keys[1] + "");
            }
        });
        gridUp.add(wKey);
        gridUp.add(new JLabel());
        JButton aKey = new JButton(keys[0] + "");
        aKey.setBackground(Color.LIGHT_GRAY);
        aKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keys[0] = verifyKey(JOptionPane.showInputDialog("Elige tecla para izquierda"), keys[0]);
                aKey.setText(keys[0] + "");
            }
        });
        gridUp.add(aKey);
        JButton sKey = new JButton(keys[2] + "");
        sKey.setBackground(Color.LIGHT_GRAY);
        sKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keys[2] = verifyKey(JOptionPane.showInputDialog("Elige tecla para abajo"), keys[2]);
                sKey.setText(keys[2] + "");
            }
        });
        gridUp.add(sKey);
        JButton dKey = new JButton(keys[3] + "");
        dKey.setBackground(Color.LIGHT_GRAY);
        dKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keys[3] = verifyKey(JOptionPane.showInputDialog("Elige tecla para derechas"), keys[3]);
                dKey.setText(keys[3] + "");
            }
        });
        gridUp.add(dKey);
        
        JButton acceptButton = new JButton("Aplicar");
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acceptAction(player, keys);
            }
        });
        
        JPanel gridDown = new JPanel(new GridLayout(1, 1));
        gridDown.setPreferredSize(new Dimension(100,50));
        
        gridDown.add(acceptButton);
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(gridUp);
        verticalBox.add(gridDown);
        this.add(verticalBox);
        this.pack();
        this.setVisible(true);
    }
    private void acceptAction(Player player, char[] keys){
        player.setKeys(keys);
        this.dispose();
    }
    
    private char verifyKey(String key, char beforeKey) {
        if( key.length() == 1 ){
            return key.charAt(0);
        }else{
            return beforeKey;
        }
    }
    
}
