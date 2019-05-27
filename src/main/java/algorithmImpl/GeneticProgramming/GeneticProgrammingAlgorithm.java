package algorithmImpl.GeneticProgramming;

import algorithm.GenerationalGeneticAlgorithm;
import algorithm.IGenotype;
import crossing.ICrossingAlgorithm;
import mutation.IMutationAlgorithm;
import selection.ISelectionAlgorithm;
import selection.impl.NTournamentSelectionWithRepetition;
import util.IRandomNumberGenerator;

import static algorithmImpl.GeneticProgramming.TreeUtil.depth;

public class GeneticProgrammingAlgorithm {
    private ISelectionAlgorithm<ITree> selection;
    private ICrossingAlgorithm<ITree> crossing;
    private IMutationAlgorithm<ITree> mutation;
    private ITreeFactory factory;
    private GenerationalGeneticAlgorithm<ITree> geneticAlgorithm;

    public GeneticProgrammingAlgorithm(GenerationalGeneticAlgorithm<ITree> geneticAlgorithm, ITreeFactory treeFactory, int maxTreeDepth, int maxNumberOfNodes) {
        this.factory = treeFactory;
        this.geneticAlgorithm = geneticAlgorithm;
        selection = new NTournamentSelectionWithRepetition(11);
        mutation = (ITree genome, IRandomNumberGenerator random) -> {
            ITree treeCopy = genome.copyTree();
            INode pickedNode = pickRandomNode(random.nextInt(1, treeCopy.getHead().getChildrenBelow() + 1), 1, treeCopy.getHead());
            int oldChildrenBelow = pickedNode.getChildrenBelow();
            for (int i = 0; i < 5; i++) {
                ITree newTree = factory.getRandomTree(random.nextInt(1, maxTreeDepth - getNumberOfParents(pickedNode)), random);
                if (treeCopy.getHead().getChildrenBelow() + 1 + newTree.getHead().getChildrenBelow() + 1 - pickedNode.getChildrenBelow() <= maxNumberOfNodes) {
                    pickedNode.replaceNode(newTree.getHead());
                    pickedNode.updateParents(oldChildrenBelow);
                    treeCopy.setDepth(depth(treeCopy.getHead()));
                    return treeCopy;
                }
            }
            return treeCopy;
        };
        crossing = new ICrossingAlgorithm<ITree>() {
            @Override
            public ITree[] cross(ITree parent1, ITree parent2, IRandomNumberGenerator random) {
                ITree[] children = new ITree[]{parent1.copyTree()};
                INode nodeFromParent1 = pickRandomNode(random.nextInt(1, parent1.getHead().getChildrenBelow() + 1), 1, children[0].getHead());
                int parentsOfNode1 = getNumberOfParents(nodeFromParent1);
                int childrenBefore = nodeFromParent1.getChildrenBelow();
                for (int i = 0; i < 5; i++) {
                    INode nodeFromParent2 = pickRandomNode(random.nextInt(1, parent2.getHead().getChildrenBelow() + 1), 1, parent2.getHead());
                    if ((parentsOfNode1 + depth(nodeFromParent2) <= maxTreeDepth) && (parent1.getHead().getChildrenBelow() + 1 - nodeFromParent1.getChildrenBelow() + nodeFromParent2.getChildrenBelow()) <= maxNumberOfNodes) {
                        nodeFromParent1.replaceNode(nodeFromParent2);
                        nodeFromParent1.updateParents(childrenBefore);
                        break;
                    }
                }
                children[0].setDepth(depth(children[0].getHead()));
                return children;
            }

            @Override
            public int numberOfGeneratedChildren() {
                return 1;
            }
        };

    }


    private int getNumberOfParents(INode node) {
        if (node.getParent() == null) return 0;
        else return 1 + getNumberOfParents(node.getParent());
    }

    private INode pickRandomNode(int nodeNumber, int currentNumber, INode node) {
        //todo
        INode chosenNode = null;
        if (node == null) return null;
        if (currentNumber == nodeNumber) return node;
        else {
            INode[] children = node.getChildren();
            chosenNode = pickRandomNode(nodeNumber, currentNumber + 1, children[0]);
            if (chosenNode != null) return chosenNode;
            for (int i = 1; i < children.length; i++) {
                if (children[i - 1] == null) break;
                currentNumber += 1 + children[i - 1].getChildrenBelow();
                chosenNode = pickRandomNode(nodeNumber, currentNumber + 1, children[i]);
                if (chosenNode != null) return chosenNode;
            }
        }
        return chosenNode;
    }

    public ITree run(boolean keepBestOfGen) {

        if (keepBestOfGen) {
            return geneticAlgorithm.runAlgorithm(selection, crossing, mutation, (oldPopulation, children, random) -> {
                double bestFit = -Double.MAX_VALUE;
                int bestIndex = 0;
                for (int i = 0; i < oldPopulation.length; i++) {

                    if (oldPopulation[i].getFitness() > bestFit) {
                        bestIndex = i;
                        bestFit = oldPopulation[i].getFitness();
                    }

                }
                for (int i = 0; i < oldPopulation.length; i++) {
                    if (i != bestIndex) oldPopulation[i] = children.get(i);
                }
            });
        } else return geneticAlgorithm.runAlgorithm(selection, crossing, mutation);
    }
}
