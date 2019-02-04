package com.zaynukov.dev.angeldiary.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class ItemOfNoteList implements Serializable, Comparable {
    private int id;
    private String title;
    private LocalDateTime dateTime;
    private String changeHistory;

    public ItemOfNoteList() {
    }

    public ItemOfNoteList(int id, String title, LocalDateTime dateTime, String changeHistory) {
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
        this.changeHistory = changeHistory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getChangeHistory() {
        return changeHistory;
    }

    public void setChangeHistory(String changeHistory) {
        this.changeHistory = changeHistory;
    }

    @Override
    public String toString() {
        return "ItemOfNoteList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dateTime=" + dateTime +
                ", changeHistory='" + changeHistory + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOfNoteList that = (ItemOfNoteList) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(changeHistory, that.changeHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, dateTime, changeHistory);
    }

    @Override
    public int compareTo(Object o) {
        return o instanceof ItemOfNoteList ? dateTime.compareTo(((ItemOfNoteList) o).dateTime) : -1;
    }
}
