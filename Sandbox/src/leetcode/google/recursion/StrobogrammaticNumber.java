package leetcode.google.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrobogrammaticNumber {
    String[] singleStrobs = {"0", "1", "8"};
    String[] doubleStrobs = {"11", "69", "88", "96"};
    String[] nStrobs = {"00", "11", "69", "88", "96"};
    public List<String> findStrobogrammatic(int n) {
        return findStrob(n, true);
    }

    public List<String> findStrob(int n, boolean first) {

        String[] strobGen = nStrobs;
        if (n == 0) {
            return new ArrayList<>();
        } else if (n == 1) {
            return Arrays.asList(singleStrobs);
        } else if (n == 2) {
            return first ? Arrays.asList(doubleStrobs) : Arrays.asList(nStrobs);
        }
        if (first) {
            strobGen = doubleStrobs; // edge case to avoid 010 etc.
        }

        List<String> strobs = new ArrayList<>();
        for (int i = 0; i < strobGen.length; ++i) {
            List<String> newStrobs = findStrob(n-2, false);
            for (String innerStrob : newStrobs) {
                String newStrob = strobGen[i].charAt(0) + innerStrob + strobGen[i].charAt(1);
                strobs.add(newStrob);
            }
        }

        return strobs;
    }

    public static void main(String[] args) {
        System.out.println(new StrobogrammaticNumber().findStrobogrammatic(3));
    }
}