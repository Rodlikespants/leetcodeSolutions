package leetcode.google.recursion.wordsearch;

import java.util.*;

public class WordSearch {

    static class Pair {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this. y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    public List<String> findWords(char[][] board, String[] words) {
        // initialize prefix map
        Map<String, List<Integer>> prefixes = buildPrefixes(words);
        Set<String> results = new HashSet<>();

        // go through each cell
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[0].length; ++col) {
                Map<Pair, Boolean> visited = new HashMap<>(); // for each word traversal attempt
                Set<String> result = processCell(0, new Pair(col, row), "", board, words, prefixes, visited);
                if (result != null) {
                    results.addAll(result);
                }
            }
        }
        List<String> resultList = new ArrayList<>();
        resultList.addAll(results);
        return resultList;
    }

    private Set<String> processCell(int step, Pair coord, String prefix, char[][] board, String[] words, Map<String, List<Integer>> prefixes, Map<Pair, Boolean> visited) {
        int row = coord.y;
        int col = coord.x;

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited.getOrDefault(coord, false)) {
            return Collections.emptySet();
        }


        String startPrefix = prefix + board[row][col];
        List<Integer> matches = prefixes.getOrDefault(startPrefix, new ArrayList<>());

        Set<String> finalResults = new HashSet<>();
        if (matches.isEmpty()) {
            return Collections.emptySet();
        }

        for (int matchIndex : matches) {
            String match = words[matchIndex];
            if (startPrefix.equals(match)) {
                finalResults.add(startPrefix);
            }
        }

        // we have matches
        visited.put(coord, true);
        Map<Pair, Boolean> visited1 = new HashMap<>();
        visited1.putAll(visited);
        Map<Pair, Boolean> visited2 = new HashMap<>();
        visited2.putAll(visited);
        Map<Pair, Boolean> visited3 = new HashMap<>();
        visited3.putAll(visited);
        Map<Pair, Boolean> visited4 = new HashMap<>();
        visited4.putAll(visited);

        // search all four directions
        Set<String> results1 = processCell(step+1, new Pair(col+1, row), startPrefix, board, words, prefixes, visited1);
        Set<String> results2 = processCell(step+1, new Pair(col-1, row), startPrefix, board, words, prefixes, visited2);
        Set<String> results3 = processCell(step+1, new Pair(col, row+1), startPrefix, board, words, prefixes, visited3);
        Set<String> results4 = processCell(step+1, new Pair(col, row-1), startPrefix, board, words, prefixes, visited4);
        finalResults.addAll(results1);
        finalResults.addAll(results2);
        finalResults.addAll(results3);
        finalResults.addAll(results4);

        return finalResults;
    }

    private Map<String, List<Integer>> buildPrefixes(String[] words) {
        Map<String, List<Integer>> prefixes = new HashMap<>(); // map to indexes
        for (int i = 0; i < words.length; ++i) {
            for (int j = 0; j < words[i].length(); ++j) {
                String prefix = words[i].substring(0, j+1);
                List<Integer> prefixMatches = prefixes.getOrDefault(prefix, new ArrayList<>());
                prefixMatches.add(i);
                prefixes.put(prefix, prefixMatches);
            }
        }
        return prefixes;
    }

    public static void main(String[] args) {
/*
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};
        List<String> results = new WordSearch().findWords(board, words); for (String word : results) System.out.println(word);

        char[][] board2 = {{'a','a'}};
        String[] words2 = {"aaa"};
        List<String> results2 = new WordSearch().findWords(board2, words2); for (String word : results2) System.out.println(word);


        char[][] board3 = {{'a','b'},{'c','d'}};
        String[] words3 = {"acdb"};
        List<String> results3 = new WordSearch().findWords(board3, words3); for (String word : results3) System.out.println(word);
  */
        char[][] board4 = {{'a','b'},{'c','d'}};
        String[] words4 = {"ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"};
        List<String> results4 = new WordSearch().findWords(board4, words4); for (String word : results4) System.out.println(word);
    }
}
