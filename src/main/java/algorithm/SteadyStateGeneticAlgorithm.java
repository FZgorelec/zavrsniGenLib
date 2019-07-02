package algorithm;

import crossing.ICross;
import mutation.IMutator;
import selection.ISelector;
import util.IRandomNumberGenerator;
import util.RandomNumberGenerator;

import java.util.Arrays;

public class SteadyStateGeneticAlgorithm<T extends IGenotype> extends GeneticAlgorithm<T> {

    IRandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    public SteadyStateGeneticAlgorithm(IGenotypeFactory<T> genotypeFactory, IFitnessFunction<T> fitnessFunction, IGeneticAlgorithmParameters parameters) {
        super(genotypeFactory, fitnessFunction, parameters);
    }

    @Override
    public T runAlgorithm(ISelector<T> selectionAlgorithm, ICross<T> crossingAlgorithm, IMutator<T> mutationAlgorithm) {
        return runAlgorithm(selectionAlgorithm, crossingAlgorithm, mutationAlgorithm, null);
    }


    public T runAlgorithm(ISelector<T> selectionAlgorithm, ICross<T> crossingAlgorithm, IMutator<T> mutationAlgorithm, INextPopulationGenerator<T> inserter) {

        double satisfactoryFitness = parameters.getSatisfactoryFitness();
        T[] population = initPopulation();
        T bestSolution=null;
        for (int i = 0, numberOfGenerations = parameters.numberOfGenerations(); i < numberOfGenerations; i++) {
            bestSolution=findBestInPopulation(population);
            if (bestSolution.getFitness()>=satisfactoryFitness) {
                return bestSolution;
            }
            T parent1 = selectionAlgorithm.select(population, randomNumberGenerator);
            T parent2 = selectionAlgorithm.select(population, randomNumberGenerator);
            T[] children = crossingAlgorithm.cross(parent1, parent2, randomNumberGenerator);
            for (int j = 0,len=children.length; j <len ; j++){
                children[j] = mutationAlgorithm.mutate(children[j], randomNumberGenerator);
                children[j].setFitness(fitnessFunction.calculateFitness(children[j]));
            }
            if(inserter==null)population[findWorstIndex(population)] = findBestInPopulation(children);
            else inserter.nextPopulation(population, Arrays.asList(children), randomNumberGenerator);
        }

        return findBestInPopulation(population);
    }

    private int findWorstIndex(T[] population) {
        double worstFitness = Double.MAX_VALUE;
        int indexOfworst = 0;
        for (int i = 0, populationSize = population.length; i < populationSize; i++) {
            if (population[i].getFitness() < worstFitness) {
                indexOfworst = i;
                worstFitness = population[i].getFitness();
            }
        }
        return indexOfworst;
    }
    public void setRandomNumberGenerator(IRandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }
}
