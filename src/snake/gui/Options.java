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
    private int yPixelSize = 40;
    private int diagonalSize = 20;
    private int initialSnakeSize = 3;

    public Options() {
    }

    public int setxPixelSize(int xPixelSize) {
        if (xPixelSize > 0) {
            this.xPixelSize = xPixelSize;
        }
        return this.xPixelSize;
    }

    public int setyPixelSize(int yPixelSize) {
        if (yPixelSize > 0) {
            this.yPixelSize = yPixelSize;
        }
        return this.yPixelSize;
    }

    public int setDiagonalSize(int diagonalSize) {
        if (diagonalSize > 0) {
            this.diagonalSize = diagonalSize;
        }
        return this.diagonalSize;
    }

    public int setInitialSnakeSize(int initialSnakeSize) {
        if (initialSnakeSize > 0) {
            this.initialSnakeSize = initialSnakeSize;
        }
        return this.initialSnakeSize;
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

    public int getInitialSnakeSize() {
        return initialSnakeSize;
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
    
    
}
