package algorithm;

import crossing.ICrossingAlgorithm;
import mutation.IMutationAlgorithm;
import selection.ISelectionAlgorithm;

abstract class GeneticAlgorithm<T extends IGenotype> {
    protected IGeneticAlgorithmParameters parameters;
    protected IGenotypeFactory<T> genotypeFactory;
    protected IFitnessFunction<T> fitnessFunction;
    protected T[] population;
    protected int numberOfThreads;

    public GeneticAlgorithm(IGenotypeFactory<T> genotypeFactory, IFitnessFunction<T> fitnessFunction,
                            IGeneticAlgorithmParameters parameters, int numberOfThreads) {
        this.genotypeFactory = genotypeFactory;
        this.parameters = parameters;
        this.fitnessFunction = fitnessFunction;
        this.numberOfThreads = numberOfThreads;
        if (numberOfThreads == 0) this.numberOfThreads = Runtime.getRuntime().availableProcessors();
    }

    public GeneticAlgorithm(IGenotypeFactory<T> genotypeFactory, IFitnessFunction<T> fitnessFunction,
                            IGeneticAlgorithmParameters parameters) {
        this(genotypeFactory, fitnessFunction, parameters, 1);
    }

    abstract T runAlgorithm(ISelectionAlgorithm<T> selectionAlgorithm, ICrossingAlgorithm<T> crossingAlgorithm,
                            IMutationAlgorithm<T> mutationAlgorithm);

    protected T[] initPopulation() {
        T[] population = genotypeFactory.getPopulationOfGenotypes(parameters.getPopulationSize());
        for (T member : population) {
            member.setFitness(fitnessFunction.calculateFitness(member));
        }
        return population;
    }

    public T[] getPopulation() {
        if (population == null) throw new NullPointerException("Population is null. " +
                "Did you call this method before running the algorithm?");
        else
            return population;
    }
}
