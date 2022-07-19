package utils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao, StudentID:1043915
 * @create: 2022-07-19 18:23
 **/
public class FileUtils {

    public static boolean isFileValid(File file) {
        return file.exists() && file.length() != 0;
    }

    public static List<String> readFileByLine(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> list = new LinkedList<>();
        String commandString = null;
        while ((commandString = reader.readLine()) != null) {
            list.add(commandString);
        }
        reader.close();
        return filterCommandStrings(list);
    }

    public static List<String> filterCommandStrings(List<String> strings) {
        List<String> filteredList = strings.stream().map(String::trim).collect(Collectors.toList());
        return filteredList;

    }
}
