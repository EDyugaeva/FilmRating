package com.example.kinorate.model;

import lombok.Data;

import java.util.List;
@Data
public class Film {

    private Long id;
    private String title;
    private String description;
    private List<Comment> commentsList;
    private int rate;
    private List<Rate> ratesList;
    private byte[] image;


    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                '}';
    }
}
