package app;

import app.utils.RandomSearch;

import java.util.ArrayList;

public class Population {
    private ArrayList<Individual> individuals;


    public Population() {
        individuals = new ArrayList<>();
    }

    public void randomInit(Individual originIndividual, int popSize) {
        RandomSearch randomSearch = new RandomSearch(originIndividual);

        for (int i = 0; i < popSize; i++) {
            Individual modelToAdd = new Individual(originIndividual);
            modelToAdd.setSolution(randomSearch.solution());
            individuals.add(modelToAdd);
        }
    }


    public Individual getFittest() {
        Individual fittest = individuals.get(0);
        for (Individual individual : individuals) {
            if (fittest.getFitness() > individual.getFitness()) {
                fittest = individual;
            }
        }
        return fittest;
    }

    public Individual getWorst() {
        Individual worst = individuals.get(0);
        for (Individual individual : individuals) {
            if (worst.getFitness() < individual.getFitness()) {
                worst = individual;
            }
        }
        return worst;
    }

    public double getAverageFitness() {
        double fitness = 0;
        for(Individual individual: individuals){
            fitness += individual.getFitness();
        }
        fitness = fitness/individuals.size();
        return fitness;
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
