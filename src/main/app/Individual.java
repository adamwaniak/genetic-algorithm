package app;

import app.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class Individual {

    /**
     * Solution is a list; indices are factories and values are places
     */
    private ArrayList<Integer> solution;
    private Model model;

    public Individual(Model model) {
        this.model = model;
    }

    public Individual(Individual individual) {
        this.model = individual.model;
        this.solution = individual.solution;
    }


    public int getFitness() {
        if (solution.size() != model.getSize()) {
            System.out.println("INDIVIDUAL GET FITNESS ERROR");
            return -1;
        }
        int sum = 0;
        for (int i = 0; i < model.getSize(); i++) {
            for (int j = 0; j < model.getSize(); j++) {
                sum += getCostBetweenTwoFacilities(i, j, solution.get(i), solution.get(j));
            }
        }
        return sum;
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

    @Override
    public String toString() {
        return "Individual{getSolution: " + solution.toString() + "; Total cost: " + getFitness() + "}";
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


    public Individual setSolution(ArrayList<Integer> solution) {
        this.solution = solution;
        return this;
    }

}
