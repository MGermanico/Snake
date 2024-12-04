/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.utils;

import snake.grid.gridObjects.Position;

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
    public static int distance(int n1, int n2){
        return Math.abs(n2 - n1);
    }
    public static double distance(double n1, double n2){
        return Math.abs(n2 - n1);
    }
    public static long speedToMilis(long speed){
        return (long) (((500/(speed+15))-10)*2.5);
    }
}
