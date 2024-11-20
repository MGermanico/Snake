/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
public class Pixel {
    
    public static final int OFF_STATE = 0;
    public static final int SNAKE_STATE = 1;
    public static final int APPLE_STATE = 2;
    public static final int WALL_STATE = 3;
    
    
    
    public static final int NONE_DIRECTION = 0;
    public static final int UP_DIRECTION = 1;
    public static final int DOWN_DIRECTION = 2;
    public static final int RIGHT_DIRECTION = 3;
    public static final int LEFT_DIRECTION = 4;
    
    private int state = OFF_STATE;
    
    private int direction;
    
    private Position position;
    
    Position chunkPosition;
    
    int pixelSize;

    public Pixel(Position position, Position chunkPosition , int pixelSize) {
        this.chunkPosition = chunkPosition;
        this.pixelSize = pixelSize;
        this.position = position;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    public JPanel getNonUpdatablePanel(){
        JPanel ret = new JPanel();
        Dimension d = new Dimension(pixelSize, pixelSize);
        ret.setPreferredSize(d);
        if (state == SNAKE_STATE) {
        }else if (state == APPLE_STATE) {
        } else{
            ret.setBackground(Color.blue);
        }
        return ret;
    }
    
    public JPanel getPanel(){
        JPanel ret = new JPanel();
        Dimension d = new Dimension(pixelSize, pixelSize);
        ret.setPreferredSize(d);
        if (state == SNAKE_STATE) {
            ret.setBackground(Color.BLACK);
        }else if (state == APPLE_STATE) {
            ret.setBackground(Color.GREEN);
        } else{
            ret.setBackground(Color.WHITE);
        }
        return ret;
    }
    
    public static int randomDirection(){
        return (int)Utils.randomNumber(1, 4);
    }
    
    public void resetPixel(){
        this.setState(Pixel.OFF_STATE);
        this.setDirection(Pixel.NONE_DIRECTION);
    }

    public int getDirection() {
        return direction;
    }
    
    public int getOppositeDirection(){
        if (direction == Pixel.DOWN_DIRECTION) {
            return Pixel.UP_DIRECTION;
        }else if (direction == Pixel.UP_DIRECTION) {
            return Pixel.DOWN_DIRECTION;
        }else if (direction == Pixel.RIGHT_DIRECTION) {
            return Pixel.LEFT_DIRECTION;
        }else if (direction == Pixel.LEFT_DIRECTION) {
            return Pixel.RIGHT_DIRECTION;
        }else return 0;
    }

    public Position getPosition() {
        return position;
    }

    public Position getChunkPosition() {
        return chunkPosition;
    }
    
    public static String positionToString(int position){
        if (position == Pixel.DOWN_DIRECTION) {
            return "DIRECTION{ABAJO    }";
        }else if (position == Pixel.UP_DIRECTION) {
            return "DIRECTION{ARRIBA   }";
        }else if (position == Pixel.RIGHT_DIRECTION) {
            return "DIRECTION{DERECHA  }";
        }else if (position == Pixel.LEFT_DIRECTION) {
            return "DIRECTION{IZQUIERDA}";
        }else{
            return "DIRECTION{ERROR    }";
        }
    }
}
