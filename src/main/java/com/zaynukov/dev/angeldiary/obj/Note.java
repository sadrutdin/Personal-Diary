package com.zaynukov.dev.angeldiary.obj;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note implements Comparable<Note>, Serializable {

    private transient final static DateTimeFormatter formatter;

    static {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    }

    @SerializedName("id")
    private int id;

    @SerializedName("date")
    private String date;

    @SerializedName("note_text")
    private String noteText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getNoteText() {
        return noteText;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", noteText='" + noteText + '\'' +
                '}';
    }

    @Override
    public int compareTo(Note o) {
        LocalDateTime im = LocalDateTime.parse(date, formatter);
        LocalDateTime heIs = LocalDateTime.parse(o.date, formatter);
        return im.compareTo(heIs);
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.parse(date, formatter);
    }
}