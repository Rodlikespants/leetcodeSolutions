package leetcode.ds;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You can do this in DFS if you change the Queue to a Stack and corresponding operations
 */
public class NumberOfIslands {
    public static char WATER = '0';
    public static char LAND = '1';
    private static final List<Coordinate> CARDINALS = Arrays.asList(
            new Coordinate(0,0),
            new Coordinate(0,1),
            new Coordinate(0,-1),
            new Coordinate(1,0),
            new Coordinate(-1,0));
    static class Coordinate {
        int x;
        int y;
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int numIslands(char[][] grid) {
        Queue<Coordinate> island;
        if (grid.length == 0) {
            return 0;
        }
        int totalRows = grid.length;
        int totalCols = grid[0].length;
        Coordinate nextLand = findNextLand(grid);

        // initialize
        int numIslands = 0;

        while (nextLand != null) {
            island = new LinkedList<>();
            island.add(nextLand);
            numIslands++;
            while (!island.isEmpty()) {
                Coordinate point = island.poll();
                int pRow = point.y;
                int pCol = point.x;
                for (Coordinate cood : CARDINALS) {
                    int row = pRow + cood.y;
                    int col = pCol + cood.x;
                    if (row >= 0 && col >= 0 && row < totalRows && col < totalCols && grid[row][col] == LAND) {
                        island.add(new Coordinate(col, row));
                        grid[row][col] = (char)('0' + numIslands + 1); // mark one extra to distinguish from LANDS=1
                    }
                }
            }

            nextLand = findNextLand(grid);
        }

        return numIslands;
    }

    public Coordinate findNextLand(char[][] grid) {
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == LAND) {
                    return new Coordinate(j,i);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        char[][] testGrid1 = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};

        NumberOfIslands solution = new NumberOfIslands();
        //int result1 = solution.numIslands(testGrid1); System.out.println(result1);

        char[][] testGrid2 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        //int result2 = solution.numIslands(testGrid2); System.out.println(result2);

        char[][] testGrid3 = {
                {'1','0','0','1','1','1','0','1','1','0','0','0','0','0','0','0','0','0','0','0'},
                {'1','0','0','1','1','0','0','1','0','0','0','1','0','1','0','1','0','0','1','0'},
                {'0','0','0','1','1','1','1','0','1','0','1','1','0','0','0','0','1','0','1','0'},
                {'0','0','0','1','1','0','0','1','0','0','0','1','1','1','0','0','1','0','0','1'},
                {'0','0','0','0','0','0','0','1','1','1','0','0','0','0','0','0','0','0','0','0'},
                {'1','0','0','0','0','1','0','1','0','1','1','0','0','0','0','0','0','1','0','1'},
                {'0','0','0','1','0','0','0','1','0','1','0','1','0','1','0','1','0','1','0','1'},
                {'0','0','0','1','0','1','0','0','1','1','0','1','0','1','1','0','1','1','1','0'},
                {'0','0','0','0','1','0','0','1','1','0','0','0','0','1','0','0','0','1','0','1'},
                {'0','0','1','0','0','1','0','0','0','0','0','1','0','0','1','0','0','0','1','0'},
                {'1','0','0','1','0','0','0','0','0','0','0','1','0','0','1','0','1','0','1','0'},
                {'0','1','0','0','0','1','0','1','0','1','1','0','1','1','1','0','1','1','0','0'},
                {'1','1','0','1','0','0','0','0','1','0','0','0','0','0','0','1','0','0','0','1'},
                {'0','1','0','0','1','1','1','0','0','0','1','1','1','1','1','0','1','0','0','0'},
                {'0','0','1','1','1','0','0','0','1','1','0','0','0','1','0','1','0','0','0','0'},
                {'1','0','0','1','0','1','0','0','0','0','1','0','0','0','1','0','1','0','1','1'},
                {'1','0','1','0','0','0','0','0','0','1','0','0','0','1','0','1','0','0','0','0'},
                {'0','1','1','0','0','0','1','1','1','0','1','0','1','0','1','1','1','1','0','0'},
                {'0','1','0','0','0','0','1','1','0','0','1','0','1','0','0','1','0','0','1','1'},
                {'0','0','0','0','0','0','1','1','1','1','0','1','0','0','0','1','1','0','0','0'}
        };
        int result3 = solution.numIslands(testGrid3); System.out.println(result3);
    }
}
