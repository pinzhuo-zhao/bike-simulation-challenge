package org.pzz.utils;

import java.util.ResourceBundle;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao, StudentID:1043915
 * @create: 2022-07-19 15:30
 **/
public class PropertiesUtil {
    public static String getProperties(String fileName, String keyName) {
        ResourceBundle bundle = null;
        try {
            bundle = ResourceBundle.getBundle(fileName);
            String value = bundle.getString(keyName);
            return value;
        } catch (Exception e) {
            System.out.printf("Configuration File Problem: %s%n",e.getMessage());
        }
        return null;
    }
}
