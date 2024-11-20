/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

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

    Thread gameThread;
    
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
        gridOfGrids.reset();

        spawnHeads();

        gridOfGrids.setApple();

        gridOfGrids.updateAllPanels();

        initializeSizes();

        gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 2;
                while (true) {
                    try {
                        if (i == 2) {
                            i = 0;
                        } else {
                            i++;
                        }
                        Thread.sleep(20L);
                        tick(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GridManager.class.getName()).log(Level.SEVERE, null, ex);
                        break;
                    }

                }
            }
        });
        gameThread.start();
    }

    private void tick(int tick) {
        if (tick == 1) {
            moveSnakes();
        } else {
//            moveSnake(true);
        }
        gridOfGrids.updatePanels();
        gridOfGrids.revalidate();
    }

    private void spawnHeads() {
        for (SnakeManager snakeManager : snakeManagerList) {
            snakeManager.spawnHead();
        }
    }

    public GridOfGrids getGridOfGrids() {
        return gridOfGrids;
    }

    ArrayList<KeyListener> getKeyListeners() {
        ArrayList<KeyListener> keyListeners = new ArrayList<>();
        for (SnakeManager snakeManager : snakeManagerList) {
            keyListeners.add(snakeManager.getKeyListener());
        }
        return keyListeners;
    }

    private void initializeSizes() {
        for (SnakeManager snakeManager : snakeManagerList) {
            snakeManager.setInitialSize(15);
        }
    }

    private void moveSnakes() {
        boolean anyAlive = false;
        for (SnakeManager snakeManager : snakeManagerList) {
            if (snakeManager.isAlive()) {
                anyAlive = true;
                snakeManager.moveSnake(false);
            }
        }
        if (!anyAlive) {
            endGame();
        }
    }

    private void endGame() {
        gameThread.interrupt();
    }

}
