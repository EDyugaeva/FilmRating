package com.example.kinorate.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
public class Film {

    private Long id;
    private String title;
    private String description;
    private List<Comment> commentsList;
    private float rate;
    private List<Rate> ratesList;
    private String image;

    public Film(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;

        ratesList = new ArrayList<>();
        commentsList = new ArrayList<>();
    }


}
