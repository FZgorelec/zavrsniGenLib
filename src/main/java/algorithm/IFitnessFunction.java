package algorithm;

public interface IFitnessFunction<T extends IGenotype> {
    double calculateFitness(T genome);
}
