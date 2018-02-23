package app;

public class Individual {
    private int numberOfLocations;
    private int[][] flowMatrix;
    private int[][] distanceMatrix;
    private int[] solution;


    public Individual() { }


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


}
