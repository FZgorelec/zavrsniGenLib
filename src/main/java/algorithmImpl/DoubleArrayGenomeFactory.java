package algorithmImpl;

import algorithm.IGenotypeFactory;

import java.util.Random;

public class DoubleArrayGenomeFactory implements IGenotypeFactory<DoubleArrayGenome> {
    Random rand=new Random();

    @Override
    public DoubleArrayGenome[] getPopulationOfGenotypes(int sizeOfPopulation) {
        DoubleArrayGenome[] population=new DoubleArrayGenome[sizeOfPopulation];
        for (int i = 0; i < sizeOfPopulation; i++) {
            population[i]=createRadnomDoubleArray();
        }
        return population;
    }

    private DoubleArrayGenome createRadnomDoubleArray() {
        double[] randomArray=new double[3];
        for (int i = 0; i < 3; i++) {
            randomArray[i]=rand.nextDouble()*10;
        }

        return new DoubleArrayGenome(randomArray, 0);
    }
}
