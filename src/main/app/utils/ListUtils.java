package app.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static List<Integer> getFilledList(int size) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(i);
        }
        return result;
    }

    private ListUtils(){}
}
