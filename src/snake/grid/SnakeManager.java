/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.grid;

import snake.grid.GridOfGrids;
import snake.utils.Utils;
import snake.grid.gridObjects.Position;
import snake.grid.gridObjects.Pixel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import snake.grid.gridObjects.Player;

/**
 *
 * @author migue
 */
public class SnakeManager{
    Player player;
    
    private boolean alive = true;
    
    GridOfGrids gridOfGrids;

    Position headPosition;
    
    int nextDirection;
    
    int queueNextDirection = -1;
    
    
    public SnakeManager(GridManager gridManager, Player player) {
        this.player = player;
        gridOfGrids = gridManager.getGridOfGrids();
    }
    
    public void moveSnake(boolean doBigger){
        try{
            Pixel nextPixel = gridOfGrids.getNextPixel(headPosition, nextDirection);
            
            if (!nextPixel.isAlreadyAnyOne()) {
                moveHead(nextPixel, doBigger);
            }else{
                this.endLife();
            }

            tryToReadQueueDirection();
        }catch(Exception ex){
            endLife();
        }
    }
    private void moveHead(Pixel nextPixel, boolean doBigger){
        nextPixel.setDirection(nextDirection);
        Position prvsHeadPosition = new Position(headPosition.getX(), headPosition.getY());
        gridOfGrids.getGridChunk(nextPixel.getChunkPosition()).setOneUpdatable();
        if (nextPixel.getState() == Pixel.APPLE_STATE) {
            gridOfGrids.setApple();
        }else if (!doBigger){
            gridOfGrids.deleteTail(prvsHeadPosition);
        }
        gridOfGrids.paintSnakePixel(nextPixel, player);
        headPosition = nextPixel.getPosition();
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
        gridOfGrids.paintSnakePixel(headPosition, player);
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
                if (key == player.getKeys()[0]) {
                    setNextDirection(Pixel.LEFT_DIRECTION);
                }else if (key == player.getKeys()[1]) {
                    setNextDirection(Pixel.UP_DIRECTION);
                }else if (key == player.getKeys()[2]) {
                    setNextDirection(Pixel.DOWN_DIRECTION);
                }else if (key == player.getKeys()[3]) {
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
        System.out.println(Pixel.positionToString(direction));
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
        Pixel headPixel = gridOfGrids.getPixel(headPosition);
        
        Pixel beforePixel = headPixel;
        Position beforePosition;
        int beforeOppositeDirection;
        while(beforePixel.getPlayer() != null){
            if (beforePixel.getPlayer().equals(headPixel.getPlayer())) {
                beforePosition = beforePixel.getPosition();
                beforeOppositeDirection = beforePixel.getOppositeDirection();
                beforePixel.resetPixel();
                beforePixel = gridOfGrids.getNextPixel(beforePosition, beforeOppositeDirection);
            }
        }
    }

    public boolean isAlive() {
        return alive;
    }
    
    
}
