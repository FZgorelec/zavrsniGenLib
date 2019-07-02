package selection;

import algorithm.IGenotype;
import util.IRandomNumberGenerator;

public interface ISelector<T extends IGenotype> {
    T select(T[] population, IRandomNumberGenerator random);
}
