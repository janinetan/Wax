package org.morewax.meetqun;

import org.morewax.support.Utils;

import java.util.Arrays;

/**
 * Problem
 * We have an array of objects A and an array of indexes B. Reorder objects in array A with given indexes in array B. Do not change array A's length.

 example:


 var A = [C, D, E, F, G];
 var B = [3, 0, 4, 1, 2];

 sort(A, B);
 // A is now [D, F, G, C, E];
 * Created by Bing on 12/5/2015.
 */
public class RestoreArray {
    public void restore(Character[] array, Integer[] indexes) {
        if (array.length == 0 || array.length != indexes.length) return;

        System.out.print("Before: ");
        Utils.print(Arrays.asList(array));

        int i = 0;
        int n = array.length;

        while (i < n) {
            while (i != indexes[i]) {
                char temp = array[i];
                array[i] = array[indexes[i]];
                array[indexes[i]] = temp;

                int tempIndex = indexes[i];
                indexes[i] = indexes[indexes[i]];
                indexes[tempIndex] = tempIndex;
            }
            ++i;
        }

        System.out.print("Restored: ");
        Utils.print(Arrays.asList(array));
    }

    public static void main(String[] args) {
        RestoreArray solution = new RestoreArray();

        Character[] A = new Character[]{'C', 'D', 'E', 'F', 'G'};
        Integer[] B = new Integer[]{3, 0, 4, 1, 2};
        solution.restore(A, B);

    }
}
