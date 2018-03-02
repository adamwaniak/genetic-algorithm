package app;

import app.utils.DataReader;
import app.utils.DataWriter;
import app.utils.RandomSearch;
import app.utils.Result;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GeneticAlgorithmApp {

    public static void main(String[] args) {
        final int popSize = 100;
        final int generationSize = 100;
        final double crossoverRate = 0.7;
        final double mutationRate = 0.05;
        final int tournamentSize = 5;

        List<Model> models = DataReader.getModels();

        for (Model model: models){
            //Genetic algorithm
            List<Result> results = GeneticAlgorithm.run(model,popSize,generationSize,crossoverRate,mutationRate,tournamentSize);
            String fileName = "genetic_algorithm_result_" + model.getSize() + "n";
            DataWriter.writeAdditionalInformation(popSize,generationSize,crossoverRate,mutationRate,tournamentSize,fileName);
            DataWriter.writeData(results, fileName);


            //Random


        }




    }
}
