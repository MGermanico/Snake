/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.grid;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tarde
 */
public class LoopThread extends Thread{
    
    GridManager gridManager;
    
    boolean end = false;
    
    public LoopThread(GridManager gridManager) {
        this.gridManager = gridManager;
    }
    
    @Override
    public void run() {
        gridManager.spawnHeads();

        gridManager.setApple();

        gridManager.updateAllPanels();

        gridManager.initializeSizes();
        int i = 2;
        while (true) {
            try {
                if (i == 2) {
                    i = 0;
                } else {
                    i++;
                }
                Thread.sleep(20L);
                tick(i);
                if (end) {
                    break;
                }
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
