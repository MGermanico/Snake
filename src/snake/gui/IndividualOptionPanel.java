/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

    private Options options = new Options();
    private PrincipalFrame owner;
    
    private Box verticalBox = Box.createVerticalBox();
    private Box playerOptionsBox = Box.createVerticalBox();
    private JPanel back = new JPanel();
    private JPanel exampleBack = new JPanel();
    
    private JSpinner speedSpinner = new JSpinner();
    private JSpinner nManzanasSpinner = new JSpinner();
    private JSpinner xSizeSpinner = new JSpinner();
    private JSpinner ySizeSpinner = new JSpinner();
    private JSpinner dSizeSpinner = new JSpinner();
    
    private JButton acceptButton = new JButton("Aceptar");
    private JButton cancelButton = new JButton("Cancelar");
    private JButton keysButton = new JButton("Teclas (a,w,s,d)");
    private JButton colorButton = new JButton();
    
    public IndividualOptionPanel(PrincipalFrame owner) {
        options.addPlayers(new Player());
        this.owner = owner;
        startSpinners();
        startButtons();
        setUpComponents();
        updateExample();
    }

    private void setUpComponents(){
        JPanel horizontal;
        JLabel text;
        
        horizontal = new JPanel();
        text = new JLabel("Opciones Generales");
        text.setFont(new java.awt.Font("Liberation Sans", 1, 24));
        horizontal.add(text);
        playerOptionsBox.add(horizontal);
        
        horizontal = new JPanel();
        horizontal.add(new JLabel(" Num Manzanas "));
        horizontal.add(nManzanasSpinner);
        horizontal.add(new JLabel(" X "));
        horizontal.add(ySizeSpinner);
        horizontal.add(new JLabel(" Y "));
        horizontal.add(xSizeSpinner);
        horizontal.add(new JLabel(" Tamaño "));
        horizontal.add(dSizeSpinner);
        playerOptionsBox.add(horizontal);
        
        horizontal = new JPanel();
        horizontal.add(new JLabel(" Elegir Velocidad "));
        horizontal.add(speedSpinner);
        playerOptionsBox.add(horizontal);
        
        playerOptionsBox.add(Box.createVerticalStrut(30));
        
        horizontal = new JPanel();
        text = new JLabel("Opciones Jugadores");
        text.setFont(new java.awt.Font("Liberation Sans", 1, 24));
        horizontal.add(text);
        playerOptionsBox.add(horizontal);
        
        horizontal = new JPanel();
        horizontal.add(new JLabel("Player 1"));
        horizontal.add(keysButton);
        horizontal.add(colorButton);
        playerOptionsBox.add(horizontal);
        back.add(playerOptionsBox);
        back.add(exampleBack);
        horizontal = new JPanel();
        horizontal.add(acceptButton);
        horizontal.add(cancelButton);
        verticalBox.add(horizontal);
        verticalBox.add(back);
        this.add(verticalBox);
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
        speedSpinner.setValue(options.getSpeed());
        speedSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                speedSpinner.setValue(options.setSpeed((int) speedSpinner.getValue()));
                updateExample();
            }
        });
        
        nManzanasSpinner.setValue(options.getnManzanas());
        nManzanasSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                nManzanasSpinner.setValue(options.setnManzanas((int) nManzanasSpinner.getValue()));
                updateExample();
            }
        });
        
        xSizeSpinner.setValue(options.getxPixelSize());
        xSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                xSizeSpinner.setValue(options.setxPixelSize((int) xSizeSpinner.getValue()));
                updateExample();
            }
        });
        
        ySizeSpinner.setValue(options.getyPixelSize());
        ySizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ySizeSpinner.setValue(options.setyPixelSize((int) ySizeSpinner.getValue()));
                updateExample();
            }
        });
        
        dSizeSpinner.setValue(options.getDiagonalSize());
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
        acceptButton.setBackground(Color.WHITE);
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                owner.setUp(PrincipalFrame.SETUP_MENU);
            }
        });
        cancelButton.setBackground(Color.WHITE);
        
        keysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new KeysSelecter(owner, options.getPlayers().getFirst());
            }
        });
        keysButton.setBackground(Color.WHITE);
        
        colorButton.setBackground(options.getPlayers().getFirst().getColor());
        colorButton.setPreferredSize(new Dimension(70, 25));
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ColorSelecter(owner, options.getPlayers().getFirst());
                colorButton.setBackground(options.getPlayers().getFirst().getColor());
            }
        });
        
        
    }
    
}
