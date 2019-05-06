import algorithm.GenerationalGeneticAlgorithm;
import crossing.ICrossingAlgorithm;
import crossing.impl.doubleArrayCrossing.BLXAlphaCross;
import mutation.impl.doubleArrayMutation.GaussianMutationDouble;
import selection.impl.NTournamentSelectionWithRepetition;
import util.GeneticAlgorithmParameters;
import util.RandomNumberGenerator;

import java.util.Random;

public class GenerationalGATest {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        GenerationalGeneticAlgorithm<GenotypeTestDouble> ga = new GenerationalGeneticAlgorithm<>(
                new GenotypeTestDoubleFactory(),
                (GenotypeTestDouble genome) -> -(genome.getSolution()[0] * genome.getSolution()[0]
                        + genome.getSolution()[1]* genome.getSolution()[1]+Math.abs(3*genome.getSolution()[2])),
                new GeneticAlgorithmParameters(100, 100000, 1000),0);

        BLXAlphaCross cross = new BLXAlphaCross(2, new Random());
        GaussianMutationDouble mutator = new GaussianMutationDouble(new Random(), 0.3, 1);

        GenotypeTestDouble best=ga.runAlgorithm(new NTournamentSelectionWithRepetition<GenotypeTestDouble>(
                        10,
                        new RandomNumberGenerator()), new ICrossingAlgorithm<GenotypeTestDouble>() {
                    @Override
                    public GenotypeTestDouble[] cross(GenotypeTestDouble parent1, GenotypeTestDouble parent2) {
                        return new GenotypeTestDouble[]{new GenotypeTestDouble(cross.cross(parent1.getSolution(), parent2.getSolution())[0], 0)};
                    }

                    @Override
                    public int numberOfGeneratedChildren() {
                        return 1;
                    }
                },
                genome -> new GenotypeTestDouble(mutator.mutate(genome.getSolution()), 0));
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        System.out.println(best.getSolution()[0]+"  "+best.getSolution()[1]+"  "+best.getSolution()[2]);
        System.out.println(best.getFitness());
        System.out.println( -(best.getSolution()[0] * best.getSolution()[0]
                + best.getSolution()[1]* best.getSolution()[1]+Math.abs(3*best.getSolution()[2])));
        System.out.println("Execution time in milliseconds: " + timeElapsed);
    }


}
