package com.csp.sample.room.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;
    private String name;

//    @Ignore
//    Bitmap picture;

//    public Word(String word) {
//        this.mWord = word;
//    }

    public Word(String word, String name) {
        mWord = word;
        this.name = name;
    }

    public String getWord() {
        return this.mWord;
    }

    public String getName() {
        return name;
    }

    public void setWord(String word) {
        mWord = word;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Bitmap getPicture() {
//        return picture;
//    }
//
//    public void setPicture(Bitmap picture) {
//        this.picture = picture;
//    }

    @Override
    public String toString() {
        return "Word{" +
                "mWord='" + mWord + '\'' +
                ", name='" + name + '\'' +
//                ", picture=" + picture +
                '}';
    }
}