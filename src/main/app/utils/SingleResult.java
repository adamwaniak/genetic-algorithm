package app.utils;

import app.Population;

public class SingleResult {
    private int generation;
    private int bestFitness;
    private double averageFitness;
    private int worstFitness;

    public SingleResult(int generation, int bestFitness, double averageFitness, int worstFitness) {
        this.generation = generation;
        this.bestFitness = bestFitness;
        this.averageFitness = averageFitness;
        this.worstFitness = worstFitness;
    }

    public SingleResult(Population population, int generation) {
        this.generation = generation;
        this.bestFitness = population.getFittest().getFitness();
        this.averageFitness = population.getAverageFitness();
        this.worstFitness = population.getWorst().getFitness();
    }

    public int getGeneration() {
        return generation;
    }

    public int getBestFitness() {
        return bestFitness;
    }

    public double getAverageFitness() {
        return averageFitness;
    }

    public int getWorstFitness() {
        return worstFitness;
    }
}
