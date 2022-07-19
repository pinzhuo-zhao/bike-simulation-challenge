package org.pzz.simulation;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao, StudentID:1043915
 * @create: 2022-07-19 01:06
 **/
public class Grid {
    private int length;
    private int width;
    private Bike bike;

    private boolean isLegalPosition(Position position) {
        int xCoordinate = position.getXCoordinate();
        int yCoordinate = position.getYCoordinate();
        return (xCoordinate >= 0 && xCoordinate <= length && yCoordinate >= 0 && yCoordinate <= width);
    }

    public Grid() {
    }

    public Grid(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public Grid(int length, int width, Bike bike) {
        this.length = length;
        this.width = width;
        this.bike = bike;
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

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }
}
