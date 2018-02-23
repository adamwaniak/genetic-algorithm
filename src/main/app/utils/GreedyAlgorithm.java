package app.utils;


import app.Individual;

import java.util.List;

import static app.utils.ListUtils.getFilledList;

public class GreedyAlgorithm {

    private Individual individual;

    public GreedyAlgorithm(Individual individual) {
        this.individual = individual;
    }


    public int[] solution() {
        int n = individual.getNumberOfLocations();
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
                if ((cost = individual.getCostBetweenTwoFacilities(i,i+1,result[i],j)) < minCost){
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
