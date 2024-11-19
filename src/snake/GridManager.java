/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
public class GridManager{
    GridOfGrids gridOfGrids;

    Position headPosition;
    
    int nextDirection;
    
    public GridManager(int x, int y, int diagonal) {
        gridOfGrids = new GridOfGrids(x, y, diagonal);
    }
    
    public JPanel getPanel(){
        gridOfGrids.updatePanels();
        gridOfGrids.revalidate();
        return gridOfGrids;
    }
    
    public void startGame(){
        gridOfGrids.reset();
        
        spawnHead();
        
        gridOfGrids.setApple();
        
        Thread tickThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(100L);
                        tick();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GridManager.class.getName()).log(Level.SEVERE, null, ex);
                        break;
                    }
                    
                }
            }
        });
        tickThread.start();
    }
    
    private void tick(){
        Pixel nextPixel = gridOfGrids.getNextPixel(headPosition, nextDirection);
        nextPixel.setDirection(nextDirection);
        
        if (nextPixel.getState() == Pixel.APPLE_STATE) {
            gridOfGrids.setApple();
        }else{
            gridOfGrids.deleteTail(headPosition);
        }
        gridOfGrids.paintPixel(nextPixel);
        headPosition = nextPixel.getPosition();
        gridOfGrids.updatePanels();
        gridOfGrids.revalidate();
    }
    
    private void spawnHead(){
        headPosition = getRandomHeadPosition();
        gridOfGrids.getPixel(headPosition).setDirection(Pixel.randomDirection());
        nextDirection = gridOfGrids.getPixel(headPosition).getDirection();
        gridOfGrids.paintPixel(headPosition);
        gridOfGrids.updatePanels();
        gridOfGrids.repaint();
    }

    private Position getRandomHeadPosition() {
        int secureXDistance = (int)(gridOfGrids.xPixel*1.0/4);
        int secureYDistance = (int)(gridOfGrids.yPixel*1.0/4);
        int x = (int) Utils.randomNumber(secureXDistance, gridOfGrids.xPixel - secureXDistance);
        int y = (int) Utils.randomNumber(secureYDistance, gridOfGrids.yPixel - secureYDistance);
        return new Position(x, y);
    }
    
    public KeyListener getKeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();
                if (key == 'a' && nextDirection != Pixel.RIGHT_DIRECTION) {
                    nextDirection = Pixel.LEFT_DIRECTION;
                }else if (key == 'w' && nextDirection != Pixel.DOWN_DIRECTION) {
                    nextDirection = Pixel.UP_DIRECTION;
                }else if (key == 's' && nextDirection != Pixel.UP_DIRECTION) {
                    nextDirection = Pixel.DOWN_DIRECTION;
                }else if (key == 'd' && nextDirection != Pixel.LEFT_DIRECTION) {
                    nextDirection = Pixel.RIGHT_DIRECTION;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
    }
    
}
