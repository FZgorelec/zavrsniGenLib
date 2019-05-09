package util;

public class GABitVectorUtil {

    public static boolean[][] crossNBreakpoints(boolean[] parent1, boolean[] parent2, int numberOfBreakpoints, IRandomNumberGenerator randomNumberGenerator) {
        if (parent1.length != parent2.length) throw new IllegalArgumentException("Parents must be of same length");
        if (numberOfBreakpoints < 1 || numberOfBreakpoints > parent1.length - 1)
            throw new IllegalArgumentException("Number of breakpoints must be between 1 and the length of the parents-1");
        boolean[] child1 = new boolean[parent1.length];
        boolean[] child2 = new boolean[parent1.length];
        int lastIndex = 0;
        int remainingCrossPoints = numberOfBreakpoints;
        for (int i = 0, len = child1.length; i < numberOfBreakpoints; i++, remainingCrossPoints--) {
            int breakPointIndex = 1 + lastIndex + randomNumberGenerator.nextInt(0, len - lastIndex - remainingCrossPoints - 1);
            for (int j = lastIndex; j < breakPointIndex; j++) {
                child1[j] = (i % 2 == 0) ? parent1[j] : parent2[j];
                child2[j] = (i % 2 == 1) ? parent1[j] : parent2[j];
            }
            lastIndex = breakPointIndex;
        }
        for (int i = lastIndex; i < child1.length; i++) {
            child1[i] = (numberOfBreakpoints % 2 == 0) ? parent1[i] : parent2[i];
            child2[i] = (numberOfBreakpoints % 2 == 1) ? parent1[i] : parent2[i];

        }
        return new boolean[][]{child1, child2};

    }

    public static boolean[]  uniformCross(boolean[] parent1, boolean[] parent2, IRandomNumberGenerator randomNumberGenerator){
        if(parent1.length!=parent2.length)throw new IllegalArgumentException("Parents must be of same length");
        boolean[] child=new boolean[parent1.length];
        for (int i = 0; i < parent1.length; i++) {
            child[i]=(randomNumberGenerator.nextDouble()<=0.5) ? parent1[i]:parent2[i];
        }
        return child;
    }

    public static boolean[]  mutate(boolean[] toMutate, double mutateProbability,IRandomNumberGenerator randomNumberGenerator){
        boolean[] child=new boolean[toMutate.length];
        for (int i = 0; i < toMutate.length; i++) {
            child[i]=(randomNumberGenerator.nextDouble()<=mutateProbability) ? !toMutate[i]:toMutate[i];
        }
        return child;
    }


}
