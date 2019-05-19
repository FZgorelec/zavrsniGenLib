package algorithmImpl.GeneticProgramming;

public class Tree implements ITree<String>{
    private INode<String> head;
    private int depth;
    private double fitness;

    public Tree(INode<String> head) {
        this.head=head;
        depth=1;

    }

    public ITree copyTree() {
        Tree tree = new Tree(new Node(null, false, head.getValue(), head.getChildrenBelow()));
        copyNodeRecursively(tree.getHead(), head);
        tree.setDepth(depth);
        tree.setFitness(fitness);
        return tree;
    }

    private void copyNodeRecursively(INode newNode, INode<String> toCopy ){
//        newNode.setChildrenBelow(toCopy.getChildrenBelow());
//        newNode.setValue(toCopy.getValue());
//        newNode.setTerminating(toCopy.isTerminating());
        INode<String>[] children=toCopy.getChildren();
        for (int i = 0,len=children.length; i < len; i++) {
            if(children[i]==null)return;
            else {
                INode child=new Node(newNode,children[i].isTerminating(),children[i].getValue(),children[i].getChildrenBelow());
                newNode.addToChildren(child);
                copyNodeRecursively(child, children[i]);
            }
        }
    }

    public INode getHead() {
        return head;
    }

    public void setHead(INode head) {
        this.head = head;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
