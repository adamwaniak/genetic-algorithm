import app.GeneticAlgorithm;
import app.Individual;
import app.utils.DataReader;
import app.utils.RandomSearch;
import org.junit.Assert;
import org.junit.Test;

public class TestGeneticAlgorithm {


    @Test
    public void testTournament(){
        Individual originIndividual = DataReader.getModels().get(0);
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        Assert.assertNotNull(geneticAlgorithm);
    }



    @Test
    public void testAlgorithm() {
        Individual originIndividual = DataReader.getModels().get(0);
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        geneticAlgorithm.run(originIndividual,100,100,1,1,5);

    }


}
