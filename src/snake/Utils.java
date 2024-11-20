/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

/**
 *
 * @author migue
 */
public class Utils {
    public static double randomNumber(double min, double max){
        return Math.random()*(max-min+1)+min;
    }
    public static Position randomPosition(int xSize, int ySize){
        return new Position((int)Utils.randomNumber(1, xSize - 1),(int)Utils.randomNumber(1, ySize - 1));
    }
    public static int getChunkByPixelX(int xPixel){
        return (int)(xPixel/16.0);
    }
    public static int getChunkByPixelY(int yPixel){
        return (int)(yPixel/16.0);
    }
    public static Position getChunkByPixelPosition(Position pixelPos){
        return new Position(getChunkByPixelX(pixelPos.getX()), getChunkByPixelY(pixelPos.getY()));
    }
}
