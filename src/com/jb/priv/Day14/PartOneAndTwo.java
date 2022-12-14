package com.jb.priv.Day14;

import com.jb.priv.InputReader;

import java.util.Arrays;

public class PartOneAndTwo {
    public static void main(String[] args) {
        var input = InputReader.getInput("src/com/jb/priv/Day14/input.txt");
        var cave = new int[1000][1000];
        cave[0][500] = 2;
        int minX = Integer.MAX_VALUE, maxX = 0;
        int minY = 0, maxY = 0;
        for (var s : input) {
            var parts = s.split(" -> ");
            var part = parts[0].split(",");
            int x = Integer.parseInt(part[0]);
            int y = Integer.parseInt(part[1]);
            cave[y][x] = -1;
            for (int i = 1; i < parts.length; i++) {
                part = parts[i].split(",");
                int X = Integer.parseInt(part[0]);
                int Y = Integer.parseInt(part[1]);
                while (x != X || y != Y) {
                    minX = Math.min(minX, x);
                    maxX = Math.max(maxX, x);
                    minY = Math.min(minY, y);
                    maxY = Math.max(maxY, y);
                    cave[y][x] = -1;
                    if (X != x) {
                        if (x < X)
                            x++;
                        else x--;
                    } else {
                        if (y < Y)
                            y++;
                        else y--;
                    }
                }
                cave[y][x] = -1;
            }
        }
        //Part Two
        maxY += 2;
        //Part Two
        Arrays.fill(cave[maxY], -1);
        draw(cave, maxY, minX, maxX);
        outer:
        while (true) {
            Sand sand = new Sand(500, 0);
            while (true) {
                //Part One
                /*if (sand.y > maxY) {
                    break outer;
                }*/
                if (cave[sand.y + 1][sand.x] == 0) {
                    sand.y++;
                } else {
                    if (cave[sand.y + 1][sand.x - 1] == 0) {
                        sand.y++;
                        sand.x--;
                        minX = Math.min(minX, sand.x);
                    } else if (cave[sand.y + 1][sand.x + 1] == 0) {
                        sand.y++;
                        sand.x++;
                        maxX = Math.max(maxX, sand.x);
                    } else {
                        cave[sand.y][sand.x] = 1;
                        //Part Two
                        if (sand.y == 0 && sand.x == 500) {
                            break outer;
                        }
                        continue outer;
                    }
                }
            }
        }
        draw(cave, maxY, minX, maxX);
        int sand = 0;
        for (int y = 0; y <= maxY; y++) {
            for (int x = minX - 1; x <= maxX + 1; x++) {
                if (cave[y][x] == 1) {
                    sand++;
                }
            }
        }
        System.out.println(sand);
    }

    static void draw(int[][] cave, int maxY, int minX, int maxX) {
        for (int y = 0; y <= maxY; y++) {
            for (int x = minX - 1; x <= maxX + 1; x++) {
                if (cave[y][x] == 2)
                    System.out.print("+");
                else if (cave[y][x] == 1)
                    System.out.print("o");
                else
                    System.out.print(cave[y][x] == -1 ? "#" : ".");
            }
            System.out.println();
        }
    }

    static class Sand {
        int x, y;

        public Sand(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
