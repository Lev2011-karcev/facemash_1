package com.example.facesmash;

public class calculate {
    public static double calculateExpectedScore(int ratingA, int ratingB) {
        return 1.0 / (1.0 + Math.pow(10, (double)(ratingB - ratingA) / 400.0));
    }
    public static int calculateNewRating(int playerRating, double expectedScore, double actualScore, int k) {
        return (int) (playerRating + k * (actualScore - expectedScore));
    }
}
