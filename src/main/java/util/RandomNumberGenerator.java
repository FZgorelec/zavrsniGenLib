package util;

import java.util.Random;

public class RandomNumberGenerator implements IRandomNumberGenerator {
    Random rand;

    public RandomNumberGenerator() {
        rand = new Random();
    }

    @Override
    public double nextDouble() {
        return rand.nextDouble();
    }

    @Override
    public double nextDouble(double min, double max) {
        return min + (max - min) * nextDouble();
    }


    public float nextFloat() {
        return rand.nextFloat();
    }


    public float nextFloat(float min, float max) {
        return min + (max - min) * nextFloat();
    }

    @Override
    public int nextInt() {
        return rand.nextInt();
    }

    @Override
    public int nextInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }


    public boolean nextBoolean() {
        return rand.nextBoolean();
    }


    public double nextGaussian() {
        return rand.nextGaussian();
    }
}
