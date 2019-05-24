import algorithm.GenerationalGeneticAlgorithm;
import algorithmImpl.GeneticProgramming.*;
import util.GeneticAlgorithmParameters;
import util.RandomNumberGenerator;

import java.util.List;

import static algorithmImpl.GeneticProgramming.MapValues.*;
public class GPtest {

    public static void main(String[] args) {
        test2();
    }

    private static void test1(){
        MovementTreeFactory fact=new MovementTreeFactory(8,100);
        GenerationalGeneticAlgorithm<ITree> ga=new GenerationalGeneticAlgorithm<>(fact,
                (ITree genome) -> {
                    return numberOfMoveNodes(genome.getHead());
                }, new GeneticAlgorithmParameters(30, 1000, 100));
        GeneticProgrammingAlgorithm gpa=new GeneticProgrammingAlgorithm(ga,fact,6,200 );
        ITree tree=gpa.run(false);
        System.out.println(tree.getFitness());
        printNodes(tree.getHead());
        System.out.println(tree);
    }

    private static void test2(){
        String[][] map=new String[][]{{OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),WALL.toString(),FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()},
                {OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()},
                {OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()},
                {OPENFIELD.toString(),OPENFIELD.toString(),FOOD.toString(),OPENFIELD.toString(),FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()},
                {OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()},
                {OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),FOOD.toString(),OPENFIELD.toString(),WALL.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()},
                {OPENFIELD.toString(),OPENFIELD.toString(),FOOD.toString(),OPENFIELD.toString(),WALL.toString(),WALL.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),FOOD.toString(),FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()},
                {OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),FOOD.toString(),FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()},
                {FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),FOOD.toString(),OPENFIELD.toString(),FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),FOOD.toString(),FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()}};
        MovementTreeFactory fact=new MovementTreeFactory(6,300);
        MovementTreeEvaluator evaluator=new MovementTreeEvaluator(map, 250);
        GenerationalGeneticAlgorithm<ITree> ga=new GenerationalGeneticAlgorithm<>(fact,
                (ITree genome) -> {
                    return evaluator.evaluate(genome);
                }, new GeneticAlgorithmParameters(21, 60000, 14.01));
        GeneticProgrammingAlgorithm gpa=new GeneticProgrammingAlgorithm(ga,fact,6,250 );
        ITree tree=gpa.run(true);

        printNodes(tree.getHead());
        System.out.println(tree);
        MovementDecoder decoder=new MovementDecoder(map, 250);
        List<String> movements= decoder.treeToMovementList(tree, 250);
        System.out.println("Potezi");
        for (String s :
                movements) {
            System.out.println(s);
        }
        System.out.println(tree.getFitness());
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
