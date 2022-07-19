package org.pzz;

import utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 *
 * @author charlespinzhuozhao
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = args[0];
        File file = new File(filePath);
        Simulator simulator = new Simulator(file);

    }

}
