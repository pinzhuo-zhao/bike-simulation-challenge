package org.pzz.entity;
/**
 * @program: bike-simulation-challenge
 * @description: Enumeration for all directions
 * @author: Pinzhuo Zhao
 * @create: 2022-07-19 01:07
 **/
public enum Direction {
    /**
     * All heading directions
     */
    NORTH, EAST, SOUTH, WEST;

    /**
     * get the next heading direction after calling TURN_LEFT
     * @return
     */
    public Direction nextLeftDirection() {
        int index  = this.ordinal();
        Direction[] directions =  Direction.values();
        return index == 0 ? directions[directions.length - 1] : directions[index - 1];
    }
    /**
     * get the next heading direction after calling TURN_RIGHT
     * @return
     */
    public Direction nextRightDirection() {
        int index  = this.ordinal();
        Direction[] directions =  Direction.values();
        return index == directions.length - 1 ? directions[0] : directions[index + 1];
    }

    /**
     *
     * @param string
     * @return the Direction has the same value as the given String
     */
    public static Direction getDirection(String string) {
        for (Direction direction : Direction.values()) {
            if (direction.name().equals(string)) {
                return direction;
            }
        }
        return null;
    }



}
