/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.grid;

import snake.grid.SnakeManager;
import snake.grid.GridOfGrids;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
public class GridManager {

    LoopThread gameThread;
    
    GridOfGrids gridOfGrids;

    ArrayList<SnakeManager> snakeManagerList = new ArrayList<>();

    public GridManager(int x, int y, int diagonal, int nPlayers) {
        gridOfGrids = new GridOfGrids(x, y, diagonal);
        SnakeManager actualSnakeManager;
        char[] keys;
        for (int i = 0; i < nPlayers; i++) {
            keys = new char[4];
            if (i == 0) {
                keys[0] = 'a';
                keys[1] = 'w';
                keys[2] = 's';
                keys[3] = 'd';
            } else {
                keys[0] = 'j';
                keys[1] = 'i';
                keys[2] = 'k';
                keys[3] = 'l';
            }
            actualSnakeManager = new SnakeManager(this, keys);
            snakeManagerList.add(actualSnakeManager);
        }
    }

    public JPanel getPanel() {
        gridOfGrids.updatePanels();
        gridOfGrids.revalidate();
        return gridOfGrids;
    }

    public void startGame() {
        gameThread = new LoopThread(this);
        gameThread.start();
    }

    public void spawnHeads() {
        for (SnakeManager snakeManager : snakeManagerList) {
            snakeManager.spawnHead();
        }
    }
    
    public void updateAllPanels(){
        gridOfGrids.updateAllPanels();
    }
    
    public void clearGrids(){
        gridOfGrids.reset();
    }
    
    public void setApple(){
        gridOfGrids.setApple();
    }

    public GridOfGrids getGridOfGrids() {
        return gridOfGrids;
    }

    public ArrayList<KeyListener> getKeyListeners() {
        ArrayList<KeyListener> keyListeners = new ArrayList<>();
        for (SnakeManager snakeManager : snakeManagerList) {
            keyListeners.add(snakeManager.getKeyListener());
        }
        return keyListeners;
    }

    public void initializeSizes() {
        for (SnakeManager snakeManager : snakeManagerList) {
            snakeManager.setInitialSize(20);
        }
    }
    
    public void moveSnake(boolean doBigger, SnakeManager snakeManager){
        snakeManager.moveSnake(doBigger);
    }

    public void moveSnakes(boolean doBigger) {
        boolean anyAlive = false;
        for (SnakeManager snakeManager : snakeManagerList) {
            if (snakeManager.isAlive()) {
                anyAlive = true;
                moveSnake(doBigger, snakeManager);
            }
        }
        if (!anyAlive) {
            endGame();
        }
    }

    private void endGame() {
        gameThread.endGame();
    }

    public void updateGridOfGrids() {
        gridOfGrids.updatePanels();
        gridOfGrids.revalidate();
    }

}
