package org.pzz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pzz.entity.Direction;
import org.pzz.entity.Grid;
import org.pzz.entity.Position;
import org.pzz.utils.FileParsingUtils;
import org.pzz.utils.Result;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao, StudentID:1043915
 * @create: 2022-07-20 22:19
 **/
public class FileParsingUtilsTest {
    private File file;
    private Grid grid;
    @Before
    public void setUp() {
        this.grid = new Grid(7,7);
    }

    @Test
    public void testIsFileValid_OnPoint() {
        file = new File("src/test/java/commands.txt");
        assertTrue(FileParsingUtils.isFileValid(file));
    }

    @Test
    public void testIsFileValid_OffPoint1() {
        file = new File("src/test/java/null.txt");
        assertFalse(FileParsingUtils.isFileValid(file));
    }

    @Test
    public void testIsFileValid_OffPoint2() {
        file = new File("src/test/java/empty.txt");
        assertFalse(FileParsingUtils.isFileValid(file));
    }

    @Test
    public void testReadFileByLine() throws IOException {
        file = new File("src/test/java/empty2.txt");
        //clears the content of "empty2" everytime
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String stringForTest = "Test";
        writer.write(stringForTest);
        writer.flush();
        writer.close();
        List<String> strings = FileParsingUtils.readFileByLine(file);
        assertEquals(strings.get(0),stringForTest);
    }

    @Test
    public void testFilterCommandStrings() throws IOException {
        List<String> strings = new LinkedList<>();
        String stringForTest = "Test";
        //All strings will be trimmed and empty strings will be removed
        strings.add("");
        strings.add(" " + stringForTest + " ");
        strings.add(" ");
        List<String> filteredStrings = FileParsingUtils.filterCommandStrings(strings);
        assertEquals(filteredStrings.get(0),stringForTest);
    }

    @Test
    public void testIsPlacingLegal_OnPoint() {
        boolean isLegal = FileParsingUtils.isPlacingLegal(grid, new Position(grid.getLength(), grid.getLength()), Direction.NORTH);
        assertTrue(isLegal);
    }

    @Test
    public void testIsPlacingLegal_OnPoint_2() {
        boolean isLegal = FileParsingUtils.isPlacingLegal(grid, new Position(0, grid.getLength()), Direction.SOUTH);
        assertTrue(isLegal);
    }

    @Test
    public void testIsPlacingLegal_OnPoint_3() {
        boolean isLegal = FileParsingUtils.isPlacingLegal(grid, new Position(grid.getLength(),0), Direction.EAST);
        assertTrue(isLegal);
    }

    @Test
    public void testIsPlacingLegal_OnPoint_4() {
        boolean isLegal = FileParsingUtils.isPlacingLegal(grid, new Position(0, 0), Direction.WEST);
        assertTrue(isLegal);
    }

    @Test
    public void testIsPlacingLegal_OffPoint1() {
        boolean isLegal = FileParsingUtils.isPlacingLegal(grid, new Position(grid.getLength(), grid.getLength()+1), Direction.NORTH);
        assertFalse(isLegal);
    }

    @Test
    public void testIsPlacingLegal_OffPoint2() {
        boolean isLegal = FileParsingUtils.isPlacingLegal(grid, new Position(grid.getLength(), grid.getLength()),null);
        assertFalse(isLegal);
    }

    @Test
    public void testParsingPlaceCommand_OnPoint() {
        int xCoordinate = 5;
        int yCoordinate = 5;
        Direction initialDirection = Direction.NORTH;
        String placeCommand = MessageFormat.format("PLACE {0},{1},{2}", xCoordinate, yCoordinate, initialDirection.name());
        Result result = FileParsingUtils.parsePlaceCommand(placeCommand);
        Position position = (Position) result.getData().get("position");
        Direction direction = (Direction) result.getData().get("direction");
        //compare the returned position and direction with the original position and direction
        assertEquals(position,new Position(xCoordinate,yCoordinate));
        assertEquals(direction,initialDirection);
    }

    @Test
    public void testParsingPlaceCommand_OffPoint() {
        String placeCommand = "Gibberish";
        Result result = FileParsingUtils.parsePlaceCommand(placeCommand);
        Boolean isSuccessful = result.getSuccess();
        assertFalse(isSuccessful);
    }
}
