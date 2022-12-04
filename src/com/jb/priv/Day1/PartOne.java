package com.jb.priv.Day1;

import com.jb.priv.InputReader;

import java.util.List;

public class PartOne {

    public static void main(String[] args) {
        List<String> input = InputReader.getInput("src/com/jb/priv/DayOne/input.txt");
        int max = 0;
        int sum = 0;
        for (String s : input) {
            if(s.isEmpty()) {
                if(sum > max) {
                    max = sum;
                }
                sum = 0;
                continue;
            }
            sum += Integer.parseInt(s);
        }
        System.out.println(max);
    }
}
