package algorithmImpl.GeneticProgramming;

import java.util.ArrayList;
import java.util.List;

import static algorithmImpl.GeneticProgramming.MovementInstructions.*;
import static algorithmImpl.GeneticProgramming.ProgramInstructions.*;
import static algorithmImpl.GeneticProgramming.MapValues.*;

public class MovementDecoder extends MovementTreeNavigator {


    public MovementDecoder(String[][] map, int numberOfSteps) {
        super(map, numberOfSteps);
    }

    public List<String> treeToMovementList(ITree<String> tree, int numberOfSteps) {
        List<String> moves = new ArrayList<>();
        position[0] = 0;
        position[1] = 0;
        position[2] = 1;
        this.numberOfSteps = numberOfSteps;
        copyMap();
        fillMovesList(moves, tree.getHead(), tree.getHead(), 1);
        return moves;
    }


    private void fillMovesList(List<String> list, INode<String> node, INode<String> headNode, int currentNodeIndex) {
//        int[] newPosition=new int[3];
        if (numberOfSteps == 0) return;
        if (node.isTerminating()) {
            String operation = node.getValue();
            list.add(new String(operation));
            numberOfSteps--;
            if (operation.equals(MOVE.toString())) {
                updatePositionMove();
                if (mapCopy[position[1]][position[0]].equals(FOOD.toString()) || mapCopy[position[1]][position[0]].equals(BOMB.toString())) {
                    mapCopy[position[1]][position[0]] = OPENFIELD.toString();
                }
            } else if (operation.equals(ROTATE_RIGHT.toString())) {
                position[2] = (position[2] + 1) % 4;
            } else if (operation.equals(ROTATE_LEFT.toString())) {
                position[2] = (position[2] - 1 + 4) % 4;
            }
            if (currentNodeIndex == headNode.getChildrenBelow() + 1) fillMovesList(list, headNode, headNode, 1);
        } else {
            if (node.getValue().equals(IF_FOOD_AHEAD.toString())) {
                if (isFoodAhead())
                    fillMovesList(list, node.getChildren()[0], headNode, currentNodeIndex + 2 + node.getChildren()[1].getChildrenBelow());
                else
                    fillMovesList(list, node.getChildren()[1], headNode, currentNodeIndex + 2 + node.getChildren()[0].getChildrenBelow());

            } else if (node.getValue().equals(IF_BOMB_AHEAD.toString())) {
                if (isBombAhead())
                    fillMovesList(list, node.getChildren()[0], headNode, currentNodeIndex + 2 + node.getChildren()[1].getChildrenBelow());
                else
                    fillMovesList(list, node.getChildren()[1], headNode, currentNodeIndex + 2 + node.getChildren()[0].getChildrenBelow());
            } else if (node.getValue().equals(IF_WALL_AHEAD.toString())) {
                if (isWallAhead())
                    fillMovesList(list, node.getChildren()[0], headNode, currentNodeIndex + 2 + node.getChildren()[1].getChildrenBelow());
                else
                    fillMovesList(list, node.getChildren()[1], headNode, currentNodeIndex + 2 + node.getChildren()[0].getChildrenBelow());
            } else if (node.getValue().equals(PROG2.toString())) {
                fillMovesList(list, node.getChildren()[0], headNode, currentNodeIndex + 1);
                fillMovesList(list, node.getChildren()[1], headNode, currentNodeIndex + 2 + node.getChildren()[0].getChildrenBelow());
            } else {
                fillMovesList(list, node.getChildren()[0], headNode, currentNodeIndex + 1);
                fillMovesList(list, node.getChildren()[1], headNode, currentNodeIndex + 2 + node.getChildren()[0].getChildrenBelow());
                fillMovesList(list, node.getChildren()[2], headNode, currentNodeIndex + 3 + node.getChildren()[0].getChildrenBelow() + node.getChildren()[1].getChildrenBelow());
            }

        }
    }


}
