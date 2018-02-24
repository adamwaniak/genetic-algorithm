package app;

import app.utils.ListUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
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
            System.out.println("Actual best individual: " + population.getFittest().getFitness()*2);
            population = evolvePopulation(population);
            generationCount++;
        }
        System.out.println("Best individual in population: " + population.getFittest().getFitness()*2);
        System.out.println("Solution: " + Arrays.toString(population.getFittest().getSolution()));
    }


    private Population evolvePopulation(@NotNull Population population) {
        Population newPopulation = new Population();
        for (int i = 0; i < population.getIndividuals().size(); i++) {
            Individual indiv1 = tournamentSelection(population);
            Individual indiv2 = tournamentSelection(population);
            Individual newIndiv = crossover(indiv1, indiv2);
//            mutate(newIndiv);
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
        Individual newIndiv;
        newIndiv = indiv1.getFitness() > indiv2.getFitness() ?  new Individual(indiv1) :  new Individual(indiv2);
        Individual worseIndiv = indiv1.getFitness() < indiv2.getFitness() ?  indiv1 :  indiv2;
        for (int i = 0; i < newIndiv.getNumberOfLocations(); i++) {
            if(indiv1.getSingleLocation(i)!=indiv2.getSingleLocation(i)){
                if (Math.random() <= crossoverRate) {
                    newIndiv.setSingleLocation(i, worseIndiv.getSingleLocation(i));
                }
            }

        }
        newIndiv.repair();
        return newIndiv;
    }

    private void mutate(Individual indiv) {
        if (Math.random() <= mutationRate) {
            List<Integer> integerList = ListUtils.getFilledList(indiv.getNumberOfLocations());
            Collections.shuffle(integerList);
            indiv.swapSolutionLocations(integerList.get(0), integerList.get(1));
            mutationCount++;
        }
    }
}
