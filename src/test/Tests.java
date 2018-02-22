import app.DataModel;
import app.utils.DataReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

public class Tests {



    @Test
    public void testDataReader(){
        DataModel dataModel = DataReader.getModelsFromData(true).get(0);
        Assert.assertNotNull(dataModel);
        String flowMatrix = Arrays.deepToString(dataModel.getFlowMatrix());
        System.out.println(flowMatrix);
    }

    @Test
    public void testTotalSum1(){
        DataModel dataModel = DataReader.getModelsFromData(true).get(0);
        int[] solution = new int[4];
        for(int i=0;i<4;i++){
            solution[i] = i;
        }
        Assert.assertEquals(dataModel.getTotalCost(solution),454);
    }

    @Test
    public void testTotalSum2(){
        DataModel dataModel = DataReader.getModelsFromData(true).get(0);
        int [] solution = {2,3,0,1};
        Assert.assertEquals(dataModel.getTotalCost(solution),395);
    }

}
