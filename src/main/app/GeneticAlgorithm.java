package app;

import app.utils.ListUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Collections;
import java.util.List;

public class GeneticAlgorithm {

    private Individual originIndividual;
    private int popSize;
    private int maxFitness;
    private Population population;
    private int generationCount;
    private int maxGenerationCount;
    private int tournamentSize;
    private double crossoverRate;
    private double mutationRate;
    private int mutationCount;


    public GeneticAlgorithm(Individual originIndividual, int popSize, int maxGenerationCount, double crossoverRate, double mutationRate, int tournamentSize) {
        population = new Population();
        maxFitness = Integer.MAX_VALUE;
        generationCount = 0;
        this.originIndividual = originIndividual;
        this.popSize = popSize;
        this.maxGenerationCount = maxGenerationCount;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.tournamentSize = tournamentSize;
    }

    public void run() {
        population.init(originIndividual, popSize);
        while (generationCount < maxGenerationCount) {
            System.out.println("Actual best individual: " + population.getFittest().getFitness() * 2);
            population = evolvePopulation(population);
            generationCount++;
        }
        System.out.println("Best individual in population: " + population.getFittest().getFitness() * 2);
        System.out.println("Solution: " + population.getFittest().getSolution().toString());
    }


    private Population evolvePopulation(@NotNull Population population) {
        Population newPopulation = new Population();
        for (int i = 0; i < population.getIndividuals().size(); i++) {
            Individual indiv1 = tournamentSelection(population);
            Individual indiv2 = tournamentSelection(population);
            Individual newIndiv = crossover(indiv1, indiv2);
            if (Math.random() <= mutationRate) {
                mutate(newIndiv);
            }
            newPopulation.getIndividuals().add(newIndiv);
        }
        return newPopulation;
    }


    private Individual tournamentSelection(@NotNull Population population) {
        Population tournament = new Population();
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * population.getIndividuals().size());
            tournament.getIndividuals().add(i, population.getIndividuals().get(randomId));
        }
        Individual fittest = tournament.getFittest();
        return fittest;
    }

    private Individual crossover(Individual indiv1, Individual indiv2) {
        int size = indiv1.getSize();
        int pivot = getRandomInt(0, size);
        Individual newIndiv = new Individual(originIndividual);
        ArrayList<Integer> child = ListUtils.getFilledListWithMinusOne(size);
        for (int i = 0; i < pivot; i++) {
            child.set(i, indiv1.getSolution().get(i));
        }
        for (int i = pivot; i < size; i++) {
            child.set(i, indiv2.getSolution().get(i));
        }
        repair(child);
        newIndiv.setSolution(child);

        return newIndiv;
    }

    private void mutate(Individual indiv) {
        List<Integer> integerList = ListUtils.getFilledList(indiv.getSize());
        Collections.shuffle(integerList);
        indiv.swapSolutionValues(integerList.get(0), integerList.get(1));
        mutationCount++;

    }

    /**
     * After crossover locations could reapate. Repair method fix it.
     */
    public void repair(ArrayList<Integer> solution) {
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

    private int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }


}
