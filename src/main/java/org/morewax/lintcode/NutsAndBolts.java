package org.morewax.lintcode;

import org.morewax.support.NBComparator;
import org.morewax.support.Utils;

/**
 * Problem
 * Nuts and Bolts ()
 * https://s3.amazonaws.com/uploads.hipchat.com/87219/1990749/WoNJaBcQJkIMfMd/upload.png
 * Created by byuan on 1/21/16.
 */
public class NutsAndBolts {
    /**
     * @param nuts: an array of integers
     * @param bolts: an array of integers
     * @param compare: a instance of Comparator
     * @return: nothing
     */
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        // write your code here
        helper(nuts, bolts, 0, nuts.length-1, compare);
    }

    private void helper(String[] nuts, String[] bolts, int l, int h, NBComparator compare) {
        if (l >= h) return;

        int p = split(nuts, l, h, bolts[h], compare);
        split(bolts, l, h, nuts[p], compare);

        helper(nuts, bolts, l, p-1, compare);
        helper(nuts, bolts, p+1, h, compare);
    }

    private int split(String[] collection, int l, int h, String pivot, NBComparator compare) {
        int smaller = l-1;
        int equal = l-1;

        for (int i = l; i <= h; ++i) {
            int result = compare.cmp(collection[i], pivot);
            if (result == 1) {
                continue;
            } else if (result == 0) {
                swap(collection, equal+1, i);
                ++equal;
            } else { // result == -1
                swap(collection, equal+1, i);
                swap(collection, equal+1, smaller+1);
                ++smaller;
                ++equal;
            }
        }

        return smaller+1;
    }

    private void swap(String[] collection, int i, int j) {
        String temp = collection[i];
        collection[i] = collection[j];
        collection[j] = temp;
    }

    public static void main(String[] args) {
        final NutsAndBolts s = new NutsAndBolts();

        String[] nuts = new String[]{"ab","bc","dd","gg"};
        String[] bolts = new String[]{"AB","GG","DD","BC"};
        s.sortNutsAndBolts(nuts, bolts, new NBComparator());
        Utils.printArray(nuts, -1);
        Utils.printArray(bolts, -1);

    }
}
