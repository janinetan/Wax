package org.morewax.lintcode;

/**
 * Problem
 * Gas Station (http://www.lintcode.com/en/problem/gas-station/)
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its
 * next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * Example
 * Given 4 gas stations with gas[i]=[1,1,3,1], and the cost[i]=[2,2,1,1]. The starting gas station's index is 2.
 *
 * Analysis:
 * http://blog.csdn.net/kenden23/article/details/14106137
 * Two key observations:
 *  1 如果总的gas - cost小于零的话，那么没有解返回-1
 *  2 如果前面所有的gas - cost加起来小于零，那么前面所有的点都不能作为出发点。
 * Created by Bing on 12/28/2015.
 */
public class GasStation {
    /**
     * @param gas: an array of integers
     * @param cost: an array of integers
     * @return: an integer
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // write your code here
        int gasLeft = 0;
        int totalGasLeft = 0;

        int startStation = -1;
        for (int i = 0; i < gas.length; ++i) {
            gasLeft += gas[i]-cost[i];
            totalGasLeft += gas[i]-cost[i];

            if (gasLeft < 0) {
                gasLeft = 0;
                startStation = i;
            }
        }

        return (totalGasLeft >= 0)? startStation+1 : -1;
    }
}
