package app;

import java.util.ArrayList;

public class Individual {

    /**
     * Solution is a list; indices are factories and values are places
     */
    private ArrayList<Integer> solution;
    private Model model;
    private int fitness;
    private double rouletteSelectionProbability;

    public Individual(Model model) {
        this.model = model;
        fitness = -1;
    }

    public Individual(Individual individual) {
        this.model = individual.model;
        this.solution = individual.solution;
    }


    public int getCostBetweenTwoFacilities(int firstFactory, int secondFactory, int firstLocation, int secondLocation) {
        return model.getFlowMatrix()[firstFactory][secondFactory] * model.getDistanceMatrix()[firstLocation][secondLocation];
    }

    public void swapSolutionValues(int i, int j) {
        int firstLocation = solution.get(j);
        int secondLocation = solution.get(i);
        solution.set(i, firstLocation);
        solution.set(j, secondLocation);
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public ArrayList<Integer> getSolution() {
        return solution;
    }

    public void setSolution(ArrayList<Integer> solution) {
        if (this.solution==null || !this.solution.equals(solution)) {
            this.solution = solution;
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

    @Override
    public String toString() {
        return "Individual{getSolution: " + solution.toString() + "; Total cost: " + getFitness() + "}";
    }

    private void setFitness() {
        if (solution.size() != model.getSize()) {
            System.out.println("INDIVIDUAL GET FITNESS ERROR");
        }
        int sum = 0;
        for (int i = 0; i < model.getSize(); i++) {
            for (int j = 0; j < model.getSize(); j++) {
                sum += getCostBetweenTwoFacilities(i, j, solution.get(i), solution.get(j));
            }
        }
        fitness = sum;
    }
}
