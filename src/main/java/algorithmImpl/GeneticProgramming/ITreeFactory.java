package algorithmImpl.GeneticProgramming;

import algorithm.IGenotypeFactory;
import util.IRandomNumberGenerator;

public interface ITreeFactory {
    ITree getRandomTree(int depth, IRandomNumberGenerator random);

}
