package app;

import java.util.ArrayList;

public class Individual {

    /**
     * Solution is a list; indices are factories and values are places
     */
    private ArrayList<Integer> genotype;
    private Model model;
    private int fitness;
    private double rouletteSelectionProbability;
    private int score;

    public Individual(Model model) {
        this.model = model;
        fitness = -1;
    }

    public Individual(Individual individual) {
        this.model = individual.model;
        this.genotype = individual.genotype;
    }


    public int getCostBetweenTwoFacilities(int firstFactory, int secondFactory, int firstLocation, int secondLocation) {
        return model.getFlowMatrix()[firstFactory][secondFactory] * model.getDistanceMatrix()[firstLocation][secondLocation];
    }

    public void swapSolutionValues(int i, int j) {
        int firstLocation = genotype.get(j);
        int secondLocation = genotype.get(i);
        genotype.set(i, firstLocation);
        genotype.set(j, secondLocation);
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public ArrayList<Integer> getGenotype() {
        return genotype;
    }

    public void setGenotype(ArrayList<Integer> genotype) {
        if (this.genotype == null || !this.genotype.equals(genotype)) {
            this.genotype = genotype;
            setFitness();
        }
    }

    public int getFitness() {
        if (fitness < 0) {
            setFitness();
        }
        return fitness;
    }

    public double getRouletteSelectionProbability() {
        return rouletteSelectionProbability;
    }

    public void setRouletteSelectionProbability(double rouletteSelectionProbability) {
        this.rouletteSelectionProbability = rouletteSelectionProbability;
    }


    public int getScore() {
        return score;
    }


    public void setScore(int score) {
        this.score = score * 2;
    }


    @Override
    public String toString() {
        return "Individual{" +
                "fitness=" + fitness +
                ", rouletteSelectionProbability=" + rouletteSelectionProbability +
                ", score=" + score +
                '}';
    }


    private void setFitness() {
        if (genotype.size() != model.getSize()) {
            System.out.println("INDIVIDUAL GET FITNESS ERROR");
        }
        int sum = 0;
        for (int i = 0; i < model.getSize(); i++) {
            for (int j = 0; j < model.getSize(); j++) {
                sum += getCostBetweenTwoFacilities(i, j, genotype.get(i), genotype.get(j));
            }
        }
        fitness = sum;
    }


}
