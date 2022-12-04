package com.jb.priv.Day2;
import com.jb.priv.InputReader;

import java.util.List;

public class PartOne {
    public static void main(String[] args) {
        List<String> input = InputReader.getInput("src/com/jb/priv/DayTwo/input.txt");
        int score = 0;
        for (String s : input) {
            score += getScore(s);
        }
        System.out.println(score);
    }

    static int getScore(String input) {
        return switch (input) {
            case "A X" -> 1 + 3;
            case "A Y" -> 2 + 6;
            case "A Z" -> 3 + 0;
            case "B X" -> 1 + 0;
            case "B Y" -> 2 + 3;
            case "B Z" -> 3 + 6;
            case "C X" -> 1 + 6;
            case "C Y" -> 2 + 0;
            case "C Z" -> 3 + 3;
            default -> 0;
        };
    }

}
