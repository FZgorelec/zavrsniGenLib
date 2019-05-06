package algorithm;

public interface IFitnessFunction<T> {
    double calculateFitness(T genome);
}
