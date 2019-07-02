package crossing;

import algorithm.IGenotype;
import util.IRandomNumberGenerator;

public interface ICross<T extends IGenotype> {
    T[] cross(T parent1, T parent2, IRandomNumberGenerator random);

    int numberOfGeneratedChildren();
}
