import algorithm.IGenotypeFactory;

import java.util.Random;

public class GenotypeTestDoubleFactory implements IGenotypeFactory<GenotypeTestDouble> {

    Random rand=new Random();

    @Override
    public GenotypeTestDouble[] getPopulationOfGenotypes(int sizeOfPopulation) {
        GenotypeTestDouble[] population=new GenotypeTestDouble[sizeOfPopulation];
        for (int i = 0; i < sizeOfPopulation; i++) {
            population[i]=createRadnomDoubleArray();
        }
        return population;
    }

    private GenotypeTestDouble createRadnomDoubleArray() {
        double[] randomArray=new double[3];
        for (int i = 0; i < 3; i++) {
            randomArray[i]=rand.nextDouble()*10;
        }

        return new GenotypeTestDouble(randomArray, 0);
    }
}
