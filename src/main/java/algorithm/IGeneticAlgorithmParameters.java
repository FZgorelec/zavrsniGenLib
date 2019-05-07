package algorithm;

public interface IGeneticAlgorithmParameters {
    int getPopulationSize();

    int numberOfGenerations();

    double getSatisfactoryFitness();
}
