package com.zaynukov.dev.angeldiary.exception;

public class DiaryInvalidPassword extends Exception {
    public DiaryInvalidPassword() {
    }

    public DiaryInvalidPassword(String message) {
        super(message);
    }

    public DiaryInvalidPassword(String message, Throwable cause) {
        super(message, cause);
    }
}
