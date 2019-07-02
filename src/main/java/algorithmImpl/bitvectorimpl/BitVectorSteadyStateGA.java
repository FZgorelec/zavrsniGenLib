package algorithmImpl.bitvectorimpl;

import algorithm.*;

public class BitVectorSteadyStateGA extends BitVectorGA{
    private SteadyStateGeneticAlgorithm<BitVectorGenome> steadyStateGeneticAlgorithm;
    private INextPopulationGenerator<BitVectorGenome> selector = null;

    public BitVectorSteadyStateGA(IGenotypeFactory<BitVectorGenome> genotypeFactory, IFitnessFunction<BitVectorGenome> fitnessFunction,
                                    IGeneticAlgorithmParameters parameters) {
        super();
        this.steadyStateGeneticAlgorithm = new SteadyStateGeneticAlgorithm<>(genotypeFactory, fitnessFunction, parameters);


    }

    public BitVectorGenome run() {
        if (selector == null) return steadyStateGeneticAlgorithm.runAlgorithm(selection, crossing, mutation);
        else return steadyStateGeneticAlgorithm.runAlgorithm(selection, crossing, mutation, selector);
    }

    public void setSelector(INextPopulationGenerator<BitVectorGenome> selector) {
        this.selector = selector;
    }
}
