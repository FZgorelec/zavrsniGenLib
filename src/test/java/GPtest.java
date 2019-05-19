import algorithm.GenerationalGeneticAlgorithm;
import algorithmImpl.GeneticProgramming.*;
import util.GeneticAlgorithmParameters;
import util.RandomNumberGenerator;

public class GPtest {

    public static void main(String[] args) {
        MovementTreeFactory fact=new MovementTreeFactory(8,100);
        GenerationalGeneticAlgorithm<ITree> ga=new GenerationalGeneticAlgorithm<>(fact,
                genome -> 0, new GeneticAlgorithmParameters(30, 1000, 100));
        GeneticProgrammingAlgorithm gpa=new GeneticProgrammingAlgorithm(ga,fact,10,100 );
        ITree tree=gpa.run();
        System.out.println(tree);
    }
}
