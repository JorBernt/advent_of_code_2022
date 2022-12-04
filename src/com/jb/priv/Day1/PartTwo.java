package com.jb.priv.Day1;

import com.jb.priv.InputReader;

import java.util.*;

public class PartTwo {

    public static void main(String[] args) {
        List<String> input = InputReader.getInput("src/com/jb/priv/DayOne/input.txt");
        List<Integer> elves = new ArrayList<>();
        int sum = 0;
        for (String s : input) {
            if(s.isEmpty()) {
                elves.add(sum);
                sum = 0;
                continue;
            }
            sum += Integer.parseInt(s);
        }
        elves.add(sum);
        elves.sort(Comparator.reverseOrder());
        int[] a = new int[2];
        System.out.println(elves.stream().limit(3).mapToInt(n -> n).sum());
    }
}
