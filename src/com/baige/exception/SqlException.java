package com.baige.exception;

public class SqlException extends Exception{
    public SqlException() {
        super();
    }

    public SqlException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public SqlException(String detailMessage) {
        super(detailMessage);
    }

    public SqlException(Throwable throwable) {
        super(throwable);
    }
}
