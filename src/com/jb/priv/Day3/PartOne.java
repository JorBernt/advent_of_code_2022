package com.jb.priv.Day3;

import com.jb.priv.InputReader;

import java.util.ArrayList;
import java.util.List;

public class PartOne {
    public static void main(String[] args) {
        var input = InputReader.getInput("src/com/jb/priv/Day3/input.txt");
        List<Character> items = new ArrayList<>();
        outer:
        for (var s : input) {
            var compA = s.substring(0, s.length() / 2);
            var compB = s.substring(s.length() / 2);
            for (char c : compA.toCharArray()) {
                for (char d : compB.toCharArray()) {
                    if (c == d) {
                        items.add(c);
                        continue outer;
                    }
                }
            }
        }
        long sum = items.stream()
                .mapToInt(a -> Character.isUpperCase(a) ? a - 'A' + 27 : a - 'a' + 1)
                .sum();
        System.out.println(sum);
    }
}
