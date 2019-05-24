package algorithmImpl.GeneticProgramming;

import algorithm.IGenotypeFactory;
import util.IRandomNumberGenerator;
import util.RandomNumberGenerator;

import static algorithmImpl.GeneticProgramming.TreeUtil.countChildrenBelow;
import static algorithmImpl.GeneticProgramming.TreeUtil.depth;

public class MovementTreeFactory implements ITreeFactory, IGenotypeFactory<ITree> {

    private String[] terminatingValues;
    private String[] nonTerminatingValues;
    private double growProbability = 0.8;
    private IRandomNumberGenerator numberGenerator;
    private int maxDepth;
    private int maxNumberOfNodes;

    public MovementTreeFactory(int maxDepth,
                               int maxNumberOfNodes) {
        this.terminatingValues = new String[]{MovementInstructions.MOVE.toString(), MovementInstructions.ROTATE_LEFT.toString(), MovementInstructions.ROTATE_RIGHT.toString()};
        this.nonTerminatingValues = new String[]{ProgramInstructions.IF_FOOD_AHEAD.toString(),
                ProgramInstructions.PROG2.toString(), ProgramInstructions.PROG3.toString(),
                ProgramInstructions.IF_BOMB_AHEAD.toString(), ProgramInstructions.IF_WALL_AHEAD.toString()};
        numberGenerator = new RandomNumberGenerator();
        this.maxDepth = maxDepth;
        this.maxNumberOfNodes = maxNumberOfNodes;
    }

    @Override
    public ITree[] getPopulationOfGenotypes(int sizeOfPopulation) {
        ITree[] population = new ITree[sizeOfPopulation];
        for (int i = 0; i < sizeOfPopulation; i++) {
            population[i] = getRandomTree(numberGenerator.nextInt(1, maxDepth), numberGenerator);
        }
        return population;
    }

    @Override
    public ITree getRandomTree(int depth, IRandomNumberGenerator random) {
        return growTree(numberGenerator.nextInt(1, depth), random);
    }

    private Node generateTerminatingNode(INode parent, IRandomNumberGenerator random) {
        return new Node(parent, true, terminatingValues[random.nextInt(0, terminatingValues.length - 1)], 0);
    }

    private Node generateNonTerminatingNode(INode parent, IRandomNumberGenerator random) {
        return new Node(parent, false, nonTerminatingValues[random.nextInt(0, nonTerminatingValues.length - 1)], 0);
    }

    private Tree growTree(int depth, IRandomNumberGenerator random) {
        Tree tree = null;
        if (depth == 1) return new Tree(generateTerminatingNode(null, random));
        tree = new Tree(generateNonTerminatingNode(null, random));
        growFill(depth, tree.getHead(), 2, random);
        tree.setDepth(depth(tree.getHead()));
        countChildrenBelow(tree.getHead());
        return tree;
    }

    private boolean isBinary(String value) {
        if (value.equals(ProgramInstructions.PROG2.toString())
                || value.equals(ProgramInstructions.IF_WALL_AHEAD.toString())
                || value.equals(ProgramInstructions.IF_BOMB_AHEAD.toString())
                || value.equals(ProgramInstructions.IF_FOOD_AHEAD.toString()))
            return true;
        return false;
    }

    private boolean isTernary(String value) {
        if (value.equals(ProgramInstructions.PROG3.toString()))
            return true;
        return false;
    }

    private void growFill(int depth, INode<String> node, int nextDepth, IRandomNumberGenerator random) {
        if (node == null || node.isTerminating()) return;
        if (isBinary(node.getValue())) {
            if (nextDepth == depth) {
                node.addToChildren(generateTerminatingNode(node, random));
                node.addToChildren(generateTerminatingNode(node, random));
            } else {
                if (random.nextDouble() <= growProbability) {
                    node.addToChildren(generateTerminatingNode(node, random));
                } else {
                    node.addToChildren(generateNonTerminatingNode(node, random));
                    growFill(depth, node.getChildren()[0], 1 + nextDepth, random);
                }
                if (random.nextDouble() <= growProbability) {
                    node.addToChildren(generateTerminatingNode(node, random));
                } else {
                    node.addToChildren(generateNonTerminatingNode(node, random));
                    growFill(depth, node.getChildren()[1], 1 + nextDepth, random);
                }
            }
        } else {
            if (nextDepth == depth) {
                for (int i = 0; i < 3; i++) {
                    node.addToChildren(generateTerminatingNode(node, random));
                }

            } else {
                if (random.nextDouble() <= growProbability) {
                    node.addToChildren(generateTerminatingNode(node, random));
                } else {
                    node.addToChildren(generateNonTerminatingNode(node, random));
                    growFill(depth, node.getChildren()[0], 1 + nextDepth, random);
                }
                if (random.nextDouble() <= growProbability) {
                    node.addToChildren(generateTerminatingNode(node, random));
                } else {
                    node.addToChildren(generateNonTerminatingNode(node, random));
                    growFill(depth, node.getChildren()[1], 1 + nextDepth, random);
                }
                if (random.nextDouble() <= growProbability) {
                    node.addToChildren(generateTerminatingNode(node, random));
                } else {
                    node.addToChildren(generateNonTerminatingNode(node, random));
                    growFill(depth, node.getChildren()[2], 1 + nextDepth, random);
                }
            }
        }
    }
}
