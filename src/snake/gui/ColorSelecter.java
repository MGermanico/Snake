/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import snake.grid.gridObjects.Player;

/**
 *
 * @author tarde
 */
public class ColorSelecter extends JDialog{

    public ColorSelecter(JFrame owner, Player player) {
        super(owner, true);
        JColorChooser colorSelector = new JColorChooser(player.getColor());
        JButton acceptButton = new JButton("Aplicar");
        Box verticalBox = Box.createVerticalBox();
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acceptAction(player, colorSelector);
            }
        });
        verticalBox.add(colorSelector);
        verticalBox.add(acceptButton);
        this.add(verticalBox);
        this.pack();
        this.setVisible(true);
    }
    private void acceptAction(Player player, JColorChooser colorSelector){
        player.setColor(colorSelector.getColor());
        this.dispose();
    }
}
