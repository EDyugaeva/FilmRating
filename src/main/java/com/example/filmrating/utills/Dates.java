package com.example.filmrating.utills;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to format LocalDateTime in JSP
 */
public final class Dates {

    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
