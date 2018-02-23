package app;

public class GeneticAlgorithm  {

    private int maxFitness;
    private Population population;
    private int generationCount;

    public GeneticAlgorithm(Population population) {
        this.population = population;
        this.tournamentSize = tournamentSize;
        maxFitness = Integer.MAX_VALUE;
        generationCount = 0;
    }

    public void run(){
        while (population.getFittest().getFitness() < getMaxFitness()) {
            System.out.println(
                    "Generation: " + generationCount
                            + " Correct genes found: " + population.getFittest().getFitness());

            population = evolvePopulation(population);
            generationCount++;
        }
    }


    private Population evolvePopulation(Population population) {
        return population;
    }


    private int getMaxFitness(){
        return maxFitness;
    }

    private Individual tournamentSelection(Population pop) {
        Population tournament = new Population(tournamentSize, false);
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.getIndividuals().size());
            tournament.getIndividuals().add(i, pop.getIndividual(randomId));
        }
        Individual fittest = tournament.getFittest();
        return fittest;
    }
}
