package org.pzz.entity;

import org.pzz.utils.Result;

/**
 * @program: bike-simulation-challenge
 * @description: Interface for bike, setting the rules/methods
 *               a bike implementation should have, could be used
 *               to extend/modify the program further by creating
 *               another implementation of this interface
 * @author: Pinzhuo Zhao
 * @create: 2022-07-19 15:05
 **/
public interface Bike {

    void place(Position position, Direction direction);
    void forward();
    void turnLeft();
    void turnRight();
    Result gpsReport();
    Direction getDirection();
    Position getPosition();

}
