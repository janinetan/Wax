package org.morewax.study;

/**
 * Created by byuan on 1/12/16.
 */
public class SegmentTree {
    private int n;
    private int[] st;

    public SegmentTree(int[] nums) {
        n = nums.length;
        int height = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        int treeSize = 2*(int)Math.pow(2, height)-1;
        st = new int[treeSize];

        buildSegmentTree(nums, 0, n-1, 0);
    }

    public int rangeQuery(int qs, int qe) {
        return queryHelper(0, n-1, qs, qe, 0);
    }

    public void updateValue(int[] nums, int i, int newValue) {
        int diff = newValue - nums[i];

        updateValueHelper(0, n-1, i, 0, diff);
    }

    private int buildSegmentTree(int[] nums, int ss, int se, int index) {
        if (ss == se) {
            st[index] = nums[ss];
            return st[index];
        }

        int mid = getMid(ss, se);
        st[index] = buildSegmentTree(nums, ss, mid, 2*index+1) +
                buildSegmentTree(nums, mid+1, se, 2*index+2);

        return st[index];
    }

    private int queryHelper(int ss, int se, int qs, int qe, int index) {
        if (qs <= ss && se <= qe) {
            return st[index];
        }

        if (se < qs || qe < ss) return 0;

        int mid = getMid(ss, se);

        return queryHelper(ss, mid, qs, qe, 2*index+1) +
                queryHelper(mid+1, se, qs, qe, 2*index+2);
    }

    private void updateValueHelper(int ss, int se, int index, int si, int diff) {
        if (index < ss || se < index) return;

        st[si] += diff;
        if (se > ss) {
            int mid = getMid(ss, se);
            updateValueHelper(ss, mid, index, 2*si+1, diff);
            updateValueHelper(mid+1, se, index, 2*si+2, diff);
        }
    }

    private int getMid(int ss, int se) {
        return ss + (se-ss)/2;
    }

    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 7, 9, 11};
        SegmentTree  tree = new SegmentTree(arr);

        // Build segment tree from given array

        // Print sum of values in array from index 1 to 3
        System.out.println("Sum of values in given range = " +
                tree.rangeQuery(1, 3));

        // Update: set arr[1] = 10 and update corresponding segment
        // tree nodes
        tree.updateValue(arr, 1, 10);

        // Find sum after the value is updated
        System.out.println("Updated sum of values in given range = " +
                tree.rangeQuery(1, 3));
    }
}
