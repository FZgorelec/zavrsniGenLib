package selection;

import util.IRandomNumberGenerator;

public interface ISelectionAlgorithm<T> {
    T select(T[] population, IRandomNumberGenerator random);
}
