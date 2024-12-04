/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.gui;

import java.util.ArrayList;
import snake.grid.gridObjects.Player;

/**
 *
 * @author tarde
 */
public class Options {
    ArrayList<Player> players = new ArrayList<>();
    private int xPixelSize = 40;
    private int yPixelSize = 70;
    private int diagonalSize = 20;
    private int nManzanas = 1;
    private int speed = 10;

    public Options() {
    }

    public int setxPixelSize(int xPixelSize) {
        if (xPixelSize > 8) {
            this.xPixelSize = xPixelSize;
        }
        return this.xPixelSize;
    }

    public int setyPixelSize(int yPixelSize) {
        if (yPixelSize > 8) {
            this.yPixelSize = yPixelSize;
        }
        return this.yPixelSize;
    }

    public int setDiagonalSize(int diagonalSize) {
        if (diagonalSize > 5) {
            this.diagonalSize = diagonalSize;
        }
        return this.diagonalSize;
    }

    public int getxPixelSize() {
        return xPixelSize;
    }

    public int getyPixelSize() {
        return yPixelSize;
    }

    public int getDiagonalSize() {
        return diagonalSize;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void addPlayers(Player player) {
        this.players.add(player);
    }

    public int getnManzanas() {
        return nManzanas;
    }

    public int setnManzanas(int nManzanas) {
        if (nManzanas > 5) {
            this.nManzanas = nManzanas;
        }
        return this.nManzanas;
    }

    public int getSpeed() {
        return speed;
    }

    public int setSpeed(int speed) {
        if (speed > 2 && speed <= 30) {
            this.speed = speed;
        }
        return this.speed;
    }
    
    
}
