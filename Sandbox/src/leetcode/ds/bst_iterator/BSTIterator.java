package leetcode.ds.bst_iterator;


import java.util.Stack;

/**
 * you can also flatten it into an array
 */
public class BSTIterator {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    Stack<TreeNode> level;

    public BSTIterator(TreeNode root) {
        level = new Stack<>();
        TreeNode curr = root;

        processLeft(curr);
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode curr = level.pop();
        if (curr == null) {
            return -1;
        }

        if (curr.right != null) {
            processLeft(curr.right);
        }

        System.out.println(curr.val);
        return curr.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !level.empty();
    }

    private void processLeft(TreeNode root) {
        if (root == null) {
            return;
        }
        level.push(root);
        while (root.left != null) {
            root = root.left;
            level.push(root);
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */