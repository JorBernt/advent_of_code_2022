package com.jb.priv.Day10;

import com.jb.priv.InputReader;


public class PartOne {
    public static void main(String[] args) {
        var input = InputReader.getInput("src/com/jb/priv/Day10/input.txt");
        int cycle = 0;
        int x = 1;
        int flag = 20;
        int sum = 0;
        for(var s : input) {
            cycle++;
            if(flag <= 220 && cycle == flag) {
                sum += cycle * x;
                flag += 40;
            }
            if(s.equals("noop")) {
                continue;
            }
            cycle++;
            if(flag <= 220 && cycle == flag) {
                sum += cycle * x;
                flag += 40;
            }
            var parts = s.split(" ");
            int n = Integer.parseInt(parts[1]);
            x += n;

        }
        System.out.println(sum);
    }
}
