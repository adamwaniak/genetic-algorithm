package app;

import app.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class Individual {
    private int size;
    private int[][] flowMatrix;
    private int[][] distanceMatrix;
    private ArrayList<Integer> solution;


    public Individual() {
    }

    public Individual(Individual individual) {
        this.size = individual.size;
        this.flowMatrix = individual.flowMatrix;
        this.distanceMatrix = individual.distanceMatrix;
        this.solution = individual.solution;
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


    /**
     * Solution is a vector; indices are factories and values are places
     *
     * @return total cost of given solution
     */
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

    public void setSingleLocation(int factoryIndex, int newLocation) {
        solution.set(factoryIndex, newLocation);
    }

    public int getSingleLocation(int factoryIndex) {
        return solution.get(factoryIndex);
    }


    /**
     * After crossover locations could reapate. Repair method fix it.
     */
    public void repair() {
        List<Integer> locations = ListUtils.getFilledList(getSize());
        List<Integer> appeard = new ArrayList<>();
        for (int i = 0; i < getSize(); i++) {
            int apperance = 0;
            for (int j = 0; j < getSize(); j++) {
                if (locations.get(i) == solution.get(j)) {
                    apperance++;
                    appeard.add(solution.get(j));
                    if (apperance > 1) {
                        solution.set(j, -1);
                    }
                }
            }
        }
        locations.removeAll(appeard);

        for (int i = 0; i < getSize(); i++) {
            if (solution.get(i) == -1) {
                solution.set(i, locations.get(0));
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
        return "Individual{solution: " + solution.toString() + "; Total cost: " + getFitness() + "}";
    }
}
