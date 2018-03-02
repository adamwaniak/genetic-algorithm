import app.GeneticAlgorithm;
import app.Model;
import app.utils.DataReader;
import org.junit.Assert;
import org.junit.Test;

public class TestGeneticAlgorithm {

    private Model model = DataReader.getModels().get(4);


    @Test
    public void testTournament() {
        GeneticAlgorithm.run(model, 100, 100, 0.7, 0.15, GeneticAlgorithm.Selection.TOURNAMENT, 5);

    }

    @Test
    public void testRoulette() {
        GeneticAlgorithm.run(model,100,100,0.7,0.05, GeneticAlgorithm.Selection.ROULETTE,0);
    }
}
