package com.example.kinorate.utills;

public class StatusCalculating {
    public static int calculateStatus(int status, float rate, int grade) {
        double difference = Math.abs(rate - grade);
        if (difference < 1) {
            return status + 1;
        } else if (difference < 2) {
            return status;
        } else if (difference < 3) {
            return status - 1;
        } else {
            return status - 2;
        }


    }
}
