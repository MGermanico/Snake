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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
public class KeysSelecter extends JDialog{
    
    public KeysSelecter(JFrame owner) {
        super(owner, true);
        this.setVisible(true);
        JPanel grid = new JPanel(new GridLayout(2, 3));
        grid.setPreferredSize(new Dimension(300, 100));
        grid.add(new JLabel());
        JButton wKey = new JButton("w");
        wKey.setBackground(Color.LIGHT_GRAY);
        wKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInputDialog("Elige tecla para arriba");
            }
        });
        grid.add(wKey);
        grid.add(new JLabel());
        JButton aKey = new JButton("a");
        aKey.setBackground(Color.LIGHT_GRAY);
        aKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInputDialog("Elige tecla para izquierda");
            }
        });
        grid.add(aKey);
        JButton sKey = new JButton("s");
        sKey.setBackground(Color.LIGHT_GRAY);
        sKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInputDialog("Elige tecla para abajo");
            }
        });
        grid.add(sKey);
        JButton dKey = new JButton("d");
        dKey.setBackground(Color.LIGHT_GRAY);
        dKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInputDialog("Elige tecla para derechas");
            }
        });
        grid.add(dKey);
        this.add(grid);
        this.pack();
    }
    
    
}
