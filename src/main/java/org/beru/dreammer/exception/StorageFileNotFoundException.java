package org.beru.dreammer.exception;

public class StorageFileNotFoundException extends StorageException{

    public StorageFileNotFoundException(String message) {
        super(message);
    }
    public StorageFileNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }

}
