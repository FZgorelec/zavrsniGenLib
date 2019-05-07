package crossing;

import util.IRandomNumberGenerator;

public interface ICrossingAlgorithm<T> {
    T[] cross(T parent1, T parent2, IRandomNumberGenerator random);
    int numberOfGeneratedChildren();
}
