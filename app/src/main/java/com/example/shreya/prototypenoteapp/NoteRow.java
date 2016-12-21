package com.example.shreya.prototypenoteapp;

/**
 * Created by Shreya on 19-12-2016.
 */

public class NoteRow {

    private String title, timestamp;

    public NoteRow() {
    }

    public NoteRow(String title, String timestamp) {
        this.title = title;
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(String time_stamp) {
        this.timestamp = time_stamp;
    }


}

