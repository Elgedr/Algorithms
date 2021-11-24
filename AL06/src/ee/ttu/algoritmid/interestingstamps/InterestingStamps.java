package ee.ttu.algoritmid.interestingstamps;

import java.util.ArrayList;
import java.util.List;

public class InterestingStamps {
    public static List<Integer> findStamps(int a, List<Integer> stampOptions) throws IllegalArgumentException {
        if (stampOptions == null || stampOptions.isEmpty() || a < 0) {
            throw new IllegalArgumentException();
        } else if (a == 0) {
            return new ArrayList<>();
        } else {
            a = a + 1;
            Integer[] lastMarks = new Integer[a + 1];
            Integer[] optimalMarks = new Integer[a + 1];
            lastMarks[0] = a;
            optimalMarks[0] = a;
            for (int i = 1; i <= a; i++) {
                optimalMarks[i] = Integer.MAX_VALUE;
                for (int j = 0; j < stampOptions.size(); j++) {
                    if (i - stampOptions.get(j) < 0) {

                    } else if (optimalMarks[i - stampOptions.get(j)] == null) {
                        optimalMarks[i - stampOptions.get(j)] = 0;
                    } else if ((i >= stampOptions.get(j)) && (optimalMarks[i] > (optimalMarks[i - stampOptions.get(j)]) + 1)) {
                        optimalMarks[i] = (optimalMarks[i - stampOptions.get(j)]) + 1;
                        lastMarks[i] = stampOptions.get(j);
                    }
                }
            }
            return getMarksList(a - 1, lastMarks);
        }
    }

    public static List<Integer> getMarksList(int listSize, Integer[] V) {
        List<Integer> integerList = new ArrayList<>();
        while (listSize > 0) {
            integerList.add(V[listSize]);
            listSize = listSize - V[listSize];
        }
        return integerList;
    }

}
