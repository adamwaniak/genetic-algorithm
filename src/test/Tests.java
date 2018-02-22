import app.DataModel;
import app.GreedyAlgorithm;
import app.RandomSearch;
import app.utils.DataReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Tests {

    private DataModel smallDataModel = DataReader.getModelsFromData(true).get(0);
    private DataModel dataModel = DataReader.getModelsFromData(true).get(1);


    @Test
    public void testDataReader(){

        Assert.assertNotNull(smallDataModel);
        String flowMatrix = Arrays.deepToString(smallDataModel.getFlowMatrix());
        System.out.println(flowMatrix);
    }

    @Test
    public void testTotalSum1(){
        int[] solution = new int[4];
        for(int i=0;i<4;i++){
            solution[i] = i;
        }
        Assert.assertEquals(smallDataModel.getTotalCost(solution),454);
    }

    @Test
    public void testTotalSum2(){
        int [] solution = {2,3,0,1};
        Assert.assertEquals(smallDataModel.getTotalCost(solution),395);
    }


    @Test
    public void testRandomSearch1(){
        RandomSearch randomSearch = new RandomSearch(smallDataModel);
        int minTotalCost = randomSearch.minTotalCost(1000);
        System.out.println("Actual solution is " + minTotalCost);
        System.out.println("Optimal solution is 395");

    }

    @Test
    public void testRandomSearch2(){
        RandomSearch randomSearch = new RandomSearch(dataModel);
        System.out.println("Actual solution is " + randomSearch.minTotalCost(1000));
        System.out.println("Optimal solution is 1160");
    }

    @Test
    public void testGreedyAlgorithm(){
        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(dataModel);
        System.out.println("Actual solution is " + greedyAlgorithm.minTotalCost());
        System.out.println("Optimal solution is 1160");
    }
}
