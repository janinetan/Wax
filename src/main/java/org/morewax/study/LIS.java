package org.morewax.study;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bing on 12/3/2015.
 */
public class LIS {
    public List<Integer> findOneLIS(int[] A) {
        List<List<Integer>> dp = new ArrayList<>();

        for (int i = 0; i < A.length; ++i) {
            dp.add(new ArrayList<>());
        }

        dp.get(0).add(A[0]);

        for (int i = 1; i < A.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (A[j] <= A[i]) {
                    if (dp.get(i).size() < dp.get(j).size()+1) {
                        dp.set(i, new ArrayList<>(dp.get(j)));
                    }
                }
            }
            dp.get(i).add(A[i]);
        }

        int maxLength = 0;
        List<Integer> result = null;

        for (List<Integer> lis : dp ) {
            if (lis.size() > maxLength) {
                maxLength = lis.size();
                result = lis;
            }
        }

        return result;
    }

    public void printResult(List<Integer> lis) {
        int[] counter = new int[1];

        System.out.print("[");
        lis.forEach(n -> {
            if (counter[0]++ != 0) {
                System.out.print(", ");
            }
            System.out.print(n);
        });
        System.out.println("]");
    }

    public static void main(String[] args) {
        LIS lis = new LIS();
        List<Integer> result = null;

        int[] A1 = new int[]{3, 2, 6, 4, 5, 1};
        lis.printResult(lis.findOneLIS(A1));
    }
}
