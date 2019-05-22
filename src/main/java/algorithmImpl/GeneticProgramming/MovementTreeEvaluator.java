package algorithmImpl.GeneticProgramming;

import java.util.List;

import static algorithmImpl.GeneticProgramming.MapValues.*;
import static algorithmImpl.GeneticProgramming.MovementInstructions.*;
import static algorithmImpl.GeneticProgramming.ProgramInstructions.*;

public class MovementTreeEvaluator extends MovementTreeNavigator {
    private double fitness;
    private double rotationPunish;
    private double wallHitPunish;

    public MovementTreeEvaluator(String[][] map, int numberOfSteps) {
        super(map, numberOfSteps);
        rotationPunish=0.09/numberOfSteps;
        wallHitPunish=0.98/numberOfSteps;
    }

    public double evaluate(ITree<String> tree) {
        int numOfStepsCopy = numberOfSteps;
        fitness = 0;
        position[0] = 0;
        position[1] = 0;
        position[2] = 1;

        copyMap();
        traverseTree(tree.getHead(), tree.getHead(), 1);
        numberOfSteps = numOfStepsCopy;
        return fitness;
    }


    private void traverseTree(INode<String> node, INode<String> headNode, int currentNodeIndex) {
        if (numberOfSteps == 0) return;
        if (node.isTerminating()) {
            String operation = node.getValue();
            numberOfSteps--;
            if (operation.equals(MOVE.toString())) {
                int oldX = position[0];
                int oldY = position[1];
                updatePositionMove();
                if (oldX == position[0] && oldY == position[1]) fitness -= wallHitPunish;
                if (mapCopy[position[1]][position[0]].equals(FOOD.toString())) {
                    mapCopy[position[1]][position[0]] = OPENFIELD.toString();
                    fitness += 1;
                } else if (mapCopy[position[1]][position[0]].equals(BOMB.toString())) {
                    mapCopy[position[1]][position[0]] = OPENFIELD.toString();
                    fitness -= 1000000;
                }
            } else if (operation.equals(ROTATE_RIGHT.toString())) {
                position[2] = (position[2] + 1) % 4;
                fitness -= rotationPunish;
            } else if (operation.equals(ROTATE_LEFT.toString())) {
                position[2] = (position[2] - 1) % 4;
                fitness -= rotationPunish;
            }
            if (currentNodeIndex == headNode.getChildrenBelow() + 1) traverseTree(headNode, headNode, 1);
        } else {

            if (node.getValue().equals(IF_FOOD_AHEAD.toString())) {
                if (isFoodAhead())
                    traverseTree(node.getChildren()[0], headNode, currentNodeIndex + 2 + node.getChildren()[1].getChildrenBelow());
                else
                    traverseTree(node.getChildren()[1], headNode, currentNodeIndex + 2 + node.getChildren()[0].getChildrenBelow());

            } else if (node.getValue().equals(IF_WALL_AHEAD.toString())) {
                if (isWallAhead())
                    traverseTree(node.getChildren()[0], headNode, currentNodeIndex + 2 + node.getChildren()[1].getChildrenBelow());
                else
                    traverseTree(node.getChildren()[1], headNode, currentNodeIndex + 2 + node.getChildren()[0].getChildrenBelow());
            } else if (node.getValue().equals(IF_BOMB_AHEAD.toString())) {
                if (isBombAhead())
                    traverseTree(node.getChildren()[0], headNode, currentNodeIndex + 2 + node.getChildren()[1].getChildrenBelow());
                else
                    traverseTree(node.getChildren()[1], headNode, currentNodeIndex + 2 + node.getChildren()[0].getChildrenBelow());
            } else if (node.getValue().equals(PROG2.toString())) {
                traverseTree(node.getChildren()[0], headNode, currentNodeIndex + 1);
                traverseTree(node.getChildren()[1], headNode, currentNodeIndex + 2 + node.getChildren()[0].getChildrenBelow());
            } else {
                traverseTree(node.getChildren()[0], headNode, currentNodeIndex + 1);
                traverseTree(node.getChildren()[1], headNode, currentNodeIndex + 2 + node.getChildren()[0].getChildrenBelow());
                traverseTree(node.getChildren()[2], headNode, currentNodeIndex + 3 + node.getChildren()[0].getChildrenBelow() + node.getChildren()[1].getChildrenBelow());
            }

        }
    }

}
