package mutation.impl.doubleArrayMutation;

import mutation.IMutationAlgorithm;

import java.util.Random;

public class GaussianMutationDouble{

    private Random rand;
    private double mutationFactor;
    private double sigma;

    public GaussianMutationDouble(Random rand, double mutationFactor, double sigma) {
        this.rand = rand;
        if(mutationFactor>1||mutationFactor<0)throw new IllegalArgumentException("mutationFactor represents a percentage," +
                " the value must be between 0.0 and 1.0");
        this.mutationFactor = mutationFactor;
        this.sigma = sigma;
    }

    public double[] mutate(double[] genome) {
        double[] mutated=new double[genome.length];
        for (int i = 0,length=mutated.length; i < length; i++) {
            if(rand.nextDouble()<=mutationFactor)mutated[i]=genome[i]+(rand.nextDouble()-0.5)*sigma;
            else mutated[i]=genome[i];
        }
        return mutated;
    }
}
