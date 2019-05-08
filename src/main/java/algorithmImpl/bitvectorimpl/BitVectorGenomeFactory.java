package algorithmImpl.bitvectorimpl;

import algorithmImpl.GenomeFactory;

public class BitVectorGenomeFactory extends GenomeFactory<BitVectorGenome> {

    private int bitVectorSize;

    public BitVectorGenomeFactory(int bitVectorSize){
        this.bitVectorSize=bitVectorSize;
    }


    @Override
    protected BitVectorGenome createRandomSolution() {
       boolean[] bits=new boolean[bitVectorSize];
        for (int i = 0; i < bitVectorSize; i++) {
            bits[i]= rand.nextDouble() <= 0.5;
        }
       return new BitVectorGenome(bits);
    }

    @Override
    protected BitVectorGenome[] createEmptyArray(int sizeOfArray) {
        return new BitVectorGenome[sizeOfArray];
    }

    public void setBitVectorSize(int bitVectorSize) {
        if(bitVectorSize>0)this.bitVectorSize = bitVectorSize;
        else throw new IllegalArgumentException("Bit vector size must be greater than 0");
    }
}
