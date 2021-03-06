package org.morewax.leetcode;

/**
 * Problem
 * Unique Path II (https://leetcode.com/problems/unique-paths-ii/)
 * Follow up for "Unique Paths":
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * [
 *  [0,0,0],
 *  [0,1,0],
 *  [0,0,0]
 * ]
 * The total number of unique paths is 2.

 * Note: m and n will be at most 100.
 * Created by byuan on 12/18/15.
 */
public class UniquePath2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;

        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;

        int[][] dp = new int[n][m];

        boolean hasObstacle = obstacleGrid[0][0] == 1;

        dp[0][0] = (hasObstacle)? 0 : 1;

        for (int i = 1; i < m; ++i) {
            if (obstacleGrid[0][i] == 1 || hasObstacle) {
                hasObstacle = true;
                dp[0][i] = 0;
            } else {
                dp[0][i] = 1;
            }
        }

        hasObstacle = obstacleGrid[0][0] == 1;
        for (int i = 1; i < n; ++i) {
            if (obstacleGrid[i][0] == 1 || hasObstacle) {
                hasObstacle = true;
                dp[i][0] = 0;
            } else {
                dp[i][0] = 1;
            }
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j){
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }

                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[n-1][m-1];
    }
}
