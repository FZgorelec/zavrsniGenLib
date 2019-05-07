package mutation;

import util.IRandomNumberGenerator;

public interface IMutationAlgorithm<T> {
    T mutate(T genome, IRandomNumberGenerator random);
}
