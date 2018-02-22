import app.DataModel;
import app.RandomSearch;
import app.utils.DataReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Tests {

    private DataModel dataModel = DataReader.getModelsFromData(true).get(0);
    RandomSearch randomSearch = new RandomSearch(dataModel);

    @Test
    public void testDataReader(){

        Assert.assertNotNull(dataModel);
        String flowMatrix = Arrays.deepToString(dataModel.getFlowMatrix());
        System.out.println(flowMatrix);
    }

    @Test
    public void testTotalSum1(){
        int[] solution = new int[4];
        for(int i=0;i<4;i++){
            solution[i] = i;
        }
        Assert.assertEquals(dataModel.getTotalCost(solution),454);
    }

    @Test
    public void testTotalSum2(){
        int [] solution = {2,3,0,1};
        Assert.assertEquals(dataModel.getTotalCost(solution),395);
    }


    @Test
    public void testRandomSearch(){
        System.out.println(randomSearch.minTotalCost(1000));
    }
}
