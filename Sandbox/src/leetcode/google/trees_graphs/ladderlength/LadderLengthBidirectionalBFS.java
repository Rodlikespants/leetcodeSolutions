package leetcode.google.trees_graphs.ladderlength;

import java.util.*;

/**
 * doesn't work for "ymain"
 * "oecij"
 * ["ymann","yycrj","oecij","ymcnj","yzcrj","yycij","xecij","yecij","ymanj","yzcnj","ymain"]
 */
public class LadderLengthBidirectionalBFS {
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
        Map<String, Boolean> beginVisited = new HashMap<>();
        Map<String, Boolean> endVisited = new HashMap<>();

        if (!wordList.contains(endWord)) {
            return 0;
        }

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

        Queue<Tuple> tuplesStart = new LinkedList<>();
        Queue<Tuple> tuplesEnd = new LinkedList<>();

        tuplesStart.add(new Tuple(beginWord,1));
        tuplesEnd.add(new Tuple(endWord, 1));

        beginVisited.put(beginWord, true);
        endVisited.put(endWord, true);
        while (!tuplesStart.isEmpty() && !tuplesEnd.isEmpty()) {
            Set<String> middleCheck = new HashSet<>();
            Tuple startTuple = tuplesStart.poll();
            if (startTuple.word.equals(endWord)) {
                return startTuple.level;
            }

            Tuple endTuple = tuplesEnd.poll();
            if (endTuple.word.equals(beginWord)) {
                return endTuple.level;
            }

            char[] beginWordArr = startTuple.word.toCharArray();
            Set<String> startNeighbors = new HashSet<>();
            Set<String> endNeighbors = new HashSet<>();
            for (int i = 0; i < beginWordArr.length; ++i) {
                char temp = beginWordArr[i];
                beginWordArr[i] = '*';
                String newBeginKey = new String(beginWordArr);
                beginWordArr[i] = temp;

                startNeighbors.add(newBeginKey);

                String newEndKey = endTuple.word.substring(0, i) + "*" + endTuple.word.substring(i+1, endTuple.word.length());
                endNeighbors.add(newEndKey);

                List<String> neighbors = preProc.getOrDefault(newBeginKey, new ArrayList<>());
                for (String neighbor : neighbors) {
                    if (!beginVisited.containsKey(neighbor)) {
                        tuplesStart.offer(new Tuple(neighbor, startTuple.level + 1));
                        beginVisited.put(neighbor, true);
                        middleCheck.add(neighbor);
                    }
                }

                if (middleCheck.contains(endTuple.word)) { // edge case if for short paths
                    return startTuple.level + endTuple.level;
                }

                for (String neighbor : preProc.getOrDefault(newEndKey, new ArrayList<>())) {
                    if (middleCheck.contains(neighbor)) {
                        return startTuple.level + endTuple.level + 1;
                    }
                    if (!endVisited.containsKey(neighbor)) {
                        tuplesEnd.offer(new Tuple(neighbor, endTuple.level + 1));
                        endVisited.put(neighbor, true);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String[] wordArr = {"hot","dot","dog","lot","log","cog"};
        List<String> wordList = Arrays.asList(wordArr);
        int result = new LadderLengthBidirectionalBFS().ladderLength("hit", "cog", wordList); System.out.println(result);

        String[] wordArr2 = {"hot","dot","dog","lot","log"};
        List<String> wordList2 = Arrays.asList(wordArr2);
        String startWord2 = "hit";
        String endWord2 = "cog";
        int result2 = new LadderLengthBidirectionalBFS().ladderLength(startWord2, endWord2, wordList2); System.out.println(result2);

        String[] wordArr3 = {"a", "b", "c"};
        List<String> wordList3 = Arrays.asList(wordArr3);
        String startWord3 = "a";
        String endWord3 = "b";
        int result3 = new LadderLengthBidirectionalBFS().ladderLength(startWord3, endWord3, wordList3); System.out.println(result3);
    }
}
