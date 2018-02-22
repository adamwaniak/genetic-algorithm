package app;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class RandomSearch {

    private DataModel dataModel;
    private static final Logger LOGGER = Logger.getLogger(RandomSearch.class.getName());
    public RandomSearch(DataModel dataModel) {
        this.dataModel = dataModel;
    }


    public int minTotalCost(int population) {
        int minTotalCost = Integer.MAX_VALUE;
        int totalCost;
        for (int i = 0; i < population; i++) {
            int[] solution = solution();
            totalCost = dataModel.getTotalCost(solution);
            System.out.println("Calculated total cost: "+ String.valueOf(totalCost));
            if(totalCost < minTotalCost){
                minTotalCost = totalCost;
            }
        }
        System.out.println("Minimal total cost: "+ String.valueOf(minTotalCost));
        return minTotalCost;
    }


    private int[] solution() {
        int n = dataModel.getNumberOfLocations();
        int[] result = new int[n];
        List<Integer> factories = getFilledList(n);
        Collections.shuffle(factories);

        for (int i = 0; i < n; i++) {
            result[i] = factories.get(i);
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
