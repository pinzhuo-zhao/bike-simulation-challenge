package org.pzz.cli;

import org.pzz.entity.*;
import org.pzz.utils.FileParsingUtils;
import org.pzz.utils.Result;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao
 * @create: 2022-07-19 18:07
 **/
public class Simulator {
    private File inputFile;
    private Grid grid;
    private Bike bike;


    /**
     * Executing commands after the first valid PLACE command is called
     * @throws IOException
     */
    public void simulate() throws IOException {
        List<String> commandStrings = FileParsingUtils.readFileByLine(inputFile);
        List<String> filteredCommands  = removeBeforePlacing(commandStrings);
        for (String commandString : filteredCommands) {
             executeCommand(commandString);
        }
    }

    /**
     * Removing every command before a valid PLACE command is found
     * @param commandStrings
     * @return
     * @throws IOException
     */
    public List<String> removeBeforePlacing(List<String> commandStrings) throws IOException{
        System.out.println("-- Start removing every command before the first valid PLACE...");
        Iterator<String> iterator = commandStrings.iterator();
        while (iterator.hasNext()) {
            String commandString = iterator.next();
            String[] split = commandString.split(" ");
            if (!Objects.equals(Command.getCommand(split[0]), Command.PLACE)) {
                printRemovingInfo(commandString);
                iterator.remove();
            } else {
                if (split.length != 2 ) {
                    iterator.remove();
                    printRemovingInfo(commandString);
                    continue;
                }
                Result result = FileParsingUtils.parsePlaceCommand(commandString);
                if (!result.getSuccess()) {
                    printRemovingInfo(commandString);
                    iterator.remove();
                    continue;
                }
                Map<String, Object> data = result.getData();
                Position position = (Position) data.get("position");
                Direction direction = (Direction) data.get("direction");
                if (!FileParsingUtils.isPlacingLegal(grid,position,direction)) {
                    printRemovingInfo(commandString);
                    iterator.remove();
                    continue;
                }
                //at this point, a legal placing command has been found
                break;
            }
        }
        System.out.println("-- Finished removing");
        return commandStrings;
    }

    private void printRemovingInfo(String commandString) {
        System.out.println(MessageFormat.format("Removing\"{0}\"...", commandString));
    }

    /**
     * Logics for validating and executing a command,
     * the command will be validated first
     *
     * @param commandString
     * @return
     * @throws IOException
     */
    private Result executeCommand(String commandString) throws IOException {
        String[] split = commandString.split(" ");
        Command command = Command.getCommand(split[0]);
        if (Objects.isNull(command)) {
            return Result.error();
        }
        Position position = null;
        Direction direction = null;
        if (command == Command.PLACE) {
            if (split.length != 2) {
                System.out.println(MessageFormat.format("{0} is not a valid PLACE command", commandString));
                return Result.error();
            }
            Result result = FileParsingUtils.parsePlaceCommand(commandString);
            Map<String, Object> data = result.getData();
            position = (Position) data.get("position");
            direction = (Direction) data.get("direction");
            if (!FileParsingUtils.isPlacingLegal(grid,position,direction)) {
                return Result.error();
            }
        } else {
            //A non-PLACE command should not have a length larger than 1
            if (split.length > 1) {
                return Result.error();
            }
        }
        //Making the bike perform different action based on the command
        switch (command) {
            case PLACE:
                bike.place(position,direction);
                break;
            case FORWARD:
                Position newPosition = bike.getPosition().nextPosition(bike.getDirection());
                if (grid.isLegalPosition(newPosition)) {
                 bike.forward();
                }
                break;
            case TURN_LEFT:
                bike.turnLeft();
                break;
            case TURN_RIGHT:
                bike.turnRight();
                break;
            case GPS_REPORT:
                Result result = bike.gpsReport();
                Position reportPosition = (Position) result.getData().get("position");
                Direction reportDirection = (Direction) result.getData().get("direction");
                String output = MessageFormat.format("\n{0}, {1}", reportPosition, reportDirection);
                System.out.println(output);
                FileParsingUtils.writeToFile(inputFile, output);
                break;
            default:

        }
        return Result.ok();
    }


    /**
     * Constructors, Setters and Getters
     * @param inputFile
     * @param grid
     * @param bike
     */
    public Simulator(File inputFile, Grid grid, Bike bike) {
        this.inputFile = inputFile;
        this.grid = grid;
        this.bike = bike;
    }

    public Simulator() {
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }
}
