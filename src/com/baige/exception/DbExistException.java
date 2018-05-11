package com.baige.exception;

public class DbExistException extends Exception{

    public DbExistException() {
        super();
    }

    public DbExistException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DbExistException(String detailMessage) {
        super(detailMessage);
    }

    public DbExistException(Throwable throwable) {
        super(throwable);
    }
}
