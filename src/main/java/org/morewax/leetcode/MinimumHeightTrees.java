package org.morewax.leetcode;

import java.util.*;

/**
 * Problem
 * Minimum Height Trees
 *
 * For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

 Format
 The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

 You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

 Example 1:

 Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

 0
 |
 1
 / \
 2   3
 return [1]

 Example 2:

 Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

 0  1  2
 \ | /
 3
 |
 4
 |
 5
 return [3, 4]

 Hint:

 How many MHTs can a graph have at most?
 Note:

 (1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”

 (2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 * Analysis:
 *
 * There are at most 1 or 2 MHTs in a undirected graph. If there are more than 2, for example 3, you can easily argue that
 * two of them are invalid by reasoning of contradiction.
 *
 * By this observation, you can get to the final answer by peeling off the leaf nodes layer by layer. The leaf node in a
 * undirected graph is a node with 1 in-degree.
 *
 * The peeling off stops when the number of nodes left is equals or less than 2.
 * Created by Bing on 11/27/2015.
 */
public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Arrays.asList(0);
        }

        List<List<Integer>> graph = new ArrayList<>();
        int[] degrees = new int[n];

        // init the graph
        for (int i = 0; i < n; ++i) {
            graph.add(i, new ArrayList<>());
        }

        // build the graph
        for (int i = 0; i < edges.length; ++i) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
            ++degrees[edges[i][0]];
            ++degrees[edges[i][1]];
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            if (degrees[i] == 1) {
                queue.offer(i);
            }
        }

        while (n > 2) {
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; ++i) {
                int node = queue.poll();
                for (int j : graph.get(node)) {
                    --degrees[j];
                    if (degrees[j] == 1) {
                        queue.offer(j);
                    }
                }
            }

            n -= queueSize;
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }

        return result;
    }
}
