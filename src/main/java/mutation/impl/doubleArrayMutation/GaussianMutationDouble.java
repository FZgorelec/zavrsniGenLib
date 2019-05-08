package mutation.impl.doubleArrayMutation;

import mutation.IMutationAlgorithm;
import util.IRandomNumberGenerator;

import java.util.Random;

public class GaussianMutationDouble {

    private double mutationFactor;
    private double sigma;

    public GaussianMutationDouble(double mutationFactor, double sigma) {
        if (mutationFactor > 1 || mutationFactor < 0)
            throw new IllegalArgumentException("mutationFactor represents a percentage," +
                    " the value must be between 0.0 and 1.0");
        this.mutationFactor = mutationFactor;
        this.sigma = sigma;
    }

    public double[] mutate(double[] genome, IRandomNumberGenerator randomNumberGenerator) {
        double[] mutated = new double[genome.length];
        for (int i = 0, length = mutated.length; i < length; i++) {
            if (randomNumberGenerator.nextDouble() <= mutationFactor) mutated[i] = genome[i] + (randomNumberGenerator.nextDouble() - 0.5) * sigma;
            else mutated[i] = genome[i];
        }
        return mutated;
    }
}
