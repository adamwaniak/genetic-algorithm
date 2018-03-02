import app.Individual;
import app.Model;
import app.utils.DataReader;
import app.utils.RandomSearch;
import org.junit.Test;

import java.util.List;

public class RandomSearchTest {

    private List<Model> models = DataReader.getModels();
    @Test
    public void test1() {
        RandomSearch randomSearch = new RandomSearch(models.get(4));
        randomSearch.run(10000);
    }
}
