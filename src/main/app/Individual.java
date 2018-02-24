package app;

import app.utils.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Individual {
    private int numberOfLocations;
    private int[][] flowMatrix;
    private int[][] distanceMatrix;
    private int[] solution;


    public Individual() {
    }

    public Individual(Individual individual) {
        this.numberOfLocations = individual.numberOfLocations;
        this.flowMatrix = individual.flowMatrix;
        this.distanceMatrix = individual.distanceMatrix;
        this.solution = individual.solution;
    }


    public int getNumberOfLocations() {
        return numberOfLocations;
    }


    public Individual setNumberOfLocations(int numberOfLocations) {
        this.numberOfLocations = numberOfLocations;
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


    public int[] getSolution() {
        return solution;
    }


    public Individual setSolution(int[] solution) {
        this.solution = solution;
        return this;
    }


    /**
     * Solution is a vector; indices are factories and values are places
     *
     * @return total cost of given solution
     */
    public int getFitness() {
        if (solution.length != getNumberOfLocations()) {
            return -1;
        }
        int sum = 0;
        for (int i = 0; i < getNumberOfLocations(); i++) {
            for (int j = i + 1; j < getNumberOfLocations(); j++) {
                sum += getCostBetweenTwoFacilities(i, j, solution[i], solution[j]);
            }
        }
        return sum;
    }


    public int getCostBetweenTwoFacilities(int firstFactory, int secondFactory, int firstLocation, int secondLocation) {
        return getFlowMatrix()[firstFactory][secondFactory] * getDistanceMatrix()[firstLocation][secondLocation];
    }

    public void setSingleLocation(int factoryIndex, int newLocation) {
        solution[factoryIndex] = newLocation;
    }

    public int getSingleLocation(int factoryIndex) {
        return solution[factoryIndex];
    }


    /**
     * After crossover locations could reapate. Repair method fix it.
     */
    public void repair() {
        List<Integer> locations = ListUtils.getFilledList(getNumberOfLocations());
        List<Integer> appeard = new ArrayList<>();
        for (int i = 0; i < getNumberOfLocations(); i++) {
            int apperance = 0;
            for (int j = 0; j < getNumberOfLocations(); j++) {
                if (locations.get(i) == solution[j]) {
                    apperance++;
                    appeard.add(solution[j]);
                    if (apperance > 1) {
                        solution[j] = -1;
                    }
                }
            }
        }
        locations.removeAll(appeard);

        for (int i = 0; i < getNumberOfLocations(); i++) {
            if (solution[i] == -1) {
                solution[i] = locations.get(0);
                locations.remove(0);
            }
        }

    }

    public void swapSolutionLocations(int i, int j) {
        setSingleLocation(i, j);
        setSingleLocation(j, i);
    }

    @Override
    public String toString() {
        return "Individual{solution: " + Arrays.toString(solution) + "; Total cost: " + getFitness() + "}";
    }
}
