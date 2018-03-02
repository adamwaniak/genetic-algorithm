import app.GeneticAlgorithm;
import app.Model;
import app.utils.DataReader;
import org.junit.Assert;
import org.junit.Test;

public class TestGeneticAlgorithm {


    @Test
    public void testTournament(){
        Model model = DataReader.getModels().get(0);
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        Assert.assertNotNull(geneticAlgorithm);
    }



    @Test
    public void testAlgorithm() {
        Model model = DataReader.getModels().get(4);
        GeneticAlgorithm.run(model,100,100,0.7,0.05, GeneticAlgorithm.Selection.TOURNAMENT,5);

    }


}
