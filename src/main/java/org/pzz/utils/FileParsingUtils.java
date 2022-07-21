package org.pzz.utils;

import org.pzz.entity.Direction;
import org.pzz.entity.Grid;
import org.pzz.entity.Position;

import java.io.*;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @program: bike-simulation-challenge
 * @description:
 * Utility class for packaging all methods related to reading
 * or manipulating the input command file
 *
 * @author: Pinzhuo Zhao
 * @create: 2022-07-19 18:23
 **/
public class FileParsingUtils {

    /**
     * The regex pattern for validating PLACE command, note that
     * x,y coordinates will be verified against the grid length/width later
     */
    private static final Pattern PLACE_PATTERN = Pattern.compile("^PLACE\\s(?<xCoordinate>(\\d)),(?<yCoordinate>(\\d)),(?<direction>(NORTH|EAST|SOUTH|WEST)$)");

    public static boolean isFileValid(File file) {
        return file.exists() && file.length() != 0;
    }

    /**
     * Reading the input command file provided in the CLI
     * @param file
     * @return a LinkedList containing all the non-empty lines of commands
     * @throws IOException
     */
    public static List<String> readFileByLine(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> list = new LinkedList<>();
        String commandString;
        while ((commandString = reader.readLine()) != null) {
            list.add(commandString);
        }
        reader.close();
        return filterCommandStrings(list);
    }

    /**
     * Writing the position and direction of the bike when GPS_REPORT is called
     * to the input file
     * @param file
     * @param data
     * @throws IOException
     */
    public static void writeToFile(File file, String data) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
        out.write(data);
        out.flush();
        out.close();
    }

    /**
     * Trimming each lines of command and filtering out all empty lines
     * @param strings: A List of original lines of commands, read from the input file
     * @return
     */
    public static List<String> filterCommandStrings(List<String> strings) {
        return strings.stream()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * Checking if the position and direction provided by the PLACE command is legal or not
     * @param grid
     * @param position
     * @param direction
     * @return
     */
    public static boolean isPlacingLegal(Grid grid, Position position, Direction direction) {
        if (!grid.isLegalPosition(position)) {
            System.out.println(MessageFormat.format("Placing position {0} is outside of the grid",position));
            return false;
        }
        if (Objects.isNull(direction)) {
            System.out.println(MessageFormat.format("Placing direction is not one of {0},{1},{2},{3}", (Direction.values())));
            return false;
        }
        return true;
    }

    /**
     * Using regex to parse a PLACE command,
     * return the position and direction to be placed
     * if a match is found
     * @param commandString
     * @return
     */
    public static Result parsePlaceCommand(String commandString) {
        Matcher matcher = PLACE_PATTERN.matcher(commandString);
        while (matcher.find()) {
            int xCoordinate = Integer.parseInt(matcher.group("xCoordinate"));
            int yCoordinate = Integer.parseInt(matcher.group("yCoordinate"));
            Direction direction = Direction.getDirection(matcher.group("direction"));
            Position position = new Position(xCoordinate, yCoordinate);
            return Result.ok().data("direction",direction).data("position",position);
        }
        return Result.error();
    }
}
