package algorithmImpl.GeneticProgramming;

public interface INode<T> {
    public INode[] getChildren();
    public int getChildrenBelow();
    void updateParent(int oldNumChildren);
    public void setChildrenBelow(int childrenBelow);
}
