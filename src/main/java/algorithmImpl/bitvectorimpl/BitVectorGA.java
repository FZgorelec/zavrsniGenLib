package algorithmImpl.bitvectorimpl;

import algorithmImpl.doublearrayimpl.DoubleArrayGenome;
import crossing.ICrossingAlgorithm;
import mutation.IMutationAlgorithm;
import selection.ISelectionAlgorithm;


abstract public class BitVectorGA {

    protected ISelectionAlgorithm<BitVectorGenome> selection;
    protected ICrossingAlgorithm<BitVectorGenome> crossing;
    protected IMutationAlgorithm<BitVectorGenome> mutation;

    public BitVectorGA() {
        init();
    }

    abstract DoubleArrayGenome run();

    protected void init() {
        //todo
//        BLXAlphaCross cross = new BLXAlphaCross(2, new Random());
//        GaussianMutationDouble mutator = new GaussianMutationDouble(0.3, 1);
//        selection = new NTournamentSelectionWithRepetition<>(10);
//        crossing = new ICrossingAlgorithm<DoubleArrayGenome>() {
//            @Override
//            public DoubleArrayGenome[] cross(DoubleArrayGenome parent1, DoubleArrayGenome parent2, IRandomNumberGenerator random) {
//                return new DoubleArrayGenome[]{new DoubleArrayGenome((cross.cross(parent1.getSolution(), parent2.getSolution(), random)))};
//            }
//
//            @Override
//            public int numberOfGeneratedChildren() {
//                return 1;
//            }
//        };
//        mutation = ((genome, random) -> new DoubleArrayGenome(mutator.mutate(genome.getSolution(), random)));

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
