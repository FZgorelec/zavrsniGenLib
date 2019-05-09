import algorithmImpl.bitvectorimpl.BitVectorGenerationalGA;
import algorithmImpl.bitvectorimpl.BitVectorGenome;
import algorithmImpl.bitvectorimpl.BitVectorGenomeFactory;
import algorithmImpl.bitvectorimpl.BitVectorSteadyStateGA;
import util.GABitVectorUtil;
import util.GeneticAlgorithmParameters;
import util.RandomNumberGenerator;

import java.util.Arrays;

public class BitVectorTest {
    public static void main(String[] args) {

        test3();

    }

    private static void test1() {
        boolean[] p1 = new boolean[]{true, true, true, true, true, true, true, true, true, true};
        boolean[] p2 = new boolean[]{false, false, false, false, false, false, false, false, false, false};
        boolean[][] children = GABitVectorUtil.crossNBreakpoints(p1, p2, 5, new RandomNumberGenerator());
        System.out.println(Arrays.toString(children[0]));
        System.out.println(Arrays.toString(children[1]));
    }

    private static void test2() {
        long startTime = System.currentTimeMillis();

        BitVectorGenerationalGA ga = new BitVectorGenerationalGA(
                new BitVectorGenomeFactory(40),
                (BitVectorGenome genome) -> {
                    double sum = 0;
                    for (int i = 0; i < 40; i += 2) {
                        sum += (genome.getSolution()[i]) ? 2 : 0;
                        sum += (genome.getSolution()[i + 1]) ? 0 : 2;

                    }
                    return sum;
                },
                new GeneticAlgorithmParameters(20, 1000000, 80), 4);
        BitVectorGenome best = ga.run();
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        System.out.println(Arrays.toString(best.getSolution()));
        System.out.println(best.getFitness());
        System.out.println("Execution time in milliseconds: " + timeElapsed);
    }
    private static void test3() {
        long startTime = System.currentTimeMillis();

        BitVectorSteadyStateGA ga = new BitVectorSteadyStateGA(
                new BitVectorGenomeFactory(80),
                (BitVectorGenome genome) -> {
                    double sum = 0;
                    for (int i = 0; i < 80; i += 2) {
                        sum += (genome.getSolution()[i]) ? 2 : 0;
                        sum += (genome.getSolution()[i + 1]) ? 0 : 2;

                    }
                    return sum;
                },
                new GeneticAlgorithmParameters(20, 100000, 160));
        BitVectorGenome best = ga.run();
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        System.out.println(Arrays.toString(best.getSolution()));
        System.out.println(best.getFitness());
        System.out.println("Execution time in milliseconds: " + timeElapsed);
    }

}
