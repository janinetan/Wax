package org.morewax.baozi;

import java.util.*;

/**
 * Question

 Write a deep iterator to iterate through a list of objects or integer which could be another list or integer. For example, this collection contains Integer or another collection. L means it is a collection that contains either integer or another L.

                      5
                      |
           3 -> 4  -> L  -> 6
           |
 1 -> 2 -> L -> 7-> 8

 We would expect an iterator to loop through it will print out 1, 2, 3, 4, 5, 6, 7, 8
 * Created by Bing on 11/29/2015.
 */
public class DeepIterator implements Iterator {
    private Integer current;
    private Stack<Iterator> s;

    public DeepIterator(Iterable collection) {
        s = new Stack<>();
        s.push(collection.iterator());
    }

    @Override
    public boolean hasNext() {
        if (current != null) return true;

        while (!s.isEmpty()) {
            Iterator tempIterator = s.peek();

            if (tempIterator.hasNext()) {
                Object temp = tempIterator.next();

                if (temp instanceof Integer) {
                    this.current = (Integer)temp;
                    return true;
                } else if (temp instanceof Iterable) {
                    s.push(((Iterable)temp).iterator());
                } else {
                    throw new RuntimeException("Unsupported data type");
                }
            } else {
                s.pop();
            }
        }

        return false;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            Integer result = this.current;
            this.current = null;
            return result;
        }

        throw new NoSuchElementException("no more element");
    }

    public static void main(String[] args) {
        List<Integer> l3 = new LinkedList<>();
        l3.add(5);

        List l2 = new LinkedList();
        l2.add(3);
        l2.add(4);
        l2.add(l3);
        l2.add(6);

        List l1 = new LinkedList();
        l1.add(1);
        l1.add(2);
        l1.add(l2);
        l1.add(7);
        l1.add(8);

        DeepIterator di = new DeepIterator(l1);
        int counter = 0;

        while (di.hasNext()) {
            if (counter++ != 0) {
                System.out.print(", ");
            }

            System.out.print(di.next());
        }
        System.out.println("");
    }
}
