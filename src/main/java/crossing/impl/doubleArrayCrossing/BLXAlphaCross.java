package crossing.impl.doubleArrayCrossing;



import util.IRandomNumberGenerator;

import java.util.Random;

public class BLXAlphaCross {

    private double alpha;


    public BLXAlphaCross(double alpha, Random random) {
        this.alpha = alpha;
    }


    public double[] cross(double[] parent1, double[] parent2,IRandomNumberGenerator random) {

        double[] child = new double[parent1.length];
        for (int i = 0; i < child.length; i++) {
            child[i]=calculateComponent(parent1[i], parent2[i],random);
        }
        return child;
    }

    private double calculateComponent(double parent1Component, double parent2Component, IRandomNumberGenerator random) {

        double cmin = (parent1Component >= parent2Component) ? parent2Component : parent1Component;
        double cmax = (parent1Component >= parent2Component) ? parent1Component : parent2Component;
        double iFactor = cmax - cmin;
        return (cmin - iFactor * alpha) + ((cmax + iFactor * alpha) - (cmin - iFactor * alpha)) * random.nextDouble();
    }

    public double[] cross(double[] parent1, double[] parent2, IRandomNumberGenerator random, double[][] restrictions) {

        double[] child = new double[parent1.length];
        for (int i = 0; i < child.length; i++) {
            child[i] = calculateComponent(parent1[i], parent2[i],random);
            if (child[i] > restrictions[i][1]) child[i] = restrictions[i][1];
            if (child[i] < restrictions[i][0]) child[i] = restrictions[i][0];
        }
        return child;
    }
}


