import app.Individual;
import app.Model;
import app.utils.DataReader;
import app.utils.GreedyAlgorithm;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GreedyTest {
    List<Model> models = DataReader.getModels();


    @Test
    public void test() {
        Model model = models.get(4);
        ArrayList<Integer> solution = GreedyAlgorithm.getSolution(model);
        Individual individual = new Individual(model);
        individual.setSolution(solution);
        System.out.println("Solution: " + solution.toString());
        System.out.println("Cost: " + individual.getFitness());
    }
}
