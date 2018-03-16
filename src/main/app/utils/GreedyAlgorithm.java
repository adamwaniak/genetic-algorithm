package app.utils;


import app.Individual;
import app.Model;

import java.util.ArrayList;
import java.util.List;

import static app.utils.ListUtils.getFilledList;

public class GreedyAlgorithm {


    public static SingleResult run(Model model) {
        Individual individual = new Individual(model);
        int n = individual.getModel().getSize();
        ArrayList<Integer> genotype = ListUtils.getFilledListWithZeroes(n);
        List<Integer> factories = getFilledList(n);
        genotype.set(0, factories.get(0));
        factories.remove(0);

        int bestFactory = -1;
        int minCost;
        int cost;
        for (int i = 0; i < n - 1; i++) {
            minCost = Integer.MAX_VALUE;
            for (int j : factories) {
                //first factory, second factory, first location, second location
                if ((cost = individual.getCostBetweenTwoFacilities(i, i + 1, genotype.get(i), j)) < minCost) {
                    bestFactory = j;
                    minCost = cost;
                }
            }
            factories.remove((Integer) bestFactory);
            genotype.set(i + 1, bestFactory);
        }
        individual.setGenotype(genotype);
        SingleResult singleResult = new SingleResult(model.getSize(), individual.getFitness(), 0, 0);
        return singleResult;
    }


}
