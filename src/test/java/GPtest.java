import algorithm.GenerationalGeneticAlgorithm;
import algorithmImpl.GeneticProgramming.*;
import util.GeneticAlgorithmParameters;
import util.RandomNumberGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        long startTime = System.currentTimeMillis();
        String[][] map=new String[][]{{OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(), OPENFIELD.toString(),OPENFIELD.toString(),FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()},
                {OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()},
                {WALL.toString(),WALL.toString(),WALL.toString(),WALL.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),FOOD.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()},
                {OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),FOOD.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()},
                {FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()},
                {OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),FOOD.toString(),OPENFIELD.toString(),WALL.toString(),WALL.toString(),OPENFIELD.toString(),FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString()},
                {OPENFIELD.toString(),OPENFIELD.toString(),FOOD.toString(),OPENFIELD.toString(),WALL.toString(),WALL.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),FOOD.toString(),FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()},
                {OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()},
                {FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),WALL.toString(),FOOD.toString(),OPENFIELD.toString(),FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),FOOD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString(),OPENFIELD.toString()}};
        MovementTreeFactory fact=new MovementTreeFactory(7,200);
        MovementTreeEvaluator evaluator=new MovementTreeEvaluator(map, 150);
        GenerationalGeneticAlgorithm<ITree> ga=new GenerationalGeneticAlgorithm<>(fact,
                (ITree genome) -> {
                    if(Thread.currentThread() instanceof EvaluatorThread)return ((EvaluatorThread)Thread.currentThread()).getEvaluator().evaluate(genome);
                    else
                        return evaluator.evaluate(genome);
                }, new GeneticAlgorithmParameters(141, 5000, 14.01),0);
        ga.setThreadFactory(new EvaluatorThreadFactory(map,250));
        GeneticProgrammingAlgorithm gpa=new GeneticProgrammingAlgorithm(ga,fact,7,200 );
        ITree tree=gpa.run(true);
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds: " + timeElapsed);
        MovementDecoder decoder=new MovementDecoder(map, 250);
        List<String> movements= decoder.treeToMovementList(tree, 250);
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
