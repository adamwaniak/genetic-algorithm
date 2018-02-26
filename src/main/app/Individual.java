package app;

import app.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class Individual {
    private int size;
    private int[][] flowMatrix;
    private int[][] distanceMatrix;
    /**
     * Solution is a list; indices are factories and values are places
     */
    private ArrayList<Integer> solution;


    public Individual() {
    }

    public Individual(Individual individual) {
        this.size = individual.size;
        this.flowMatrix = individual.flowMatrix;
        this.distanceMatrix = individual.distanceMatrix;
        this.solution = individual.solution;
    }


    public int getFitness() {
        if (solution.size() != getSize()) {
            return -1;
        }
        int sum = 0;
        for (int i = 0; i < getSize(); i++) {
            for (int j = i + 1; j < getSize(); j++) {
                sum += getCostBetweenTwoFacilities(i, j, solution.get(i), solution.get(j));
            }
        }
        return sum;
    }


    public int getCostBetweenTwoFacilities(int firstFactory, int secondFactory, int firstLocation, int secondLocation) {
        return getFlowMatrix()[firstFactory][secondFactory] * getDistanceMatrix()[firstLocation][secondLocation];
    }


    public void swapSolutionValues(int i, int j) {
        int firstLocation = solution.get(j);
        int secondLocation = solution.get(i);
        solution.set(i, firstLocation);
        solution.set(j, secondLocation);
    }

    @Override
    public String toString() {
        return "Individual{solution: " + solution.toString() + "; Total cost: " + getFitness() + "}";
    }


    public int getSize() {
        return size;
    }


    public Individual setSize(int size) {
        this.size = size;
        return this;
    }


    public int[][] getFlowMatrix() {
        return flowMatrix;
    }


    public Individual setFlowMatrix(int[][] flowMatrix) {
        this.flowMatrix = flowMatrix;
        return this;
    }


    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }


    public Individual setDistanceMatrix(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
        return this;
    }


    public ArrayList<Integer> getSolution() {
        return solution;
    }


    public Individual setSolution(ArrayList<Integer> solution) {
        this.solution = solution;
        return this;
    }

}
