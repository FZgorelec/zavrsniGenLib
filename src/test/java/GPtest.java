import algorithm.GenerationalGeneticAlgorithm;
import algorithmImpl.GeneticProgramming.*;
import util.GeneticAlgorithmParameters;
import util.RandomNumberGenerator;

public class GPtest {

    public static void main(String[] args) {
        MovementTreeFactory fact=new MovementTreeFactory(8,100);
        GenerationalGeneticAlgorithm<ITree> ga=new GenerationalGeneticAlgorithm<>(fact,
                (ITree genome) -> {
                    return numberOfMoveNodes(genome.getHead());
                }, new GeneticAlgorithmParameters(30, 1000, 100));
        GeneticProgrammingAlgorithm gpa=new GeneticProgrammingAlgorithm(ga,fact,10,100 );
        ITree tree=gpa.run();
        System.out.println(tree.getFitness());
        printNodes(tree.getHead());
        System.out.println(tree);
    }
    private static double numberOfMoveNodes(INode node){
        double sum=0;
        if(node==null)return 0;
        for (int i = 0; i < node.getChildren().length; i++) {
            if(node.getChildren()[i]!=null)sum+=numberOfMoveNodes(node.getChildren()[i]);
        }
        if(((INode<String>)node).getValue().equals(MovementInstructions.MOVE.toString()))sum++;
        return sum;
    }
    private static void printNodes(INode node){
        System.out.println(node.getValue());
        for (int i = 0; i < node.getChildren().length; i++) {
            if(node.getChildren()[i]!=null)printNodes(node.getChildren()[i]);
        }

    }
}
