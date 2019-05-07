package algorithmImpl;

import algorithm.IGenotype;

public class DoubleArrayGenome implements IGenotype {

    double[] solution;
    double fitness;

    public DoubleArrayGenome(double[] solution, double fitness) {
        this.solution = solution;
        this.fitness = fitness;
    }
    public DoubleArrayGenome(double[] solution) {
        this(solution,0);
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public void setFitness(double fitness) {
        this.fitness=fitness;
    }

    public double[] getSolution() {
        return solution;
    }

}
