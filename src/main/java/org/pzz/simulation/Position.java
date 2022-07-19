package org.pzz.simulation;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao, StudentID:1043915
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
     * Using pre Java 12 version of switch case syntax
     * @param direction
     * @return position with updated value
     */
    public void nextPosition(Direction direction) {
        switch (direction) {
            case NORTH:
                this.yCoordinate++;
                break;
            case EAST:
                this.xCoordinate++;
                break;
            case SOUTH:
                this.yCoordinate--;
                break;
            case WEST:
                this.xCoordinate--;
                break;
            default:
                break;
        }
    }

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
}
