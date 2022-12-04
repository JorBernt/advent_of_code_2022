package com.jb.priv.Day3;

import com.jb.priv.InputReader;

import java.util.ArrayList;
import java.util.List;

public class PartTwo {
    public static void main(String[] args) {
        var input = InputReader.getInput("src/com/jb/priv/DayThree/input.txt");
        int count = 1;
        List<Character> badges = new ArrayList<>();
        for (int i = 0; i < input.size(); i += 3) {
            char badge = getBadge(input, i);
            badges.add(badge);
        }
        long sum = badges.stream()
                .mapToInt(a -> Character.isUpperCase(a) ? a - 'A' + 27 : a - 'a' + 1)
                .sum();
        System.out.println(sum);
    }

    static char getBadge(List<String> input, int from) {
        for (char a : input.get(from).toCharArray()) {
            for(char b : input.get(from + 1).toCharArray()) {
                if(a == b) {
                    for(char c : input.get(from + 2).toCharArray()) {
                        if(b == c)
                            return c;
                    }
                }
            }
        }
        return '-';
    }
}
