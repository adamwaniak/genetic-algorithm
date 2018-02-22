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



}
