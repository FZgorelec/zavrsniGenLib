package algorithmImpl.doublearrayimpl;

import algorithm.IGenotype;
import algorithmImpl.Genome;

public class DoubleArrayGenome extends Genome {

    double[] solution;

    public DoubleArrayGenome(double[] solution, double fitness) {
       super(fitness);
        this.solution = solution;
    }

    public DoubleArrayGenome(double[] solution) {
        this.solution=solution;
    }

    public double[] getSolution() {
        return solution;
    }

}
