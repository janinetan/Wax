package org.morewax.lintcode;

/**
 * Problem
 * Number of Islands (http://www.lintcode.com/en/problem/number-of-islands/)
 * Given a boolean 2D matrix, find the number of islands.
 * Example
 * Given graph:

 [
 [1, 1, 0, 0, 0],
 [0, 1, 0, 0, 1],
 [0, 0, 0, 1, 1],
 [0, 0, 0, 0, 0],
 [0, 0, 0, 0, 1]
 ]
 return 3.

 Note
 0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island.
 We only consider up/down/left/right adjacent.

 * Created by byuan on 1/20/16.
 */
public class NumberOfIslands {
    /**
     * @param grid a boolean 2D matrix
     * @return an integer
     */
    public int numIslands(boolean[][] grid) {
        // Write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int count = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (safeToVisit(grid, i, j, visited)) {
                    ++count;
                    dfs(grid, i, j, visited);
                }
            }
        }

        return count;
    }

    private boolean safeToVisit(boolean[][] grid, int x, int y, boolean[][] visited) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == false || visited[x][y] == true) {
            return false;
        } else {
            return true;
        }
    }

    private void dfs(boolean[][] grid, int x, int y, boolean[][] visited) {
        if (safeToVisit(grid, x, y, visited)) {
            visited[x][y] = true;

            dfs(grid, x-1, y, visited);
            dfs(grid, x+1, y, visited);
            dfs(grid, x, y-1, visited);
            dfs(grid, x, y+1, visited);
        }
    }
}
