package com.example.kinorate.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Comment {

    private Long id;
    private Long author;
    private Long film;
    private String text;
    private LocalDateTime date;
    private String authorName;

}
