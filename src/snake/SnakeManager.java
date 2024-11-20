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
public class SnakeManager{
    private boolean alive = true;
    
    GridOfGrids gridOfGrids;

    Position headPosition;
    
    int nextDirection;
    
    int queueNextDirection = -1;
    
    char keys[];
    
    public SnakeManager(GridManager gridManager, char keys[]) {
        gridOfGrids = gridManager.getGridOfGrids();
        this.keys = keys;
    }
    
    public void moveSnake(boolean doBigger){
        try{
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

            tryToReadQueueDirection();
        }catch(Exception ex){
            endLife();
        }
    }
    private void tryToReadQueueDirection(){
        if (queueNextDirection != -1) {
            System.out.println(Pixel.positionToString(queueNextDirection));
            if(setNextDirection(queueNextDirection)){
                System.out.println("cambiado");
                queueNextDirection = -1;
            }else{
                System.out.println("no cambiado");
            }
        }
    }
    public void spawnHead(){
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
                System.out.println(key);
//                System.out.println(Pixel.positionToString(prvsHeadOppositeDirection));
                if (key == keys[0]) {
                    setNextDirection(Pixel.LEFT_DIRECTION);
                }else if (key == keys[1]) {
                    setNextDirection(Pixel.UP_DIRECTION);
                }else if (key == keys[2]) {
                    setNextDirection(Pixel.DOWN_DIRECTION);
                }else if (key == keys[3]) {
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
            System.out.println("prvsHoppos: " + Pixel.positionToString(prvsHeadOppositeDirection));
            System.out.println("hOppos: " + Pixel.positionToString(oppositeHeadDirection));
            if (prvsHeadOppositeDirection != direction || (oppositeHeadDirection != direction && prvsHeadOppositeDirection == direction)) {
                nextDirection = direction;
                return true;
            }else{
                queueNextDirection = direction;
                return false;
            }
        }else{
            Logger.getLogger(SnakeManager.class.getName()).log(Level.SEVERE, null, new Exception());
            return false;
        }
    }
    
    public void setInitialSize(int n){
        for (int i = 0; i < n; i++) {
            moveSnake(true);
        }
    }

    private void endLife() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }
    
    
}
