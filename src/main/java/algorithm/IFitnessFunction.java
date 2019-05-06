package algorithm;

public interface IFitnessFunction<T> {
    double getFitness(T genome);
}
