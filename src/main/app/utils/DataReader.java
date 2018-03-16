package app.utils;


import app.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DataReader {

    //Linux
    private static final String dataPath = "/home/adam/IdeaProjects/genetic-algorithm/src/resources/data.txt";
    private static final String testDatePath = "/home/adam/IdeaProjects/genetic-algorithm/src/resources/test-data.txt";
    //Windows
//    private static final String dataPath = "C:\\Users\\adamw\\IdeaProjects\\genetic-algorithm\\src\\resources\\data.txt";
//    private static final String testDatePath = "C:\\Users\\adamw\\IdeaProjects\\genetic-algorithm\\src\\resources\\test-data.txt";

    private static List<Model> models;

    private DataReader() {
    }


    public static List<Model> getModels(boolean isTest) {
        if (models == null) {
            models = getModelsFromData(isTest);
        }
        return models;
    }

    public static List<Model> getModels() {
        return getModels(false);
    }


    private static List<Model> getModelsFromData() {
        return getModelsFromData(false);
    }

    /**
     * @return Map<n       ,               Individual> where n in number of locations
     */
    private static List<Model> getModelsFromData(boolean isTest) {
        List<Model> models = new ArrayList<>();
        int numberOfLocations;
        String path;
        if (isTest) {
            path = testDatePath;
        } else {
            path = dataPath;
        }
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Scanner scanner = new Scanner(bufferedReader);

            while (scanner.hasNextInt()) {
                numberOfLocations = scanner.nextInt();
                Model model = new Model();
                model.setSize(numberOfLocations);
                int[][] flowMatrix = new int[numberOfLocations][numberOfLocations];
                int[][] distanceMatrix = new int[numberOfLocations][numberOfLocations];
                for (int i = 0; i < numberOfLocations; i++) {
                    for (int j = 0; j < numberOfLocations; j++) {
                        int n = scanner.nextInt();
                        flowMatrix[i][j] = n;
                    }
                }

                for (int i = 0; i < numberOfLocations; i++) {
                    for (int j = 0; j < numberOfLocations; j++) {
                        distanceMatrix[i][j] = scanner.nextInt();
                    }
                }
                model.setFlowMatrix(flowMatrix).setDistanceMatrix(distanceMatrix);
                models.add(model);
            }
            scanner.close();
            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            path + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + path + "'");
        }

        return models;
    }


}
