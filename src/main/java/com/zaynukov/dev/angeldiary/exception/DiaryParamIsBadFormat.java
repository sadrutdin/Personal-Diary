package com.zaynukov.dev.angeldiary.exception;

public class DiaryParamIsBadFormat extends Exception {
    public DiaryParamIsBadFormat() {
    }

    public DiaryParamIsBadFormat(String message) {
        super(message);
    }

    public DiaryParamIsBadFormat(String message, Throwable cause) {
        super(message, cause);
    }
}
