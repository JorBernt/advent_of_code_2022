package com.jb.priv.DayTwo;

import com.jb.priv.InputReader;

import java.util.List;

public class PartTwo {
    public static void main(String[] args) {
        List<String> input = InputReader.getInput("src/com/jb/priv/DayTwo/input.txt");
        int sum = 0;
        for(String s : input) {
            var parts = s.split(" ");
            switch (parts[1]) {
                case "X" ->  sum += getScore(String.format("%s %s",parts[0], getLosing(parts)));
                case "Y" ->  sum += getScore(String.format("%s %s",parts[0], getDraw(parts)));
                case "Z" ->  sum += getScore(String.format("%s %s",parts[0], getWin(parts)));
            }
        }
        System.out.println(sum);
    }

    static String getWin(String[] parts) {
        return switch (parts[0]) {
            case "A" -> "Y";
            case "B" -> "Z";
            case "C" -> "X";
            default -> "";
        };
    }

    static String getDraw(String[] parts) {
        return switch (parts[0]) {
            case "A" -> "X";
            case "B" -> "Y";
            case "C" -> "Z";
            default -> "";
        };
    }

    static String getLosing(String[] parts) {
        return switch (parts[0]) {
            case "A" -> "Z";
            case "B" -> "X";
            case "C" -> "Y";
            default -> "";
        };
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
