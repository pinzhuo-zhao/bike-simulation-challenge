package org.pzz.entity;

/**
 * @program: bike-simulation-challenge
 * @description: Simulating the grid the bike is on
 * @author: Pinzhuo Zhao
 * @create: 2022-07-19 01:06
 **/
public class Grid {
    /**
     * Length and width of the grid their values
     * will be assigned by user input from the config.properties
     */
    private int length;
    private int width;

    /**
     * Test if a position is legal (not out of bound) on this grid
     * @param position
     * @return
     */
    public boolean isLegalPosition(Position position) {
        int xCoordinate = position.getXCoordinate();
        int yCoordinate = position.getYCoordinate();
        return (xCoordinate >= 0 && xCoordinate <= length && yCoordinate >= 0 && yCoordinate <= width);
    }

    /**
     * Constructors, Setters and Getters
     */
    public Grid() {
    }

    public Grid(int length, int width) {
        if (length <= 0 || width <= 0) {
            throw new IllegalArgumentException("Initial length/width of the grid cannot be lower than 1");
        }
        this.length = length;
        this.width = width;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
