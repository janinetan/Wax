package org.morewax.lintcode;

import org.morewax.support.NBComparator;
import org.morewax.support.Utils;

/**
 * Problem
 * Nuts and Bolts (http://www.lintcode.com/en/problem/nuts-bolts-problem/)
 * Given a set of n nuts of different sizes and n bolts of different sizes. There is a one-one mapping between nuts and
 * bolts. Comparison of a nut to another nut or a bolt to another bolt is not allowed. It means nut can only be compared
 * with bolt and bolt can only be compared with nut to see which one is bigger/smaller.
 * We will give you a compare function to compare nut with bolt.
 *  Example
 *      Given nuts = ['ab','bc','dd','gg'], bolts = ['AB','GG', 'DD', 'BC'].
 * Your code should find the matching bolts and nuts.
 * one of the possible return:
 * nuts = ['ab','bc','dd','gg'], bolts = ['AB','BC','DD','GG'].
 * we will tell you the match compare function. If we give you another compare function.
 * the possible return is the following:
 * nuts = ['ab','bc','dd','gg'], bolts = ['BC','AA','DD','GG'].
 * So you must use the compare function that we give to do the sorting.
 * The order of the nuts or bolts does not matter. You just need to find the matching bolt for each nut.
 *
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

        for (int i = l; i < h; ++i) {
            if (compare.cmp(collection[i], pivot) == -1 || compare.cmp(pivot, collection[i]) == -1) {
                ++smaller;
                swap(collection, i, smaller);
            } else if (compare.cmp(collection[i], pivot) == 0 || compare.cmp(pivot, collection[i]) == 0) {
                swap(collection, i, h);
                --i;
            }
        }

        ++smaller;
        swap(collection, smaller, h);

        return smaller;
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
