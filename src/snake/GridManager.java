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
    
    int queueNextDirection = -1;
    
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
        
        gridOfGrids.updateAllPanels();
        
        moveSnake(true);
        moveSnake(true);
        moveSnake(true);
        moveSnake(true);
        moveSnake(true);
        moveSnake(true);
        moveSnake(true);
        moveSnake(true);
        moveSnake(true);
        moveSnake(true);
        
        Thread tickThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 2;
                while(true){
                    try {
                        if (i == 2) {
                            i = 0;
                        }else{
                            i++;
                        }
                        Thread.sleep(50L);
                        tick(i);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GridManager.class.getName()).log(Level.SEVERE, null, ex);
                        break;
                    }
                    
                }
            }
        });
        tickThread.start();
    }
    
    private void tick(int tick){
        if (tick == 1) {
            moveSnake(false);
        }else{
//            moveSnake(true);
        }
    }
    
    private void moveSnake(boolean doBigger){
        
        
        Pixel nextPixel = gridOfGrids.getNextPixel(headPosition, nextDirection);
        nextPixel.setDirection(nextDirection);
        Position prvsHeadPosition = new Position(headPosition.getX(), headPosition.getY());
        gridOfGrids.getGridChunk(nextPixel.getChunkPosition()).setOneUpdatable();
        if (nextPixel.getState() == Pixel.APPLE_STATE) {
            gridOfGrids.setApple();
        }else if (!doBigger){
            gridOfGrids.deleteTail(prvsHeadPosition);
        }
        gridOfGrids.paintPixel(nextPixel);
        headPosition = nextPixel.getPosition();
        gridOfGrids.updatePanels();
        gridOfGrids.revalidate();
        
        tryToReadQueueDirection();
    }
    private void tryToReadQueueDirection(){
        if (queueNextDirection != -1) {
            System.out.println(Pixel.positionToString(queueNextDirection));
            if(setNextDirection(queueNextDirection)){
                queueNextDirection = -1;
            }
        }
    }
    private void spawnHead(){
        headPosition = getRandomHeadPosition();
        Pixel spawnPixel = gridOfGrids.getPixel(headPosition);
        spawnPixel.setDirection(Pixel.randomDirection());
        Position chunkPosition = spawnPixel.getChunkPosition();
        gridOfGrids.getGridChunk(chunkPosition).incrementUpdatable();
        nextDirection = spawnPixel.getDirection();
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
                
//                System.out.println(Pixel.positionToString(prvsHeadOppositeDirection));
                if (key == 'a') {
                    setNextDirection(Pixel.LEFT_DIRECTION);
                }else if (key == 'w') {
                    setNextDirection(Pixel.UP_DIRECTION);
                }else if (key == 's') {
                    setNextDirection(Pixel.DOWN_DIRECTION);
                }else if (key == 'd') {
                    setNextDirection(Pixel.RIGHT_DIRECTION);
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
    public boolean setNextDirection(int direction){
        int oppositeHeadDirection = gridOfGrids.getPixel(headPosition).getOppositeDirection();
        Pixel prvsHeadPosition = gridOfGrids.getNextPixel(headPosition, oppositeHeadDirection);
        int prvsHeadOppositeDirection = prvsHeadPosition.getOppositeDirection();
        if (direction == Pixel.LEFT_DIRECTION || direction == Pixel.RIGHT_DIRECTION || direction == Pixel.DOWN_DIRECTION || direction == Pixel.UP_DIRECTION) {
            if (prvsHeadOppositeDirection != direction) {
                nextDirection = direction;
                return true;
            }else{
                queueNextDirection = direction;
                return false;
            }
        }else{
            Logger.getLogger(GridManager.class.getName()).log(Level.SEVERE, null, new Exception());
            return false;
        }
    }
}
