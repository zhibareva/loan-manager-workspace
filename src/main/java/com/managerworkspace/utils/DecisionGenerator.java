package com.managerworkspace.utils;

import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class DecisionGenerator {

  public static final int MIN_DAYS = 30;
  public static final int MAX_DAYS = 365;
  public static final double CHANCE = 0.7;
  public static final double COEFFICIENT = 0.8;

  public boolean getDecision() {
    Random random = new Random();
    return random.nextDouble() <= CHANCE;
  }

  public int getDays() {
    Random random = new Random();
    return random.ints(MIN_DAYS, MAX_DAYS)
        .findFirst()
        .getAsInt();
  }

  public double roundAmount(double value) {
    double scale = Math.pow(10, 2);
    return Math.ceil(value * scale) / scale;
  }
}
