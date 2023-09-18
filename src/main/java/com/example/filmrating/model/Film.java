package com.example.filmrating.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Film {

    private Long id;
    private String title;
    private String description;
    private float rate;
    private List<Rate> ratesList;
    private String image;

    public Film(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
        ratesList = new ArrayList<>();
    }

}
