package com.jb.priv.Day8;

import com.jb.priv.InputReader;

import java.util.Arrays;

public class PartTwo {
    public static void main(String[] args) {
        var input = InputReader.getInput("src/com/jb/priv/Day8/input.txt");
        int n = input.get(0).length();
        var grid = new int[n][n];
        for (int i = 0; i < input.size(); i++) {
            grid[i] = Arrays.stream(input.get(i).split("")).mapToInt(Integer::parseInt).toArray();
        }
        int best = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                best = Math.max(visible(grid, i, j, grid[i][j]), best);
            }
        }
        System.out.println(best);
    }

    public static int visible(int[][] grid, int y, int x, int h) {
        int a = 0,b = 0,c = 0,d = 0;
        for (int i = y - 1; i >= 0; i--) {
            a++;
            if (grid[i][x] >= h) {
                break;
            }
        }
        for (int i = y + 1; i < grid.length; i++) {
            b++;
            if (grid[i][x] >= h) {
                break;
            }
        }
        for (int i = x - 1; i >= 0; i--) {
            c++;
            if (grid[y][i] >= h) {
                break;
            }
        }
        for (int i = x + 1; i < grid.length; i++) {
            d++;
            if (grid[y][i] >= h) {
                break;
            }
        }
        return a * b * c * d;
    }
}
