package app.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static List<Integer> getFilledList(int size) {
        List<Integer> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(i);
        }
        return result;
    }

    public static List<Boolean> getFalseList(int size){
        List<Boolean> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(false);
        }
        return result;
    }

    private ListUtils(){}
}
