package org.pzz.entity;

import org.pzz.cli.Command;
import org.pzz.utils.Result;

/**
 * @program: bike-simulation-challenge
 * @description: Default implementation Bike interface,
 * @author: Pinzhuo Zhao
 * @create: 2022-07-19 15:05
 **/
public class DefaultBike implements Bike{
    /**
     * Current direction and position of the bike
     */
    private Direction direction;
    private Position position;

    /**
     * Executed when PLACE command is called,
     * assigning the bike in a new position and heading direction
     * @param position
     * @param direction
     */
    @Override
    public void place(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;

    }

    /**
     * Executed when FORWARD command is called,
     * making the bike move towards its current direction by 1 unit
     */
    @Override
    public void forward() {
        position = position.nextPosition(direction);
        System.out.println(Command.FORWARD);
    }

    /**
     * Executed when TURN_LEFT command is called,
     * making the bike's heading direction turn 90 degrees to the left
     */
    @Override
    public void turnLeft() {
        direction = direction.nextLeftDirection();
        System.out.println(Command.TURN_LEFT);

    }

    /**
     * Executed when TURN_RIGHT command is called,
     * making the bike's heading direction turn 90 degrees to the right
     */
    @Override
    public void turnRight() {
        direction = direction.nextRightDirection();
        System.out.println(Command.TURN_RIGHT);
    }

    /**
     * Getting the bike's current position and direction,
     * return the value in the specified format as provided in the instruction:
     * (<X>, <Y>), <Facing-direction>
     * @return
     */
    @Override
    public Result gpsReport() {
        return Result.ok().data("position",position).data("direction",direction);
    }


    /**
     * Constructors, Getters and Setters
     */
    public DefaultBike() {

    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
