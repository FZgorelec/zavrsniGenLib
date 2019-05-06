package selection;

import algorithm.IGenotype;
import util.IRandomNumberGenerator;

import java.util.List;

public abstract class NTournamentSelection<T extends IGenotype> implements ISelectionAlgorithm<T> {

    protected int numberOfSelectedGenomes;
    protected IRandomNumberGenerator random;

    protected NTournamentSelection(int numberOfSelectedGenomes, IRandomNumberGenerator random) {
        this.numberOfSelectedGenomes=numberOfSelectedGenomes;
        this.random=random;
    }

    protected abstract List<T> selectTournamentParticipants(T[] population);

    @Override
    public T select(T[] population) {
        T bestSolution=null;
        List<T> solutionCandidates=selectTournamentParticipants(population);
        double maxFitness=-Double.MAX_VALUE;

        for (T candidate:solutionCandidates) {
            if(candidate.getFitness()>maxFitness){
                bestSolution=candidate;
                maxFitness=candidate.getFitness();
            }
        }
        return  bestSolution;
    }
}
