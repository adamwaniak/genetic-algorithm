package app;

import app.utils.RandomSearch;

import java.util.ArrayList;

public class Population {
    private ArrayList<Individual> individuals;


    public Population() {
        individuals = new ArrayList<>();
    }

    public void init(Individual originIndividual, int popSize) {
        RandomSearch randomSearch = new RandomSearch(originIndividual);

        for (int i = 0; i < popSize; i++) {
            Individual modelToAdd = new Individual(originIndividual);
            modelToAdd.setSolution(randomSearch.solution());
            individuals.add(modelToAdd);
        }
    }


    public Individual getFittest() {
        Individual fittest = individuals.get(0);
        for (Individual indivudual : individuals) {
            if (fittest.getFitness() > indivudual.getFitness()) {
                fittest = indivudual;
            }
        }
        return fittest;
    }

    public ArrayList<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(ArrayList<Individual> individuals) {
        this.individuals = individuals;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Individual individual: individuals){
            stringBuilder.append(individual.toString());
        }


        return "Population{" +
                "individuals=" + stringBuilder.toString() +
                '}';
    }
}
