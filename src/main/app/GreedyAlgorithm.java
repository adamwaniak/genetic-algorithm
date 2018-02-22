package app;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreedyAlgorithm  {
    private DataModel dataModel;

    public GreedyAlgorithm(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public int minTotalCost(){
        return dataModel.getTotalCost(solution());
    }

    public int[] solution() {
        int n = dataModel.getNumberOfLocations();
        int[] result = new int[n];
        List<Integer> factories = getFilledList(n);
        result[0] = factories.get(0);
        factories.remove(0);

        int bestFactory = -1;
        int minCost;
        int cost;
        for (int i = 0; i < n-1; i++) {
            minCost = Integer.MAX_VALUE;
            for(int j : factories){
                if ((cost = dataModel.getCostBetweenTwoFacilities(i,i+1,result[i],j)) < minCost){
                    bestFactory = j;
                    minCost = cost;
                }
            }
            factories.remove((Integer)bestFactory);
            result[i+1] = bestFactory;
        }
        return result;
    }


    private List<Integer> getFilledList(int size) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(i);
        }
        return result;
    }




}
