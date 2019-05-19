package algorithmImpl.GeneticProgramming;


public class Node implements INode<String> {


    private INode parent;
    private boolean isTerminating;
    private String value;
    private int childrenBelow;
    private INode[] children;
    private int currentChildren=0;



    public Node(INode parent, boolean isTerminating, String operation, int childrenBelow) {
        super();
        this.parent = parent;
        this.isTerminating = isTerminating;
        this.value = operation;
        this.childrenBelow = childrenBelow;
        children=new INode[3];

    }

    public INode[] getChildren() {
        return children;
    }





    public boolean isTerminating() {
        return isTerminating;
    }

    public void setTerminating(boolean isTerminating) {
        this.isTerminating = isTerminating;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getChildrenBelow() {
        return childrenBelow;
    }

    public void setChildrenBelow(int childrenBelow) {
        this.childrenBelow = childrenBelow;
    }

    public void addToChildren(INode node){
        children[currentChildren++]=node;
    }

    public INode getParent() {
        return parent;
    }

    public void setParent(INode parent) {
        this.parent = parent;
    }


    public void updateParents(int oldNumChildren) {
        if (this.getParent() == null) return;
        else {
            INode parent = this.getParent();
            int parentChildren = parent.getChildrenBelow();
            parent.setChildrenBelow(parentChildren + this.childrenBelow + 1 - oldNumChildren);
            parent.updateParents(parentChildren);
        }
    }

    public void replaceNode(INode newNode) {
        Node node = (Node) newNode;
        for (int i = 0; i < 3; i++) {
            children[i]=newNode.getChildren()[i];
        }
        this.isTerminating=node.isTerminating();
        this.setValue(node.getValue());
        this.childrenBelow=node.getChildrenBelow();
    }
}
