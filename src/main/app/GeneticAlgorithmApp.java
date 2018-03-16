package app;

import app.utils.*;

import java.util.LinkedList;
import java.util.List;

public class GeneticAlgorithmApp {
    private final static GeneticAlgorithm.Selection tournament = GeneticAlgorithm.Selection.TOURNAMENT;
    private final static GeneticAlgorithm.Selection roulette = GeneticAlgorithm.Selection.ROULETTE;
    private final static List<Model> models = DataReader.getModels();


    public static void main(String[] args) {
        int[] popSizes = {100, 1000};
        int[] generationSizes = {100, 1000};
        double[] crossoverSizes = {0.2, 0.4, 0.6, 0.8};
        double[] mutationSizes = {0.05, 0.1, 0.2, 0.4, 0.6};
        int[] tournamentSizes = {2, 5, 20, 50};
        int testsNumber = 10;
        int[] popSize_100 = {100};
        int[] generationSize_100 = {100};
        double[] crossoverSize_07 = {0.7};
        double[] mutationSize_005 = {0.05};
        int[] tournamentSize_5 = {5};
        List<Model> chosenModels = new LinkedList<>();
        chosenModels.add(models.get(0));
        chosenModels.add(models.get(4));
        greedyAlgoritmResult(models);
        randomAlgoritmResults(models, popSize_100, generationSize_100, testsNumber);
        geneticAlgorithmResults(models, popSizes, generationSizes, crossoverSize_07, mutationSize_005, tournamentSize_5, testsNumber);
        geneticAlgorithmResults(chosenModels, popSize_100, generationSize_100, crossoverSizes, mutationSize_005, tournamentSize_5, testsNumber);
        geneticAlgorithmResults(chosenModels, popSize_100, generationSize_100, crossoverSize_07, mutationSizes, tournamentSize_5, testsNumber);
        geneticAlgorithmResults(chosenModels, popSize_100, generationSize_100, crossoverSize_07, mutationSize_005, tournamentSizes, testsNumber);

    }


    public static void geneticAlgorithmResults(List<Model> models, int[] popSizes, int[] generationSizes, double[] crossoverSizes, double[] mutationSizes,
                                               int[] tournamentSizes, int testsNumber) {
        for (Model model : models) {
            for (int popSize : popSizes) {
                for (int generationSize : generationSizes) {
                    for (double crossoverRate : crossoverSizes) {
                        for (double mutationRate : mutationSizes) {
                            List<List<SingleResult>> singleResultsList;
                            List<SingleResult> averageResults;
                            String fileName;
                            //Genetic tournament
                            for (int tournamentSize : tournamentSizes) {
                                singleResultsList = new LinkedList<>();
                                for (int i = 0; i < testsNumber; i++) {
                                    List<SingleResult> singleResults = GeneticAlgorithm.run(model, popSize, generationSize, crossoverRate, mutationRate, tournament, tournamentSize);
                                    singleResultsList.add(singleResults);
                                }
                                averageResults = AverageResult.getAverageResultFromResults(singleResultsList, generationSize);
                                fileName = "ga_tournament_" + model.getSize() + "n_popSize" + popSize + "_generationSize" + generationSize +
                                        "_crossoverRate" + crossoverRate + "_mutationRate" + mutationRate + "_tournamentSize" + tournamentSize;
                                DataWriter.writeAdditionalInformation(popSize, generationSize, crossoverRate, mutationRate, tournamentSize, fileName);
                                DataWriter.writeData(averageResults, fileName);
                            }


                            //Genetic roulette
                            singleResultsList = new LinkedList<>();
                            for (int i = 0; i < testsNumber; i++) {
                                List<SingleResult> singleResults = GeneticAlgorithm.run(model, popSize, generationSize, crossoverRate, mutationRate, roulette, 0);
                                singleResultsList.add(singleResults);
                            }
                            averageResults = AverageResult.getAverageResultFromResults(singleResultsList, generationSize);
                            fileName = "ga_roulette_" + model.getSize() + "n_popSize" + popSize + "_generationSize" + generationSize +
                                    "_crossoverRate" + crossoverRate + "_mutationRate" + mutationRate;
                            DataWriter.writeAdditionalInformation(popSize, generationSize, crossoverRate, mutationRate, 0, fileName);
                            DataWriter.writeData(averageResults, fileName);
                        }
                    }
                }
            }
        }
    }

    public static void greedyAlgoritmResult(List<Model> models) {
        List<SingleResult> greedyAlgorithmResults = new LinkedList<>();

        for (Model model : models) {
            SingleResult singleResult = GreedyAlgorithm.run(model);
            greedyAlgorithmResults.add(singleResult);
        }
        DataWriter.writeData(greedyAlgorithmResults, "greedy_results_for_all");
    }

    public static void randomAlgoritmResults(List<Model> models, int[] popSizes, int[] generationSizes, int testsNumber) {
        for (Model model : models) {
            for (int popSize : popSizes) {
                for (int generationSize : generationSizes) {
                    List<List<SingleResult>> singleResultsList = new LinkedList<>();
                    for (int i = 0; i < testsNumber; i++) {
                        List<SingleResult> singleResults = RandomSearch.run(model, popSize, generationSize);
                        singleResultsList.add(singleResults);
                    }
                    List<SingleResult> averageResults = AverageResult.getAverageResultFromResults(singleResultsList, generationSize);
                    String fileName = "ga_random_" + model.getSize() + "n_popSize" + popSize + "_generationSize" + generationSize;
                    DataWriter.writeAdditionalInformation(popSize, generationSize, 0, 0, 0, fileName);
                    DataWriter.writeData(averageResults, fileName);
                }
            }
        }
    }
}