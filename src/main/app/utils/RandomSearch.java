package app.utils;


import app.Individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static app.utils.ListUtils.getFilledList;

public class RandomSearch {

    private Individual individual;

    public RandomSearch(Individual individual) {

        this.individual = individual;
    }

    public int run(int times) {
        int minTotalCost = Integer.MAX_VALUE;
        int totalCost;
        for (int i = 0; i < times; i++) {
            ArrayList<Integer> solution = solution();
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




    public ArrayList<Integer> solution() {
        int n = individual.getSize();
        ArrayList<Integer> result = ListUtils.getFilledListWithZeroes(n);
        List<Integer> factories = getFilledList(n);
        Collections.shuffle(factories);

        for (int i = 0; i < n; i++) {
            result.set(i, factories.get(i));
        }
        return result;
    }



}
