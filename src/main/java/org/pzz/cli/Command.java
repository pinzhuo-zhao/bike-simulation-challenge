package org.pzz.cli;

import org.pzz.ex.CommandInvalidException;

import java.util.Objects;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao, StudentID:1043915
 * @create: 2022-07-19 01:23
 **/
public enum Command {
    /**
     *
     */
    PLACE, FORWARD, TURN_LEFT, TURN_RIGHT,GPS_REPORT;

    public static Command getCommand(String string) throws CommandInvalidException {
        for (Command command : Command.values()) {
            if (Objects.equals(command.name(),string)) {
                return command;
            }
        }
        return null;
    }
}
