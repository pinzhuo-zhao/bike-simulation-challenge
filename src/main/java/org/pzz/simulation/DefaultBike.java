package org.pzz.simulation;

import utils.Result;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao, StudentID:1043915
 * @create: 2022-07-19 15:05
 **/
public class DefaultBike implements Bike{
    private Direction direction;
    private Position position;


    @Override
    public Result place(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
        return null;
    }

    @Override
    public Result forward() {
        position.nextPosition(direction);
        return Result.ok();
    }

    @Override
    public Result turnLeft() {
        direction = direction.nextLeftDirection();
        return Result.ok();
    }

    @Override
    public Result turnRight() {
        direction = direction.nextRightDirection();
        return Result.ok();
    }

    @Override
    public Result gpsReport() {
        return Result.ok().data("position",position).data("direction",direction);
    }

    public DefaultBike(Direction direction, Position position) {
        this.direction = direction;
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
