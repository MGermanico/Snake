/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.grid.gridObjects;

import java.awt.Color;

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
        this.color = Color.BLACK;
        char[] defaultKeys = {'a','w','s','d'};
        this.keys = defaultKeys;
    }
    
    private static int idCount = -1;
    
    int id_Player;
    Color color;
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
        return "idPlayer = " +  id_Player + "";
    }
    
    
}
