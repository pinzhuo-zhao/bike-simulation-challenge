package org.pzz;

import org.junit.Before;
import org.junit.Test;
import org.pzz.cli.Command;
import org.pzz.cli.Simulator;
import org.pzz.entity.*;
import org.pzz.utils.Result;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao
 * @create: 2022-07-20 21:18
 **/
public class SimulatorTest {
    private Simulator simulator;
    private int xCoordinate;
    private int yCoordinate;
    @Before
    public void setUp() throws Exception {
        File file = new File("src/test/java/commands.txt");
        Grid grid = new Grid(7,7);
        Bike bike = new DefaultBike();
        this.simulator = new Simulator(file,grid,bike);
        xCoordinate = simulator.getGrid().getWidth();
        yCoordinate = simulator.getGrid().getLength();
    }

    @Test
    public void testRemoveBeforePlacing_OnPoint() {
        List<String> commandStrings = new LinkedList<>();
        commandStrings.add("FORWARD");
        commandStrings.add("TURN_LEFT");
        commandStrings.add("TEST");
        commandStrings.add("RANDOM");
        String ValidPlaceCommand = "PLACE 0,5,NORTH";
        commandStrings.add(ValidPlaceCommand);
        commandStrings.add("FORWARD");
        List<String> removedStrings = simulator.removeBeforePlacing(commandStrings);
        assertEquals(ValidPlaceCommand, removedStrings.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class )
    public void testRemoveBeforePlacing_OffPoint() {
        List<String> commandStrings = new LinkedList<>();
        commandStrings.add("FORWARD");
        commandStrings.add("TURN_LEFT");
        commandStrings.add("TEST");
        commandStrings.add("RANDOM");
        //Inserting an invalid PLACE command
        String invalidPlaceCommand = "PLACE 50,50,NORTH";
        commandStrings.add(invalidPlaceCommand);
        commandStrings.add("FORWARD");
        List<String> removedStrings = simulator.removeBeforePlacing(commandStrings);
        //This list should be empty as all commands were removed
        assertNull(removedStrings.get(0));
    }

    @Test
    public void testExecuteCommand_Place_OnPoint() throws IOException {
        Direction initialDirection = Direction.NORTH;
        String placeCommand = MessageFormat.format("PLACE {0},{1},{2}", xCoordinate, yCoordinate, initialDirection.name());
        simulator.executeCommand(placeCommand);
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        //compare the GPS returned position and direction with the original position and direction
        assertEquals(position,new Position(xCoordinate,yCoordinate));
        assertEquals(direction,initialDirection);
    }

    @Test
    public void testExecuteCommand_Place_OffPoint1() throws IOException {
        int xCoordinate = simulator.getGrid().getWidth() + 1 ;
        Direction initialDirection = Direction.NORTH;
        String placeCommand = MessageFormat.format("PLACE {0},{1},{2}", xCoordinate, yCoordinate, initialDirection.name());
        Result result = simulator.executeCommand(placeCommand);
        assertFalse(result.getSuccess());
    }

    @Test
    public void testExecuteCommand_Place_OffPoint2() throws IOException {
        yCoordinate = simulator.getGrid().getLength() + 1;
        Direction initialDirection = Direction.NORTH;
        String placeCommand = MessageFormat.format("PLACE {0},{1},{2}", xCoordinate, yCoordinate, initialDirection.name());
        Result result = simulator.executeCommand(placeCommand);
        assertFalse(result.getSuccess());
    }

    @Test
    public void testExecuteCommand_Place_OffPoint3() throws IOException {
        xCoordinate = -1;
        Direction initialDirection = Direction.NORTH;
        String placeCommand = MessageFormat.format("PLACE {0},{1},{2}", xCoordinate, yCoordinate, initialDirection.name());
        Result result = simulator.executeCommand(placeCommand);
        assertFalse(result.getSuccess());
    }

    @Test
    public void testExecuteCommand_Place_OffPoint4() throws IOException {
        yCoordinate = -1;
        Direction initialDirection = Direction.NORTH;
        String placeCommand = MessageFormat.format("PLACE {0},{1},{2}", xCoordinate, yCoordinate, initialDirection.name());
        Result result = simulator.executeCommand(placeCommand);
        assertFalse(result.getSuccess());
    }

    @Test
    public void testExecuteCommand_Place_OffPoint5() throws IOException {
        String placeCommand = MessageFormat.format("PLACE {0},{1},{2}", xCoordinate, yCoordinate, null);
        Result result = simulator.executeCommand(placeCommand);
        assertFalse(result.getSuccess());
    }

    @Test
    public void testExecuteCommand_Place_OffPoint6() throws IOException {
        Direction initialDirection = Direction.NORTH;
        //Mess up the PLACE command "PLACExx {0},{1},{2}"
        String placeCommand = MessageFormat.format("PLACExx {0},{1},{2}", xCoordinate, yCoordinate, initialDirection.name());
        Result result = simulator.executeCommand(placeCommand);
        assertFalse(result.getSuccess());
    }

    @Test
    public void testExecuteCommand_Place_OffPoint7() throws IOException {
        Direction initialDirection = Direction.NORTH;
        //Mess up the PLACE command by deleting the space
        String placeCommand = MessageFormat.format("PLACE{0},{1},{2}", xCoordinate, yCoordinate, initialDirection.name());
        Result result = simulator.executeCommand(placeCommand);
        assertFalse(result.getSuccess());
    }

    @Test
    public void testExecuteCommand_Place_OffPoint8() throws IOException {
        Direction initialDirection = Direction.NORTH;
        //Mess up the PLACE command by adding extra contents
        String placeCommand = MessageFormat.format("PLACE{0},{1},{2} XXX", xCoordinate, yCoordinate, initialDirection.name());
        Result result = simulator.executeCommand(placeCommand);
        assertFalse(result.getSuccess());
    }

    @Test
    public void testExecuteCommand_Forward_NortheastCorner_OnPoint1() throws IOException {
        yCoordinate = simulator.getGrid().getLength() - 1;
        Direction initialDirection = Direction.NORTH;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //The bike will move to the north as it has not reached the northeast corner
        simulator.executeCommand(Command.FORWARD.name());
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate,yCoordinate + 1));
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_Forward_NortheastCorner_OnPoint2() throws IOException {
        xCoordinate = simulator.getGrid().getWidth() - 1;
        Direction initialDirection = Direction.EAST;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //The bike will move to the east as it has not reached the northeast corner
        simulator.executeCommand(Command.FORWARD.name());
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate + 1,yCoordinate));
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_Forward_NortheastCorner_OffPoint1() throws IOException {
        Direction initialDirection = Direction.NORTH;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //The bike won't move as it is already in the northeast corner
        simulator.executeCommand(Command.FORWARD.name());
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate,yCoordinate));
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_Forward_NortheastCorner_OffPoint2() throws IOException {
        Direction initialDirection = Direction.EAST;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //   //The bike won't move as it is already in the northeast corner
        simulator.executeCommand(Command.FORWARD.name());
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate,yCoordinate));
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_Forward_SoutheastCorner_OnPoint1() throws IOException {
        yCoordinate = 1;
        Direction initialDirection = Direction.SOUTH;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //The bike will move as it has not reached the southeast corner
        simulator.executeCommand(Command.FORWARD.name());
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate,yCoordinate - 1));
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_Forward_SoutheastCorner_OnPoint2() throws IOException {
        yCoordinate = 0;
        xCoordinate = simulator.getGrid().getWidth() - 1;
        Direction initialDirection = Direction.EAST;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //The bike will move as it has not reached the southeast corner
        simulator.executeCommand(Command.FORWARD.name());
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate + 1,yCoordinate));
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_Forward_SoutheastCorner_OffPoint1() throws IOException {
        yCoordinate = 0;
        Direction initialDirection = Direction.SOUTH;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //The bike will not move as it has reached the northeast corner
        simulator.executeCommand(Command.FORWARD.name());
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate,yCoordinate));
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_Forward_SoutheastCorner_OffPoint2() throws IOException {
        yCoordinate = 0;
        Direction initialDirection = Direction.EAST;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //The bike will not move as it has reached the northeast corner
        simulator.executeCommand(Command.FORWARD.name());
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate,yCoordinate));
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_Forward_SouthwestCorner_OnPoint1() throws IOException {
        xCoordinate = 0;
        yCoordinate = 1;
        Direction initialDirection = Direction.SOUTH;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //The bike will move as it has not reached the southwest corner
        simulator.executeCommand(Command.FORWARD.name());
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate,yCoordinate - 1));
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_Forward_SouthwestCorner_OnPoint2() throws IOException {
        xCoordinate = 1;
        yCoordinate = 0;
        Direction initialDirection = Direction.WEST;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //The bike will move as it has not reached the southwest corner
        simulator.executeCommand(Command.FORWARD.name());
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate - 1,yCoordinate));
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_Forward_SouthwestCorner_OffPoint1() throws IOException {
        xCoordinate = 0;
        yCoordinate = 0;
        Direction initialDirection = Direction.SOUTH;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //The bike will not move as it has reached the southwest corner
        simulator.executeCommand(Command.FORWARD.name());
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate,yCoordinate));
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_Forward_SouthwestCorner_OffPoint2() throws IOException {
        xCoordinate = 0;
        yCoordinate = 0;
        Direction initialDirection = Direction.WEST;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //The bike will not move as it has reached the southwest corner
        simulator.executeCommand(Command.FORWARD.name());
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate,yCoordinate));
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_Forward_NorthwestCorner_OnPoint1() throws IOException {
        xCoordinate = 0;
        yCoordinate = simulator.getGrid().getLength() - 1;
        Direction initialDirection = Direction.NORTH;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //The bike will move as it has not reached the northwest corner
        simulator.executeCommand(Command.FORWARD.name());
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate,yCoordinate + 1));
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_Forward_NorthwestCorner_OnPoint2() throws IOException {
        xCoordinate = 1;
        Direction initialDirection = Direction.WEST;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //The bike will move as it has not reached the northwest corner
        simulator.executeCommand(Command.FORWARD.name());
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate - 1,yCoordinate));
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_Forward_NorthwestCorner_OffPoint1() throws IOException {
        xCoordinate = 0;
        Direction initialDirection = Direction.WEST;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //The bike will not move as it has reached the northwest corner
        simulator.executeCommand(Command.FORWARD.name());
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate,yCoordinate));
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_Forward_NorthwestCorner_OffPoint2() throws IOException {
        xCoordinate = 0;
        Direction initialDirection = Direction.NORTH;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //The bike will not move as it has reached the northwest corner
        simulator.executeCommand(Command.FORWARD.name());
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate,yCoordinate));
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_Forward() throws IOException {
        Direction initialDirection = Direction.SOUTH;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //mess up the forward command, so the forward command won't be executed
        simulator.executeCommand(Command.FORWARD.name() + "random");
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        assertEquals(position,new Position(xCoordinate,yCoordinate));
        assertEquals(direction, initialDirection);
    }
    /**
     * TURN_LEFT and TURN_RIGHT commands only calls bike object's turnLeft()
     * and turnRight() methods, they were tested in DirectionTest
     */

    @Test
    public void testExecuteCommand_TurnLeft() throws IOException {
        Direction initialDirection = Direction.SOUTH;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //mess up the turnLeft command, so the turnLeft command won't be executed
        simulator.executeCommand(Command.TURN_LEFT.name() + "random");
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Direction direction = (Direction) result.getData().get("direction");
        //direction won't change
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_TurnRight() throws IOException {
        Direction initialDirection = Direction.SOUTH;
        simulator.getBike().place(new Position(xCoordinate,yCoordinate),initialDirection);
        //mess up the turnRight command, so the turnRight command won't be executed
        simulator.executeCommand(Command.TURN_RIGHT.name() + "random");
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Direction direction = (Direction) result.getData().get("direction");
        //direction won't change
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_GPSReport_OnPoint() throws IOException {
        Direction initialDirection = Direction.SOUTH;
        Position initialPosition = new Position(xCoordinate, yCoordinate);
        simulator.getBike().place(initialPosition,initialDirection);
        Result result = simulator.executeCommand(Command.GPS_REPORT.name());
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        //compare the position and direction returned by GPS and the original input
        assertEquals(position,initialPosition);
        assertEquals(direction, initialDirection);
    }

    @Test
    public void testExecuteCommand_GPSReport_OffPoint() throws IOException {
        Direction initialDirection = Direction.SOUTH;
        Position initialPosition = new Position(xCoordinate, yCoordinate);
        simulator.getBike().place(initialPosition,initialDirection);
        //mess up the GPS command, so it won't be executed
        Result result = simulator.executeCommand(Command.GPS_REPORT.name()+"random");
        assertFalse(result.getSuccess());
    }
}
