package com.wellnessapp.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReminderEntry {

    private int id;
    private String title;
    private Date date;
    private String time;
    private String comments;
    private String url;

    public ReminderEntry(String title, Date date, String time, String comments, String url){
        this.title = title;
        this.date = date;
        this.time = time;
        this.comments = comments;
        this.url = url;

    }


    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {return this.title;}

    public void setTitle(String title) {this.title = title;}
    public Date getDate() {return this.date;}
    public void setDate(Date date) {this.date = date;}
    public String getTime() {return this.time;}
    public void setTime(String time) {this.time = time;}
    public String getComments() {return this.comments;}
    public void setComments(String comments) { this.comments = comments; }
    public String getUrl() {return this.url;}
    public void setUrl(String url) {this.url = url;}

}
