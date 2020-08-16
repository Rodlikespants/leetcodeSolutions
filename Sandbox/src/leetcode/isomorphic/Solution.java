package leetcode.isomorphic;

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, List<Integer>> counts1 = new HashMap<>();
        Map<Character, List<Integer>> counts2 = new HashMap<>();

        for (int i = 0; i < s.length(); ++i) {
            Character ch = s.charAt(i);
            List<Integer> indices = counts1.getOrDefault(ch, new ArrayList<>());
            indices.add(i);
            counts1.put(ch, indices);
        }

        for (int i = 0; i < t.length(); ++i) {
            Character ch = t.charAt(i);
            List<Integer> indices = counts2.getOrDefault(ch, new ArrayList<>());
            indices.add(i);
            counts2.put(ch, indices);
        }

        Set<List<Integer>> vals1 = counts1.values().stream().collect(Collectors.toSet());
        Set<List<Integer>> vals2 = counts2.values().stream().collect(Collectors.toSet());

        if (vals1.size() != vals2.size()) {
            return false;
        }

        for(List<Integer> list1 : vals1) {
            //int size1 = list1.size();
            boolean match = false;
            for (List<Integer> list2 : vals2) {
                if (list1.equals(list2)) {
                    match = true;
                    break;
                }
            }
            if (!match) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "ab";
        String t1 = "aa";
        //System.out.println(solution.isIsomorphic(s1, t1));

        String s2 = "egg";
        String t2 = "add";
        System.out.println(solution.isIsomorphic(s2, t2));
    }
}