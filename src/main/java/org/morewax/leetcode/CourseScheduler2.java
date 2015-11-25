package org.morewax.leetcode;

import java.util.*;

/**
 * Created by byuan on 11/25/15.
 */
public class CourseScheduler2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[numCourses];

        for (int i = 0; i < numCourses; ++i) {
            graph.put(i, new HashSet<>());
        }

        // build the graph first
        for (int i = 0; i < prerequisites.length; ++i) {
            graph.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        boolean hasCycle = false;
        for (int i = 0; i < numCourses; ++i) {
            if (!dfs(graph, i, visited, queue)) {
                hasCycle = true;
                break;
            }
        }

        if (hasCycle) {
            return new int[0];
        } else {
            int[] result = new int[numCourses];

            for (int i = 0; i < numCourses; ++i) {
                result[i] = queue.poll();
            }

            return result;
        }
    }

    private boolean dfs(Map<Integer, Set<Integer>> graph, int vertex, int[] visited, Queue<Integer> queue) {
        if (visited[vertex] == 2) return true;
        if (visited[vertex] == 1) return false;

        visited[vertex] = 1;

        for (int n : graph.get(vertex)) {
            if (!dfs(graph, n, visited, queue)) {
                return false;
            }
        }

        visited[vertex] = 2;

        queue.offer(vertex);

        return true;
    }

    public static void main(String[] args) {
        CourseScheduler2 cs = new CourseScheduler2();
        int[] result;

        // 4, [[1,0],[2,0],[3,1],[3,2]]
        int[][] edges = new int[4][2];
        edges[0][0] = 1;
        edges[0][1] = 0;
        edges[1][0] = 2;
        edges[1][1] = 0;
        edges[2][0] = 3;
        edges[2][1] = 1;
        edges[3][0] = 3;
        edges[3][1] = 2;

        result = cs.findOrder(4, edges);

        for (int i = 0; i < result.length; ++i) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(result[i]);
        }

        System.out.println("");
        System.out.println("Hello World!");
    }
}
