package app;

import app.utils.RandomSearch;

import java.util.ArrayList;

public class Population {
    private ArrayList<Individual> individuals;
    private int popSize;
    private int tournamentSize;


    public Population(int popSize, Individual individual) {
        RandomSearch randomSearch = new RandomSearch(individual);

        for (int i = 0; i < popSize; i++) {
            Individual modelToAdd = new Individual().setDistanceMatrix(individual.getDistanceMatrix()).setFlowMatrix(individual.getFlowMatrix())
                    .setNumberOfLocations(individual.getNumberOfLocations());
            modelToAdd.setSolution(randomSearch.solution());
            individuals.add(modelToAdd);
        }
    }



    public Individual getFittest(){
        Individual fittest = individuals.get(0);
        for (Individual indivudual: individuals){
            if (fittest.getFitness() > indivudual.getFitness()){
                fittest = indivudual;
            }
        }
        return fittest;
    }





}
