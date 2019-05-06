package selection;

public interface ISelectionAlgorithm<T> {
    T select(T[] population);
}
