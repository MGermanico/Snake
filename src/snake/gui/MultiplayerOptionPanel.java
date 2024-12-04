/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import snake.grid.ExampleLoopThread;
import snake.grid.GridManager;
import snake.grid.gridObjects.Player;

/**
 *
 * @author tarde
 */
public class MultiplayerOptionPanel extends JPanel{

    private Options options = new Options();
    public PrincipalFrame owner;
    
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
    private ArrayList<JButton> keysButtons = new ArrayList<>();
    private ArrayList<JButton> colorButtons = new ArrayList<>();
    private JButton addPlayerButton = new JButton("Añadir jugador");
    
    GridManager exampleGrid;
    ExampleLoopThread exLoop;
    
    public MultiplayerOptionPanel(PrincipalFrame owner, Options opt) {
        if (opt != null) {
            options = opt;
        }else{
            options.addPlayers(new Player());
        }
        this.owner = owner;
        exampleGrid = new GridManager(options.getxPixelSize(), options.getyPixelSize(), options.getDiagonalSize(), options.getPlayers(), null);
        exLoop = new ExampleLoopThread(exampleGrid, this);
        exLoop.start();
        startSpinners();
        startButtons();
        setUpComponents();
    }
    
    private void setUpComponents(){
        JPanel horizontal;
        
        playerOptionsBox.removeAll();
        back.removeAll();
        verticalBox.removeAll();
        
        playerOptionsBox.add(getGeneralOptionsTextPanel());
        
        playerOptionsBox.add(getFirstGeneralOptionsPanel());
        
        playerOptionsBox.add(getSecondGeneralOptionsPanel());
        
        playerOptionsBox.add(Box.createVerticalStrut(50));
        
        playerOptionsBox.add(getPlayerOptionsTextPanel());
        
        for (int i = 0; i < options.getPlayers().size(); i++) {
            playerOptionsBox.add(getIndividualPlayerPanel(i));
        }
        
        playerOptionsBox.add(Box.createVerticalStrut(20));
        
        horizontal = new JPanel();
        horizontal.add(addPlayerButton);
        playerOptionsBox.add(horizontal);
        
        back.add(playerOptionsBox);
        back.add(exampleBack);
        
        verticalBox.add(getTittlePanel());
        verticalBox.add(getSubtittlePanel());
        
        horizontal = new JPanel();
        horizontal.add(acceptButton);
        horizontal.add(cancelButton);
        verticalBox.add(Box.createVerticalStrut(40));
        verticalBox.add(horizontal);
        verticalBox.add(back);
        this.add(verticalBox);
    }
    
    private JPanel getGeneralOptionsTextPanel(){
        JPanel horizontal = new JPanel();
        JLabel text = new JLabel("Opciones Generales");
        text.setFont(new java.awt.Font("Liberation Sans", 1, 24));
        horizontal.add(text);
        return horizontal;
    }
    
    private JPanel getSecondGeneralOptionsPanel(){
        JPanel horizontal = new JPanel();
        horizontal.add(new JLabel(" Elegir Velocidad "));
        horizontal.add(speedSpinner);
        return horizontal;
    }
    
    private JPanel getFirstGeneralOptionsPanel(){
        JPanel horizontal = new JPanel();
        horizontal.add(new JLabel(" Num Manzanas "));
        horizontal.add(nManzanasSpinner);
        horizontal.add(new JLabel(" X "));
        horizontal.add(ySizeSpinner);
        horizontal.add(new JLabel(" Y "));
        horizontal.add(xSizeSpinner);
        horizontal.add(new JLabel(" Tamaño "));
        horizontal.add(dSizeSpinner);
        return horizontal;
    }
    
    private JPanel getPlayerOptionsTextPanel(){
        JPanel horizontal = new JPanel();
        JLabel text = new JLabel("Opciones Jugadores");
        text.setFont(new java.awt.Font("Liberation Sans", 1, 24));
        horizontal.add(text);
        return horizontal;
    }
    
    private JPanel getIndividualPlayerPanel(int i){
        JPanel horizontal = new JPanel();
        JButton deleteButton = new JButton("x");
        deleteButton.setSize(30, 30);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                options.getPlayers().remove(i);
                keysButtons.remove(i);
                colorButtons.remove(i);
                setUpComponents();
                owner.validate();
                owner.revalidate();
                owner.repaint();
            }
        });
        deleteButton.setBackground(Color.WHITE);
        horizontal.add(deleteButton);
        horizontal.add(new JLabel("Player " + options.getPlayers().get(i).getId_Player()));
        horizontal.add(keysButtons.get(i));
        horizontal.add(colorButtons.get(i));
        return horizontal;
    }
    
    private JPanel getTittlePanel(){
        JPanel horizontal = new JPanel();
        JLabel text;
        horizontal = new JPanel();
        text = new JLabel("MODO MULTIJUGADOR");
        text.setFont(new java.awt.Font("Liberation Sans", 1, 60));
        horizontal.add(text);
        return horizontal;
    }
    
    private JPanel getSubtittlePanel(){
        JPanel horizontal = new JPanel();
        JLabel text;
        horizontal = new JPanel();
        text = new JLabel("(opciones)");
        text.setFont(new java.awt.Font("Liberation Sans", 1, 30));
        horizontal.add(text);
        return horizontal;
    }
    
    public void updateExample(JPanel panel) {
        exampleBack.removeAll();
        exampleBack.add(panel);
        owner.validate();
        owner.revalidate();
        owner.repaint();
    }
    boolean waitting = false;
    boolean pressing = false;
    public void updateExample() {
        if (!waitting) {
            Thread waitThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    pressing = true;
                    exampleBack.removeAll();
                    waitting = true;
                    while(pressing){
                        pressing = false;
                        exLoop.waitMilis(500);
                    }
                    exampleGrid = new GridManager(options.getxPixelSize(), options.getyPixelSize(), options.getDiagonalSize(), options.getPlayers(), null);
                    exLoop.setGridManager(exampleGrid);
                    exLoop.updateAll();
                    owner.validate();
                    owner.revalidate();
                    owner.repaint();
                    waitting = false;
                }
            });
            waitThread.start();
        }else{
            pressing = true;
        }
    }
    
    private void startSpinners() {
        speedSpinner.setValue(options.getSpeed());
        speedSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                speedSpinner.setValue(options.setSpeed((int) speedSpinner.getValue()));
                exLoop.setSpeed(options.getSpeed());
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
        addKeyButton();
        
        addColorButton();
        MultiplayerOptionPanel this2 = this;
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (options.getPlayers().size() >= 2) {
                    exLoop.endGame();
                    owner.setUp(PrincipalFrame.SETUP_SNAKE_GAME, options);
                }else if (options.getPlayers().size() == 1) {
                    JOptionPane.showMessageDialog(this2, "Cambiando a modo un jugador");
                    exLoop.endGame();
                    owner.setUp(PrincipalFrame.SETUP_INDIVIDUAL_OPTIONS, options);
                }else{
                    JOptionPane.showMessageDialog(this2, "Añade al menos un jugador");
                }
            }
        });
        acceptButton.setBackground(Color.WHITE);
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exLoop.endGame();
                owner.setUp(PrincipalFrame.SETUP_MENU);
            }
        });
        cancelButton.setBackground(Color.WHITE);
        
        addPlayerButton.setPreferredSize(new Dimension(150, 50));
        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayerAction();
            }
        });
        addPlayerButton.setBackground(Color.WHITE);
        
    }
    
    private void addPlayerAction(){
        options.addPlayers(new Player());
        
        addKeyButton();
        
        addColorButton();
        
        setUpComponents();
        owner.revalidate();
        owner.validate();
    }

    private void addColorButton(){
        int i = options.getPlayers().size() - 1;
        Player actualPlayer = options.getPlayers().get(i);
        
        JButton colorActualButton;
        colorActualButton = new JButton();
        colorActualButton.setBackground(actualPlayer.getColor());
        colorActualButton.setPreferredSize(new Dimension(70, 25));
        colorActualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ColorSelecter(owner, actualPlayer);
                colorActualButton.setBackground(actualPlayer.getColor());
            }   
        });
        colorButtons.add(colorActualButton);
    }
    
    private void addKeyButton() {
        int i = options.getPlayers().size() - 1;
        
        Player actualPlayer = options.getPlayers().get(i);
        
        JButton keysActualButton = new JButton(options.getPlayers().get(i).keysToString());
        keysActualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new KeysSelecter(owner, actualPlayer);
                keysActualButton.setText(actualPlayer.keysToString());
            }
        });
        keysActualButton.setBackground(Color.WHITE);
        keysButtons.add(keysActualButton);
    }
    
}
