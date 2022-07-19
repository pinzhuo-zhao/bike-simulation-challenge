package org.pzz.simulation;

import org.pzz.ex.CommandInvalidException;

import java.util.Arrays;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao, StudentID:1043915
 * @create: 2022-07-19 01:07
 **/
public enum Direction {
    /**
     *
     */
    NORTH, EAST, SOUTH, WEST;

    public Direction nextLeftDirection() {
        int index  = this.ordinal();
        Direction[] directions =  Direction.values();
        if (index == 0) {
            return directions[directions.length - 1];
        } else {
            return directions[index - 1];
        }
    }

    public Direction nextRightDirection() {
        int index  = this.ordinal();
        Direction[] directions =  Direction.values();
        if (index == directions.length - 1) {
            return directions[0];
        } else {
            return directions[index + 1];
        }
    }


    public static Direction getDirection(String string) throws CommandInvalidException {
        for (Direction direction : Direction.values()) {
            if (direction.name().equals(string)) {
                return direction;
            }
        }
        return null;
    }



}
