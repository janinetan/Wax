package org.morewax.study;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem
 * Remove fewest elements from the input sequence so that the remaining elements are ascending sorted
 *
 * Allegedly this is a FB phone screening question (really? DP for a phone screening?). I guess the interviewer was really
 * in a bad mood at that time.
 *
 * Here is the description of the original question:
 *

 Given a sequence of distinct integers, your program must remove as few
 elements as possible in order for the elements which are not removed
 to appear in ascending order.  If there is more than one way to do
 this, your program must print one solution, then print the number
 of all solutions.

 Example.
 Given   1 2 3 8 10 5 6 7 12 9 11 4 0
 Remove        8 10       12      4 0
 Remain  1 2 3      5 6 7    9 11       (ascending)

 To form an ascending sequence, you must remove at least 5 elements.
 There is only one way to do it.

 * This actually is an alternative description of Longest Increasing Sequence or LIS problem.
 * The variation here is also asking for the total number of LIS.
 *
 * The implementation here can easily be extended get all LIS.
 *
 * Created by Bing on 12/3/2015.
 */
public class LIS {
    public MutablePair<List<Integer>, Integer> findOneLISWithCount(int[] A) {
        List<MutablePair<List<Integer>, Integer>> dp = new ArrayList<>(); // LIS that ends at A[i] as well as the number of LIS that end at A[i]

        for (int i = 0; i < A.length; ++i) {
            dp.add(MutablePair.of(new ArrayList<>(), 0));
        }

        dp.get(0).getLeft().add(A[0]);
        dp.get(0).setRight(1);

        for (int i = 1; i < A.length; ++i) {
            MutablePair<List<Integer>, Integer> currentLis= null;
            for (int j = 0; j < i; ++j) {
                if (A[j] <= A[i]) {
                    if (currentLis == null || currentLis.getLeft().size()  < dp.get(j).getLeft().size()) {
                        currentLis = MutablePair.of(new ArrayList<>(dp.get(j).getLeft()), dp.get(j).getRight());
                    } else if (currentLis.getLeft().size() == dp.get(j).getLeft().size()){
                        currentLis.setRight(currentLis.getRight()+1);
                    }
                }
            }

            if (currentLis != null) {
                currentLis.getLeft().add(A[i]);
                dp.set(i, currentLis);
            } else {
                dp.get(i).getLeft().add(A[i]);
                dp.get(i).setRight(1);
            }
        }

        int maxLength = 0;
        MutablePair<List<Integer>, Integer> result = null;

        for (MutablePair<List<Integer>, Integer> lis : dp ) {
            if (lis.getKey().size() > maxLength) {
                maxLength = lis.getKey().size();
                result = lis;
            }
        }

        return result;
    }

    public void printResult(Pair<List<Integer>, Integer> lis) {
        int[] counter = new int[1];

        System.out.print(lis.getValue() + ": [");
        lis.getKey().forEach(n -> {
            if (counter[0]++ != 0) {
                System.out.print(", ");
            }
            System.out.print(n);
        });
        System.out.println("]");
    }

    public static void main(String[] args) {
        LIS lis = new LIS();

        int[] A1 = new int[]{3, 2, 6, 4, 5, 1};
        lis.printResult(lis.findOneLISWithCount(A1));


        int[] A2 = new int[]{1, 2, 3, 8, 10, 5, 6, 7, 12, 9, 11, 4, 0};
        lis.printResult(lis.findOneLISWithCount(A2));
    }
}
