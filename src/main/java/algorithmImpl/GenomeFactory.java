package algorithmImpl;

import algorithm.IGenotype;
import algorithm.IGenotypeFactory;
import util.IRandomNumberGenerator;
import util.RandomNumberGenerator;

abstract public class GenomeFactory<T extends IGenotype> implements IGenotypeFactory<T> {
    protected IRandomNumberGenerator rand;

    public GenomeFactory() {
        this.rand = new RandomNumberGenerator();
    }


    protected abstract T createRandomSolution();

    protected abstract T[] createEmptyArray(int sizeOfArray);

    public T[] getPopulationOfGenotypes(int sizeOfPopulation) {
        T[] population = createEmptyArray(sizeOfPopulation);
        for (int i = 0; i < sizeOfPopulation; i++) {
            population[i] = createRandomSolution();
        }
        return population;
    }

}
