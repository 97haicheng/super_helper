package com.chao.helper.exception;

/**
 * 流量平台异常

 */
public class PlatformException extends RuntimeException {

    private static final long serialVersionUID = -2609810710632713363L;

    public PlatformException(String message) {
        super(message);
    }

    public PlatformException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlatformException(Throwable cause) {
        super(cause);
    }
}
