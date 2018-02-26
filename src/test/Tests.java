import app.Individual;
import app.utils.GreedyAlgorithm;
import app.utils.ListUtils;
import app.utils.RandomSearch;
import app.utils.DataReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Tests {

    private Individual smallIndividual = DataReader.getModels(true).get(0);
    private Individual individual = DataReader.getModels(true).get(1);


    @Test
    public void testDataReader(){
        Assert.assertEquals(DataReader.getModels(true).size(),2);
    }

    @Test
    public void testTotalSum1(){
        ArrayList<Integer> solution = ListUtils.getFilledListWithZeroes(4);
        for(int i=0;i<4;i++){
            solution.set(i, i);
        }
        smallIndividual.setSolution(solution);
        Assert.assertEquals(smallIndividual.getFitness(),454);
    }

    @Test
    public void testTotalSum2(){
        ArrayList<Integer> solution = new ArrayList<>();
        solution.add(2);
        solution.add(3);
        solution.add(0);
        solution.add(1);
        smallIndividual.setSolution(solution);
        Assert.assertEquals(smallIndividual.getFitness(),395);
    }


    @Test
    public void testRandomSearch1(){
        RandomSearch randomSearch = new RandomSearch(smallIndividual);
        int minTotalCost = randomSearch.run(1000);
        System.out.println("Actual solution is " + minTotalCost);
        System.out.println("Optimal solution is 395");

    }

    @Test
    public void testRandomSearch2(){
        RandomSearch randomSearch = new RandomSearch(individual);
        System.out.println("Actual solution is " + randomSearch.run(1000));
        System.out.println("Optimal solution is 1160");
    }

    @Test
    public void testGreedyAlgorithm(){
        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(individual);
        individual.setSolution(greedyAlgorithm.solution());
        System.out.println("Actual solution is " + individual.getFitness());
        System.out.println("Optimal solution is 1160");
    }

    @Test
    public void random() {
        int startIndex = getRandomInt(0, 5);
        int endIndex = getRandomInt(startIndex,5);

        System.out.println("Start: " + startIndex + " end:" + endIndex );

    }

    private int getRandomInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    @Test
    public void listFilled() {
        ArrayList<Integer> list = ListUtils.getFilledListWithMinusOne(20);
        System.out.println(list.size());
        System.out.println(list.toString());
    }

    @Test
    public void addSubList() {
        ArrayList<Integer> solution = new ArrayList<>();
        solution.add(2);
        solution.add(3);
        solution.add(0);
        solution.add(1);

        ArrayList<Integer> child = new ArrayList<>();
        child.addAll(solution.subList(2,4));
        System.out.println(child);
    }
}
