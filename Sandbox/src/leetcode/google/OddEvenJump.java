package leetcode.google;

import java.util.*;

public class OddEvenJump {
    public int oddEvenJumps(int[] A) {
        int N = A.length;
        if (N <= 1) return N;
        boolean[] odd = new boolean[N];
        boolean[] even = new boolean[N];
        odd[N-1] = even[N-1] = true;

        TreeMap<Integer, Integer> vals = new TreeMap();
        vals.put(A[N-1], N-1);
        for (int i = N-2; i >= 0; --i) {
            int v = A[i];
            if (vals.containsKey(v)) {
                odd[i] = even[vals.get(v)];
                even[i] = odd[vals.get(v)];
            } else {
                Integer lower = vals.lowerKey(v);
                Integer higher = vals.higherKey(v);

                if (lower != null)
                    even[i] = odd[vals.get(lower)];
                if (higher != null) {
                    odd[i] = even[vals.get(higher)];
                }
            }
            vals.put(v, i);
        }

        int ans = 0;
        for (boolean b: odd)
            if (b) ans++;
        return ans;
    }
    public static void main(String[] args) {
        OddEvenJump solution = new OddEvenJump();

        int[] A = {10,13,12,14,15};
        int result = solution.oddEvenJumps(A);
        System.out.println(result);
    }
    /*
    enum Parity {
        EVEN,
        ODD
    }
    static class Pair {
        int index;
        Parity parity;

        Pair(int index, Parity parity) {
            this.index = index;
            this.parity = parity;
        }
    }
    public int oddEvenJumps(int[] A) {
        Map<Pair, Integer> cache = new HashMap<>();

        List<Integer> goodIndexes = new ArrayList<>();
        for (int i = 0; i < A.length-1; ++i) {
            int jump = 0;

            int nextIndex = processIndex(A, i, ++jump, cache);
            System.out.println(nextIndex);

            while (nextIndex != A.length-1 && nextIndex != -1) {
                nextIndex = processIndex(A, nextIndex, ++jump, cache);
                System.out.println(nextIndex);
            }

            if (nextIndex == A.length-1) {
                goodIndexes.add(i);
                System.out.println(i + " is a good index");
            }
        }
        goodIndexes.add(A.length-1);
        return goodIndexes.size();
    }

    public int processIndex(int[] A, int i, int jump, Map<Pair, Integer> cache) {
        Parity parity = jump % 2 == 0 ? Parity.EVEN : Parity.ODD;
        Pair pair = new Pair(i, parity);
        if (cache.containsKey(pair)) {
            return cache.get(pair);
        } else {
            int newIndex = nextIndex(A, i, parity);
            cache.put(pair, newIndex);
            return newIndex;
        }
    }

    public int nextIndex(int[] A, int i, Parity parity) {
        int[] sub = Arrays.copyOfRange(A, i+1, A.length);
        int relativeIndex = parity == Parity.ODD ? findMinValueIndex(sub, A[i]) : findMaxValueIndex(sub, A[i]);
        if (relativeIndex == -1) {
            return -1;
        }
        return relativeIndex + i + 1;
    }

    public int findMinValueIndex(int[] sub, int value) {
        int lowest = Integer.MAX_VALUE;
        int lowestIndex = -1;
        for (int i = 0; i < sub.length; ++i) {
            if (sub[i] >= value && sub[i] < lowest) {
                lowest = sub[i];
                lowestIndex = i;
            }
        }
        return lowestIndex;
    }

    public int findMaxValueIndex(int[] sub, int value) {
        int highest = Integer.MIN_VALUE;
        int highestIndex = -1;
        for (int i = 0; i < sub.length; ++i) {
            if (sub[i] <= value && sub[i] > highest) {
                highest = sub[i];
                highestIndex = i;
            }
        }
        return highestIndex;
    }
     */
}
