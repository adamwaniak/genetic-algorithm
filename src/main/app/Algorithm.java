package app;

import java.util.ArrayList;
import java.util.List;

public abstract class Algorithm {

    protected DataModel dataModel;


    public Algorithm(DataModel dataModel) {
        this.dataModel = dataModel;
    }


    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }


    public abstract int[] solution();


    public int minTotalCost(int population) {
        int minTotalCost = Integer.MAX_VALUE;
        int totalCost;
        for (int i = 0; i < population; i++) {
            int[] solution = solution();
            totalCost = getTotalCost(solution);
            System.out.println("Calculated total cost: " + String.valueOf(totalCost));
            if (totalCost < minTotalCost) {
                minTotalCost = totalCost;
            }
        }
        System.out.println("Minimal total cost: " + String.valueOf(minTotalCost));
        return minTotalCost;
    }


    /**
     * @param solution is a vector; indices are factories and values are places
     * @return total cost of given solution
     */
    public int getTotalCost(int[] solution) {
        if (solution.length != dataModel.getNumberOfLocations()) {
            return -1;
        }
        int sum = 0;
        for (int i = 0; i < dataModel.getNumberOfLocations(); i++) {
            for (int j = i + 1; j < dataModel.getNumberOfLocations(); j++) {
                sum += getCostBetweenTwoFacilities(i, j, solution[i], solution[j]);
            }
        }
        return sum;
    }


    protected int getCostBetweenTwoFacilities(int firstFactory, int secondFactory, int firstLocation, int secondLocation) {
        return dataModel.getFlowMatrix()[firstFactory][secondFactory] * dataModel.getDistanceMatrix()[firstLocation][secondLocation];
    }


    protected List<Integer> getFilledList(int size) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(i);
        }
        return result;
    }
}
