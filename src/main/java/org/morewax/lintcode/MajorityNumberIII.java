package org.morewax.lintcode;

import java.util.*;

/**
 * Problem
 * Majority Number III ()
 * Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the
 * size of the array.
 * Find it.
 * Example
 * Given [3,1,2,3,2,3,3,4,4,4] and k=3, return 3.
 *
 * Created by byuan on 12/29/15.
 */
public class MajorityNumberIII {
    /**
     * @param nums: A list of integers
     * @param k: As described
     * @return: The majority number
     */
    public int majorityNumber(ArrayList<Integer> nums, int k) {
        // write your code
        if (nums == null || nums.isEmpty() || k == 0) return 0;

        Map<Integer, Integer> m = new HashMap<>();

        for (int i = 0; i < nums.size(); ++i) {
            if (m.containsKey(nums.get(i))) {
                int count = m.get(nums.get(i))+1;
                m.put(nums.get(i), count);
            } else {
                if (m.size() < k) {
                    m.put(nums.get(i), 1);
                } else {
                    Collection<Integer> toBeDeleted = new ArrayList<>();
                    for(Map.Entry<Integer, Integer> p : m.entrySet()) {
                        int count = p.getValue()-1;
                        if (count == 0) {
                            toBeDeleted.add(p.getKey());
                        } else {
                            m.put(p.getKey(), count);
                        }
                    }

                    for (Integer key : toBeDeleted) {
                        m.remove(key);
                    }
                }
            }
        }

        int ret = 0;
        int maxCount = 0;

        for (Map.Entry<Integer, Integer> p : m.entrySet()) {
            m.put(p.getKey(), 0);
        }

        for (Integer num : nums) {
            if (m.containsKey(num)) {
                int count = m.get(num)+1;
                if (count > maxCount) {
                    ret = num;
                    maxCount = count;
                }
                m.put(num, count);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        final MajorityNumberIII s = new MajorityNumberIII();

        Integer[] a1 = new Integer[]{3,1,2,3,2,3,3,4,4,4};
        System.out.println(s.majorityNumber(new ArrayList<>(Arrays.asList(a1)), 3));
    }
}
