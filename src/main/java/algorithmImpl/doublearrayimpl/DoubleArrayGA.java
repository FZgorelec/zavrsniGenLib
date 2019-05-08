package algorithmImpl.doublearrayimpl;

import crossing.ICrossingAlgorithm;
import mutation.IMutationAlgorithm;

import selection.ISelectionAlgorithm;
import selection.impl.NTournamentSelectionWithRepetition;
import util.GADoubleArrayUtilities;
import util.IRandomNumberGenerator;


public abstract class DoubleArrayGA {
    protected ISelectionAlgorithm<DoubleArrayGenome> selection;
    protected ICrossingAlgorithm<DoubleArrayGenome> crossing;
    protected IMutationAlgorithm<DoubleArrayGenome> mutation;

    public DoubleArrayGA() {
        init();
    }

    abstract DoubleArrayGenome run();

    protected void init() {


        selection = new NTournamentSelectionWithRepetition<>(10);
        crossing = new ICrossingAlgorithm<DoubleArrayGenome>() {
            @Override
            public DoubleArrayGenome[] cross(DoubleArrayGenome parent1, DoubleArrayGenome parent2, IRandomNumberGenerator random) {
                return new DoubleArrayGenome[]{new DoubleArrayGenome((GADoubleArrayUtilities.BLXAlphaCross(parent1.getSolution(), parent2.getSolution(), 2, random)))};
            }

            @Override
            public int numberOfGeneratedChildren() {
                return 1;
            }
        };
        mutation = ((genome, random) -> new DoubleArrayGenome(GADoubleArrayUtilities.gaussianMutation(genome.getSolution(),0.3,1, random)));

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
