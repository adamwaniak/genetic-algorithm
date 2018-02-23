package app.utils;


import app.DataModel;

import java.io.*;
import java.util.*;


public class DataReader {

    //Linux
    private static final String dataPath = "/home/adam/IdeaProjects/genetic-algorithm/src/resources/data.txt";
    private static final String testDatePath = "/home/adam/IdeaProjects/genetic-algorithm/src/resources/test-data.txt";

    private static List<DataModel> models;

    private DataReader() { }


    public static List<DataModel> getModels(boolean isTest) {
        if (models == null){
            models = getModelsFromData(isTest);
        }
        return models;
    }

    public static List<DataModel> getModels() {
        return getModels(false);
    }


    private static List<DataModel> getModelsFromData(){
        return getModelsFromData(false);
    }

    /**
     * @return Map<n   ,       DataModel> where n in number of locations
     */
    private static List<DataModel> getModelsFromData(boolean isTest ) {
        List<DataModel> dataModels = new ArrayList<>();
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
                DataModel dataModel = new DataModel();
                dataModel.setNumberOfLocations(numberOfLocations);
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
                dataModel.setFlowMatrix(flowMatrix).setDistanceMatrix(distanceMatrix);
                dataModels.add(dataModel);
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

        return dataModels;
    }



}
