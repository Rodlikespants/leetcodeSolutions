package leetcode.ds;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WallsAndGates {
    static final List<Coordinate> CARDINALS = Arrays.asList(
            new Coordinate(0,1),
            new Coordinate(0,-1),
            new Coordinate(1,0),
            new Coordinate(-1,0));
    static final int EMPTY = Integer.MAX_VALUE;
    static final int GATE = 0;
    static final int WALL = -1;

    static class Coordinate {
        int x;
        int y;
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void wallsAndGates(int[][] rooms) {
        Queue<Coordinate> gates = new LinkedList<>();
        if (rooms.length == 0 || rooms[0].length == 0) {
            return;
        }
        for (int i = 0; i < rooms.length; ++i) {
            for (int j = 0; j < rooms[0].length; ++j) {
                if (rooms[i][j] == GATE) {
                    gates.add(new Coordinate(j,i));
                }
            }
        }
        fillInRooms(rooms, gates);
    }

    public void fillInRooms(int[][] rooms, Queue<Coordinate> gates) {
        int totalRows = rooms.length;
        int totalCols = rooms[0].length;

        while (!gates.isEmpty()) {
            Coordinate point = gates.poll();
            int pRow = point.y;
            int pCol = point.x;
            for (Coordinate cood : CARDINALS) {
                int row = pRow + cood.y;
                int col = pCol + cood.x;

                if (row >= 0 && col >= 0  && row < totalRows && col < totalCols && rooms[row][col] == EMPTY) {
                    rooms[row][col] = rooms[pRow][pCol] + 1; // because gates are 0
                    gates.add(new Coordinate(col, row));
                }
            }
        }
    }
}
