package mutation;

import algorithm.IGenotype;
import util.IRandomNumberGenerator;

public interface IMutator<T extends IGenotype> {
    T mutate(T genome, IRandomNumberGenerator random);
}
