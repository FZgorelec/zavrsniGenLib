package algorithm;

import crossing.ICrossingAlgorithm;
import mutation.IMutationAlgorithm;
import selection.ISelectionAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class GenerationalGeneticAlgorithm<T extends IGenotype> extends GeneticAlgorithm<T> {

    public GenerationalGeneticAlgorithm(IGenotypeFactory<T> genotypeFactory, IFitnessFunction<T> fitnessFunction,
                                        IGeneticAlgorithmParameters parameters) {
        super(genotypeFactory, fitnessFunction, parameters);
    }

    @Override
    public T runAlgorithm(ISelectionAlgorithm<T> selectionAlgorithm, ICrossingAlgorithm<T> crossingAlgorithm,
                   IMutationAlgorithm<T> mutationAlgorithm) {
        int populationSize = parameters.getPopulationSize();
        boolean foundSatisfactory = false;
        T bestSolution = null;
        double satisfactoryFitness = parameters.getSatisfactoryFitness();
        T[] population = initPopulation();


        for (int i = 0, numberOfGenerations = parameters.numberOfGenerations(); i < numberOfGenerations; i++) {
            System.out.println(i);
            for (T genotype : population) {
                genotype.setFitness(fitnessFunction.getFitness(genotype));
                if (genotype.getFitness() >= satisfactoryFitness) {
                    foundSatisfactory = true;
                    bestSolution = genotype;
                }
            }
            if (foundSatisfactory) return bestSolution;
            List<T> newPopulation = new ArrayList<>();
            while (newPopulation.size() < populationSize) {
                T parent1 = selectionAlgorithm.select(population);
                T parent2 = selectionAlgorithm.select(population);
                T[] children = crossingAlgorithm.cross(parent1, parent2);
                for (T child : children) {
                    newPopulation.add(mutationAlgorithm.mutate(child));
                }
            }
            for (int j = 0; j < populationSize; j++) {
                population[j] = newPopulation.get(j);
            }
        }

        double bestFitness=-Double.MAX_VALUE;
        for (int i = 0; i < populationSize; i++) {
            population[i].setFitness(fitnessFunction.getFitness(population[i]));
            if(population[i].getFitness()>bestFitness){
                bestSolution=population[i];
                bestFitness=population[i].getFitness();
            }
        }
        return bestSolution;
    }
}
