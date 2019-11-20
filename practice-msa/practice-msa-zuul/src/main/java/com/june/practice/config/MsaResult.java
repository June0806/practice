package com.june.practice.config;

import java.io.Serializable;

/**
 *
 */
public class MsaResult implements Serializable {
    private int result;
    private String message;
    private Object data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
