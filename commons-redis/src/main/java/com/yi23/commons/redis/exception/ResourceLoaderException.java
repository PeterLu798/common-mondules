package com.yi23.commons.redis.exception;

public class ResourceLoaderException extends ExceptionInInitializerError {

    private static final long serialVersionUID = 5330198285317086090L;

    public ResourceLoaderException() {
    }

    public ResourceLoaderException(String s) {
        super(s);
    }

    public ResourceLoaderException(Throwable thrown) {
        super(thrown);
    }

}
