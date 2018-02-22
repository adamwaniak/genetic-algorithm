package app;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class RandomSearch extends Algorithm{


    public RandomSearch(DataModel dataModel) {
        super(dataModel);
    }




    public int[] solution() {
        int n = dataModel.getNumberOfLocations();
        int[] result = new int[n];
        List<Integer> factories = getFilledList(n);
        Collections.shuffle(factories);

        for (int i = 0; i < n; i++) {
            result[i] = factories.get(i);
        }
        return result;
    }



}
