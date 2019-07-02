package algorithmImpl.GeneticProgramming;

import java.util.*;

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

    public List<String> removeCycles(List<String> movementList){
        position[0] = 0;
        position[1] = 0;
        position[2] = 1;
        copyMap();
        List<String> copy=new ArrayList<>();
        for (String s :movementList) {
            copy.add(s);
        }
        List<Position> positions=new ArrayList<>();
        positions.add(new Position(position[0],position[1],position[2],map[position[1]][position[0]].equals(FOOD.toString()),0));
        for (int i = 0; i < movementList.size(); i++) {
            if(movementList.get(i).equals(ROTATE_LEFT.toString())){
                positions.add(new Position(position[0],position[1],(position[2]-1+4)%4,false,i));
                position[2]=(position[2]-1+4)%4;
            }
            else if(movementList.get(i).equals(ROTATE_RIGHT.toString())){
                positions.add(new Position(position[0],position[1],(position[2]+1)%4,false,i));
                position[2]=(position[2]+1)%4;
            }
            else {
                updatePositionMove();
                boolean foodOnPosition=mapCopy[position[1]][position[0]].equals(FOOD.toString());
                mapCopy[position[1]][position[0]]= OPENFIELD.toString();
                positions.add(new Position(position[0],position[1],position[2],foodOnPosition,i));
            }
        }
        List<int[]> toRemove=new ArrayList<>();
        List<int[]> rotationsToAdd=new ArrayList<>();
        for (int j = 0; j < positions.size(); j++) {
            boolean foodEaten=false;
            boolean atLeastOneMove=false;
            for (int i = j+1; i < positions.size(); i++) {
                if(positions.get(j).x!=positions.get(i).x||positions.get(j).y!=positions.get(i).y) atLeastOneMove=true;
                if(!foodEaten)foodEaten=positions.get(i).foodEaten;
                if(atLeastOneMove&&positions.get(j).x==positions.get(i).x&&positions.get(j).y==positions.get(i).y&&!foodEaten){

                    toRemove.add(new int[]{j,i-j});
//                    int k=i;
//                    while(copy.get(k).equals(ROTATE_RIGHT.toString())||copy.get(k).equals(ROTATE_LEFT.toString())){
//                        toRemove.get(toRemove.size()-1)[1]++;
//                        k++;
//                    }
                   int numOrRotations=0;
                    while(positions.get(j).rotation!=positions.get(i).rotation){
                        positions.get(j).rotation=(positions.get(j).rotation+1)%4;
                       // copy.add(j+numOrRotations, ROTATE_RIGHT.toString());
                        //positions.add(j,new Position(positions.get(j).x,positions.get(j).y,(positions.get(j).rotation+1)%4,false,0));
                        numOrRotations++;
                    }
                    rotationsToAdd.add(new int[]{j,numOrRotations});
//                    for (int k = j; k < i; k++) {
//                        positions.remove(k);
//                    }
                    j=i-1;
                    break;
                }
            }

        }

        int difference=0;
        for (int i = 0; i < toRemove.size(); i++) {
            int[] remove=toRemove.get(i);
            int[] add=rotationsToAdd.get(i);
            for (int j = remove[0]+difference; j <remove[0]+difference+remove[1] ; j++) {
                copy.remove(remove[0]+difference);
            }
            for (int j = 0; j < add[1]; j++) {
                copy.add(remove[0]+difference,ROTATE_RIGHT.toString());
            }
            difference=difference-remove[1]+add[1];
        }

        for (int i = 0; i < copy.size()-4; i++) {
            if((copy.get(i).equals(ROTATE_LEFT.toString())&&copy.get(i+1).equals(ROTATE_LEFT.toString())&&copy.get(i+2).equals(ROTATE_LEFT.toString())&&copy.get(i+3).equals(ROTATE_LEFT.toString()))
                    ||(copy.get(i).equals(ROTATE_RIGHT.toString())&&copy.get(i+1).equals(ROTATE_RIGHT.toString())&&copy.get(i+2).equals(ROTATE_RIGHT.toString())&&copy.get(i+3).equals(ROTATE_RIGHT.toString()))){
                for (int j = 0; j < 4; j++) {
                    copy.remove(i);
                }
                i--;
            }
        }
        return copy;
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
    private class Position{
        private int x;
        private int y;
        private int rotation;
        private boolean foodEaten;
        private int index;
        public Position(int x, int y, int rotation, boolean foodEaten,int index) {
            this.x = x;
            this.y = y;
            this.rotation=rotation;
            this.foodEaten = foodEaten;
            this.index=index;
        }
    }

}
