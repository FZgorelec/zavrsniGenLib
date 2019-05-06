package crossing;

import java.util.List;

public interface IChildrenSelectionAlgorithm<T> {
    T[] newPopulation(T[] oldPopulation, List<T> children);
}
