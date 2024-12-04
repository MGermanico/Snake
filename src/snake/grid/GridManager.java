/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.grid;

import java.awt.Color;
import snake.grid.SnakeManager;
import snake.grid.GridOfGrids;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import snake.grid.gridObjects.Player;
import snake.grid.gridObjects.Position;

/**
 *
 * @author migue
 */
public class GridManager {

    LoopThread gameThread;
    ExampleLoopThread exampleGameThread;
    
    GridOfGrids gridOfGrids;

    ArrayList<SnakeManager> snakeManagerList = new ArrayList<>();

    public GridManager(int x, int y, int area, ArrayList<Player> players) {
        gridOfGrids = new GridOfGrids(x, y, area);
        SnakeManager actualSnakeManager;
        for (Player player : players) {
            actualSnakeManager = new SnakeManager(this, player);
            snakeManagerList.add(actualSnakeManager);
        }
    }
    
    public JPanel getSizePanel(){
        gridOfGrids.updateSizePanels();
        gridOfGrids.revalidate();
        return gridOfGrids;
    }

    public JPanel getPanel() {
        gridOfGrids.updatePanels();
        gridOfGrids.revalidate();
        return gridOfGrids;
    }

    public void startGame(int nManzanas) {
        gameThread = new LoopThread(this, nManzanas);
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
            snakeManager.setInitialSize(3);
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
    
    public void endGame() {
        gameThread.endGame();
    }

    public void updateGridOfGrids() {
        gridOfGrids.updatePanels();
        gridOfGrids.revalidate();
    }
    
    public Set<Position> getHeadsPositions(){
        HashSet<Position> headsPositions = new HashSet<>();
        for (SnakeManager snakeManager : snakeManagerList) {
            if (snakeManager.headPosition != null) {
                headsPositions.add(snakeManager.headPosition);
            }
        }
        return headsPositions;
    }

}
