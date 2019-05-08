package crossing;

import java.util.List;

public interface IInserter<T> {
    T[] newPopulation(T[] oldPopulation, List<T> children);
}
