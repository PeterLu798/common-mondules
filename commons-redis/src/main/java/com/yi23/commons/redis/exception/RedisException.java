package com.yi23.commons.redis.exception;

public class RedisException extends Exception {

    private static final long serialVersionUID = -559190538540570836L;

    public RedisException() {
    }

    public RedisException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RedisException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisException(String message) {
        super(message);
    }

    public RedisException(Throwable cause) {
        super(cause);
    }

}
