/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.grid;

import java.awt.Color;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import snake.grid.gridObjects.Pixel;
import snake.grid.gridObjects.Player;
import snake.grid.gridObjects.Position;
import snake.gui.IndividualOptionPanel;
import snake.gui.MultiplayerOptionPanel;
import snake.utils.Utils;

/**
 *
 * @author tarde
 */
public class ExampleLoopThread extends Thread{
    
    GridManager gridManager;
    
    boolean end = false;
    boolean updateAll = false;
    
    long miliseconds = 40L;
    
    IndividualOptionPanel indivOwner;
    MultiplayerOptionPanel multiplayerOwner;
    
    public ExampleLoopThread(GridManager gridManager, IndividualOptionPanel owner) {
        this.gridManager = gridManager;
        this.indivOwner = owner;
    }
    
    public ExampleLoopThread(GridManager gridManager, MultiplayerOptionPanel owner) {
        this.gridManager = gridManager;
        this.multiplayerOwner = owner;
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
                Thread.sleep(miliseconds);
                if (updateAll) {
                    gridManager.getGridOfGrids().updateAllSizePanels();
                    updateAll = false;
                }else{
                    tick(i);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(GridManager.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }

        }
    }
    
    static int movedCount = 0;
    
    public synchronized void waitMilis(long milis){
        try {
            Thread.sleep(milis);
        } catch (InterruptedException ex) {
            Logger.getLogger(ExampleLoopThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void tick(int tick) {
        if (tick == 1) {
            
            GridOfGrids gridOfGrids = gridManager.getGridOfGrids();
            int halfHeight = gridOfGrids.getxPixel()/2;
            
            setNextPosition(halfHeight);
            
            deletepreviousPosition(halfHeight);
            
            endOfMapComprobator(halfHeight);
            
            if (indivOwner != null) {
                indivOwner.updateExample(gridManager.getSizePanel());
                indivOwner.owner.validate();
                indivOwner.owner.revalidate();
                indivOwner.owner.repaint();
            }else{
                multiplayerOwner.updateExample(gridManager.getSizePanel());
                multiplayerOwner.owner.validate();
                multiplayerOwner.owner.revalidate();
                multiplayerOwner.owner.repaint();
            }
        } else {
//            gridManager.moveSnakes(true);
        }
        gridManager.updateGridOfGrids();
    }
    
    public synchronized void endGame(){
        this.end = true;
    }
    
    public synchronized void updateAll(){
        this.updateAll = true;
    }

    private void setNextPosition(int halfHeight) {
        Position posToUpdate;
        Pixel pixelToUpdate;
        GridOfGrids gridOfGrids = gridManager.getGridOfGrids();
        try{
            posToUpdate = new Position(halfHeight, movedCount);
            pixelToUpdate = gridOfGrids.getPixel(posToUpdate);
            pixelToUpdate.setState(Pixel.RED_STATE);


            gridOfGrids.getGridChunk(pixelToUpdate.getChunkPosition()).setOneUpdatable();
        }catch(IndexOutOfBoundsException ex){
            //controlado
        }
    }

    private void deletepreviousPosition(int halfHeight) {
        Position posToUpdate;
        Pixel pixelToUpdate;
        GridOfGrids gridOfGrids = gridManager.getGridOfGrids();
        try{
            posToUpdate = new Position(halfHeight, movedCount - 5);
            pixelToUpdate = gridOfGrids.getPixel(posToUpdate);
            pixelToUpdate.setState(Pixel.OFF_STATE);
            gridOfGrids.getGridChunk(pixelToUpdate.getChunkPosition()).setOneUpdatable();
        }catch(IndexOutOfBoundsException ex){
            //controlado
        }
    }

    private void endOfMapComprobator(int halfHeight) {
        GridOfGrids gridOfGrids = gridManager.getGridOfGrids();
        if (movedCount < gridOfGrids.getyPixel() - 1) {
            movedCount++;
        }else{
            deleteSnake(halfHeight);
            movedCount = 0;
        }
    }

    private void deleteSnake(int halfHeight) {
        GridOfGrids gridOfGrids = gridManager.getGridOfGrids();
        Position posToUpdate;
        Pixel pixelToUpdate;
        for (int i = 0; i < 10; i++) {
            try{
                posToUpdate = new Position(halfHeight, movedCount - i);
                pixelToUpdate = gridOfGrids.getPixel(posToUpdate);
                pixelToUpdate.setState(Pixel.OFF_STATE);
                gridOfGrids.getGridChunk(pixelToUpdate.getChunkPosition()).setOneUpdatable();
            }catch(IndexOutOfBoundsException ex){
                //controlado
            }
            
        }
    }

    public synchronized void setGridManager(GridManager gridManager) {
        this.gridManager = gridManager;
    }

    public synchronized void setMiliseconds(long miliseconds) {
        this.miliseconds = miliseconds;
    }
    
    public synchronized void setSpeed(long speed) {
        this.miliseconds = Utils.speedToMilis(speed); //  1 = 1/10 = 0,1 ,, 2 = 1/20 = 0,05
    }
    
}
