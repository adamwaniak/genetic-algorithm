package app;


import java.util.List;

public class GreedyAlgorithm  extends Algorithm {

    public GreedyAlgorithm(DataModel dataModel) {
        super(dataModel);
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
                if ((cost = getCostBetweenTwoFacilities(i,i+1,result[i],j)) < minCost){
                    bestFactory = j;
                    minCost = cost;
                }
            }
            factories.remove((Integer)bestFactory);
            result[i+1] = bestFactory;
        }
        return result;
    }







}
