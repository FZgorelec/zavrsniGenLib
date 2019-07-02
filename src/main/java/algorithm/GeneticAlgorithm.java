package algorithm;

import crossing.ICross;
import mutation.IMutator;
import selection.ISelector;

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

    abstract T runAlgorithm(ISelector<T> selectionAlgorithm, ICross<T> crossingAlgorithm,
                            IMutator<T> mutationAlgorithm);

    protected T[] initPopulation() {
        T[] population = genotypeFactory.getPopulationOfGenotypes(parameters.getPopulationSize());
        for (T member : population) {
            member.setFitness(fitnessFunction.calculateFitness(member));
        }
        return population;
    }


    protected T findBestInPopulation(T[] population){
        double bestFitness = -Double.MAX_VALUE;
        T bestSolution=null;
        for (int i = 0,populationSize=population.length; i < populationSize; i++) {
            if (population[i].getFitness() > bestFitness) {
                bestSolution = population[i];
                bestFitness = population[i].getFitness();
            }
        }
        return bestSolution;
    }

    public T[] getPopulation() {
        if (population == null) throw new NullPointerException("Population is null. " +
                "Did you call this method before running the algorithm?");
        else
            return population;
    }
}
