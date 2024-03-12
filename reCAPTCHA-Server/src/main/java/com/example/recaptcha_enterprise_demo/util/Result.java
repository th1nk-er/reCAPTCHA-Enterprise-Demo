package com.example.recaptcha_enterprise_demo.util;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static Result success() {
        return success(null);
    }

    public static Result success(Object data) {
        return success(data, "success");
    }

    public static Result success(Object data, String msg) {
        Result result = new Result();
        result.setCode(0);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    public static Result error(int code) {
        return error(code, "error");
    }

    public static Result error(String msg) {
        return error(500, msg);
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setData(null);
        result.setMsg(msg);
        return result;
    }
}
