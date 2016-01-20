package org.morewax.lintcode;

import org.morewax.support.Point;

/**
 * Problem
 * Max Points On a Line (http://www.lintcode.com/en/problem/max-points-on-a-line/)
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * Example
 * Given 4 points: (1,2), (3,6), (0,0), (1,3).
 * The maximum number is 3.
 * Created by byuan on 1/19/16.
 */
public class MaxPointsOnALine {
    /**
     * @param points an array of point
     * @return an integer
     */
    public int maxPoints(Point[] points) {
        // Write your code here
        if(points.length <= 1 || allSamePoints(points))
            return points.length;

        int maxPoints = 2;

        for(int i = 0; i < points.length; ++i) {
            for(int j = i+1; j < points.length; ++j) {
                if(points[i].x == points[j].x && points[i].y == points[j].y)
                    continue;

                int count = 2;
                for(int k = 0; k < points.length; ++k) {
                    if(k!=i && k!=j && sameLine(points[i], points[j], points[k]))
                        count++;
                }
                maxPoints = Math.max(maxPoints, count);
            }
        }

        return maxPoints;
    }

    private boolean allSamePoints(Point[] points) {
        int i = 0;

        while(i < points.length) {
            if(points[i].x != points[0].x || points[i].y != points[0].y)
                break;
            ++i;
        }

        return i==points.length;
    }

    private boolean sameLine(Point i, Point j, Point k) {
        // (20-10)*(-5-1) - (20-20)*(1-1)
        return (j.y-i.y)*(k.x-j.x)-(k.y-j.y)*(j.x-i.x) == 0;
        //  a(n−y)+m(y−b)+x(b−n)=0
        // i: (a, b); j: (m, n); k: (x, y)
        // i: (1, 10); j: (1, 20); k: (-5, 20)
        // 1*(20-20) + 1*(20-10) + -5*(10-20)
        // 0 + 10 + -50
        //return i.x*(j.y-k.y) + j.x*(k.y-i.y) + k.x*(i.y-j.y) == 0;
    }

    public static void main(String[] args) {
        final MaxPointsOnALine s = new MaxPointsOnALine();

        Point[] points = new Point[3];
        points[0] = new Point(1, 20);
        points[1] = new Point(1, 20);
        points[2] = new Point(-5, 20);

        System.out.println(s.maxPoints(points));
    }
}
