package com.example.bruno.mymixpics.model;

/**
 * Created by Bruno on 12/3/2015.
 */
public class Comment {

    private Long created_time;
    private String text;
    private User from;
    private Long id;

    public Long getCreatedTime() {
        return created_time;
    }

    public String getText() {
        return text;
    }

    public User getFrom() {
        return from;
    }

    public Long getId() {
        return id;
    }

}
