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
            mutate(newIndiv);
            newPopulation.getIndividuals().add(i, newIndiv);
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
        int start = getRandomInt(0, size);
        int end = getRandomInt(start, size);
        ArrayList<Integer> child = ListUtils.getFilledListWithMinusOne(size);
        ArrayList<Integer> parent;

        if (Math.random() > 0.5) {
            for (int i = start;i<end;i++){
                child.set(i,indiv1.getSolution().get(i));
            }
            parent = indiv2.getSolution();

        }else {
            for (int i = start;i<end;i++){
                child.set(i,indiv2.getSolution().get(i));
            }
            parent = indiv1.getSolution();
        }
        int currentLocationIndex = 0;
        int currentLocationInParent = 0;





        Individual newIndiv = new Individual(indiv1);
        newIndiv.setSolution(child);
        newIndiv.repair();
        if(child.size()>size || child.size()<size){
            System.out.println("Start: " + start);
            System.out.println("End: " + end);

            System.out.println("Indiv1: " + indiv1.getSolution().toString());
            System.out.println("Indiv2: " + indiv2.getSolution().toString());
            System.out.println("Parent solution: " + parent.toString());

            System.out.println("Child solution: " + child.toString());

        }
        return newIndiv;
    }

    private void mutate(Individual indiv) {
        if (Math.random() <= mutationRate) {
            List<Integer> integerList = ListUtils.getFilledList(indiv.getSize());
            Collections.shuffle(integerList);
            indiv.swapSolutionLocations(integerList.get(0), integerList.get(1));
            mutationCount++;
        }
    }

    private int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
