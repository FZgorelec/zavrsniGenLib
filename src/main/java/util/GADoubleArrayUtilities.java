package util;

public class GADoubleArrayUtilities {
    public static boolean checkIntervalRestrictions(double[][] restrictions) {
        for (int i = 0, len = restrictions.length; i < len; i++) {
            if (restrictions[i][1] <= restrictions[i][0]) return false;
        }
        return true;
    }

    public static double[] BLXAlphaCross(double[] parent1, double[] parent2, double alpha, IRandomNumberGenerator random, double[][] restrictions) {
        if(!checkIntervalRestrictions(restrictions))throw new IllegalArgumentException("restrictions[i][1] must be bigger than restrictions[i][0] for every i");
        double[] child = new double[parent1.length];
        for (int i = 0; i < child.length; i++) {
            child[i] = calculateComponent(parent1[i], parent2[i],alpha, random);
            if (child[i] > restrictions[i][1]) child[i] = restrictions[i][1];
            if (child[i] < restrictions[i][0]) child[i] = restrictions[i][0];
        }
        return child;
    }

    public static double[] BLXAlphaCross(double[] parent1, double[] parent2, double alpha, IRandomNumberGenerator random) {

        double[] child = new double[parent1.length];
        for (int i = 0; i < child.length; i++) {
            child[i] = calculateComponent(parent1[i], parent2[i], alpha,random);
        }
        return child;
    }

    private static double calculateComponent(double parent1Component, double parent2Component, double alpha, IRandomNumberGenerator random) {

        double cmin = (parent1Component >= parent2Component) ? parent2Component : parent1Component;
        double cmax = (parent1Component >= parent2Component) ? parent1Component : parent2Component;
        double iFactor = cmax - cmin;
        return (cmin - iFactor * alpha) + ((cmax + iFactor * alpha) - (cmin - iFactor * alpha)) * random.nextDouble();
    }

    public static double[] gaussianMutation(double[] genome,double mutationFactor,double sigma, IRandomNumberGenerator randomNumberGenerator) {
        double[] mutated = new double[genome.length];
        for (int i = 0, length = mutated.length; i < length; i++) {
            if (randomNumberGenerator.nextDouble() <= mutationFactor) mutated[i] = genome[i] + (randomNumberGenerator.nextDouble() - 0.5) * sigma;
            else mutated[i] = genome[i];
        }
        return mutated;
    }
}
