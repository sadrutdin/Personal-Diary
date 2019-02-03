package com.zaynukov.dev.angeldiary.exception;

public class DiaryIsExistException extends Throwable {
    public DiaryIsExistException() {
    }

    public DiaryIsExistException(String message) {
        super(message);
    }

    public DiaryIsExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
