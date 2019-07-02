package algorithmImpl.bitvectorimpl;

import algorithm.*;


public class BitVectorGenerationalGA extends BitVectorGA{
    private GenerationalGeneticAlgorithm<BitVectorGenome> generationalGeneticAlgorithm;
    private INextPopulationGenerator<BitVectorGenome> selector = null;

    public BitVectorGenerationalGA(IGenotypeFactory<BitVectorGenome> genotypeFactory, IFitnessFunction<BitVectorGenome> fitnessFunction,
                                     IGeneticAlgorithmParameters parameters) {
        super();
        this.generationalGeneticAlgorithm = new GenerationalGeneticAlgorithm<>(genotypeFactory, fitnessFunction, parameters);

    }


    public BitVectorGenerationalGA(IGenotypeFactory<BitVectorGenome> genotypeFactory, IFitnessFunction<BitVectorGenome> fitnessFunction,
                                     IGeneticAlgorithmParameters parameters, int numberOfThreads) {
        super();
        this.generationalGeneticAlgorithm = new GenerationalGeneticAlgorithm<>(genotypeFactory, fitnessFunction, parameters, numberOfThreads);


    }

    public BitVectorGenome run() {
        if (selector == null) return generationalGeneticAlgorithm.runAlgorithm(selection, crossing, mutation);
        else return generationalGeneticAlgorithm.runAlgorithm(selection, crossing, mutation, selector);
    }


    public GenerationalGeneticAlgorithm<BitVectorGenome> getGenerationalGeneticAlgorithm() {
        return generationalGeneticAlgorithm;
    }

    public void setSelector(INextPopulationGenerator<BitVectorGenome> selector) {
        this.selector = selector;
    }
}
