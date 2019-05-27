package algorithmImpl.GeneticProgramming;

import util.RNGThread;
import util.RNGThreadProvider;

public class EvaluatorThreadFactory extends RNGThreadProvider {
    String[][] map;
    int numberOfSteps;

    public EvaluatorThreadFactory(String[][] map, int numberOfSteps) {
        this.map = map;
        this.numberOfSteps = numberOfSteps;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new EvaluatorThread(map,numberOfSteps,r);
    }
}
