package algorithm;

import crossing.IInserter;
import crossing.ICrossingAlgorithm;
import mutation.IMutationAlgorithm;
import selection.ISelectionAlgorithm;
import util.IRandomNumberGeneratorProvider;
import util.RNGThreadProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class GenerationalGeneticAlgorithm<T extends IGenotype> extends GeneticAlgorithm<T> {

    protected ThreadFactory threadFactory;
    protected int numberOfThreads;

    public GenerationalGeneticAlgorithm(IGenotypeFactory<T> genotypeFactory, IFitnessFunction<T> fitnessFunction,
                                        IGeneticAlgorithmParameters parameters) {
        this(genotypeFactory, fitnessFunction, parameters,1);
    }

    public GenerationalGeneticAlgorithm(IGenotypeFactory<T> genotypeFactory, IFitnessFunction<T> fitnessFunction,
                                        IGeneticAlgorithmParameters parameters, int numberOfThreads) {
        super(genotypeFactory, fitnessFunction, parameters);
        threadFactory = new RNGThreadProvider();
        this.numberOfThreads = numberOfThreads;
        if (numberOfThreads == 0) this.numberOfThreads = Runtime.getRuntime().availableProcessors();
    }

    @Override
    public T runAlgorithm(ISelectionAlgorithm<T> selectionAlgorithm, ICrossingAlgorithm<T> crossingAlgorithm,
                          IMutationAlgorithm<T> mutationAlgorithm) {
        return runAlgorithm(selectionAlgorithm, crossingAlgorithm, mutationAlgorithm, null);
    }


    public T runAlgorithm(ISelectionAlgorithm<T> selectionAlgorithm, ICrossingAlgorithm<T> crossingAlgorithm,
                          IMutationAlgorithm<T> mutationAlgorithm, IInserter<T> childrenSelectionAlgorithm) {
        int populationSize = parameters.getPopulationSize();
        T bestSolution = null;
        double satisfactoryFitness = parameters.getSatisfactoryFitness();
        T[] population = initPopulation();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads, threadFactory);
        int numberOfIterationsPerGeneration = calcNumberOfIterationsPerGeneration(populationSize, crossingAlgorithm.numberOfGeneratedChildren());
        for (int i = 0, numberOfGenerations = parameters.numberOfGenerations(); i < numberOfGenerations; i++) {
            bestSolution=findBestInPopulation(population);
            if (bestSolution.getFitness()>=satisfactoryFitness) {
                executorService.shutdown();
                return bestSolution;
            }
            List<T> newPopulation = new ArrayList<>();
            List<Callable<T[]>> jobList = new ArrayList<>();
            for (int j = 0; j < numberOfIterationsPerGeneration; j++) {
                jobList.add(() -> {
                    T parent1 = selectionAlgorithm.select(population, ((IRandomNumberGeneratorProvider) Thread.currentThread()).getRNG());
                    T parent2 = selectionAlgorithm.select(population, ((IRandomNumberGeneratorProvider) Thread.currentThread()).getRNG());
                    T[] children = crossingAlgorithm.cross(parent1, parent2, ((IRandomNumberGeneratorProvider) Thread.currentThread()).getRNG());
                    for (int k = 0, len = children.length; k < len; k++) {
                        children[k] = mutationAlgorithm.mutate(children[k], ((IRandomNumberGeneratorProvider) Thread.currentThread()).getRNG());
                        children[k].setFitness(fitnessFunction.calculateFitness(children[k]));
                    }
                    return children;
                });
            }

            try {
                List<Future<T[]>> generatedChildren = executorService.invokeAll(jobList);
                for (Future<T[]> futureChildArray : generatedChildren) {
                    T[] childArray = futureChildArray.get();
                    newPopulation.addAll(Arrays.asList(childArray));
                }
            } catch (InterruptedException e) {
                System.err.println("Executor service failed");
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if (childrenSelectionAlgorithm != null) childrenSelectionAlgorithm.newPopulation(population, newPopulation);
            else {
                for (int j = 0; j < populationSize; j++) {
                    population[j] = newPopulation.get(j);
                }
            }
        }
        executorService.shutdown();
        return findBestInPopulation(population);
    }

    public void setThreadFactory(ThreadFactory threadFactory) {
        if (threadFactory.newThread(() -> {
        }) instanceof IRandomNumberGeneratorProvider) this.threadFactory = threadFactory;
        else
            throw new IllegalArgumentException("Provided thread factory must return threads that implement the IRandomNumberGeneratorProvider interface");
    }

    private int calcNumberOfIterationsPerGeneration(int popSize, int childrenCreated) {
        if (childrenCreated == 1) return popSize;
        if (popSize % childrenCreated == 0) return popSize / childrenCreated;
        return popSize / childrenCreated + 1;

    }
}
