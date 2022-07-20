package org.pzz.cli;
import java.util.Objects;

/**
 * @program: bike-simulation-challenge
 * @description: Enumeration for all Valid commands
 * @author: Pinzhuo Zhao
 * @create: 2022-07-19 01:23
 **/
public enum Command {
    /**
     * Map to each valid command as provided in the instruction
     */
    PLACE, FORWARD, TURN_LEFT, TURN_RIGHT, GPS_REPORT;

    /**
     *
     * @param string
     * @return A Command that is mapped to the String provided,
     * return null if not found
     */
    public static Command getCommand(String string) {
        for (Command command : Command.values()) {
            if (Objects.equals(command.name(),string)) {
                return command;
            }
        }
        return null;
    }
}
