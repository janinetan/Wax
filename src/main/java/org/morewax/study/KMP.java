package org.morewax.study;

/**
 * This is the code for studying KMP algorithm.
 * This article (http://blog.csdn.net/v_july_v/article/details/7041827) is one of the best out there
 * that describes this algorithm in the most easily understandable manner
 *
 * Created by byuan on 12/1/15.
 */
public class KMP {
    /**
     * It searches p in s.
     *
     * @param s the target string
     * @param p the pattern to be searched
     * @return -1 if p doesn't exist in s; otherwise the index of first occurrence of p in s.
     */
    public int search(String s, String p) {
        if (!s.isEmpty() && !p.isEmpty()) {
            int[] next = getNext(p);
            int i = 0;
            int n = s.length();

            int j = 0;
            int m = p.length();

            while (i < n && j < m) {
                if (j == -1 || s.charAt(i) == p.charAt(j)) {
                    ++i;
                    ++j;
                } else {
                    j = next[j];
                }
            }

            if (j == m) {
                return i-j;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    private int[] getNext(String p) {
        int n = p.length();
        int[] next = new int[p.length()];

        next[0] = -1;
        int k = -1;
        int j = 0;

        while (j < n-1) {
            //p[k]表示前缀，p[j]表示后缀
            if (k == -1 || p.charAt(j) == p.charAt(k)) {
                ++j;
                ++k;
                //较之前next数组求法，改动在下面4行
                if (p.charAt(j) != p.charAt(k))
                    next[j] = k;   //之前只有这一行
                else
                    //因为不能出现p[j] = p[ next[j ]]，所以当出现时需要继续递归，k = next[k] = next[next[k]]
                    next[j] = next[k];
            }
            else {
                k = next[k];
            }
            /*
            if (k == -1 || p.charAt(k) == p.charAt(j)) {
                ++k;
                ++j;
                next[j] = k;
            } else {
                k = next[k];
            }*/
        }

        return next;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP();
        int index = -1;

        String s;
        String p;

        s = "abacababc";
        p = "abab";

        index = kmp.search(s, p);
        if (index == -1) {
            System.out.println(p + " is not found in " + s);
        } else {
            System.out.println(p + " is found at index " + index);
        }

        p = "abad";
        index = kmp.search(s, p);
        if (index == -1) {
            System.out.println(p + " is not found in " + s);
        } else {
            System.out.println(p + " is found at index " + index);
        }
    }
}
