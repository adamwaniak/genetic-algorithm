package app;

import app.utils.RandomSearch;

import java.util.ArrayList;

public class Population {
    private ArrayList<Individual> individuals;


    public Population() {
        individuals = new ArrayList<>();
    }

    public void randomInit(Model model, int popSize) {


        for (int i = 0; i < popSize; i++) {
            Individual individual = new Individual(model);
            RandomSearch randomSearch = new RandomSearch(model);
            individual.setSolution(randomSearch.getSolution());
            individuals.add(individual);
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

    public int getSumFitness(){
        int result = 0;
        for(Individual individual: individuals){
            result+= individual.getFitness();
        }
        return result;
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
