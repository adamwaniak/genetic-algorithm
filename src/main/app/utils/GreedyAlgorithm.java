package app.utils;


import app.Individual;

import java.util.ArrayList;
import java.util.List;

import static app.utils.ListUtils.getFilledList;

public class GreedyAlgorithm {

    private Individual individual;

    public GreedyAlgorithm(Individual individual) {
        this.individual = individual;
    }


    public ArrayList<Integer> solution() {
        int n = individual.getSize();
        ArrayList<Integer> result = ListUtils.getFilledListWithZeroes(n);
        List<Integer> factories = getFilledList(n);
        result.set(0, factories.get(0));
        factories.remove(0);

        int bestFactory = -1;
        int minCost;
        int cost;
        for (int i = 0; i < n-1; i++) {
            minCost = Integer.MAX_VALUE;
            for(int j : factories){
                if ((cost = individual.getCostBetweenTwoFacilities(i,i+1, result.get(i),j)) < minCost){
                    bestFactory = j;
                    minCost = cost;
                }
            }
            factories.remove((Integer)bestFactory);
            result.set(i + 1, bestFactory);
        }
        return result;
    }







}
