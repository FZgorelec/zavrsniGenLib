package algorithmImpl.GeneticProgramming;

import util.RNGThread;

public class EvaluatorThread extends RNGThread {
    MovementTreeEvaluator evaluator;
    public EvaluatorThread(String[][] map,int numberOfSteps,Runnable r){
        super(r);
        evaluator=new MovementTreeEvaluator(map,numberOfSteps);
    }

    public MovementTreeEvaluator getEvaluator() {
        return evaluator;
    }
}
