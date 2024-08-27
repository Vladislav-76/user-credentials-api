package com.userCredentials.userCredentialsAPI.controller;

import java.util.Random;

public class Delay {
    public static void randomDelay(int fromSeconds, int toSeconds) throws InterruptedException {
        Random random = new Random();
        int delayInSeconds = random.nextInt(fromSeconds, toSeconds + 1);
        long secondsInMilliseconds = 1000;
        Thread.sleep(delayInSeconds * secondsInMilliseconds);
    }
}
