package algorithm;

public interface IGenotypeFactory<T> {
    T[] getPopulationOfGenotypes(int sizeOfPopulation);
}
