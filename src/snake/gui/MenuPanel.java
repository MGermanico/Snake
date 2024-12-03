/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tarde
 */
public class MenuPanel extends JPanel{
    
    JButton nuevaPartidaIndividual;
    JButton nuevaPartidaMultijugador;
    
    public MenuPanel(PrincipalFrame owner) {
        
        Box verticalBox = Box.createVerticalBox();
        JPanel horizontal;
        
        verticalBox.add(Box.createVerticalStrut(100));
        
        horizontal = new JPanel();
        nuevaPartidaIndividual = new JButton("Nueva partida individual");
        nuevaPartidaIndividual.setFont(new java.awt.Font("Liberation Sans", 1, 35));
        nuevaPartidaIndividual.setPreferredSize(new Dimension(600, 200));
        nuevaPartidaIndividual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                owner.setUp(PrincipalFrame.SETUP_INDIVIDUAL_OPTIONS);
            }
        });
        nuevaPartidaIndividual.setBackground(Color.WHITE);
        horizontal.add(nuevaPartidaIndividual);
        verticalBox.add(horizontal);
        
        horizontal = new JPanel();
        nuevaPartidaMultijugador = new JButton("Nueva partida multijugador");
        nuevaPartidaMultijugador.setFont(new java.awt.Font("Liberation Sans", 1, 35));
        nuevaPartidaMultijugador.setPreferredSize(new Dimension(600, 200));
        nuevaPartidaMultijugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                owner.setUp(PrincipalFrame.SETUP_MULTIPLAYER_OPTIONS);
            }
        });
        nuevaPartidaMultijugador.setBackground(Color.WHITE);
        horizontal.add(nuevaPartidaMultijugador);
        verticalBox.add(horizontal);
        this.add(verticalBox);
    }
    
    
}
