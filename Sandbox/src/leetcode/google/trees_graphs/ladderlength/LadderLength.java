package leetcode.google.trees_graphs.ladderlength;

import java.util.*;

public class LadderLength {
    static class Tuple {
        String word;
        int level;
        Tuple(String word, int level) {
            this.word = word;
            this.level = level;
        }
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> preProc = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>();

        // preprocess word search
        for (String word: wordList) {
            char[] wordArr = word.toCharArray();
            for (int i = 0; i < wordArr.length; ++i) {
                char temp = wordArr[i];
                wordArr[i] = '*';
                String newKey = new String(wordArr);
                List<String> value = preProc.getOrDefault(newKey, new ArrayList<>());
                value.add(word);
                preProc.put(newKey, value);
                wordArr[i] = temp;
            }
        }

        Queue<Tuple> tuples = new LinkedList<>();
        tuples.add(new Tuple(beginWord,1));
        visited.put(beginWord, true);
        while (!tuples.isEmpty()) {
            Tuple tuple = tuples.poll();
            if (tuple.word.equals(endWord)) {
                return tuple.level;
            }

            char[] wordArr = tuple.word.toCharArray();
            for (int i = 0; i < wordArr.length; ++i) {
                char temp = wordArr[i];
                wordArr[i] = '*';
                String newKey = new String(wordArr);
                wordArr[i] = temp;

                List<String> neighbors = preProc.getOrDefault(newKey, new ArrayList<>());
                for (String neighbor : neighbors) {
                    if (!visited.containsKey(neighbor)) {
                        tuples.offer(new Tuple(neighbor, tuple.level + 1));
                        visited.put(neighbor, true);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String[] wordArr = {"hot","dot","dog","lot","log","cog"};
        List<String> wordList = Arrays.asList(wordArr);
        int result = new LadderLength().ladderLength("hit", "cog", wordList);
        System.out.println(result);
    }
}
