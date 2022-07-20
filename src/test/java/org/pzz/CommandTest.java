package org.pzz;

import org.junit.Before;
import org.junit.Test;
import org.pzz.cli.Command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao, StudentID:1043915
 * @create: 2022-07-20 21:00
 **/
public class CommandTest {
    @Before
    public void setUp() {

    }

    @Test
    public void testGetCommand_PLACE() {
        Command place = Command.getCommand("PLACE");
        assertEquals(place, Command.PLACE);
    }

    @Test
    public void testGetCommand_FORWARD() {
        Command forward = Command.getCommand("FORWARD");
        assertEquals(forward, Command.FORWARD);
    }

    @Test
    public void testGetCommand_TURN_LEFT() {
        Command turnLeft = Command.getCommand("TURN_LEFT");
        assertEquals(turnLeft, Command.TURN_LEFT);
    }

    @Test
    public void testGetCommand_TURN_RIGHT() {
        Command turnRight = Command.getCommand("TURN_RIGHT");
        assertEquals(turnRight, Command.TURN_RIGHT);
    }

    @Test
    public void testGetCommand_GPS_REPORT() {
        Command place = Command.getCommand("GPS_REPORT");
        assertEquals(place, Command.PLACE);
    }

}
