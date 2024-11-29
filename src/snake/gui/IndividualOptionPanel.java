/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import snake.grid.GridManager;

/**
 *
 * @author tarde
 */
public class IndividualOptionPanel extends JPanel{

    Options options = new Options();
    PrincipalFrame owner;
    
    JPanel back = new JPanel();
    JPanel exampleBack = new JPanel();
    
    JSpinner xSizeSpinner = new JSpinner();
    
    public IndividualOptionPanel(PrincipalFrame owner) {
        this.owner = owner;
        startSpinners();
        back.add(exampleBack);
        this.add(back);
        updateExample();
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        owner.validate();
                        owner.revalidate();
                        owner.repaint();
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(IndividualOptionPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        hilo.start();
    }

    private void updateExample() {
        exampleBack.removeAll();
        GridManager exampleGrid = new GridManager(options.getxPixelSize(), options.getyPixelSize(), options.getDiagonalSize(), 0);
        exampleGrid.startGame();
        exampleGrid.endGame();
        exampleBack.add(exampleGrid.getPanel());
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
    }
    
    
    
}
