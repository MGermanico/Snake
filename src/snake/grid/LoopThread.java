/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.grid;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import snake.gui.Options;
import snake.utils.Utils;

/**
 *
 * @author tarde
 */
public class LoopThread extends Thread{
    
    GridManager gridManager;
    
    boolean end = false;
    
    int nManzanas;
    
    long miliseconds;
    
    public LoopThread(GridManager gridManager, Options options) {
        this.gridManager = gridManager;
        this.miliseconds = Utils.speedToMilis(options.getSpeed());
        this.nManzanas = options.getnManzanas();
    }
    
    @Override
    public void run() {
        gridManager.spawnHeads();

        for (int i = 0; i < nManzanas; i++) {
            gridManager.setApple();
        }

        gridManager.initializeSizes();
        
        gridManager.updateAllPanels();
        
        int i = 2;
        
        while (!end) {
            try {
                if (i == 2) {
                    i = 0;
                } else {
                    i++;
                }
                Thread.sleep(miliseconds);
                tick(i);
            } catch (InterruptedException ex) {
                Logger.getLogger(GridManager.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }

        }
    }
    
    public void tick(int tick) {
        if (tick == 1) {
            gridManager.moveSnakes(false);
        } else {
//            gridManager.moveSnakes(true);
        }
        gridManager.updateGridOfGrids();
    }
    
    public void endGame(){
        this.end = true;
    }
}
