import algorithmImpl.GeneticProgramming.INode;
import algorithmImpl.GeneticProgramming.Node;

public class GPtest {

    public static void main(String[] args) {

        Node node=new Node(null,false,"yes", 3);
        INode[] nodes=node.getChildren();
        for (int i = 0; i < 3; i++) {
            System.out.println(nodes[i]);
        }
        node.setMiddleChild(new Node(null,true,"no", 0));
        nodes=node.getChildren();
        for (int i = 0; i < 3; i++) {
            System.out.println(nodes[i]);
        }

        System.out.println(node.getMiddleChild());
    }
}
