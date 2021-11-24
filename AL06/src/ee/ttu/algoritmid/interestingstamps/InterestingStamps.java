package ee.ttu.algoritmid.interestingstamps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InterestingStamps {

    public static List<Integer> findStamps(int sum, List<Integer> stampOptions) throws IllegalArgumentException {
        if (stampOptions == null || stampOptions.isEmpty()) {
            throw new IllegalArgumentException();
        } else if (sum <= 0) {
            return new ArrayList<>();
        } else {
            int[] marksSums = new int[sum + 1];
            marksSums[0] = 0;
            for (int i = 1; i <= sum; i++) {
                marksSums[i] = Integer.MAX_VALUE;
            }
            HashMap<Integer, List<Integer>> marksSumsMap = new HashMap<>();
            for (int index = 1; index <= sum; index++) {
                List<Integer> marksOpt = new ArrayList<>();
                for (int j = 0; j < stampOptions.size() - 1; j++) {
                    int currentStampOption = stampOptions.get(j);
                    if (currentStampOption <= index) {
                        int subSum = marksSums[index - currentStampOption];
                        if (subSum != Integer.MAX_VALUE && subSum + 1 < marksSums[index]) {
                            marksOpt.add(currentStampOption);
                            marksSums[index] = subSum + 1;
                            List<Integer> existSolution = marksSumsMap.get(index - currentStampOption);
                            if (existSolution != null) marksOpt.addAll(existSolution);
                            marksSumsMap.put(index, marksOpt);
                            marksOpt = new ArrayList<>();
                        }
                    }
                }
            }
            return marksSumsMap.get(sum);
        }

    }

    public static void main(String[] args) {
        List<Integer> result = InterestingStamps.findStamps(100, Arrays.asList(1, 10, 24, 30, 33, 36));
        System.out.println(result);
    }
}



