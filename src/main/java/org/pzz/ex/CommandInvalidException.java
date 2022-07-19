package org.pzz.ex;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao, StudentID:1043915
 * @create: 2022-07-19 21:19
 **/
public class CommandInvalidException extends RuntimeException{
    public CommandInvalidException() {
        super("Invalid Command");
    }

    public CommandInvalidException(String message) {
        super(message);
    }

    public CommandInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandInvalidException(Throwable cause) {
        super(cause);
    }

    public CommandInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
