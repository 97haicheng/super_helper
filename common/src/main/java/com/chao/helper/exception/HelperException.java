package com.chao.helper.exception;

/**
 * Created by think on 2016/11/9.
 *
 * Helper系统异常
 */
public class HelperException extends RuntimeException {

    public HelperException(String message) {
        super(message);
    }

    public HelperException(String message, Throwable cause) {
        super(message, cause);
    }

    public HelperException(Throwable cause) {
        super(cause);
    }
}
