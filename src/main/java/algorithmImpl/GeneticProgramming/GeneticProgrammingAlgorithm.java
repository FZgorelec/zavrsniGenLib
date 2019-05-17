package algorithmImpl.GeneticProgramming;

import algorithm.GenerationalGeneticAlgorithm;
import crossing.ICrossingAlgorithm;
import mutation.IMutationAlgorithm;
import selection.ISelectionAlgorithm;
import selection.impl.NTournamentSelectionWithRepetition;
import util.IRandomNumberGenerator;

public class GeneticProgrammingAlgorithm<T extends ITree> {
    private ISelectionAlgorithm<T> selection;
    private ICrossingAlgorithm<T> crossing;
    private IMutationAlgorithm<T> mutation;
    private ITreeFactory<T> factory;

    public GeneticProgrammingAlgorithm(ITreeFactory<T> treeFactory){
        this.factory=treeFactory;
        selection = new NTournamentSelectionWithRepetition(10);
        mutation=(ITree genome, IRandomNumberGenerator random) -> {
            INode pickedNode=pickRandomNode(random.nextInt(1, genome.getNumberOfNodes()), 0, genome.getHead());
            ITree newTree=factory.getRandomTree();
            replaceNode(pickedNode,newTree.getHead());
            //todo
            return null;
        };
    }


    private void replaceNode(INode oldNode,INode newNode){
        //todo
    }

    private INode pickRandomNode(int nodeNumber, int currentNumber, INode node) {
        INode chosenNode = null;
        if (currentNumber == nodeNumber) return node;
        else {
            INode[] children=node.getChildren();
            for (int i = 0; i < children.length; i++) {
                chosenNode=pickRandomNode(nodeNumber, currentNumber+1, children[i]);
                if(!(chosenNode==null))return chosenNode;
            }
        }
        return chosenNode;
    }
}
