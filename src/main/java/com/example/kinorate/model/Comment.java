package com.example.kinorate.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Comment {

    private Long id;
    private User author;
    private Film film;
    private String text;
    private LocalDateTime date;
}
