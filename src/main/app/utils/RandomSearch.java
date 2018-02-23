package app.utils;


import app.Individual;

import java.util.Collections;
import java.util.List;

import static app.utils.ListUtils.getFilledList;

public class RandomSearch {

    private Individual individual;

    public RandomSearch(Individual individual) {

        this.individual = individual;
    }

    public int minTotalCost(int times) {
        int minTotalCost = Integer.MAX_VALUE;
        int totalCost;
        for (int i = 0; i < times; i++) {
            int[] solution = solution();
            individual.setSolution(solution);
            totalCost = individual.getFitness();
            System.out.println("Calculated total cost: " + String.valueOf(totalCost));
            if (totalCost < minTotalCost) {
                minTotalCost = totalCost;
            }
        }
        System.out.println("Minimal total cost: " + String.valueOf(minTotalCost));
        return minTotalCost;
    }




    public int[] solution() {
        int n = individual.getNumberOfLocations();
        int[] result = new int[n];
        List<Integer> factories = getFilledList(n);
        Collections.shuffle(factories);

        for (int i = 0; i < n; i++) {
            result[i] = factories.get(i);
        }
        return result;
    }



}
