package org.morewax.support;

import java.util.List;

/**
 * Created by Bing on 12/6/2015.
 */
public class Utils {
    public static <T> void print(List<T> list) {
        int[] counter = new int[1];

        System.out.print("[");
        list.forEach(item -> {
            if (counter[0]++ != 0) {
                System.out.print(", ");
            }
            System.out.print(item);
        });
        System.out.println("]");
    }

    public static void printMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; ++i) {
            int[] counter = new int[1];
            for (int j = 0; j < cols; ++j) {
                if (counter[0]++ != 0) {
                    System.out.print(", ");
                }
                System.out.print(matrix[i][j]);
            }
            System.out.println("");
        }
    }

    public static void printArray(int[] A, int count) {
        System.out.print("[");

        for (int i = 0; i < count; ++i) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(A[i]);
        }

        System.out.println("]");
    }
}
