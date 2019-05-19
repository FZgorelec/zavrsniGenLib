package algorithmImpl.GeneticProgramming;

import algorithm.IGenotype;

public interface ITree<T> extends IGenotype {
    INode<T> getHead();
    ITree<T> copyTree();
    void setHead(INode head);
    int getDepth();
    void setDepth(int depth);
    double getFitness();
    void setFitness(double fitness);
}
