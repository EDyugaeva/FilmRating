package com.example.filmrating.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class Comment {

    private Long id;
    private Long author;
    private Long film;
    private String text;
    private LocalDateTime date;
    private String authorName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


