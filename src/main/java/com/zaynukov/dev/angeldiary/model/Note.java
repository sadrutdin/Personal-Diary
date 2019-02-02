package com.zaynukov.dev.angeldiary.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Note implements Serializable, Comparable {
    private String note;
    private LocalDateTime dateTime;

    public Note(String note, LocalDateTime dateTime) {
        this.note = note;
        this.dateTime = dateTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note1 = (Note) o;
        return Objects.equals(note, note1.note) &&
                Objects.equals(dateTime, note1.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(note, dateTime);
    }

    @Override
    public String toString() {
        return "Note{" +
                "note='" + note + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return o instanceof Note ? dateTime.compareTo(((Note) o).dateTime) : -1;
    }
}
