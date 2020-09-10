package interviews;

import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class BalancedBST {


    /*
     * To execute Java, please define "static void main" on a class
     * named Solution.
     *
     * If you need more classes, simply define them inline.
     */




    static class Node {
        int val;
        Node left;
        Node right;

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // case [1, 2, 3]
    // generateBalancedBST(arr, 0, arr.length-1);
    /**
     assuming sorted array input
     */
    public Node generateBalancedBST(int[] arr, int start, int end) {
        // base case
        if (start == end) {
            return new Node(arr[start], null, null);
        }

        // get index of the halfway point
        int rootIndex = (start + end)/2;
        // create a node with the halfway index value
        Node root = new Node(arr[rootIndex], null, null);

        // recursively return the balanced subtree of the left (pass list of index start index to half)
        root.left = generateBalancedBST(arr, start, rootIndex - 1);
        // recursively return the balanced subtree of the right (pass sublist of half index + 1 to end)
        root.right = generateBalancedBST(arr, rootIndex + 1, end);

        return root;
    }


    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }
    }
}
