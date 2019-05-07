
import algorithmImpl.DoubleArrayGenerationalGA;
import algorithmImpl.DoubleArrayGenome;
import algorithmImpl.DoubleArrayGenomeFactory;
import util.GeneticAlgorithmParameters;

public class GenerationalGATest {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
//        GenerationalGeneticAlgorithm<GenotypeTestDouble> ga = new GenerationalGeneticAlgorithm<>(
//                new GenotypeTestDoubleFactory(),
//                (GenotypeTestDouble genome) -> {
//                    double sum = 0;
//                    for (int i = 0; i < 2000; i++) {
//                        sum += (genome.getSolution()[0] * genome.getSolution()[0]
//                                + genome.getSolution()[1] * genome.getSolution()[1] + Math.abs(3 * genome.getSolution()[2])) + Math.pow(genome.getSolution()[1], 2);
//
//                    }
//                    return -sum / 100;
//                },
//                new GeneticAlgorithmParameters(100, 100000, 1000), 4);
//
//        BLXAlphaCross cross = new BLXAlphaCross(2, new Random());
//        GaussianMutationDouble mutator = new GaussianMutationDouble(new Random(), 0.3, 1);
//
//        GenotypeTestDouble best = ga.runAlgorithm(new NTournamentSelectionWithRepetition<GenotypeTestDouble>(
//                        10), new ICrossingAlgorithm<GenotypeTestDouble>() {
//                    @Override
//                    public GenotypeTestDouble[] cross(GenotypeTestDouble parent1, GenotypeTestDouble parent2, IRandomNumberGenerator random) {
//                        return new GenotypeTestDouble[]{new GenotypeTestDouble(cross.cross(parent1.getSolution(), parent2.getSolution(),random), 0)};
//                    }
//
//                    @Override
//                    public int numberOfGeneratedChildren() {
//                        return 1;
//                    }
//                },
//                (genome, r) -> new GenotypeTestDouble(mutator.mutate(genome.getSolution()), 0));

        DoubleArrayGenerationalGA ga= new DoubleArrayGenerationalGA(
                new DoubleArrayGenomeFactory(),
                (DoubleArrayGenome genome) -> {
                    double sum = 0;
                    for (int i = 0; i < 2000; i++) {
                        sum += (genome.getSolution()[0] * genome.getSolution()[0]
                                + genome.getSolution()[1] * genome.getSolution()[1] + Math.abs(3 * genome.getSolution()[2])) + Math.pow(genome.getSolution()[1], 2);

                    }
                    return -sum / 100;
                },
                new GeneticAlgorithmParameters(100, 100000, 1000), 4);
        DoubleArrayGenome best=ga.run();
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        System.out.println(best.getSolution()[0] + "  " + best.getSolution()[1] + "  " + best.getSolution()[2]);
        System.out.println(best.getFitness());
        System.out.println(-(best.getSolution()[0] * best.getSolution()[0]
                + best.getSolution()[1] * best.getSolution()[1] + Math.abs(3 * best.getSolution()[2])));
        System.out.println("Execution time in milliseconds: " + timeElapsed);
    }


}
