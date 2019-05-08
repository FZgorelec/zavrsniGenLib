package algorithmImpl.doublearrayimpl;


import algorithmImpl.GenomeFactory;
import util.GADoubleArrayUtilities;
import util.IRandomNumberGenerator;
import util.RandomNumberGenerator;


public class DoubleArrayGenomeFactory extends GenomeFactory<DoubleArrayGenome> {
    private IRandomNumberGenerator rand = new RandomNumberGenerator();
    private int arraySize = 0;
    private double[][] restrictions = null;
    private double interval = 0;

    public DoubleArrayGenomeFactory(int arraySize) {
        setArraySize(arraySize);
    }

    public DoubleArrayGenomeFactory(int arraySize, double interval) {
        setArraySize(arraySize);
        setInterval(interval);
    }

    public DoubleArrayGenomeFactory(int arraySize, double[][] restirctions) {
        setArraySize(arraySize);
        if (GADoubleArrayUtilities.checkIntervalRestrictions(restrictions)) this.restrictions = restirctions;
        else
            throw new IllegalArgumentException("restrictions[i][1] must be bigger than restrictions[i][0] for every i");
    }

    @Override
    protected DoubleArrayGenome createRandomSolution() {
        double[] randomArray = new double[arraySize];
        for (int i = 0; i < arraySize; i++) {
            if (restrictions != null) randomArray[i] = rand.nextDouble(restrictions[i][0], restrictions[i][1]);
            else if (interval != 0) randomArray[i] = rand.nextDouble(-interval, interval);
            else randomArray[i] = rand.nextDouble();
        }

        return new DoubleArrayGenome(randomArray, 0);
    }

    @Override
    protected DoubleArrayGenome[] createEmptyArray(int sizeOfArray) {
        return new DoubleArrayGenome[sizeOfArray];
    }


    public void setArraySize(int arraySize) {
        if (arraySize <= 0) throw new IllegalArgumentException("Array size must be greater than 0");
        else this.arraySize = arraySize;
    }

    public void setInterval(double interval) {
        if (interval <= 0) throw new IllegalArgumentException("Interval size must be greater than 0");
        else this.interval = interval;
    }
}
