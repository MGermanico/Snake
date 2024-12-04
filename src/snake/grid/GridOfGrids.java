/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake.grid;

import snake.grid.Grid;
import snake.utils.Utils;
import snake.grid.gridObjects.Position;
import snake.grid.gridObjects.Pixel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import snake.grid.gridObjects.Player;

/**
 *
 * @author tarde
 */
public class GridOfGrids extends JPanel{
    JPanel gridOfGridsPanel;
    Grid[][] gridOfGrids;
    
    int width;
    int height;
    
    int xPixel;
    int yPixel;
    
    int xChunk;
    int yChunk;

    public GridOfGrids(int xPixel, int yPixel, int diagonalSize) {
        this.xPixel = xPixel;
        this.yPixel = yPixel;
        double pixelSize = getPixelSize(diagonalSize);
        width = (int) (pixelSize*xPixel);
        height = (int) (pixelSize*yPixel);
        initGrids(pixelSize);
        this.add(gridOfGridsPanel);
    }
    
    private void initGrids(double pixelSize){
        int lastXSize = xPixel%16;
        int lastYSize = yPixel%16;
        if (lastXSize == 0) {
            xChunk = (int)(xPixel/16.0);
        }else{
            xChunk = Math.ceilDiv(xPixel, 16);
        }
//        System.out.println("xCHUNK: " + xChunk);
        if (lastYSize == 0) {
            yChunk = (int)(yPixel/16.0);
        }else{
            yChunk = Math.ceilDiv(yPixel, 16);
        }
        gridOfGridsPanel = new JPanel(new GridLayout(xChunk, yChunk));
//        System.out.println("yCHUNK: " + yChunk);
        gridOfGrids = new Grid[xChunk][yChunk];
        int xSize;
        int ySize;
        int xCounter = 0;
        int yCounter = 0;
        for (int xActualChunk = 0; xActualChunk < gridOfGrids.length; xActualChunk++) {
            for (int yActualChunk = 0; yActualChunk < gridOfGrids[xActualChunk].length; yActualChunk++) {
                if (xActualChunk == gridOfGrids.length - 1 && lastXSize != 0) {
                    xSize = lastXSize;
                }else{
                    xSize = 16;
                }
                if (yActualChunk == gridOfGrids[xActualChunk].length - 1 && lastYSize != 0) {
                    ySize = lastYSize;
                }else{
                    ySize = 16;
                }
//                System.out.println("yc"+yCounter+"xc"+xCounter);
                gridOfGrids[xActualChunk][yActualChunk] = new Grid(xSize, ySize, xCounter, yCounter, pixelSize);
                gridOfGridsPanel.add(gridOfGrids[xActualChunk][yActualChunk]);
                yCounter += 16;
            }
            yCounter = 0;
            xCounter += 16;
        }
    }
    
    private double getPixelSize(int a) {
        System.out.println(Math.sqrt(a*10000.0/(xPixel*yPixel)));
        return Math.sqrt(a*10000.0/(xPixel*yPixel));
    }
    public void updateAllPanels(){
        int i = 0;
        for (Grid[] gridOfGrid : gridOfGrids) {
            for (Grid grid : gridOfGrid) {
                if (gridOfGridsPanel.getComponentCount() > i) {
                    gridOfGridsPanel.remove(i);
                    gridOfGridsPanel.add(grid.getPanel(), i);
                    i++;
                }
            }
        }
    }
    void updatePanels() {
//        gridOfGridsPanel.removeAll();
//        gridOfGridsPanel.setPreferredSize(new Dimension(1000, 1000));
        int i = 0;
        JPanel panelNoUp;
        for (Grid[] gridOfGrid : gridOfGrids) {
            for (Grid grid : gridOfGrid) {
                if (grid.isUpdatable() || grid.isOneUpdate()) {
//                    System.out.println("update");
                    gridOfGridsPanel.remove(i);
                    gridOfGridsPanel.add(grid.getPanel(), i);
                    if (grid.isOneUpdate()) {
//                        System.out.println("last update");
                        grid.incrementUpdatable();
                    }
                }else{
//                    gridOfGridsPanel.remove(i);
//                    gridOfGridsPanel.add(grid.getNonUpdatablePanel(), i);
                }
                i++;
            }
        }
    }

    public Grid getGridChunk(Position chunkPosition){
//        System.out.println(gridOfGrids[chunkPosition.getX()][chunkPosition.getY()]);
        return gridOfGrids[chunkPosition.getX()][chunkPosition.getY()];
    }
    
    public void setApple(){
        Position randomPos;
        Pixel applePixel;
        boolean correctPosition = true;
        do {
            randomPos = Utils.randomPosition(xPixel, yPixel);
            applePixel = getPixel(randomPos);
            if (applePixel.getState() == Pixel.OFF_STATE) {
                correctPosition = false;
                applePixel.setState(Pixel.APPLE_STATE);
                getGridChunk(Utils.getChunkByPixelPosition(randomPos)).setOneUpdatable();
            }
        } while (correctPosition);
    }

    Pixel getNextPixel(Position position, int direction) {
        int xOffSet = 0;
        int yOffSet = 0;
        if(direction == Pixel.RIGHT_DIRECTION)xOffSet = 1;
        if(direction == Pixel.LEFT_DIRECTION)xOffSet = -1;
        if(direction == Pixel.DOWN_DIRECTION)yOffSet = 1;
        if(direction == Pixel.UP_DIRECTION)yOffSet = -1;
        return getPixelWithOffset(position, xOffSet, yOffSet);
    }
    
    public Pixel getPixelWithOffset(Position position, int xOffset, int yOffset){
        return getPixel(new Position(position.getX() + yOffset, position.getY() + xOffset));
    }
    
    void deleteTail(Position position) {
        Pixel pixel = getPixel(position);
        Pixel nextPixel = getNextPixel(position, pixel.getOppositeDirection());
        if (nextPixel.getState() == Pixel.SNAKE_STATE) {
            deleteTail(nextPixel.getPosition());
        }else{
            this.getGridChunk(pixel.getChunkPosition()).setOneUpdatable();
            pixel.resetPixel();
        }
    }

    public void paintSnakePixel(Pixel pixel, Player player) {
        pixel.setSnake(player);
    }

    Pixel getPixel(Position position) {
        int xChunk = (int)(position.getX()/16.0);
        int yChunk = (int)(position.getY()/16.0);
//        System.out.println(position + " xChunk " + xChunk + " yChunk" + yChunk);
//        System.out.println(gridOfGrids.length);
        return gridOfGrids[xChunk][yChunk].getPixel(position);
    }

    public void paintSnakePixel(Position position, Player player) {
        paintSnakePixel(getPixel(position), player);
    }

    void reset() {
        for (Grid[] gridOfGrid : gridOfGrids) {
            for (Grid grid : gridOfGrid) {
                grid.reset();
            }
        }
    }

    void updateSizePanels() {
        int i = 0;
        JPanel panelNoUp;
        for (Grid[] gridOfGrid : gridOfGrids) {
            for (Grid grid : gridOfGrid) {
//                    System.out.println("update");
                if (grid.isUpdatable() || grid.isOneUpdate()) {
                    gridOfGridsPanel.remove(i);
                    gridOfGridsPanel.add(grid.getSizePanel(), i);
                    if (grid.isOneUpdate()) {
//                        System.out.println("last update");
                        grid.incrementUpdatable();
                    }
                }else{
//                    gridOfGridsPanel.remove(i);
//                    gridOfGridsPanel.add(grid.getNonUpdatablePanel(), i);
                }
                    
                
                i++;
            }
        }
    }
    public void updateAllSizePanels(){
        int i = 0;
        JPanel panelNoUp;
        for (Grid[] gridOfGrid : gridOfGrids) {
            for (Grid grid : gridOfGrid) {
//                    System.out.println("update");
                gridOfGridsPanel.remove(i);
                gridOfGridsPanel.add(grid.getSizePanel(), i);
                if (grid.isOneUpdate()) {
//                        System.out.println("last update");
                    grid.incrementUpdatable();
                }
                i++;
            }
        }
    }

    public int getyPixel() {
        return yPixel;
    }

    public int getxPixel() {
        return xPixel;
    }
    
    
}
