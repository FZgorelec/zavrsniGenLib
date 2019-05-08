
import algorithmImpl.doublearrayimpl.DoubleArrayGenerationalGA;
import algorithmImpl.doublearrayimpl.DoubleArrayGenome;
import algorithmImpl.doublearrayimpl.DoubleArrayGenomeFactory;
import util.GeneticAlgorithmParameters;

public class GenerationalGATest {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        DoubleArrayGenerationalGA ga = new DoubleArrayGenerationalGA(
                new DoubleArrayGenomeFactory(10,5),
                (DoubleArrayGenome genome) -> {
                    double sum = 0;
                    for (int i = 0; i < 100; i++) {
                        sum += (genome.getSolution()[0] * genome.getSolution()[0]
                                + genome.getSolution()[1] * genome.getSolution()[1] + Math.abs(3 * genome.getSolution()[2])) + Math.pow(genome.getSolution()[1], 2);

                    }
                    return -sum / 100;
                },
                new GeneticAlgorithmParameters(100, 100000, 1000), 4);
        DoubleArrayGenome best = ga.run();
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        System.out.println(best.getSolution()[0] + "  " + best.getSolution()[1] + "  " + best.getSolution()[2]);
        System.out.println(best.getFitness());
        System.out.println(-(best.getSolution()[0] * best.getSolution()[0]
                + best.getSolution()[1] * best.getSolution()[1] + Math.abs(3 * best.getSolution()[2])));
        System.out.println("Execution time in milliseconds: " + timeElapsed);
    }

}
