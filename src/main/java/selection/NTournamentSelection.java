package selection;

import algorithm.IGenotype;
import util.IRandomNumberGenerator;

import java.util.List;

public abstract class NTournamentSelection<T extends IGenotype> implements ISelector<T> {

    protected int numberOfSelectedGenomes;

    protected NTournamentSelection(int numberOfSelectedGenomes) {
        this.numberOfSelectedGenomes = numberOfSelectedGenomes;
    }

    protected abstract List<T> selectTournamentParticipants(T[] population, IRandomNumberGenerator random);

    @Override
    public T select(T[] population, IRandomNumberGenerator random) {
        T bestSolution = null;
        List<T> solutionCandidates = selectTournamentParticipants(population, random);
        double maxFitness = -Double.MAX_VALUE;

        for (T candidate : solutionCandidates) {
            if (candidate.getFitness() > maxFitness) {
                bestSolution = candidate;
                maxFitness = candidate.getFitness();
            }
        }
        return bestSolution;
    }
}
