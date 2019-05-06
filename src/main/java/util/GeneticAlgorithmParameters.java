package util;

import algorithm.IGeneticAlgorithmParameters;

public class GeneticAlgorithmParameters implements IGeneticAlgorithmParameters {
   private int populationSize;
    private int numberOfGenerations;
    private double satisfactoryFitness;

    public GeneticAlgorithmParameters(int populationSize, int numberOfGenerations, double satisfactoryFitness) {
        this.populationSize = populationSize;
        this.numberOfGenerations = numberOfGenerations;
        this.satisfactoryFitness = satisfactoryFitness;
    }

    @Override
    public int getPopulationSize() {
        return populationSize;
    }

    @Override
    public int numberOfGenerations() {
        return numberOfGenerations;
    }

    @Override
    public double getSatisfactoryFitness() {
        return satisfactoryFitness;
    }
}
