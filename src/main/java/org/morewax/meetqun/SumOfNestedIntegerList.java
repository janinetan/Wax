package org.morewax.meetqun;

import java.util.*;

/**
 * Problem
 * Sum of nested integer list.
 *
 * Allegedly this is phone screening from LN, here is the original description of the problem:
 *
 *
 * Given a nested list of integers, returns the sum of all integers in the list weighted by their depth
 * For example, given the list {{1,1},2,{1,1}} the function should return 10 (four 1's at depth 2, one 2 at depth 1)
 * Given the list {1,{4,{6}}} the function should return 27 (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3)

 * Created by byuan on 12/4/15.
 */
public class SumOfNestedIntegerList {
    public int getSum(List list) {
        int result = 0;

        if (list != null) {
            Stack<Iterator> s = new Stack<>();
            s.push(list.iterator());
            int depth = 1;

            while (!s.isEmpty()) {
                Iterator it = s.peek();
                if (it.hasNext()) {
                    Object obj = it.next();

                    if (obj instanceof Integer) {
                        result += ((Integer)obj).intValue()*depth;
                    } else {
                        s.push(((List)obj).iterator());
                        ++depth;
                    }
                } else {
                    --depth;
                    s.pop();
                }
            }
        }

        return result;
    }

    // This is a variation of previous question (getSum).
    // This sum is REVERSED weighted by its depth
    public int getReversedSum(List list) {
        int result = 0;

        if (list != null) {
            List next = new ArrayList();
            List curr = list;

            int prev = 0;

            while(!curr.isEmpty()) {
                for (Object obj : curr) {
                    if (obj instanceof Integer) {
                        // this is very smart!!!
                        // The total times of execution of following line is the same as the depth of the nested list.
                        // A key to understand this:
                        // prev is the sum of integers of current level. Then this sum value is being added to the overall sum
                        // that is tracked by "result" variable
                        // then this sum value will be calculated again on the next level.
                        prev += ((Integer)obj).intValue();
                    } else {
                        next.addAll((List)obj);
                    }
                }

                curr = next;

                next = new ArrayList();

                result += prev;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SumOfNestedIntegerList sumOfNestedIntegerList = new SumOfNestedIntegerList();
        int result;

        List l1 = new ArrayList();
        l1.add(Arrays.asList(1, 1));
        l1.add(2);
        l1.add(Arrays.asList(1, 1));
        result = sumOfNestedIntegerList.getSum(l1);
        System.out.println(result);
        System.out.println("Reversed: " + sumOfNestedIntegerList.getReversedSum(l1));

        List l2 = new ArrayList();
        l2.add(1);
        List l4 = Arrays.asList(6);
        List l3 = new ArrayList();
        l3.add(4);
        l3.add(l4);
        l2.add(l3);
        result = sumOfNestedIntegerList.getSum(l2);
        System.out.println(result);
        System.out.println("Reversed: " + sumOfNestedIntegerList.getReversedSum(l2));
    }
}
