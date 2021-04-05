package com.wlhse.exception;

public class WLHSException extends RuntimeException {

    private int code = 2000;
    private String message;

    public WLHSException(String message) {
        this.message = message;
    }

    public WLHSException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
