package com.example.filmrating.utills;

import com.example.filmrating.model.Rate;

import java.util.ArrayList;
import java.util.List;

public class Calculating {

    /**
     * Calculate new status after sending new grade to film
     *
     * @param status               - user status
     * @param rate                 - film general rate
     * @param grade                - rate from user
     * @param countOfRatesFromFilm - quantity of rates, if there is not enough rates, status will not be changed
     * @return new status level
     */
    public static int calculateStatus(int status, float rate, int grade, int countOfRatesFromFilm) {
        if (countOfRatesFromFilm < 10) {
            return status;
        }
        double difference = Math.abs(rate - grade);
        int resultStatus;
        if (difference < 1) {
            resultStatus = status + 1;
        } else if (difference < 2) {
            resultStatus = status;
        } else if (difference < 3) {
            resultStatus = status - 1;
        } else {
            resultStatus = status - 2;
        }
        if (resultStatus < 0 || resultStatus > 100) {
            return status;
        }
        return resultStatus;
    }

    /**
     * Calculate new rating to film
     *
     * @param rateList from Film model
     * @param rate     - new rate
     * @return new general rate for film
     */
    public static float calculateNewFilmRating(List<Rate> rateList, Rate rate) {
        if (rateList == null) {
            rateList = new ArrayList<>();
        }
        rateList.add(rate);
        int sum = 0;
        for (int i = 0; i < rateList.size(); i++) {
            sum = sum + rateList.get(i).getRate();

        }
        return (float) sum / rateList.size();
    }

    /**
     * Calculate general rating from rate list
     *
     * @param rateList - rate list from Film model
     * @return new general rating for film
     */
    public static float calculateFilmRating(List<Rate> rateList) {
        int sum = 0;
        for (int i = 0; i < rateList.size(); i++) {
            sum = sum + rateList.get(i).getRate();
        }
        return (float) sum / rateList.size();
    }
}
