package algorithmImpl.GeneticProgramming;

import algorithm.IGenotypeFactory;

public interface ITreeFactory extends IGenotypeFactory<ITree> {
    ITree getRandomTree();

}
