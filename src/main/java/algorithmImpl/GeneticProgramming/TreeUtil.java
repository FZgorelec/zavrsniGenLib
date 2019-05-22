package algorithmImpl.GeneticProgramming;

public class TreeUtil {
    public static int depth(INode node) {
        int deepest = 0;
        for (INode child : node.getChildren()) {
            if (child != null) deepest = Math.max(deepest, depth(child));
        }
        return deepest + 1;
    }

    public static void countChildrenBelow(INode node) {
        if(node==null)return;
        int sum = 0;
        for (INode child : node.getChildren()) {
            countChildrenBelow(child);
            if(child!=null)sum += 1+child.getChildrenBelow();
        }
        node.setChildrenBelow(sum);
        return;
    }


}
