/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.grid.gridObjects;

import java.awt.Color;
import javax.swing.Icon;

/**
 *
 * @author tarde
 */
public class Player {

    private static int incrementId() {
        idCount++;
        return idCount;
    }

    public Player() {
        id_Player = Player.incrementId();
        this.color = new Color((int)(Math.random()*230),(int)(Math.random()*230),(int)(Math.random()*230));
        
        if (id_Player == 0) {
            char[] defaultKeys = {'a','w','s','d'};
            this.keys = defaultKeys;
        }else{
            char[] defaultKeys = {'j','i','k','l'};
            this.keys = defaultKeys;
        }
    }
    
    private static int idCount = -1;
    
    int id_Player;
    int points = 0;
    Color color;
    int initialSize;
    private char[] keys;

    public Player(Color color, char[] keys) {
        id_Player = Player.incrementId();
        this.color = color;
        this.keys = keys;
    }

    public int getId_Player() {
        return id_Player;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Player)obj).getId_Player() == this.getId_Player();
    }

    public void setKeys(char[] keys) {
        this.keys = keys;
    }

    public char[] getKeys() {
        return keys;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "idPlayer = " +  id_Player + " Color = " + color + " : " + points;
    }

    public int setInitialSize(int initialSize) {
        if (initialSize > 0) {
            this.initialSize = initialSize;
        }
        return this.initialSize;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public String keysToString() {
        return "Teclas (" + keys[0] + "," + keys[1] + "," + keys[2] + "," + keys[3] + ")";
    }

    public int getPoints() {
        return points;
    }

    public int setPoints(int points) {
        if (points > 0) {
            this.points = points;
        }
        return this.points;
    }
    
    public void addPoint(){
        this.points++;
    }
}
