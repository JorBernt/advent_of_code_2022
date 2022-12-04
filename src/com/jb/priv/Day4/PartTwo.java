package com.jb.priv.Day4;

import com.jb.priv.InputReader;

import java.util.Arrays;

public class PartTwo {
    public static void main(String[] args) {
        System.out.println(InputReader.getInput("src/com/jb/priv/Day4/input.txt").stream().mapToInt(s -> Arrays.stream(s.split(",")).map(p -> Arrays.stream(p.split("-")).mapToInt(Integer::parseInt).toArray()).reduce((a,b) -> ((a[0] >= b[0] && a[0] <= b[1]) || (a[1] >= b[0] && a[1] <= b[1]) || b[0] >= a[0] && b[0] <= a[1]) || (b[1] >= a[0] && b[1] <= a[1])  ? new int[]{1}:new int[]{0}).get()[0]).sum());
    }

}
