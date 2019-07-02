package algorithm;

import util.IRandomNumberGenerator;

import java.util.List;

public interface INextPopulationGenerator<T extends IGenotype> {
    void nextPopulation(T[] population, List<T> children, IRandomNumberGenerator random);
}
