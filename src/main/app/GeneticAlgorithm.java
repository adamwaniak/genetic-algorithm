package app;

import app.utils.ListUtils;
import app.utils.Result;

import java.util.*;
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
    private static Selection selectionType;

    public enum Selection {
        TOURNAMENT, ROULETTE
    }


    public GeneticAlgorithm() {
    }

    public static List<Result> run(Model model, int popSize, int maxGenerationCount, double crossoverRate, double mutationRate, Selection selectionType, int tournamentSize) {
        init(model, popSize, maxGenerationCount, crossoverRate, mutationRate, selectionType, tournamentSize);
        List<Result> results = new LinkedList<>();
        results.add(new Result(population, 0));
        while (generationCount < maxGenerationCount) {
            System.out.println("Actual best individual: " + population.getFittest().getFitness());
            population = evolvePopulation(population);
            generationCount++;
            results.add(new Result(population, generationCount));
        }
        System.out.println("Best individual in population: " + population.getFittest().getFitness());
        System.out.println("Solution: " + population.getFittest().getGenotype().toString());
        return results;
    }

    private static void init(Model model, int popSize, int maxGenerationCount, double crossoverRate, double mutationRate, Selection selectionType, int tournamentSize) {
        population = new Population();
        population.randomPopulate(model, popSize);
        generationCount = 0;
        GeneticAlgorithm.model = model;
        GeneticAlgorithm.popSize = popSize;
        GeneticAlgorithm.maxGenerationCount = maxGenerationCount;
        GeneticAlgorithm.crossoverRate = crossoverRate;
        GeneticAlgorithm.mutationRate = mutationRate;
        GeneticAlgorithm.selectionType = selectionType;
        GeneticAlgorithm.tournamentSize = tournamentSize;
        GeneticAlgorithm.solutionSize = model.getSize();
    }

    private static Population evolvePopulation(Population population) {
        Population newPopulation = new Population();
        if (selectionType.equals(Selection.ROULETTE)) {
            initRoulette(population);
        }
        for (int i = 0; i < population.getAll().size(); i++) {

            Individual newIndiv;

            if (Math.random() <= crossoverRate) {
                Individual indiv1;
                Individual indiv2;
                if (selectionType.equals(Selection.TOURNAMENT)) {
                    indiv1 = tournamentSelection(population);
                    indiv2 = tournamentSelection(population);
                } else {
                    indiv1 = rouletteSelection(population);
                    indiv2 = rouletteSelection(population);
                }
                newIndiv = crossover(indiv1, indiv2);
            }else{
                newIndiv = population.getRandomIndividual();
            }
            if (Math.random() <= mutationRate) {
                mutate(newIndiv);
            }
            newPopulation.getAll().add(newIndiv);
        }
        return newPopulation;
    }


    private static Individual tournamentSelection(Population population) {
        Population tournament = new Population();
        for (int i = 0; i < tournamentSize; i++) {
            tournament.getAll().add(i, population.getRandomIndividual());
        }
        return tournament.getFittest();
    }

    private static Individual rouletteSelection(Population population) {
        double randomInt = Math.random();
        ArrayList<Individual> individuals = population.getAll();
        individuals.sort(Comparator.comparingDouble(Individual::getRouletteSelectionProbability));
        for (int i = 0; i < individuals.size() - 1; i++) {
            if(i==0){
                if(randomInt<individuals.get(i).getRouletteSelectionProbability()){
                    return individuals.get(i);
                }
            }
            if (randomInt >= individuals.get(i).getRouletteSelectionProbability() && randomInt < individuals.get(i + 1).getRouletteSelectionProbability()) {
                return individuals.get(i+1);
            }
        }
        return individuals.get(individuals.size() - 1);
    }

    private static void initRoulette(Population population) {
        population.setScores();
        double scoresSum = population.getSumScores();
        double probability;
        double previousProbability = 0;
        for (Individual individual : population.getAll()) {
            probability = previousProbability +  (individual.getScore() / scoresSum);
            individual.setRouletteSelectionProbability(probability);
            previousProbability = probability;
        }
    }


    private static Individual crossover(Individual indiv1, Individual indiv2) {

        int pivot = getRandomInt(0, solutionSize);
        Individual newIndiv = new Individual(model);
        ArrayList<Integer> child = ListUtils.getFilledListWithMinusOne(solutionSize);
        for (int i = 0; i < pivot; i++) {
            child.set(i, indiv1.getGenotype().get(i));
        }
        for (int i = pivot; i < solutionSize; i++) {
            child.set(i, indiv2.getGenotype().get(i));
        }
        repair(child);
        newIndiv.setGenotype(child);

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
