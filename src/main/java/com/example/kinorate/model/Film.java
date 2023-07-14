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

}
