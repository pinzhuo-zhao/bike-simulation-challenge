package org.pzz.simulation;

import utils.Result;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao, StudentID:1043915
 * @create: 2022-07-19 15:05
 **/
public interface Bike {
    Result place(Position position, Direction direction);
    Result forward();
    Result turnLeft();
    Result turnRight();
    Result gpsReport();


}
