package com.example.kinorate.utills;

import com.example.kinorate.model.Rate;

import java.util.ArrayList;
import java.util.List;

public class Calculating {

    /**
     * Calculate new status after sending new grade to film
      * @param status - user status
     * @param rate - film general rate
     * @param grade - rate from user
     * @param countOfRatesFromFilm  - quantity of rates, if there is not enough rates, status will not be changed
     * @return new status level
     */

    public static int calculateStatus(int status, float rate, int grade, int countOfRatesFromFilm) {
        if (status == 0 || status == 100 || countOfRatesFromFilm < 10) {
            return status;
        }
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

    /**
     * Calculate new rating to film
     * @param rateList from Film model
     * @param rate - new rate
     * @return
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
        System.out.println(sum);
        System.out.println(rateList.size());

        System.out.println((float) sum / rateList.size());

        return (float) sum / rateList.size();

    }
}
