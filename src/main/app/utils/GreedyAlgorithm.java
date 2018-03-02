package app.utils;


import app.Individual;
import app.Model;

import java.util.ArrayList;
import java.util.List;

import static app.utils.ListUtils.getFilledList;

public class GreedyAlgorithm {

    private static Individual individual;

    private static void init(Model model) {
        GreedyAlgorithm.individual = new Individual(model);
    }

    public static ArrayList<Integer> getSolution(Model model) {
        init(model);
        int n = individual.getModel().getSize();
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
                //first factory, second factory, first location, second location
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
