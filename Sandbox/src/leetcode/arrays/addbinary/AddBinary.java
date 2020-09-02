package leetcode.arrays.addbinary;

public class AddBinary {
    public String addBinary(String a, String b) {
        // process from right to left
        // don't need to figure out which is larger
        // use two pointers to iterate left on both strings
        int ptr1 = a.length()-1;
        int ptr2 = b.length()-1;

        StringBuilder sb = new StringBuilder();
        // initialize carry to 0
        int carry = 0;
        // while the pointer is not negative for both a and b
        while (ptr1 >= 0 || ptr2 >= 0) {
            // add the current digits, save current value
            int digit1 = ptr1 >= 0 ? Character.getNumericValue(a.charAt(ptr1)) : 0;
            int digit2 = ptr2 >= 0 ? Character.getNumericValue(b.charAt(ptr2)) : 0;

            int sum = carry + digit1 + digit2;
            // save the carry
            carry = sum >> 1; // or sum & 2?
            //System.out.println("digit1=" + digit1 + ", digit2=" + digit2 + ", sum=" + sum + ", carry=" + carry);
            int newDigit = sum & 1;
            sb.append(newDigit);
            // decrement the pointers
            ptr1--;
            ptr2--;
        }

        if (carry != 0) {
            sb.append(carry);
        }
        sb.reverse();
        return sb.toString();
    }
}