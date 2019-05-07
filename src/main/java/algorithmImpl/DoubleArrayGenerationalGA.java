package algorithmImpl;

import algorithm.GenerationalGeneticAlgorithm;
import algorithm.IFitnessFunction;
import algorithm.IGeneticAlgorithmParameters;
import algorithm.IGenotypeFactory;
import crossing.ICrossingAlgorithm;
import crossing.impl.doubleArrayCrossing.BLXAlphaCross;
import mutation.IMutationAlgorithm;
import mutation.impl.doubleArrayMutation.GaussianMutationDouble;
import selection.ISelectionAlgorithm;
import selection.impl.NTournamentSelectionWithRepetition;
import util.IRandomNumberGenerator;


import java.util.Random;

public class DoubleArrayGenerationalGA {
    private GenerationalGeneticAlgorithm<DoubleArrayGenome> generationalGeneticAlgorithm;

    private ISelectionAlgorithm<DoubleArrayGenome> selection;
    private ICrossingAlgorithm<DoubleArrayGenome> crossing;
    private IMutationAlgorithm<DoubleArrayGenome> mutation;

    public DoubleArrayGenerationalGA(IGenotypeFactory<DoubleArrayGenome> genotypeFactory, IFitnessFunction<DoubleArrayGenome> fitnessFunction,
                                     IGeneticAlgorithmParameters parameters) {
        this.generationalGeneticAlgorithm=new GenerationalGeneticAlgorithm<>(genotypeFactory, fitnessFunction, parameters);
        init();
    }



    public DoubleArrayGenerationalGA(IGenotypeFactory<DoubleArrayGenome> genotypeFactory, IFitnessFunction<DoubleArrayGenome> fitnessFunction,
                                        IGeneticAlgorithmParameters parameters,int numberOfThreads) {
        this.generationalGeneticAlgorithm=new GenerationalGeneticAlgorithm<>(genotypeFactory, fitnessFunction, parameters,numberOfThreads);
        init();

    }

    public DoubleArrayGenome run(){
        return generationalGeneticAlgorithm.runAlgorithm(selection, crossing, mutation);
    }

    private void init() {
        BLXAlphaCross cross = new BLXAlphaCross(2, new Random());
        GaussianMutationDouble mutator = new GaussianMutationDouble(new Random(), 0.3, 1);
        selection=new NTournamentSelectionWithRepetition<>(10);
        crossing=new ICrossingAlgorithm<DoubleArrayGenome>() {
            @Override
            public DoubleArrayGenome[] cross(DoubleArrayGenome parent1, DoubleArrayGenome parent2, IRandomNumberGenerator random) {
                return new DoubleArrayGenome[]{new DoubleArrayGenome((cross.cross(parent1.getSolution(), parent2.getSolution(), random)))};
            }

            @Override
            public int numberOfGeneratedChildren() {
                return 1;
            }
        };
        mutation=((genome, random) -> new DoubleArrayGenome(mutator.mutate(genome.getSolution())));

    }

    public void setSelection(ISelectionAlgorithm<DoubleArrayGenome> selection) {
        this.selection = selection;
    }

    public void setCrossing(ICrossingAlgorithm<DoubleArrayGenome> crossing) {
        this.crossing = crossing;
    }

    public void setMutation(IMutationAlgorithm<DoubleArrayGenome> mutation) {
        this.mutation = mutation;
    }

    public GenerationalGeneticAlgorithm<DoubleArrayGenome> getGenerationalGeneticAlgorithm() {
        return generationalGeneticAlgorithm;
    }
}
