package com.zaynukov.dev.angeldiary.model;

import com.zaynukov.dev.angeldiary.service.DiaryUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class ItemOfNoteListDTO implements Serializable, Comparable<ItemOfNoteListDTO> {
    private int id;
    private String title;
    private LocalDateTime createDateTime, lastChangeDateTime;

    public ItemOfNoteListDTO(int id,
                             @NotNull String title,
                             @NotNull LocalDateTime createDateTime) {
        this.id = id;
        this.title = title;
        this.createDateTime = createDateTime;

    }

    public ItemOfNoteListDTO(int id,
                             @NotNull String title,
                             @NotNull LocalDateTime createDateTime,
                             @Nullable LocalDateTime lastChangeDateTime) {
        this(id, title, createDateTime);
        this.lastChangeDateTime = lastChangeDateTime;
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

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getLastChangeDateTime() {
        return lastChangeDateTime;
    }

    public void setLastChangeDateTime(LocalDateTime lastChangeDateTime) {
        this.lastChangeDateTime = lastChangeDateTime;
    }

    @SuppressWarnings("WeakerAccess")
    public String createDateTimeAsStr() {
        return DiaryUtils.formatDateTime(createDateTime);
    }

    @SuppressWarnings("WeakerAccess")
    public String lastChangeDateTimeAsStr() {
        return lastChangeDateTime != null
                ? DiaryUtils.formatDateTime(lastChangeDateTime)
                : null;
    }


    @Override
    public String toString() {
        return "ItemOfNoteListDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createDateTime=" + createDateTimeAsStr() +
                ", lastChangeDateTime=" + lastChangeDateTimeAsStr() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOfNoteListDTO that = (ItemOfNoteListDTO) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(createDateTime, that.createDateTime) &&
                Objects.equals(lastChangeDateTime, that.lastChangeDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, createDateTime, lastChangeDateTime);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(ItemOfNoteListDTO o) {
        return o == null
                ? 1
                : this.createDateTime.compareTo(o.createDateTime);
    }
}
