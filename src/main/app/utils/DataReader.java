package app.utils;


import app.DataModel;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class DataReader {
    private static final String dataPath = "C:\\Users\\adam\\IdeaProjects\\genetic-algorithm\\src\\resources\\data.txt";
    private static final String testDatePath = "C:\\Users\\adam\\IdeaProjects\\genetic-algorithm\\src\\resources\\test-data.txt";

    private DataReader() {
    }

    public static Map<Integer, DataModel> getModelsFromData(){
        return getModelsFromData(false);
    }

    /**
     * @return Map<n   ,       DataModel> where n in number of locations
     */
    public static Map<Integer, DataModel> getModelsFromData(boolean isTest ) {
        Map<Integer, DataModel> locationsMap = new HashMap<>();
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
                locationsMap.put(numberOfLocations, dataModel);
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

        return locationsMap;
    }



}
