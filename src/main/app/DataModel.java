package app;

public class DataModel {
    private int numberOfLocations;
    private int[][] flowMatrix;
    private int[][] distanceMatrix;

    public DataModel() {
    }

    public int getNumberOfLocations() {
        return numberOfLocations;
    }

    public DataModel setNumberOfLocations(int numberOfLocations) {
        this.numberOfLocations = numberOfLocations;
        return this;
    }

    public int[][] getFlowMatrix() {
        return flowMatrix;
    }

    public DataModel setFlowMatrix(int[][] flowMatrix) {
        this.flowMatrix = flowMatrix;
        return this;
    }

    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public DataModel setDistanceMatrix(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
        return this;
    }


    /**
     *
     * @param solution is a vector; indices are factories and values are places
     * @return total cost of given solution
     */
    public int getTotalCost(int[] solution) {
        if (solution.length != numberOfLocations) {
            return -1;
        }
        int sum = 0;
        for (int i = 0; i < numberOfLocations; i++) {
            for (int j = i + 1; j < numberOfLocations; j++) {
                sum += getCostBetweenTwoFacilities(i,j,solution[i],solution[j]);
            }
        }
        return sum;
    }

    public int getCostBetweenTwoFacilities(int firstFactory, int secondFactory, int firstLocation, int secondLocation){
        return flowMatrix[firstFactory][secondFactory] * distanceMatrix[firstLocation][secondLocation];
    }
}
