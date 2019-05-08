package algorithmImpl;

import algorithm.IGenotypeFactory;
import util.IRandomNumberGenerator;
import util.RandomNumberGenerator;

import java.util.Random;

public class DoubleArrayGenomeFactory implements IGenotypeFactory<DoubleArrayGenome> {
    private IRandomNumberGenerator rand = new RandomNumberGenerator();
    private int arraySize=0;
    private double[][] restrictions=null;
    private double interval=0;
    public DoubleArrayGenomeFactory(int arraySize){
        this.arraySize=arraySize;
    }

    public DoubleArrayGenomeFactory(int arraySize,double interval){
        this.arraySize=arraySize;
        this.interval=interval;
    }

    public DoubleArrayGenomeFactory(int arraySize,double[][] restirctions){
        this.arraySize=arraySize;
        this.restrictions=restirctions;
    }

    @Override
    public DoubleArrayGenome[] getPopulationOfGenotypes(int sizeOfPopulation) {
        DoubleArrayGenome[] population = new DoubleArrayGenome[sizeOfPopulation];
        for (int i = 0; i < sizeOfPopulation; i++) {
            population[i] = createRadnomDoubleArray();
        }
        return population;
    }

    private DoubleArrayGenome createRadnomDoubleArray() {
        double[] randomArray = new double[arraySize];
        for (int i = 0; i < arraySize; i++) {
            if(restrictions!=null)randomArray[i]=rand.nextDouble(restrictions[i][0],restrictions[i][1]);
            else if(interval!=0)randomArray[i]=rand.nextDouble(-interval, interval);
            else randomArray[i] = rand.nextDouble();
        }

        return new DoubleArrayGenome(randomArray, 0);
    }
}
