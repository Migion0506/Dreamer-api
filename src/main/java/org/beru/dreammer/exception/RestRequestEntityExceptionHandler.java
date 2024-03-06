package org.beru.dreammer.exception;

public class RestRequestEntityExceptionHandler extends RuntimeException{
    public RestRequestEntityExceptionHandler(String message){
        super(message);
    }
    public RestRequestEntityExceptionHandler(String message, Throwable cause){
        super(message, cause);
    }
}
