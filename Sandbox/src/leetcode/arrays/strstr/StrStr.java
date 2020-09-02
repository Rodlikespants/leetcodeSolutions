package leetcode.arrays.strstr;

public class StrStr {
    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }

        if (needle.length() > haystack.length()) {
            return -1;
        }

        if (needle.length() == haystack.length()) {
            return haystack.equals(needle) ? 0 : -1;
        }

        int result = -1;

        for (int i = 0; i < haystack.length(); ++i) {
            // match start
            if (haystack.charAt(i) == needle.charAt(0)) {
                // match end towards in
                if (matchSubstr(haystack, needle, i)) {
                    return i;
                }
            }
        }
        return result;
    }

    private boolean matchSubstr(String haystack, String needle, int i) {
        if (i+needle.length()-1 > haystack.length()-1) {
            return false;
        }

        for (int j = i+needle.length()-1; j >= i && j < haystack.length(); j--) {
            System.out.println("i="+ i + ", j = " + j);
            if (haystack.charAt(j) != needle.charAt(j-i)) {
                return false;
            }
        }

        return true;
    }
}