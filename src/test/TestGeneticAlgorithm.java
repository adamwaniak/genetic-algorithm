import app.GeneticAlgorithm;
import app.Individual;
import app.Model;
import app.utils.DataReader;
import app.utils.RandomSearch;
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
        GeneticAlgorithm.run(model,100,1000,0.7,0.5,5);

    }


}
