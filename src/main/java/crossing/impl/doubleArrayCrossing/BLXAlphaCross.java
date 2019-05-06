package crossing.impl.doubleArrayCrossing;



import java.util.Random;

public class BLXAlphaCross {

    private double alpha;
    private Random rand;

    public BLXAlphaCross(double alpha, Random random) {
        this.alpha = alpha;
        this.rand = random;
    }


    public double[][] cross(double[] parent1, double[] parent2) {

        double[] child = new double[parent1.length];
        for (int i = 0; i < child.length; i++) {
            child[i]=calculateComponent(parent1[i], parent2[i]);
        }
        return new double[][]{child};
    }

    private double calculateComponent(double parent1Component, double parent2Component) {

        double cmin = (parent1Component >= parent2Component) ? parent2Component : parent1Component;
        double cmax = (parent1Component >= parent2Component) ? parent1Component : parent2Component;
        double iFactor = cmax - cmin;
        return (cmin - iFactor * alpha) + ((cmax + iFactor * alpha) - (cmin - iFactor * alpha)) * rand.nextDouble();
    }

    public double[][] cross(double[] parent1, double[] parent2, double[][] restrictions) {

        double[] child = new double[parent1.length];
        for (int i = 0; i < child.length; i++) {
            child[i] = calculateComponent(parent1[i], parent2[i]);
            if (child[i] > restrictions[i][1]) child[i] = restrictions[i][1];
            if (child[i] < restrictions[i][0]) child[i] = restrictions[i][0];
        }
        return new double[][]{child};
    }
}


