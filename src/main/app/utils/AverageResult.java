package app.utils;

import java.util.LinkedList;
import java.util.List;

public class AverageResult {

    public static List<SingleResult> getAverageResultFromResults(List<List<SingleResult>> listOfSingleGenerationsResults, int generationSize) {
        List<SingleResult> averageResults = new LinkedList<>();
        int numberOfRunTests = listOfSingleGenerationsResults.size();
        for (int i = 0; i < generationSize; i++) {
            int averageBestFitness = 0;
            double averageAverageFitness = 0;
            int averageWorstFitness = 0;
            for (int j = 0; j < numberOfRunTests; j++) {
                averageBestFitness += listOfSingleGenerationsResults.get(j).get(i).getBestFitness();
                averageAverageFitness += listOfSingleGenerationsResults.get(j).get(i).getAverageFitness();
                averageWorstFitness += listOfSingleGenerationsResults.get(j).get(i).getWorstFitness();
            }
            averageBestFitness /= numberOfRunTests;
            averageAverageFitness /= numberOfRunTests;
            averageWorstFitness /= numberOfRunTests;

            SingleResult singleResult = new SingleResult(i, averageBestFitness, averageAverageFitness, averageWorstFitness);
            averageResults.add(singleResult);
        }


        return averageResults;
    }
}
