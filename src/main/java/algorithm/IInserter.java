package algorithm;

import util.IRandomNumberGenerator;

public interface IInserter<T extends IGenotype> {
    void insert (T[] population, T[] children, IRandomNumberGenerator random);
}
