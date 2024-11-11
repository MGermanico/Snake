/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
public class Grid extends JPanel{

    private JPanel gridPanel;
    private Pixel[][] grid;
    
    private int width;
    private int height;
    
    private int x;
    private int y;
    
    public Grid(int x, int y, int diagonalSize) {
        this.grid = new Pixel[x][y];
        this.gridPanel = new JPanel(new GridLayout(x, y));
        double pixelSize = getPixelSize(diagonalSize);
        this.x = x;
        this.y = y;
        width = (int) (pixelSize*x);
        height = (int) (pixelSize*y);
        initGrid(pixelSize);
        updatePanel();
        this.add(gridPanel);
    }

    public void paintPixel(Position position){
        grid[position.getX()][position.getY()].setState(Pixel.SNAKE_STATE);
    }
    public void paintPixel(Pixel pixel){
        System.out.println("pintar ");
        pixel.setState(Pixel.SNAKE_STATE);
    }
    
    public Pixel getPixel(Position position){
        return grid[position.getX()][position.getY()];
    }
    public Pixel getPixel(int x, int y){
        return grid[x][y];
    }
    
    private void initGrid(double pixelWidthSize) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                grid[x][y] = new Pixel(new Position(x, y), (int) pixelWidthSize);
            }
        }
    }
    
    public void updatePanel(){
        gridPanel.removeAll();
        for (int width = 0; width < grid.length; width++) {
            for (int height = 0; height < grid[width].length; height++) {
                gridPanel.add(getPixel(width, height).getPanel());
            }
        }
    }

    private double getPixelSize(int d) {
        int x = grid.length;
        int y = grid[0].length;
        double alfa = Math.atan(y*1.0/x);
        double h = Math.sin(alfa);
        double ret = (d*h)/y;
//        System.out.println("x: " + x + " , y: " + y + " , d: " + d);
//        System.out.printf("\n"
//                + "%d*sin(atan(%d/%d))/%d"
//                + "\n\n",d, y, x, y);
//        System.out.println(alfa);
//        System.out.println(h);
//        System.out.println(ret);
        return ret+5;
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
        System.out.println(xOffset + " , " + yOffset);
        System.out.println("x: " + (position.getX() + xOffset) + " y: " + (position.getY() + yOffset));
        return grid[position.getX() + yOffset][position.getY() + xOffset];
    }

    public void deleteTail(Position position){
        Pixel pixel = getPixel(position);
        Pixel nextPixel = getNextPixel(position, pixel.getOppositeDirection());
        System.out.println(nextPixel.getPosition());
        if (nextPixel.getState() == Pixel.SNAKE_STATE) {
            deleteTail(nextPixel.getPosition());
        }else{
            pixel.resetPixel();
        }
    }
    
    public void setApple(){
        grid[(int)Utils.randomNumber(1, x - 1)][(int)Utils.randomNumber(1, y - 1)].setState(Pixel.APPLE_STATE);
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
    
}
