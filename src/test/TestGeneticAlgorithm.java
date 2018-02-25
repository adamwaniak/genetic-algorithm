import app.GeneticAlgorithm;
import app.Individual;
import app.utils.DataReader;
import app.utils.RandomSearch;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestGeneticAlgorithm {


    @Test
    public void testTournament(){
        Individual originIndividual = DataReader.getModels().get(0);
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(originIndividual,10,10,0.7,0.05,2);
        Assert.assertNotNull(geneticAlgorithm);
    }



    @Test
    public void testAlgorithm() {
        Individual originIndividual = DataReader.getModels(true).get(0);
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(originIndividual,100,100,0.7,0.05,5);
        geneticAlgorithm.run();

    }

    @Test
    public void testRandomSearch2(){
        Individual originIndividual = DataReader.getModels(true).get(0);
        RandomSearch randomSearch = new RandomSearch(originIndividual);
        randomSearch.minTotalCost(100);
    }
}
