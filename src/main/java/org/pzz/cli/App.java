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
    public static void main(String[] args) throws FileNotFoundException {
        //Reading the command file from the input path
        String filePath = "/Users/charlespinzhuozhao/Desktop/sample.txt";
        File file = new File(filePath);
        if (!FileParsingUtils.isFileValid(file)) {
            throw new FileNotFoundException("Empty File");
        }
        //set the grid's length and width based on user inputs from the properties file
        int width = Integer.parseInt(Objects.requireNonNull(PropertiesUtil.getProperties("config", "simulation.grid.width")));
        int length = Integer.parseInt(Objects.requireNonNull(PropertiesUtil.getProperties("config", "simulation.grid.length")));
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
