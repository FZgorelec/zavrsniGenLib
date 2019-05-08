package algorithmImpl.bitvectorimpl;

import algorithmImpl.Genome;

public class BitVectorGenome extends Genome {

    private boolean[] bits;

    public BitVectorGenome(boolean[] bits, double fitness) {
        super(fitness);
        this.bits = bits;
    }

    public BitVectorGenome(boolean[] solution) {
        this.bits=solution;
    }

    public boolean[] getSolution() {
        return bits;
    }

}
