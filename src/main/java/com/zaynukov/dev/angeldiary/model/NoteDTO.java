package com.zaynukov.dev.angeldiary.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class NoteDTO {
    private int id;
    private String title, noteText;
    private LocalDateTime createDateTime;
    private List<LocalDateTime> changeList;

    public NoteDTO(int id, String title, String noteText, LocalDateTime createDateTime, List<LocalDateTime> changeList) {
        this.id = id;
        this.title = title;
        this.noteText = noteText;
        this.createDateTime = createDateTime;
        this.changeList = changeList;
    }

    public LocalDateTime lastChange() {
        int index = changeList.size() - 1;
        return changeList.get(index);
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

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public List<LocalDateTime> getChangeList() {
        return changeList;
    }

    public void setChangeList(List<LocalDateTime> changeList) {
        this.changeList = changeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteDTO noteDTO = (NoteDTO) o;
        return id == noteDTO.id &&
                Objects.equals(title, noteDTO.title) &&
                Objects.equals(noteText, noteDTO.noteText) &&
                Objects.equals(createDateTime, noteDTO.createDateTime) &&
                Objects.equals(changeList, noteDTO.changeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, noteText, createDateTime, changeList);
    }

    @Override
    public String toString() {
        return "NoteDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", noteText='" + noteText + '\'' +
                ", createDateTime=" + createDateTime +
                ", changeList=" + changeList +
                '}';
    }
}
