package algorithm;

public interface IGenotypeFactory<T extends IGenotype> {
    T[] getPopulationOfGenotypes(int sizeOfPopulation);
}
