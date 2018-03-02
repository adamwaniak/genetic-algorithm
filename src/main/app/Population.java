package app;

import app.utils.RandomSearch;

import java.util.ArrayList;

public class Population {
    private ArrayList<Individual> population;


    public Population() {
        population = new ArrayList<>();
    }

    public void randomPopulate(Model model, int popSize) {


        for (int i = 0; i < popSize; i++) {
            Individual individual = new Individual(model);
            RandomSearch randomSearch = new RandomSearch(model);
            individual.setGenotype(randomSearch.getSolution());
            population.add(individual);
        }
    }

    public Individual getRandomIndividual(){
        int randomId = (int) (Math.random() * population.size());
        return population.get(randomId);
    }


    public Individual getFittest() {
        Individual fittest = population.get(0);
        for (Individual individual : population) {
            if (fittest.getFitness() > individual.getFitness()) {
                fittest = individual;
            }
        }
        return fittest;
    }

    public Individual getWorst() {
        Individual worst = population.get(0);
        for (Individual individual : population) {
            if (worst.getFitness() < individual.getFitness()) {
                worst = individual;
            }
        }
        return worst;
    }

    public double getAverageFitness() {
        double fitness = 0;
        for(Individual individual: population){
            fitness += individual.getFitness();
        }
        fitness = fitness/ population.size();
        return fitness;
    }

    public int getSumFitness(){
        int result = 0;
        for(Individual individual: population){
            result+= individual.getFitness();
        }
        return result;
    }

    public int getSumScores(){
        int result = 0;
        for(Individual individual: population){
            result+= individual.getScore();
        }
        return result;
    }

    public int getSize(){
        return population.size();
    }

    public void setScores(){
        if(population==null || population.size()==0){
            System.out.println("Population setScores: POPULATION IS ZERO OR NULL");
        }
        int worstFitness = getWorst().getFitness();
        for(Individual individual : population){

            individual.setScore(worstFitness - individual.getFitness());
        }
    }

    public ArrayList<Individual> getAll() {
        return population;
    }

    public Individual get(int index) {
        return population.get(index);
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Individual individual: population){
            stringBuilder.append(individual.toString());
        }


        return "Population{" +
                "population=" + stringBuilder.toString() +
                '}';
    }
}
