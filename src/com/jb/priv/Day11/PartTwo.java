package com.jb.priv.Day11;

import java.util.Comparator;


public class PartTwo {
    static long MOD = 1;

    public static void main(String[] args) {
        Parser parser = new Parser("src/com/jb/priv/Day11/input.txt");
        var monkeys = parser.getMonkeys();
        for (Monkey m : monkeys) {
            MOD *= m.test.n;
        }
        for (int round = 0; round < 10000; round++) {
            for (Monkey monkey : monkeys) {
                while (!monkey.items.isEmpty()) {
                    monkey.operate();
                    int throwTo = monkey.test();
                    monkeys.get(throwTo).items.add(monkey.getItem());
                }
            }
        }
        monkeys.sort(Comparator.comparingLong(a -> -a.inspected));
        System.out.println(monkeys.get(0).inspected * monkeys.get(1).inspected);
    }
}
