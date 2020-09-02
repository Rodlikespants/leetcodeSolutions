package leetcode.arrays.longest_common_prefix;

// V2, closer to vertical scan
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        // binary search approach
        // first find the shortest length string in the bunch
        // use that as the basis for the binary search
        if (strs.length == 0) {
            return "";
        }
        return search(strs);
    }

    // helper method that will match substring across all strings given a start and end (exclusive)
    private String search(String[] strs) {
        for (int col = 0; col < strs[0].length(); col++) {
            char ch = strs[0].charAt(col);
            for (int row = 0; row < strs.length; ++row) {
                if (col == strs[row].length() || strs[row].charAt(col) != ch) {
                    return strs[0].substring(0, col);
                }
            }
        }

        return strs[0];
    }
}