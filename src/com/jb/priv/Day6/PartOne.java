package com.jb.priv.Day6;

import com.jb.priv.InputReader;

public class PartOne {
    public static void main(String[] args) {
        var input = InputReader.getInput("src/com/jb/priv/Day6/input.txt").get(0);
        outer:
        for (int i = 0; i < input.length() - 14; i++) {
            var chars = new boolean[26];
            for (int j = i; j < i + 14; j++) {
                var c = input.charAt(j) - 'a';
                if (chars[c]) {
                    continue outer;
                }
                chars[c] = true;
            }
            System.out.println(i + 14);
            return;
        }
    }
}
