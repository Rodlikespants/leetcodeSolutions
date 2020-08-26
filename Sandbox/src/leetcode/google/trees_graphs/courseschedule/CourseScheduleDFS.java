package leetcode.google.trees_graphs.courseschedule;

import java.util.*;

public class CourseScheduleDFS {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Stack<Integer> topSorted = new Stack<>();
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        Map<Integer, Boolean> seen = new HashMap<>();

        for (int i = 0; i < prerequisites.length; ++i) {
            List<Integer> values = adjList.getOrDefault(prerequisites[i][1], new ArrayList<>());
            values.add(prerequisites[i][0]);
            adjList.put(prerequisites[i][1], values);
        }

        for (int i = 0; i < numCourses; ++i) {
            if (seen.get(i) == null) {
                if (!dfs(i, adjList, seen, topSorted)) {
                    return new int[0];
                }
            }
        }

        int[] ret = new int[topSorted.size()];
        for (int i = 0; i < ret.length; ++i) {
            ret[i] = topSorted.pop();
        }
        return ret;
    }

    private boolean dfs(int node, Map<Integer, List<Integer>> adjList, Map<Integer, Boolean> seen, Stack<Integer> topSorted) {
        seen.put(node, false); // equivalent to gray

        for (Integer neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
            if (!seen.containsKey(neighbor)) { // white
                if(!dfs(neighbor, adjList, seen, topSorted)) {
                    return false;
                }
            } else if (seen.get(neighbor) == false) {
                return false;
            }
        }

        seen.put(node, true);
        topSorted.add(node);

        return true;

    }

    public static void main(String[] args) {
        int numCourses0 = 2;
        int[][] prerequs0 = {{1,0}};
        int[] result0 = new CourseScheduleDFS().findOrder(numCourses0, prerequs0); System.out.println(result0.length);

        int numCourses = 3;
        int[][] prereqs = {{0,2},{1,2},{2,0}};
        int[] result = new CourseScheduleDFS().findOrder(numCourses, prereqs); System.out.println(result.length);

        int numCourses2 = 3;
        int[][] prereqs2 = {{1,0},{2,0},{0,1}};
        int[] result2 = new CourseScheduleDFS().findOrder(numCourses2, prereqs2); System.out.println(result2.length);
    }
}
