package org.morewax.study;

/**
 * This is the study note for binary indexed tree
 * Created by byuan on 1/15/16.
 */
public class BIT {
    private int[] bit;

    public BIT (int n) {
        bit = new int[n+1];
    }

    public BIT(int[] nums) {
        buildBIT(nums);
    }

    public int getSum(int index) {
        int result = 0;

        int k = index+1;
        while (k > 0) {
            result += bit[k];
            k -= (k&-k);
        }

        return result;
    }

    public int rangeSum(int l, int r) {
        int left = getSum(l-1);
        int right = getSum(r);

        return right-left;
    }

    public void update(int[] nums, int index, int newValue) {
        updateValue(index+1, newValue-nums[index]);
    }

    private void buildBIT(int[] nums) {
        int n = nums.length;
        bit = new int[n+1];

        for (int i = 1; i <= n; ++i) {
            updateValue(i, nums[i-1]);
        }
    }

    public void updateValue(int index, int diff) {
        int k = index;

        while (k < bit.length) {
            bit[k] += diff;
            k += (k&-k);
        }
    }

    public static void main(String[] args) {
        int[] a1 = new int[]{2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
        final BIT s1 = new BIT(a1);
        System.out.println("Sum of elements in a1[0..5] is: "+ s1.getSum(5));
        System.out.println("Sum of elements in a1[3, 7] is: " + s1.rangeSum(3, 7));

        s1.update(a1, 3, 9);
        System.out.println("Sum of elements in a1[0..5] after update is: " + s1.getSum(5));
        System.out.println("Sum of elements in a1[3, 7] after update is: " + s1.rangeSum(3, 7));
    }
}
