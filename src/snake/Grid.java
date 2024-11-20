/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
public class Grid extends JPanel{

    private int updatable = 0;
    
    private JPanel gridPanel;
    private Pixel[][] grid;
    
    private int width;
    private int height;
    
    private int x;
    private int y;
    
    int xOffset;
    int yOffset;
    
    public Grid(int x, int y, int xOffset, int yOffset, double pixelSize) {
        this.grid = new Pixel[x][y];
        this.gridPanel = new JPanel(new GridLayout(x, y));
        this.x = x;
        this.y = y;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        width = (int) (pixelSize*x);
        height = (int) (pixelSize*y);
        initGrid(pixelSize);
        updatePanel();
        this.add(gridPanel);
    }
    
    public Pixel getPixel(Position position){
//        System.out.println("- " + xOffset);
        return grid[position.getX()-xOffset][position.getY()-yOffset];
    }
    public Pixel getPixel(int x, int y){
        return grid[x][y];
    }
    
    private void initGrid(double pixelWidthSize) {
        Position chunkPosition = new Position(xOffset/16, yOffset/16);
//        System.out.println("::POS " + chunkPosition);
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                grid[x][y] = new Pixel(
                        new Position(x+xOffset, y+yOffset),
                        chunkPosition, 
                        (int) pixelWidthSize);
            }
        }
    }
    
    public JPanel getNonUpdatablePanel(){
        JPanel ret = new JPanel(new GridLayout(16, 16));
        for (int width = 0; width < 16; width++) {
            for (int height = 0; height < 16; height++) {
                if (width < grid.length && height < grid[width].length) {
                    ret.add(getPixel(width, height).getNonUpdatablePanel());
                }else{
                    ret.add(new JLabel());
                }
            }
        }
        return ret;
    }
    
    public JPanel getPanel(){
        JPanel ret = new JPanel(new GridLayout(16, 16));
        for (int width = 0; width < 16; width++) {
            for (int height = 0; height < 16; height++) {
                if (width < grid.length && height < grid[width].length) {
                    ret.add(getPixel(width, height).getPanel());
                }else{
                    ret.add(new JLabel());
                }
            }
        }
        return ret;
    }
    
    public void updatePanel(){
        
        gridPanel.removeAll();
        for (int width = 0; width < grid.length; width++) {
            for (int height = 0; height < grid[width].length; height++) {
                gridPanel.add(getPixel(width, height).getPanel());
            }
        }
    }

    public void reset() {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                getPixel(x, y).resetPixel();
            }
        }
    }
    
    public Pixel getNextPixel(Position position, int direction){
        int xOffSet = 0;
        int yOffSet = 0;
        if(direction == Pixel.RIGHT_DIRECTION)xOffSet = 1;
        if(direction == Pixel.LEFT_DIRECTION)xOffSet = -1;
        if(direction == Pixel.DOWN_DIRECTION)yOffSet = 1;
        if(direction == Pixel.UP_DIRECTION)yOffSet = -1;
        return getPixelWithOffset(position, xOffSet, yOffSet);
    }
    
    public Pixel getPixelWithOffset(Position position, int xOffset, int yOffset){
//        System.out.println(xOffset + " , " + yOffset);
//        System.out.println("x: " + (position.getX() + xOffset) + " y: " + (position.getY() + yOffset));
        return getPixel(position.getX() + yOffset, position.getY() + xOffset);
    }
    
    
    
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getUpdatable() {
        return updatable;
    }

    public void setUpdatable(int updatable) {
        this.updatable = updatable;
    }
    
    public void incrementUpdatable(){
        updatable++;
    }
    
    public void decrementUpdatable(){
        updatable--;
    }
    
    public boolean isUpdatable(){
        return updatable != 0;
    }

    boolean isOneUpdate() {
        return updatable < 0;
    }
    
    void setOneUpdatable(){
        if (updatable < 1) {
            updatable = - 1;
        }
    }
}
