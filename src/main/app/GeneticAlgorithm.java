package app;

import app.utils.ListUtils;
import app.utils.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeneticAlgorithm {

    private static int solutionSize;
    private static Model model;
    private static int popSize;
    private static Population population;
    private static int generationCount;
    private static int maxGenerationCount;
    private static int tournamentSize;
    private static double crossoverRate;
    private static double mutationRate;
    private static int mutationCount;


    public GeneticAlgorithm() {
    }


    public static List<Result> run(Model model, int popSize, int maxGenerationCount, double crossoverRate, double mutationRate, int tournamentSize) {
        init(model, popSize, maxGenerationCount, crossoverRate, mutationRate, tournamentSize);
        List<Result> results = new LinkedList<>();
        results.add(new Result(population, 0));
        while (generationCount < maxGenerationCount) {
            System.out.println("Actual best individual: " + population.getFittest().getFitness());
            population = evolvePopulation(population);
            generationCount++;
            results.add(new Result(population, generationCount));
        }
        System.out.println("Best individual in population: " + population.getFittest().getFitness());
        System.out.println("Solution: " + population.getFittest().getSolution().toString());
        return results;
    }

    private static void init(Model model, int popSize, int maxGenerationCount, double crossoverRate, double mutationRate, int tournamentSize) {
        population = new Population();
        population.randomInit(model, popSize);
        generationCount = 0;
        GeneticAlgorithm.model = model;
        GeneticAlgorithm.popSize = popSize;
        GeneticAlgorithm.maxGenerationCount = maxGenerationCount;
        GeneticAlgorithm.crossoverRate = crossoverRate;
        GeneticAlgorithm.mutationRate = mutationRate;
        GeneticAlgorithm.tournamentSize = tournamentSize;
        GeneticAlgorithm.solutionSize = model.getSize();
    }

    private static Population evolvePopulation( Population population) {
        Population newPopulation = new Population();
        for (int i = 0; i < population.getIndividuals().size(); i++) {

            int randomId = (int) (Math.random() * population.getIndividuals().size());
            Individual newIndiv = population.getIndividuals().get(randomId);

            if (Math.random() <= crossoverRate){
                Individual indiv1 = tournamentSelection(population);
                Individual indiv2 = tournamentSelection(population);
                newIndiv = crossover(indiv1, indiv2);
            }

            if (Math.random() <= mutationRate) {
                mutate(newIndiv);
            }
            newPopulation.getIndividuals().add(newIndiv);
        }
        return newPopulation;
    }


    private static Individual tournamentSelection(Population population) {
        Population tournament = new Population();
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * population.getIndividuals().size());
            tournament.getIndividuals().add(i, population.getIndividuals().get(randomId));
        }
        return tournament.getFittest();
    }

    private static Individual rouletteSelection(Population population){
        return null;
    }

    private static void rouletteInit(){

    }



    private static Individual crossover(Individual indiv1, Individual indiv2) {

        int pivot = getRandomInt(0, solutionSize);
        Individual newIndiv = new Individual(model);
        ArrayList<Integer> child = ListUtils.getFilledListWithMinusOne(solutionSize);
        for (int i = 0; i < pivot; i++) {
            child.set(i, indiv1.getSolution().get(i));
        }
        for (int i = pivot; i < solutionSize; i++) {
            child.set(i, indiv2.getSolution().get(i));
        }
        repair(child);
        newIndiv.setSolution(child);

        return newIndiv;
    }

    private static void mutate(Individual indiv) {
        List<Integer> integerList = ListUtils.getFilledList(solutionSize);
        Collections.shuffle(integerList);
        indiv.swapSolutionValues(integerList.get(0), integerList.get(1));
        mutationCount++;
    }

    /**
     * After crossover locations could reapate. Repair method fix it.
     */
    private static void repair(ArrayList<Integer> solution) {
        int size = solution.size();
        List<Integer> locations = ListUtils.getFilledList(size);
        List<Integer> appeared = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int appearance = 0;
            for (int j = 0; j < size; j++) {
                if (locations.get(i).equals(solution.get(j))) {
                    appearance++;
                    appeared.add(solution.get(j));
                    if (appearance > 1) {
                        solution.set(j, -1);
                    }
                }
            }
        }
        locations.removeAll(appeared);

        for (int i = 0; i < size; i++) {
            if (solution.get(i) == -1) {
                solution.set(i, locations.get(0));
                locations.remove(0);
            }
        }

    }

    private static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }


}
