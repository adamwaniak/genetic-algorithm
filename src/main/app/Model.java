package app;

public class Model {
    private int size;
    private int[][] flowMatrix;
    private int[][] distanceMatrix;


    public int getSize() {
        return size;
    }


    public Model setSize(int size) {
        this.size = size;
        return this;
    }


    public int[][] getFlowMatrix() {
        return flowMatrix;
    }


    public Model setFlowMatrix(int[][] flowMatrix) {
        this.flowMatrix = flowMatrix;
        return this;
    }


    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }


    public Model setDistanceMatrix(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
        return this;
    }
}
