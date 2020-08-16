package leetcode.ds;

/*
// Definition for a Node.
*/

import java.util.*;

class CloneGraph {

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Node copy = new Node(node.val);
        Stack<Node> stack = new Stack<>();
        stack.push(node);

        // Hash map to save the visited node and its respective clone
        // as key and value respectively. This helps to avoid cycles.
        Map<Node, Node> visited = new HashMap<>();
        visited.put(node, copy);

        while (!stack.isEmpty()) {
            List<Node> newNb = new ArrayList<>();
            Node currNode = stack.pop();
            for (Node nb : currNode.neighbors) {
                if (!visited.containsKey(nb)) {
                    Node copyNb = new Node(nb.val);
                    newNb.add(copyNb);
                    stack.push(nb);
                    visited.put(nb, copyNb);
                } else {
                    Node copyNb = visited.get(nb);
                    newNb.add(copyNb);
                }
            }

            Node copyNode = visited.get(currNode);
            copyNode.neighbors = newNb;
        }

        return copy;
    }

    public static void main(String[] args) {
        // Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors = Arrays.asList(node2, node4);
        node2.neighbors = Arrays.asList(node1, node3);
        node3.neighbors = Arrays.asList(node2, node4);
        node4.neighbors = Arrays.asList(node1, node3);

        CloneGraph cg = new CloneGraph();
        cg.cloneGraph(node1);
    }
}