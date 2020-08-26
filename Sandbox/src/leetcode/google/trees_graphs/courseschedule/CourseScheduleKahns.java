package leetcode.google.trees_graphs.courseschedule;

import java.util.*;

public class CourseScheduleKahns {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> topSorted = new ArrayList<>();
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        Queue<Integer> topQueue = new LinkedList<>();
        Map<Integer, Integer> degrees = new HashMap<>();

        for (int i = 0; i < prerequisites.length; ++i) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            // degrees init
            Integer degree = degrees.getOrDefault(dest, 0);
            degrees.put(dest, degree + 1);
            degrees.put(src, degrees.getOrDefault(src, 0));

            // adjacency list init
            List<Integer> values = adjList.getOrDefault(prerequisites[i][1], new ArrayList<>());
            values.add(prerequisites[i][0]);
            adjList.put(prerequisites[i][1], values);
        }

        //List<Integer> initial = degrees.entrySet().stream().filter(e -> e.getValue() == 0).map(x -> x.getKey()).collect(Collectors.toList());
        //topQueue.addAll(initial);
        for (int i = 0; i < numCourses; ++i) {
            if (degrees.getOrDefault(i, 0) == 0) {
                topQueue.offer(i);
            }
        }
        while (!topQueue.isEmpty()) {
            Integer curr = topQueue.poll();
            for (Integer neighbor : adjList.getOrDefault(curr, new ArrayList<>())) {
                Integer value = degrees.getOrDefault(neighbor, 1);
                value--;
                degrees.put(neighbor, value);
                if (value == 0) {
                    topQueue.offer(neighbor);
                }
            }
            adjList.remove(curr);
            topSorted.add(curr);
        }

        if (topSorted.size() != numCourses) {
            return new int[0];
        }

        int[] ret = new int[topSorted.size()];
        for (int i = 0; i < ret.length; ++i) {
            ret[i] = topSorted.get(i);
        }
        return ret;
    }

    /**
     * official solution
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrderSolution(int numCourses, int[][] prerequisites) {

        boolean isPossible = true;
        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        int[] indegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];

        // Create the adjacency list representation of the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
            lst.add(dest);
            adjList.put(src, lst);

            // Record in-degree of each vertex
            indegree[dest] += 1;
        }

        // Add all vertices with 0 in-degree to the queue
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int i = 0;
        // Process until the Q becomes empty
        while (!q.isEmpty()) {
            int node = q.remove();
            topologicalOrder[i++] = node;

            // Reduce the in-degree of each neighbor by 1
            if (adjList.containsKey(node)) {
                for (Integer neighbor : adjList.get(node)) {
                    indegree[neighbor]--;

                    // If in-degree of a neighbor becomes 0, add it to the Q
                    if (indegree[neighbor] == 0) {
                        q.add(neighbor);
                    }
                }
            }
        }

        // Check to see if topological sort is possible or not.
        if (i == numCourses) {
            return topologicalOrder;
        }

        return new int[0];
    }

    public static void main(String[] args) {
        int numCourses0 = 2;
        int[][] prerequs0 = {{1,0}};
        int[] result0 = new CourseScheduleKahns().findOrder(numCourses0, prerequs0); System.out.println(result0.length);

        int numCourses = 3;
        int[][] prereqs = {{0,2},{1,2},{2,0}};
        int[] result = new CourseScheduleKahns().findOrder(numCourses, prereqs); System.out.println(result.length);

        int numCourses2 = 3;
        int[][] prereqs2 = {{1,0},{2,0},{0,1}};
        int[] result2 = new CourseScheduleKahns().findOrder(numCourses2, prereqs2); System.out.println(result2.length);

        int numCourses3 = 1;
        int[][] prereqs3 = {};
        int[] result3 = new CourseScheduleKahns().findOrder(numCourses3, prereqs3); System.out.println(result3.length);
    }
}
