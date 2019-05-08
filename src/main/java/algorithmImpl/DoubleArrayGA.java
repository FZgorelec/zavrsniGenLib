package algorithmImpl;

import crossing.ICrossingAlgorithm;
import crossing.impl.doubleArrayCrossing.BLXAlphaCross;
import mutation.IMutationAlgorithm;
import mutation.impl.doubleArrayMutation.GaussianMutationDouble;
import selection.ISelectionAlgorithm;
import selection.impl.NTournamentSelectionWithRepetition;
import util.IRandomNumberGenerator;

import java.util.Random;

public abstract class DoubleArrayGA {
    protected ISelectionAlgorithm<DoubleArrayGenome> selection;
    protected ICrossingAlgorithm<DoubleArrayGenome> crossing;
    protected IMutationAlgorithm<DoubleArrayGenome> mutation;

    public DoubleArrayGA() {
        init();
    }

    abstract DoubleArrayGenome run();

    protected void init() {
        BLXAlphaCross cross = new BLXAlphaCross(2, new Random());
        GaussianMutationDouble mutator = new GaussianMutationDouble(0.3, 1);
        selection = new NTournamentSelectionWithRepetition<>(10);
        crossing = new ICrossingAlgorithm<DoubleArrayGenome>() {
            @Override
            public DoubleArrayGenome[] cross(DoubleArrayGenome parent1, DoubleArrayGenome parent2, IRandomNumberGenerator random) {
                return new DoubleArrayGenome[]{new DoubleArrayGenome((cross.cross(parent1.getSolution(), parent2.getSolution(), random)))};
            }

            @Override
            public int numberOfGeneratedChildren() {
                return 1;
            }
        };
        mutation = ((genome, random) -> new DoubleArrayGenome(mutator.mutate(genome.getSolution(), random)));

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
}
