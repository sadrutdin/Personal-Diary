package com.zaynukov.dev.angeldiary.exception;

public class DiaryIsNotCreateException extends Throwable {
    public DiaryIsNotCreateException() {
    }

    public DiaryIsNotCreateException(String message) {
        super(message);
    }

    public DiaryIsNotCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
