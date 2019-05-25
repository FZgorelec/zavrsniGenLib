package algorithmImpl.GeneticProgramming;

public class MovementTreeNavigator {

    protected String[][] map;
    protected String[][] mapCopy;
    protected int maxX;
    protected int maxY;
    protected int position[];
    protected int numberOfSteps;

    public MovementTreeNavigator(String[][] map,int numberOfSteps) {
        this.map = map;
        this.numberOfSteps=numberOfSteps;
        maxX = map[0].length;
        maxY = map.length;
        position = new int[3];
    }
    protected void copyMap() {
        mapCopy = new String[map.length][];
        for (int i = 0; i < map.length; i++) {
            mapCopy[i] = map[i].clone();
        }
    }

    protected void updatePositionMove() {
        int newX;
        int newY;
        switch (position[2]) {
            case 0:
                newX = position[0];
                newY = position[1] - 1;
                if (!isWallAhead(newX,newY) && newY >= 0) {
                    position[0] = newX;
                    position[1] = newY;
                }
                break;
            case 1:
                newX = position[0] + 1;
                newY = position[1];
                if (!isWallAhead(newX,newY) && newX < maxX) {
                    position[0] = newX;
                    position[1] = newY;
                }
                break;
            case 2:
                newX = position[0];
                newY = position[1] + 1;
                if (!isWallAhead(newX,newY) && newY < maxY) {
                    position[0] = newX;
                    position[1] = newY;
                }
                break;
            case 3:
                newX = position[0] - 1;
                newY = position[1];
                if (!isWallAhead(newX,newY) && newX >= 0) {
                    position[0] = newX;
                    position[1] = newY;
                }
                break;
        }
    }

    protected int[] calculateNextPosition() {
        int newX=0;
        int newY=0;
        switch (position[2]) {
            case 0:
                newX = position[0];
                newY = position[1] - 1;
                break;
            case 1:
                newX = position[0] + 1;
                newY = position[1];
                break;
            case 2:
                newX = position[0];
                newY = position[1] + 1;

                break;
            case 3:
                newX = position[0] - 1;
                newY = position[1];
                break;
        }
        return new int[]{newX,newY,position[2]};
    }


    protected boolean isWallAhead() {
        int[] newPosition=calculateNextPosition();
        if(newPosition[0]>=maxX||newPosition[0]<0||newPosition[1]>=maxY||newPosition[1]<0)return true;
        if (mapCopy[newPosition[1]][newPosition[0]].equals(MapValues.WALL.toString())) return true;
        return false;
    }

    protected boolean isFoodAhead() {
        int[] newPosition=calculateNextPosition();
        if(newPosition[0]>=maxX||newPosition[0]<0||newPosition[1]>=maxY||newPosition[1]<0)return false;
        if (mapCopy[newPosition[1]][newPosition[0]].equals(MapValues.FOOD.toString())) return true;
        return false;
    }

    protected boolean isBombAhead() {
        int[] newPosition=calculateNextPosition();
        if(newPosition[0]>=maxX||newPosition[0]<0||newPosition[1]>=maxY||newPosition[1]<0)return false;
        if (mapCopy[newPosition[1]][newPosition[0]].equals(MapValues.BOMB.toString())) return true;
        return false;
    }
    protected boolean isWallAhead(int x, int y) {
        if(x>=maxX||x<0||y>=maxY||y<0)return true;
        if (mapCopy[y][x].equals(MapValues.WALL.toString())) return true;
        return false;
    }

    protected boolean isFoodAhead(int x, int y) {
        if(x>=maxX||x<0||y>=maxY||y<0)return false;
        if (mapCopy[y][x].equals(MapValues.FOOD.toString())) return true;
        return false;
    }

    protected boolean isBombAhead(int x, int y) {
        if(x>=maxX||x<0||y>=maxY||y<0)return false;
        if (mapCopy[y][x].equals(MapValues.BOMB.toString())) return true;
        return false;
    }
}
