package algorithmImpl.GeneticProgramming;

public interface INode<T> {
    INode[] getChildren();

    int getChildrenBelow();

    void updateParents(int oldNumChildren);

    void setChildrenBelow(int childrenBelow);

    void replaceNode(INode newNode);

    T getValue();

    void setValue(T value);

    void addToChildren(INode node);

    boolean isTerminating();

    void setTerminating(boolean isTerminating);

    public INode getParent();

    public void setParent(INode parent);
}
