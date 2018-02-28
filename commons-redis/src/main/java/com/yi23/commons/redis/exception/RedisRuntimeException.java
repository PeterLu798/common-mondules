package com.yi23.commons.redis.exception;

public class RedisRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 3738676519115706030L;

    public RedisRuntimeException() {
    }

    public RedisRuntimeException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RedisRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisRuntimeException(String message) {
        super(message);
    }

    public RedisRuntimeException(Throwable cause) {
        super(cause);
    }

}
