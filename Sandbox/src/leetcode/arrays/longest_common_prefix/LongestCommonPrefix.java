package leetcode.arrays.longest_common_prefix;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        // binary search approach
        // first find the shortest length string in the bunch
        int maxLen = Integer.MAX_VALUE;
        String shortStr = "";
        for (int i = 0; i < strs.length; ++i) {
            if (strs[i].length() < maxLen) {
                maxLen = strs[i].length();
                shortStr = strs[i];
            }
        }

        // use that as the basis for the binary search
        return search(strs, 0, maxLen, shortStr);
    }

    // helper method that will match substring across all strings given a start and end (exclusive)
    private String search(String[] strs, int start, int end, String shortStr) {
        if (end == 0) {
            return "";
        }
        if (shortStr.isEmpty()) {
            return "";
        }

        String result = "";
        for (int col = start; col < end; col++) {
            for (int row = 0; row < strs.length; ++row) {
                if (strs[row].charAt(col) != shortStr.charAt(col)) {
                    return result;
                }
            }
            result += shortStr.charAt(col) + "";
        }

        return result;
    }
}