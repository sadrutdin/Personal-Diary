package com.zaynukov.dev.angeldiary.exception;

public class DiaryIsNotExistsException extends Throwable {
    public DiaryIsNotExistsException() {
    }

    public DiaryIsNotExistsException(String message) {
        super(message);
    }

    public DiaryIsNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
