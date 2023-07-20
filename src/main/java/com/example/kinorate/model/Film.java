package com.example.kinorate.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class Film {

    private Long id;
    private String title;
    private String description;
    private List<Comment> commentsList;
    private int rate;
    private List<Rate> ratesList;
    private String image;

    public Film(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

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
