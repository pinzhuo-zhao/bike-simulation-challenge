package org.pzz;

import org.junit.Before;
import org.junit.Test;
import org.pzz.cli.Command;

import static org.junit.Assert.*;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao
 * @create: 2022-07-20 21:00
 **/
public class CommandTest {
    @Test
    public void testGetCommand_Place() {
        Command place = Command.getCommand("PLACE");
        assertEquals(place, Command.PLACE);
    }

    @Test
    public void testGetCommand_Forward() {
        Command forward = Command.getCommand("FORWARD");
        assertEquals(forward, Command.FORWARD);
    }

    @Test
    public void testGetCommand_Turn_left() {
        Command turnLeft = Command.getCommand("TURN_LEFT");
        assertEquals(turnLeft, Command.TURN_LEFT);
    }

    @Test
    public void testGetCommand_Turn_right() {
        Command turnRight = Command.getCommand("TURN_RIGHT");
        assertEquals(turnRight, Command.TURN_RIGHT);
    }

    @Test
    public void testGetCommand_Gps_report() {
        Command gpsReport = Command.getCommand("GPS_REPORT");
        assertEquals(gpsReport, Command.GPS_REPORT);
    }

    @Test
    public void testGetCommand_Null() {
        Command gibberish = Command.getCommand("Gibberish");
        assertNull(gibberish);
    }


}
