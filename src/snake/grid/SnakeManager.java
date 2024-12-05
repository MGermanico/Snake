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
    
    GridManager gridManager;

    Position headPosition = null;
    
    int nextDirection;
    
    int queueNextDirection = -1;
    
    
    public SnakeManager(GridManager gridManager, Player player) {
        this.player = player;
        this.gridManager = gridManager;
    }
    
    public void moveSnake(boolean doBigger){
        try{
            Pixel nextPixel = gridManager.getGridOfGrids().getNextPixel(headPosition, nextDirection);
            
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
        gridManager.getGridOfGrids().getGridChunk(nextPixel.getChunkPosition()).setOneUpdatable();
        if (nextPixel.getState() == Pixel.APPLE_STATE) {
            gridManager.getGridOfGrids().setApple();
            player.addPoint();
            gridManager.score.updatePanel();
        }else if (!doBigger){
            gridManager.getGridOfGrids().deleteTail(prvsHeadPosition);
        }
        gridManager.getGridOfGrids().paintSnakePixel(nextPixel, player);
        headPosition = nextPixel.getPosition();
    }
    private void tryToReadQueueDirection(){
        if (queueNextDirection != -1) {
            System.out.println(Pixel.positionToString(queueNextDirection));
            if(setNextDirection(queueNextDirection)){
                queueNextDirection = -1;
            }else{
            }
        }
    }
    public void spawnHead(){
        headPosition = getRandomHeadPosition();
        Pixel spawnPixel = gridManager.getGridOfGrids().getPixel(headPosition);
        int headDirection = getHeadDirection(headPosition);
        spawnPixel.setDirection(headDirection);
        Position chunkPosition = spawnPixel.getChunkPosition();
        gridManager.getGridOfGrids().getGridChunk(chunkPosition).incrementUpdatable();
        nextDirection = spawnPixel.getDirection();
        gridManager.getGridOfGrids().paintSnakePixel(headPosition, player);
        gridManager.getGridOfGrids().updatePanels();
        gridManager.getGridOfGrids().repaint();
    }
    
    private int getHeadDirection(Position headPosition){
        if (headPosition.getX() > gridManager.getGridOfGrids().xPixel*1.0/2) {
//            System.out.println("izq");
            return Pixel.LEFT_DIRECTION;
        }else{
//            System.out.println("der");
            return Pixel.RIGHT_DIRECTION;
        }
    }

    private Position getRandomHeadPosition() {
        int secureXDistance = (int)(gridManager.getGridOfGrids().xPixel*1.0/5);
        int secureYDistance = (int)(gridManager.getGridOfGrids().yPixel*1.0/3);
        boolean gotX;
        int x;
        int y = (int) Utils.randomNumber(secureYDistance, gridManager.getGridOfGrids().yPixel - secureYDistance);
        do {
            gotX = false;
            x = (int) Utils.randomNumber(secureXDistance, gridManager.getGridOfGrids().xPixel - secureXDistance);
            for (Position headsPosition : gridManager.getHeadsPositions()) {
                if(Utils.distance(x, headsPosition.getX()) < 3)gotX = true;
            }
        } while (gotX);
        return new Position(x, y);
    }
    
    public KeyListener getKeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();
                System.out.println(key);
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
        int oppositeHeadDirection = gridManager.getGridOfGrids().getPixel(headPosition).getOppositeDirection();
        Pixel prvsHeadPosition = gridManager.getGridOfGrids().getNextPixel(headPosition, oppositeHeadDirection);
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
        
        deleteSnake();
    }

    public boolean isAlive() {
        return alive;
    }

    private void deleteSnake() {
        Pixel beforePixel = gridManager.getGridOfGrids().getPixel(headPosition);
        Player myPlayer = beforePixel.getPlayer();
        
        Position beforePosition;
        int beforeOppositeDirection;
        while(beforePixel.getPlayer() != null){
            if (beforePixel.getPlayer().equals(myPlayer)) {
                beforePosition = beforePixel.getPosition();
                beforeOppositeDirection = beforePixel.getOppositeDirection();
                beforePixel.resetPixel();
                gridManager.getGridOfGrids().getGridChunk(beforePixel.getChunkPosition()).setOneUpdatable();
                
                beforePixel = gridManager.getGridOfGrids().getNextPixel(beforePosition, beforeOppositeDirection);
            }
        }
    }
    
    
}
