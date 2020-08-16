package leetcode.regex;

import java.util.*;

class Solution {

    /**
     * not exactly a prefix tree, more like a permutation tree
     */
    static class Trie {
        private Map<String, Trie> children;
        // root has empty string content
        private String content;
        private String wordPath;

        Trie(String content, String wordPath) {
            this.content = content;
            this.children = new HashMap<>();
            this.wordPath = wordPath;
        }

        String getContent() {
            return this.content;
        }

        String getWordPath() {
            return this.wordPath;
        }

        Map<String, Trie> getChildren() {
            return this.children;
        }

        void setChildren(Map<String, Trie> children) {
            this.children = children;
        }

        void addChild(String content, Trie child) {
            this.children.put(content, child);
        }
    }

    private static final Set<String> QUANTIFIERS = new HashSet<>(Arrays.asList("*"));
    public static final String EMPTY_STRING = "";

    /**
     *
     * @param root Trie node
     * @param s the reamining part of the original string
     * @param numPGroups the number of pattern groups, necessary to limit the tree height or it will grow infinitely
     * @return the completed sub-Trie
     */
    private Trie generateTrie(Trie root, String s, int numPGroups) {
        if (numPGroups < 1) {
            return root;
        }

        // generate substrings as children
        for (int i = 0; i < s.length(); ++i) {
            String substr = s.substring(0, i+1);
            String remaining = i+1 < s.length() ? s.substring(i+1) : "";
            root.addChild(substr,
                          generateTrie(new Trie(substr, root.getWordPath() + substr),
                                       remaining,
                                       numPGroups - 1));
        }

        // hacky extra logic for empty * case
        root.addChild(EMPTY_STRING,
                generateTrie(new Trie(EMPTY_STRING, root.getWordPath()),
                        s,
                        numPGroups - 1));

        return root;
    }

    public boolean isMatch(String s, String p) {
        if (s.equals(p)) {
            return true; // hacky, doesn't take into account special chars
        }
        List<String> pGroups = parsePatternGroups(p);
        Trie trie = generateTrie(new Trie("", ""), s, pGroups.size()); // root is empty string content

        return processTrie(trie, pGroups, s);
    }

    private List<String> parsePatternGroups(String p) {
        List<String> pGroups = new ArrayList<>();
        String currGroup = "";
        for (int i = 0; i < p.length(); ++i) {
            String ch = p.charAt(i) + "";
            // TODO generalize logic for multiple quantifiers and groupings
            if (QUANTIFIERS.contains(ch)) {
                if (currGroup.isEmpty()) {
                    throw new RuntimeException("bad regex pattern input");
                }
                currGroup += ch;
                pGroups.add(currGroup);
                currGroup = "";
            } else {
                if (!currGroup.isEmpty()) {
                    pGroups.add(currGroup);
                }
                currGroup = ch;
            }
        }

        // ugly dangling case
        if (!currGroup.isEmpty()) {
            pGroups.add(currGroup);
        }

        return pGroups;
    }

    private boolean processTrie(Trie root, List<String> pGroups, String fullWord) {
        int pGroupIndex = 0;

        // ignore root, process children

        for (String childKey : root.getChildren().keySet()) {
            Trie child = root.getChildren().get(childKey);
            boolean childMatch = processGroups(child, pGroups, pGroupIndex, fullWord);
            if (childMatch) {
                return true;
            }
        }
        return false;
    }

    private boolean processGroups(Trie root, List<String> pGroups, int pGroupIndex, String fullWord) {
        if (pGroupIndex > pGroups.size()) {
            return true;
        }

        String pGroup = pGroups.get(pGroupIndex);
        if (!doesPatternMatch(root.getContent(), pGroup)) {
            return false;
        } else if (root.getWordPath().equals(fullWord) && pGroupIndex == pGroups.size() - 1) {
            return true; // hacky, return true when both the word and the pattern are iterated to the end
        }

        for (String childKey : root.getChildren().keySet()) {
            Trie child = root.getChildren().get(childKey);
            boolean childMatch = processGroups(child, pGroups, pGroupIndex+1, fullWord);
            if (childMatch) {
                return true;
            }
        }
        return false;
    }

    private boolean doesPatternMatch(String content, String pGroup) {
        // TODO generalize logic for more quantifiers and groups
        if (pGroup.length() == 2) {
            if (pGroup.charAt(1) == '*') {
                if (content.isEmpty()) { // 0 times matching '*' quantifier is a match
                    return true;
                }
                char firstPatternChar = pGroup.charAt(0);
                boolean allMatch = true;
                for (int i = 0; i < content.length(); ++i) {
                    char contentChar = content.charAt(i);
                    allMatch &= doesSingleCharMatch(contentChar, firstPatternChar);
                }
                return allMatch;
            } else {
                return false;
            }
        }
        else if (pGroup.length() == 1) {
            if (content.length() == 1) {
                return doesSingleCharMatch(content.charAt(0), pGroup.charAt(0));
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean doesSingleCharMatch(char ch, char pattern) {
        if (pattern == '.') {
            return true;
        } else {
            return (ch == pattern);
        }
    }

    public static void main(String[] args) {
        String s = "aab";
        String p = "a*b*ab";
        Solution solution = new Solution();
        boolean result = false;
        result = solution.isMatch(s, p);
        System.out.println(result);

        s = "aa";
        p = "a";
        result = solution.isMatch(s, p);
        System.out.println(result);

        s = "ab";
        p = ".*c";
        result = solution.isMatch(s, p);
        System.out.println(result);

        s = "aasdfasdfasdfasdfas";
        p = "aasdf.*asdf.*asdf.*asdf.*s";
        result = solution.isMatch(s, p);
        System.out.println(result);

    }
}