package com.zaynukov.dev.angeldiary.model;

import java.sql.Timestamp;
import java.util.Objects;

public final class TimestampPair {
    private Timestamp begin, end;

    public Timestamp getBegin() {
        return begin;
    }

    public Timestamp getEnd() {
        return end;
    }

    public TimestampPair(Timestamp begin, Timestamp end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimestampPair that = (TimestampPair) o;
        return Objects.equals(begin, that.begin) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, end);
    }

    @Override
    public String toString() {
        return "TimestampPair{" +
                "begin=" + begin +
                ", end=" + end +
                '}';
    }
}
