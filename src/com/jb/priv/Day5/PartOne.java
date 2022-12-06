package com.jb.priv.Day5;

import com.jb.priv.InputReader;

import java.util.Deque;
import java.util.LinkedList;

public class PartOne {
    public static void main(String[] args) {
        var input = InputReader.getInput("src/com/jb/priv/Day5/input.txt");
        Deque<Character>[] stacks = new LinkedList[9];
        for (int i = 0; i < 8; i++) {
            var parts = input.get(i);
            for (int j = 1, l = 0; j < parts.length(); j += 4, l++) {
                if(Character.isAlphabetic(parts.charAt(j))) {
                    if(stacks[l] == null)
                        stacks[l] = new LinkedList<>();
                    stacks[l].addLast(parts.charAt(j));
                }
            }
        }
        for (int i = 10; i < input.size(); i++) {
            var parts = input.get(i).split(" ");
            int amount = Integer.parseInt(parts[1]);
            int from = Integer.parseInt(parts[3]) - 1;
            int to = Integer.parseInt(parts[5]) - 1;
            for (int j = 0; j < amount; j++) {
                stacks[to].addFirst(stacks[from].pop());
            }
        }
        for (var stack : stacks) {
            System.out.print(stack.peek());
        }
        System.out.println();
    }
}
