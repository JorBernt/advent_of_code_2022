package com.jb.priv.Day8;

import com.jb.priv.InputReader;

import java.util.Arrays;

public class PartOne {
    public static void main(String[] args) {
        var input = InputReader.getInput("src/com/jb/priv/Day8/input.txt");
        int n = input.get(0).length();
        var grid = new int[n][n];
        for (int i = 0; i < input.size(); i++) {
            grid[i] = Arrays.stream(input.get(i).split("")).mapToInt(Integer::parseInt).toArray();
        }
        int visibleTrees = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visible(grid, i, j, grid[i][j]))
                    visibleTrees++;
            }
        }
        System.out.println(visibleTrees);
    }

    public static boolean visible(int[][] grid, int y, int x, int h) {
        boolean n = true, s = true, w = true, e = true;
        for (int i = y - 1; i >= 0; i--) {
            if (grid[i][x] >= h) {
                n = false;
                break;
            }
        }
        for (int i = y + 1; i < grid.length; i++) {
            if (grid[i][x] >= h) {
                s = false;
                break;
            }
        }
        for (int i = x - 1; i >= 0; i--) {
            if (grid[y][i] >= h) {
                w = false;
                break;
            }
        }
        for (int i = x + 1; i < grid.length; i++) {
            if (grid[y][i] >= h) {
                e = false;
                break;
            }
        }
        return n | s | w | e;
    }
}
