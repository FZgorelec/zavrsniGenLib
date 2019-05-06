package algorithm;

import crossing.ICrossingAlgorithm;
import mutation.IMutationAlgorithm;
import selection.ISelectionAlgorithm;

abstract class GeneticAlgorithm<T extends IGenotype> {
    protected IGeneticAlgorithmParameters parameters;
    protected IGenotypeFactory<T> genotypeFactory;
    protected IFitnessFunction<T> fitnessFunction;
    protected T[] population;

    public GeneticAlgorithm(IGenotypeFactory<T> genotypeFactory, IFitnessFunction<T> fitnessFunction,
                            IGeneticAlgorithmParameters parameters) {
        this.genotypeFactory = genotypeFactory;
        this.parameters = parameters;
        this.fitnessFunction = fitnessFunction;
    }

   abstract T runAlgorithm(ISelectionAlgorithm<T> selectionAlgorithm, ICrossingAlgorithm<T> crossingAlgorithm,
                   IMutationAlgorithm<T> mutationAlgorithm);

    protected T[] initPopulation() {
        return genotypeFactory.getPopulationOfGenotypes(parameters.getPopulationSize());
    }

    public T[] getPopulation() {
        if (population == null) throw new NullPointerException("Population is null. " +
                "Did you call this method before running the algorithm?");
        else
            return population;
    }
}
