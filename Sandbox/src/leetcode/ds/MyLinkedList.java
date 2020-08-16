package leetcode.ds;

class MyLinkedList {

    private int length;
    class Node {
        int val;
        Node next;
        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
    Node head;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        this.length = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        int i = 0;
        Node curr = head;
        while (curr != null && i <= index) {
            if (i == index) {
                return curr.val;
            }
            curr = curr.next;
            ++i;
        }
        return -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node curr = new Node(val, head);
        this.head = curr;
        this.length++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        int i = 0;
        Node curr = head;
        Node prev = null;

        while (curr != null) {
            prev = curr;
            curr = curr.next;
            ++i;
        }

        Node node = new Node(val, null);
        // if there are 0 elements
        if (prev == null) {
            head = node;
        } else {
            prev.next = node;
        }
        this.length++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        int i = 0;
        Node curr = head;
        Node prev = null;
        // edge case
        if (index == this.length) {
            addAtTail(val);
            return;
        }
        while (curr != null && i <= index) {
            if (index == i) {
                Node node = new Node(val, curr);
                if (prev != null) {
                    prev.next = node;
                } else {
                    head = node;
                }
            }
            prev = curr;
            curr = curr.next;
            ++i;
        }
        this.length++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        int i = 0;
        Node curr = head;
        Node prev = null;

        while (curr != null && i <= index) {
            if (index == i) {
                if (prev != null) {
                    prev.next = curr.next;
                } else {
                    head = curr.next;
                }
                this.length--;
                return;
            }
            prev = curr;
            curr = curr.next;
            ++i;
        }
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        list.addAtHead(86);
        list.addAtIndex(1, 54);
        list.addAtIndex(1, 14);
        list.addAtHead(83);
        list.deleteAtIndex(4);
        list.addAtIndex(3, 18);
        list.addAtHead(46);
        list.addAtTail(3);
        list.addAtHead(76);
        int result3 = list.get(5);

        list = new MyLinkedList();

        list.addAtIndex(0,10);
        list.addAtIndex(0,20);
        list.addAtIndex(1,30);
        int result0 = list.get(0);

        list = new MyLinkedList();

        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2);
        int result1 = list.get(1);
        list.deleteAtIndex(1);
        int result2 = list.get(1);

        list = new MyLinkedList();


        list.addAtHead(7);
        list.addAtHead(2);
        list.addAtHead(1);
        list.addAtIndex(3,0);
        list.deleteAtIndex(2);
        list.addAtHead(6);
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
