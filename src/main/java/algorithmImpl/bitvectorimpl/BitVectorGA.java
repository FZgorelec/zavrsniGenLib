package algorithmImpl.bitvectorimpl;

import algorithmImpl.doublearrayimpl.DoubleArrayGenome;
import crossing.ICrossingAlgorithm;
import mutation.IMutationAlgorithm;
import selection.ISelectionAlgorithm;
import selection.impl.NTournamentSelectionWithRepetition;
import util.GABitVectorUtil;
import util.IRandomNumberGenerator;


abstract public class BitVectorGA {

    protected ISelectionAlgorithm<BitVectorGenome> selection;
    protected ICrossingAlgorithm<BitVectorGenome> crossing;
    protected IMutationAlgorithm<BitVectorGenome> mutation;

    public BitVectorGA() {
        init();
    }

    abstract BitVectorGenome run();

    protected void init() {
        selection = new NTournamentSelectionWithRepetition<>(5);
        crossing = new ICrossingAlgorithm<BitVectorGenome>() {
            @Override
            public BitVectorGenome[] cross(BitVectorGenome parent1, BitVectorGenome parent2, IRandomNumberGenerator random) {
                BitVectorGenome[] children = new BitVectorGenome[2];
                boolean[][] newVectors = GABitVectorUtil.crossNBreakpoints(parent1.getSolution(), parent2.getSolution(), 3, random);
                children[0] = new BitVectorGenome(newVectors[0]);
                children[1] = new BitVectorGenome(newVectors[1]);
                return children;
            }

            @Override
            public int numberOfGeneratedChildren() {
                return 2;
            }
        };
        mutation = (BitVectorGenome toMutate, IRandomNumberGenerator random) -> new BitVectorGenome(GABitVectorUtil.mutate(toMutate.getSolution(), 0.1, random));
    }

    public void setSelection(ISelectionAlgorithm<BitVectorGenome> selection) {
        this.selection = selection;
    }

    public void setCrossing(ICrossingAlgorithm<BitVectorGenome> crossing) {
        this.crossing = crossing;
    }

    public void setMutation(IMutationAlgorithm<BitVectorGenome> mutation) {
        this.mutation = mutation;
    }

}
