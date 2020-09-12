package leetcode.google.dp;

import java.util.Comparator;
import java.util.stream.IntStream;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        //coins = IntStream.of(coins).boxed().sorted(Comparator.reverseOrder()).mapToInt(i -> i).toArray();
        int result = findCoins(coins, amount, new int[amount]);
        return result;
    }

    /**
     * @param coins
     * @param amount
     * @param cache int array acting as a hash for memoized results
     * @return
     */
    int findCoins(int[] coins, int amount, int[] cache) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        // off by one indexing
        if (cache[amount-1] != 0) {
            return cache[amount-1];
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < coins.length; ++i) {
            int coin = coins[i];
            int result = findCoins(coins, amount-coin, cache);
            if (result >= 0 && result < min) {
                min = result + 1;
            }
        }

        cache[amount-1] = min != Integer.MAX_VALUE ? min : -1;
        return cache[amount-1];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(new CoinChange().coinChange(coins, amount));
    }
}
