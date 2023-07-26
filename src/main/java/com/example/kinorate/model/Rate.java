package com.example.kinorate.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Rate {

    private Long id;
    private Long user;
    private Long film;
    private int rate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate = (Rate) o;
        return Objects.equals(id, rate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
