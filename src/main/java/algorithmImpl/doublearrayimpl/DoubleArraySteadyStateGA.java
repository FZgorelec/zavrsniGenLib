package algorithmImpl.doublearrayimpl;

import algorithm.*;


public class DoubleArraySteadyStateGA extends DoubleArrayGA {
    private SteadyStateGeneticAlgorithm<DoubleArrayGenome> steadyStateGeneticAlgorithm;
    private INextPopulationGenerator<DoubleArrayGenome> selector = null;

    public DoubleArraySteadyStateGA(IGenotypeFactory<DoubleArrayGenome> genotypeFactory, IFitnessFunction<DoubleArrayGenome> fitnessFunction,
                                    IGeneticAlgorithmParameters parameters) {
        super();
        this.steadyStateGeneticAlgorithm = new SteadyStateGeneticAlgorithm<>(genotypeFactory, fitnessFunction, parameters);


    }

    public DoubleArrayGenome run() {
        if (selector == null) return steadyStateGeneticAlgorithm.runAlgorithm(selection, crossing, mutation);
        else return steadyStateGeneticAlgorithm.runAlgorithm(selection, crossing, mutation, selector);
    }

    public void setSelector(INextPopulationGenerator<DoubleArrayGenome> selector) {
        this.selector = selector;
    }
}
