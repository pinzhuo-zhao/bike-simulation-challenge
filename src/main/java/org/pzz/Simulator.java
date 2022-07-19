package org.pzz;

import org.pzz.cli.Command;
import org.pzz.ex.CommandInvalidException;
import org.pzz.simulation.Direction;
import org.pzz.simulation.Grid;
import org.pzz.simulation.Position;
import utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao, StudentID:1043915
 * @create: 2022-07-19 18:07
 **/
public class Simulator {
    private File inputFile;
    private Grid grid;


    public List<String> removeBeforePlace() throws IOException{
        List<String> commandStrings = FileUtils.readFileByLine(inputFile);
        for (String str : commandStrings) {
            String[] split = str.split(" ");
            if (!Objects.equals(Command.getCommand(split[0]), Command.PLACE)) {
                commandStrings.remove(str);
            } else {
                break;
            }
        }
        return commandStrings;
    }
    
    private boolean isValidCommand(String commandStr) {
        String[] split = commandStr.split(" ");
        Command command = Command.getCommand(split[0]);
        if (Objects.isNull(command)) {
            return false;
        }
        if (command == Command.PLACE) {
            if (split.length < 2) {
                return false;
            }
            String[] posAndDir  = split[1].split(",");
            int xCoordinate = Integer.parseInt(posAndDir[0]);
            int yCoordinate = Integer.parseInt(posAndDir[1]);
            if (xCoordinate < 0 || xCoordinate > grid.getWidth()
                    || yCoordinate < 0 || yCoordinate > grid.getLength()) {
                return false;
            }
            if (Objects.isNull(Direction.getDirection(posAndDir[2]))) {
                return false;
            }
        }
        return true;
    }

    private void handlePlaceCommand(String placeCommand) throws CommandInvalidException, NumberFormatException {
        String[] posAndDir  = placeCommand.split(",");
        int xCoordinate = Integer.parseInt(posAndDir[0]);
        int yCoordinate = Integer.parseInt(posAndDir[1]);
        Direction direction = Direction.getDirection(posAndDir[2]);
        grid.getBike().place(new Position(xCoordinate,yCoordinate),direction);
    }

    public Simulator() {
    }

    public Simulator(File inputFile) throws FileNotFoundException {
        if (!FileUtils.isFileValid(inputFile)) {
            throw new FileNotFoundException();
        }
        this.inputFile = inputFile;
    }
}
