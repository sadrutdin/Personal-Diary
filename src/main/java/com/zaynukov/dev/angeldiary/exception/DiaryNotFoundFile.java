package com.zaynukov.dev.angeldiary.exception;

public class DiaryNotFoundFile extends Exception {

    public DiaryNotFoundFile() {
    }

    public DiaryNotFoundFile(String message) {
        super(message);
    }

    public DiaryNotFoundFile(String message, Throwable cause) {
        super(message, cause);
    }
}
