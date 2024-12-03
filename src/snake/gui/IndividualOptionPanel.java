/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import snake.grid.GridManager;
import snake.grid.gridObjects.Player;

/**
 *
 * @author tarde
 */
public class IndividualOptionPanel extends JPanel{

    Options options = new Options();
    PrincipalFrame owner;
    
    Box verticalBox = Box.createVerticalBox();
    JPanel back = new JPanel();
    JPanel exampleBack = new JPanel();
    
    JSpinner xSizeSpinner = new JSpinner();
    JSpinner ySizeSpinner = new JSpinner();
    JSpinner dSizeSpinner = new JSpinner();
    
    JButton acceptButton = new JButton("Aceptar");
    JButton keysButton = new JButton("Teclas");
    
    public IndividualOptionPanel(PrincipalFrame owner) {
        options.addPlayers(new Player());
        this.owner = owner;
        startSpinners();
        startButtons();
        back.add(exampleBack);
        verticalBox.add(back);
        this.add(verticalBox);
        updateExample();
    }

    public void updateExample() {
        exampleBack.removeAll();
        GridManager exampleGrid = new GridManager(options.getxPixelSize(), options.getyPixelSize(), options.getDiagonalSize(), options.getPlayers());
        exampleBack.add(exampleGrid.getSizePanel());
        owner.validate();
        owner.revalidate();
        owner.repaint();
    }

    private void startSpinners() {
        xSizeSpinner.setValue(options.getxPixelSize());
        back.add(xSizeSpinner);
        xSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                xSizeSpinner.setValue(options.setxPixelSize((int) xSizeSpinner.getValue()));
                updateExample();
            }
        });
        
        ySizeSpinner.setValue(options.getyPixelSize());
        back.add(ySizeSpinner);
        ySizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ySizeSpinner.setValue(options.setyPixelSize((int) ySizeSpinner.getValue()));
                updateExample();
            }
        });
        
        dSizeSpinner.setValue(options.getDiagonalSize());
        back.add(dSizeSpinner);
        dSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dSizeSpinner.setValue(options.setDiagonalSize((int) dSizeSpinner.getValue()));
                updateExample();
            }
        });
    }

    private void startButtons() {
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                owner.setUp(PrincipalFrame.SETUP_SNAKE_GAME, options);
            }
        });
        verticalBox.add(acceptButton);
        
        keysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KeysSelecter keySelector = new KeysSelecter(owner);
            }
        });
        back.add(keysButton);
    }
    
    
    
}
