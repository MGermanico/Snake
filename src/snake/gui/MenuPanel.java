/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author tarde
 */
public class MenuPanel extends JPanel{

    JButton nuevaPartidaIndividual;
    JButton nuevaPartidaMultijugador;
    
    public MenuPanel(PrincipalFrame owner) {
        nuevaPartidaIndividual = new JButton("Nueva partida individual");
        nuevaPartidaIndividual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                owner.setUp(PrincipalFrame.SETUP_INDIVIDUAL_OPTIONS);
            }
        });
        this.add(nuevaPartidaIndividual);
        
        nuevaPartidaMultijugador = new JButton("Nueva partida multijugador");
        nuevaPartidaMultijugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                owner.setUp(PrincipalFrame.SETUP_MULTIPLAYER_OPTIONS);
            }
        });
        this.add(nuevaPartidaMultijugador);
    }
    
    
}
