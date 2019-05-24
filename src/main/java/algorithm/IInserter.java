package algorithm;

import util.IRandomNumberGenerator;

import java.util.List;

public interface IInserter<T extends IGenotype> {
    void insert (T[] population, List<T> children, IRandomNumberGenerator random);
}
