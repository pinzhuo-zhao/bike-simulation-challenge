package org.pzz.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: bike-simulation-challenge
 * @description: A standardized result format in this program
 * @author: Pinzhuo Zhao
 * @create: 2022-07-19 15:25
 **/
public class Result {
    private Boolean success;
    /**
     * Return code of the result,
     * not used in this program, but could be useful if the program is extended
     */
    private int code;
    /**
     * Storing result data in a map
     */
    private Map<String, Object> data = new HashMap<>();

    private static final int DEFAULT_SUCCESS_CODE = 200;
    private static final int DEFAULT_ERROR_CODE = 400;


    public Result code(int code){
        this.setCode(code);
        return this;
    }

    /**
     * Load data needs to be returned into the Result object
     * @param key
     * @param value
     * @return
     */
    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }


    public static Result ok() {
        Result r = new Result();
        r.setSuccess(true);
        String successCode = PropertiesUtil.getProperties("config",
                "simulation.successCode");
        //if success code is not specified in the properties file, use the default success code
        r.code((successCode != null ? Integer.parseInt(successCode) : DEFAULT_SUCCESS_CODE));
        return r;
    }

    public static Result error() {
        Result r = new Result();
        r.setSuccess(false);
        String errorCode =  PropertiesUtil.getProperties("config",
                "simulation.errorCode");
        //if error code is not specified in the properties file, use the default error code
        r.code((errorCode != null ? Integer.parseInt(errorCode) : DEFAULT_ERROR_CODE));
        return r;
    }

    /**
     * Constructors and Setters
     */
    private Result() {

    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
