package org.pzz.cli;

import org.pzz.entity.Bike;
import org.pzz.entity.DefaultBike;
import org.pzz.entity.Grid;
import org.pzz.utils.FileParsingUtils;
import org.pzz.utils.PropertiesUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

/**
 * Entrance of the program
 *
 * @author Pinzhuo Zhao
 */
public class App {
    private static final int DEFAULT_GRID_LENGTH = 7;
    private static final int DEFAULT_GRID_WIDTH = 7;

    public static void main(String[] args) throws FileNotFoundException {
        //Reading the command file from the input path
        String filePath = "/Users/charlespinzhuozhao/Desktop/sample.txt";
        File file = new File(filePath);
        if (!FileParsingUtils.isFileValid(file)) {
            throw new FileNotFoundException("Empty File");
        }
        //set the grid's length and width based on user inputs from the properties file
        String lengthStr = PropertiesUtil.getProperties("config", "simulation.grid.length");
        String widthStr = PropertiesUtil.getProperties("config", "simulation.grid.width");
        //set the grid's length width to default value is no user input specified
        int width = widthStr == null ? DEFAULT_GRID_WIDTH : Integer.parseInt(widthStr);
        int length = lengthStr == null ? DEFAULT_GRID_LENGTH : Integer.parseInt(lengthStr);
        Grid grid = new Grid(length, width);
        Bike bike = new DefaultBike();
        Simulator simulator = new Simulator(file,grid,bike);
        try {
            simulator.simulate();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
