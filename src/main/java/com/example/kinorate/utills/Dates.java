package com.example.kinorate.utills;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Dates {

    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

}
