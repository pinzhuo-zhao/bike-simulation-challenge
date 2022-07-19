package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: bike-simulation-challenge
 * @description:
 * @author: Pinzhuo Zhao, StudentID:1043915
 * @create: 2022-07-19 15:25
 **/
public class Result {
    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    private static final int DEFAULT_SUCCESS_CODE = 200;
    private static final int DEFAULT_ERROR_CODE = 400;

    private Result() {

    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public static Result ok() {
        Result r = new Result();
        r.setSuccess(true);
        String successCode = PropertiesUtil.getProperties("config",
                "simulation.successCode");
        r.code((successCode != null ? Integer.parseInt(successCode) : DEFAULT_SUCCESS_CODE));
        r.setMessage("success");
        return r;
    }

    public static Result error() {
        Result r = new Result();
        r.setSuccess(false);
        String errorCode =  PropertiesUtil.getProperties("config",
                "simulation.errorCode");
        r.code((errorCode != null ? Integer.parseInt(errorCode) : DEFAULT_ERROR_CODE));
        r.setMessage("error");
        return r;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
