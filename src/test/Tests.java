import app.Individual;
import app.utils.GreedyAlgorithm;
import app.utils.RandomSearch;
import app.utils.DataReader;
import org.junit.Assert;
import org.junit.Test;

public class Tests {

    private Individual smallIndividual = DataReader.getModels(true).get(0);
    private Individual individual = DataReader.getModels(true).get(1);


    @Test
    public void testDataReader(){
        Assert.assertEquals(DataReader.getModels(true).size(),2);
    }

    @Test
    public void testTotalSum1(){
        int[] solution = new int[4];
        for(int i=0;i<4;i++){
            solution[i] = i;
        }
        smallIndividual.setSolution(solution);
        Assert.assertEquals(smallIndividual.getFitness(),454);
    }

    @Test
    public void testTotalSum2(){
        int [] solution = {2,3,0,1};
        smallIndividual.setSolution(solution);
        Assert.assertEquals(smallIndividual.getFitness(),395);
    }


    @Test
    public void testRandomSearch1(){
        RandomSearch randomSearch = new RandomSearch(smallIndividual);
        int minTotalCost = randomSearch.minTotalCost(1000);
        System.out.println("Actual solution is " + minTotalCost);
        System.out.println("Optimal solution is 395");

    }

    @Test
    public void testRandomSearch2(){
        RandomSearch randomSearch = new RandomSearch(individual);
        System.out.println("Actual solution is " + randomSearch.minTotalCost(1000));
        System.out.println("Optimal solution is 1160");
    }

    @Test
    public void testGreedyAlgorithm(){
        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(individual);
        individual.setSolution(greedyAlgorithm.solution());
        System.out.println("Actual solution is " + individual.getFitness());
        System.out.println("Optimal solution is 1160");
    }
}
