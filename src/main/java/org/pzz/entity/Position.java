package org.pzz.entity;

import java.text.MessageFormat;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao
 * @create: 2022-07-19 01:09
 **/
public class Position {
    private int xCoordinate;
    private int yCoordinate;

    public Position() {
    }

    public Position(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Get the next position of the current position based on bike's direction
     * @param direction: bike's heading direction
     * @return new position
     */
    public Position nextPosition(Direction direction) {
        Position newPosition = new Position(xCoordinate,yCoordinate);
        switch (direction) {
            case NORTH:
                newPosition.yCoordinate++;
                break;
            case EAST:
                newPosition.xCoordinate++;
                break;
            case SOUTH:
                newPosition.yCoordinate--;
                break;
            case WEST:
                newPosition.xCoordinate--;
                break;
            default:
                break;
        }
        return newPosition;
    }

    /**
     * Getters and Setters
     * @return
     */

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Formatted String for returning a position (used when GPS_REPORT command is called)
     * @return
     */
    @Override
    public String toString() {
        String coordinate = "({0},{1})";
        return MessageFormat.format(coordinate,xCoordinate,yCoordinate);

    }
}
