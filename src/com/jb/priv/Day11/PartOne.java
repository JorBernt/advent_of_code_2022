package com.jb.priv.Day11;

import com.jb.priv.InputReader;

import java.util.*;

import static com.jb.priv.Day11.PartTwo.MOD;

class Operation {
    Type type;
    Value a, b;

    public Operation(Type type, Value a, Value b) {
        this.type = type;
        this.a = a;
        this.b = b;
    }

    public long operate(long old) {
        long A = a.old ? old : a.value;
        long B = b.old ? old : b.value;
        if (B == 0)
            return 0;
        return type.equals(Type.MULTIPLICATION) ?
                (A * B) % MOD :
                (A + B) % MOD;
    }

    enum Type {
        MULTIPLICATION,
        ADDITION
    }

    static class Value {
        boolean old;
        int value;

        public Value(boolean old, int n) {
            this.old = old;
            this.value = n;
        }
    }
}

class Test {
    int n;
    int trueMonkey, falseMonkey;

    public Test(int n, int trueMonkey, int falseMonkey) {
        this.n = n;
        this.trueMonkey = trueMonkey;
        this.falseMonkey = falseMonkey;
    }

    public int test(long m) {
        return m % n == 0 ? trueMonkey : falseMonkey;
    }
}

class Parser {
    List<String> input;
    int index = 1;

    public Parser(String path) {
        input = InputReader.getInput(path);
    }

    public void newMonkey() {
        index += 2;
    }

    public Queue<Item> getItems() {
        return new LinkedList<>(Arrays.stream(input.get(index++).replaceAll("[^\\d,]", "").split(","))
                .mapToInt(Integer::parseInt).mapToObj(Item::new).toList());
    }

    public Operation getOperation() {
        var parts = input.get(index++).trim().split(" ");
        var a = new Operation.Value(parts[3].equals("old"), !parts[3].equals("old") ? Integer.parseInt(parts[3].replace(",", "")) : 0);
        var b = new Operation.Value(parts[5].equals("old"), !parts[5].equals("old") ? Integer.parseInt(parts[5].replace(",", "")) : 0);
        return new Operation(parts[4].equals("+") ? Operation.Type.ADDITION : Operation.Type.MULTIPLICATION, a, b);
    }

    public Test getTest() {
        var parts = input.get(index++).trim().split(" ");
        int div = Integer.parseInt(parts[3]);
        parts = input.get(index++).trim().split(" ");
        int trueMonkey = Integer.parseInt(parts[5]);
        parts = input.get(index++).trim().split(" ");
        int falseMonkey = Integer.parseInt(parts[5]);
        return new Test(div, trueMonkey, falseMonkey);
    }

    public List<Monkey> getMonkeys() {
        List<Monkey> monkeys = new ArrayList<>();
        while (index < input.size()) {
            Monkey monkey = new Monkey(getItems(), getOperation(), getTest());
            monkeys.add(monkey);
            newMonkey();
        }
        return monkeys;
    }
}

class Item {
    long value;

    public Item(long value) {
        this.value = value;
    }
}

class Monkey {
    Queue<Item> items;
    Operation operation;
    Test test;
    long inspected = 0;

    public Monkey(Queue<Item> items, Operation operation, Test test) {
        this.items = items;
        this.operation = operation;
        this.test = test;
    }

    public int test() {
        return test.test(items.peek().value);
    }

    public void operate() {
        var item = items.peek();
        item.value = operation.operate(item.value);
        inspected++;
    }

    public void divide() {
        var item = items.peek();
        item.value = Math.floorDiv(item.value, 3);
    }

    public Item getItem() {
        return items.poll();
    }
}

public class PartOne {

    public static void main(String[] args) {
        Parser parser = new Parser("src/com/jb/priv/Day11/input.txt");
        var monkeys = parser.getMonkeys();
        for (Monkey m : monkeys) {
            MOD *= m.test.n;
        }
        for (int round = 0; round < 20; round++) {
            for (Monkey monkey : monkeys) {
                while (!monkey.items.isEmpty()) {
                    monkey.operate();
                    monkey.divide();
                    int throwTo = monkey.test();
                    monkeys.get(throwTo).items.add(monkey.getItem());
                }
            }
        }

        monkeys.sort(Comparator.comparingLong(a -> -a.inspected));
        System.out.println(monkeys.get(0).inspected * monkeys.get(1).inspected);
    }
}
