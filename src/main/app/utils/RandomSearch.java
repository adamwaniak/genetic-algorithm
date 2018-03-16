package app.utils;


import app.Model;
import app.Population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class RandomSearch {


    public static List<SingleResult> run(Model model, int popSize, int generationSize) {
        List<SingleResult> result = new LinkedList<>();
        for (int i = 0; i < generationSize; i++) {
            Population population = new Population();
            population.randomPopulate(model, popSize);
            SingleResult singleResult = new SingleResult(population, i);
            result.add(singleResult);
        }
        return result;
    }


    public static ArrayList<Integer> getSolution(Model model) {
        int n = model.getSize();
        ArrayList<Integer> result = ListUtils.getFilledListWithZeroes(n);
        List<Integer> factories = ListUtils.getFilledList(n);
        Collections.shuffle(factories);

        for (int i = 0; i < n; i++) {
            result.set(i, factories.get(i));
        }
        return result;
    }


}
