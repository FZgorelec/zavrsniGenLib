package selection.impl;

import algorithm.IGenotype;
import util.IRandomNumberGenerator;
import selection.NTournamentSelection;

import java.util.ArrayList;
import java.util.List;

public class NTournamentSelectionWithRepetition<T extends IGenotype> extends NTournamentSelection<T> {
    public NTournamentSelectionWithRepetition(int numberOfSelectedGenomes) {
        super(numberOfSelectedGenomes);
    }

    @Override
    protected List<T> selectTournamentParticipants(T[] population, IRandomNumberGenerator random) {
        List<T> selectedParticipants=new ArrayList<>();
        for (int i = 0; i < numberOfSelectedGenomes; i++) {
            selectedParticipants.add(population[random.nextInt(0, population.length-1)]);
        }
        return selectedParticipants;
    }

    @Override
    public T select(T[] population,IRandomNumberGenerator random) {
        return super.select(population,random);
    }

}
