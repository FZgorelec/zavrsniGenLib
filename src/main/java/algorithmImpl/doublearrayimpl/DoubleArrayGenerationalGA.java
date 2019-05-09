package algorithmImpl.doublearrayimpl;

import algorithm.GenerationalGeneticAlgorithm;
import algorithm.IFitnessFunction;
import algorithm.IGeneticAlgorithmParameters;
import algorithm.IGenotypeFactory;
import crossing.IInserter;


public class DoubleArrayGenerationalGA extends DoubleArrayGA {
    private GenerationalGeneticAlgorithm<DoubleArrayGenome> generationalGeneticAlgorithm;
    private IInserter<DoubleArrayGenome> selector = null;

    public DoubleArrayGenerationalGA(IGenotypeFactory<DoubleArrayGenome> genotypeFactory, IFitnessFunction<DoubleArrayGenome> fitnessFunction,
                                     IGeneticAlgorithmParameters parameters) {
        super();
        this.generationalGeneticAlgorithm = new GenerationalGeneticAlgorithm<>(genotypeFactory, fitnessFunction, parameters);

    }


    public DoubleArrayGenerationalGA(IGenotypeFactory<DoubleArrayGenome> genotypeFactory, IFitnessFunction<DoubleArrayGenome> fitnessFunction,
                                     IGeneticAlgorithmParameters parameters, int numberOfThreads) {
        super();
        this.generationalGeneticAlgorithm = new GenerationalGeneticAlgorithm<>(genotypeFactory, fitnessFunction, parameters, numberOfThreads);


    }

    public DoubleArrayGenome run() {
        if (selector == null) return generationalGeneticAlgorithm.runAlgorithm(selection, crossing, mutation);
        else return generationalGeneticAlgorithm.runAlgorithm(selection, crossing, mutation, selector);
    }


    public GenerationalGeneticAlgorithm<DoubleArrayGenome> getGenerationalGeneticAlgorithm() {
        return generationalGeneticAlgorithm;
    }

    public void setSelector(IInserter<DoubleArrayGenome> selector) {
        this.selector = selector;
    }
}