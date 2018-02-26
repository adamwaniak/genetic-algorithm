package app.utils;


import java.io.*;
import java.util.Date;
import java.util.List;

// <generation_number, best_fitness, average_fitness, worst fitness>  
public class DataWriter {

    private static final String dataDirPath = "C:\\Users\\adamw\\IdeaProjects\\genetic-algorithm\\src\\resources\\";

    public static void writeData(List<Result> results, String fileName) {
        try {

            File file = new File(dataDirPath + fileName + ".txt");

            PrintWriter printWriter = new PrintWriter(new FileOutputStream(file,true));
            printWriter.println("generation_number, best_fitness, average_fitness, worst fitness");

            for (Result result : results) {
                printWriter.printf("%d,%d,%f,%d \n", result.getGeneration(), result.getBestFitness(), result.getAverageFitness(), result.getWorstFitness());
            }
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void writeAdditionalInformation(int popSize, int generationSize, double crossoverRate, double mutationRate, int tournamentSize, String fileName) {
        try {

            File file = new File(dataDirPath + fileName + ".txt");
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.printf("Population size %d, Generation size:%d, Crossover rate: %f, Mutation rate: %f, Tournament size: %d \n",
                    popSize, generationSize, crossoverRate, mutationRate, tournamentSize);

            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
