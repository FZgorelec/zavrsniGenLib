package algorithmImpl;

import algorithm.IGenotype;

public class Genome implements IGenotype {

    protected double fitness;

    public Genome(double fitness) {
        this.fitness = fitness;
    }

    public Genome(){
        this.fitness=0;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

}
