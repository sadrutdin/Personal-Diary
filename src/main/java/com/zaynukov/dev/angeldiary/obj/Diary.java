package com.zaynukov.dev.angeldiary.obj;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
public class Diary {

    @SerializedName("diary")
    private List<Note> diary;

    public void setDiary(List<Note> diary) {
        this.diary = diary;
    }

    public List<Note> getDiary() {
        return diary;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "diary=" + diary +
                '}';
    }
}