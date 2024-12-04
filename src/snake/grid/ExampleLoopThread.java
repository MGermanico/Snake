/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.grid;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import snake.grid.gridObjects.Player;
import snake.grid.gridObjects.Position;

/**
 *
 * @author tarde
 */
public class ExampleLoopThread extends Thread{
    
    GridManager gridManager;
    
    boolean end = false;
    
    JFrame owner;
    
    public ExampleLoopThread(GridManager gridManager, JFrame owner) {
        this.gridManager = gridManager;
        this.owner = owner;
    }
    
    @Override
    public void run() {
        
        gridManager.getGridOfGrids().updateAllSizePanels();
        
        int i = 2;
        
        while (!end) {
            try {
                if (i == 2) {
                    i = 0;
                } else {
                    i++;
                }
                Thread.sleep(40L);
                tick(i);
            } catch (InterruptedException ex) {
                Logger.getLogger(GridManager.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }

        }
    }
    
    public void tick(int tick) {
        if (tick == 1) {
            gridManager.getSizePanel();
            gridManager.getGridOfGrids().paintSnakePixel(new Position(3, 3), new Player());
            owner.validate();
        owner.revalidate();
        owner.repaint();
        } else {
//            gridManager.moveSnakes(true);
        }
        gridManager.updateGridOfGrids();
    }
    
    public void endGame(){
        this.end = true;
    }
}
