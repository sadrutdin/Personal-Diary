package com.zaynukov.dev.angeldiary.exception;

public class DiaryUnknownException extends Exception {

    public DiaryUnknownException() {
    }

    public DiaryUnknownException(String message) {
        super(message);
    }

    public DiaryUnknownException(String message, Throwable cause) {
        super(message, cause);
    }
}
