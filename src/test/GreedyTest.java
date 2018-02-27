import app.Individual;
import app.utils.DataReader;
import app.utils.GreedyAlgorithm;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GreedyTest {
    List<Individual> models = DataReader.getModels();


    @Test
    public void test() {
        Individual originIndividual = models.get(4);
        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(originIndividual);
        ArrayList<Integer> solution = greedyAlgorithm.solution();
        originIndividual.setSolution(solution);
        System.out.println("Solution: " + solution.toString());
        System.out.println("Cost: " + originIndividual.getFitness());
    }
}
