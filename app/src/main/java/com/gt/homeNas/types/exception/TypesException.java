package com.gt.homeNas.types.exception;

public class TypesException extends Throwable{

    public TypesException() {
        super();
    }

    public TypesException(String message) {
        super(message);
    }

    public TypesException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypesException(Throwable cause) {
        super(cause);
    }
}