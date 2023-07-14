package com.example.kinorate.model;

import lombok.Data;

@Data
public class Rate {
    private Long id;
    private User user;
    private Film film;
    private int rate;


}
