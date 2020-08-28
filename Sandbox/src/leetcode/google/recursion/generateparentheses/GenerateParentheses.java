package leetcode.google.recursion.generateparentheses;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    /*
    ()()
    */
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        genParen(results, "", n*2, 0, n);
        return results;
    }
    /* genParen(int nLeft, int numOpen)
    if (nLeft - numOpen == 0)
      then close
    if (numOpen > 0)
      then select an open and a closed option on genParen(nLeft-1, numOpen+x)
    */

    public void genParen(List<String> results, String curr, int nLeft, int numOpen, int total) {
        if (curr.length() == total * 2) {
            results.add(curr);
            return;
        }

        if (nLeft == numOpen) {
            genParen(results, curr+")", nLeft-1, numOpen-1, total);
        } else {
            genParen(results, curr+"(", nLeft-1, numOpen+1, total);
            if (numOpen > 0) {
                genParen(results, curr+")", nLeft-1, numOpen-1, total);
            }
        }
    }
}
