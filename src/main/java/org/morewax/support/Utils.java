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

    public static void printArray(String[] A, int count) {
        System.out.print("[");

        if (count == -1) count = A.length;
        for (int i = 0; i < count; ++i) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(A[i]);
        }

        System.out.println("]");
    }

    /**
     * It gets median.
     * If the length is even, then both floor and ceiling elements will be returned with floor element at index 0 of
     * returned array;
     * If the length is odd, then both index 0 and index 1 of returned array contain the same value
     * @param A the input array
     * @return array with two elements
     */
    public static int[] getMedians(int[] A) {
        int kthOrder = (A.length+1)/2;
        select(A, 0, A.length-1, kthOrder);

        boolean isEven = (A.length%2) == 0;

        int[] result = new int[2];
        if (isEven) {
            result[0] = A[A.length/2-1];
            result[1] = A[A.length/2];
        } else {
            result[0] = A[A.length/2];
            result[1] = A[A.length/2];
        }

        return result;
    }

    public static int select(int[] A, int start, int end, int kthOrder) {
        int index = partition(A, start, end);
        int count = index-start+1;
        if (count == kthOrder) {
            return A[index];
        } else if (count > kthOrder) {
            return select(A, start, index-1, kthOrder);
        } else {
            return select(A, index+1, end, kthOrder-count);
        }
    }

    private static int partition(int[] A, int start, int end) {
        // [start, i-1] <= pivot;
        // [i, j-1] > pivot;
        // [j, end] unknown;
        int i = start-1;
        int pivot = A[end];
        for (int j = start; j <= end; ++j) {
            if (A[j] <= pivot) {
                int temp = A[i+1];
                A[++i] = A[j];
                A[j] = temp;
            }
        }

        return i;
    }
}
