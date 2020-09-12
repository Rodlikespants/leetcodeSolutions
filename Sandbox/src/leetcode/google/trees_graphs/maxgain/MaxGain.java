package leetcode.google.trees_graphs.maxgain;

public class MaxGain {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    class Result {
        int maxSum;
        int subGain;
        Result(int maxSum, int subGain) {
            this.maxSum = maxSum;
            this.subGain = subGain;
        }
    }
    public Result maxGainRecur(TreeNode root) {
        if (root == null) {
            return new Result(Integer.MIN_VALUE, 0);
        }

        Result leftResult = maxGainRecur(root.left);
        Result rightResult = maxGainRecur(root.right);
        int leftGain  = Math.max(leftResult.subGain, 0);
        int rightGain = Math.max(rightResult.subGain, 0);
        int maxSum = Math.max(leftResult.maxSum, rightResult.maxSum);

        int subtreeSum = root.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, subtreeSum);

        return new Result(maxSum, root.val + Math.max(leftGain, rightGain));
    }
    public int maxPathSum(TreeNode root) {
        return maxGainRecur(root).maxSum;
    }
}
