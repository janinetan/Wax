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
}
