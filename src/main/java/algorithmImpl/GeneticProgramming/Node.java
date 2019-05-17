package algorithmImpl.GeneticProgramming;


public class Node implements INode<String> {

        private Node leftChild;
        private Node rightChild;
        private Node parent;
        private boolean isTerminating;
        private String value;
        private int childrenBelow;
        private Node middleChild;


        public Node(Node parent, boolean isTerminating, String operation, int childrenBelow) {
            super();
            this.parent = parent;
            this.isTerminating = isTerminating;
            this.value = operation;
            this.childrenBelow = childrenBelow;

        }

        public INode[] getChildren(){
            return new Node[]{leftChild,rightChild,middleChild};
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public Node getMiddleChild() {
            return middleChild;
        }

        public void setMiddleChild(Node middleChild) {
            this.middleChild = middleChild;
        }



        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
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



        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public String print(String prefix, boolean isTail) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + (value));
            return (prefix + (isTail ? "└── " : "├── ") + (value)+"\n")
                    +((leftChild != null) ? (leftChild.print(prefix + (isTail ? "    " : "│   "), false) ): (""))
                    +((middleChild != null) ?middleChild.print(prefix + (isTail ? "    " : "│   "), false):(""))
                    +((rightChild != null)?rightChild.print(prefix + (isTail ? "    " : "│   "), true):(""));
        }

        public void updateParent(int oldNumChildren){
            if(this.getParent()==null)return;
            else{
                INode parent=this.getParent();
                int parentChildren=((Node) parent).getChildrenBelow();
                parent.setChildrenBelow(parentChildren+this.childrenBelow+1-oldNumChildren);
                parent.updateParent(parentChildren);
            }
        }
}
