package algorithmImpl.bitvectorimpl;

import algorithm.GenerationalGeneticAlgorithm;
import algorithm.IFitnessFunction;
import algorithm.IGeneticAlgorithmParameters;
import algorithm.IGenotypeFactory;
import crossing.IInserter;

public class BitVectorGenerationalGA extends BitVectorGA{
    private GenerationalGeneticAlgorithm<BitVectorGenome> generationalGeneticAlgorithm;
    private IInserter<BitVectorGenome> selector = null;

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

    public void setSelector(IInserter<BitVectorGenome> selector) {
        this.selector = selector;
    }
}
