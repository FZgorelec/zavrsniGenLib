import algorithm.IGenotype;

public class GenotypeTestDouble implements IGenotype {

    double[] solution;
    double fitness;

    public GenotypeTestDouble(double[] solution, double fitness) {
        this.solution = solution;
        this.fitness = fitness;
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
