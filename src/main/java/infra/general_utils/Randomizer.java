package infra.general_utils;

import java.util.Random;

public class Randomizer {

    public static int getRandomNum(int min, int max) {

        if (min > max) {
            throw new IllegalArgumentException("invalid input");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}


