package algorithmImpl;

import algorithm.*;


public class DoubleArraySteadyStateGA extends DoubleArrayGA {
    private SteadyStateGeneticAlgorithm<DoubleArrayGenome> steadyStateGeneticAlgorithm;
    private IInserter<DoubleArrayGenome> selector = null;

    public DoubleArraySteadyStateGA(IGenotypeFactory<DoubleArrayGenome> genotypeFactory, IFitnessFunction<DoubleArrayGenome> fitnessFunction,
                                    IGeneticAlgorithmParameters parameters) {
        super();
        this.steadyStateGeneticAlgorithm = new SteadyStateGeneticAlgorithm<>(genotypeFactory, fitnessFunction, parameters);


    }

    public DoubleArrayGenome run() {
        if (selector == null) return steadyStateGeneticAlgorithm.runAlgorithm(selection, crossing, mutation);
        else return steadyStateGeneticAlgorithm.runAlgorithm(selection, crossing, mutation, selector);
    }

    public void setSelector(IInserter<DoubleArrayGenome> selector) {
        this.selector = selector;
    }
}
