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
}
