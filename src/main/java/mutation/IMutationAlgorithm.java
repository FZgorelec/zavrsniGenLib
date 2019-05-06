package mutation;

public interface IMutationAlgorithm<T> {
    T mutate(T genome);
}
