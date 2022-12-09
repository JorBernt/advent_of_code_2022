package com.jb.priv.Day9;

import com.jb.priv.InputReader;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Knot {
    int x, y;
    Set<Knot> pos = new HashSet<>();

    public Knot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Knot copy() {
        return new Knot(x, y);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Knot knot = (Knot) o;
        return x == knot.x && y == knot.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }
}

public class PartOne {
    public static void main(String[] args) {
        var input = InputReader.getInput("src/com/jb/priv/Day9/input.txt");
        var H = new Knot(0, 0);
        var T = H.copy();
        for (var s : input) {
            var parts = s.split(" ");
            int n = Integer.parseInt(parts[1]);
            for (int i = 0; i < n; i++) {
                switch (parts[0]) {
                    case "R" -> H.x++;
                    case "L" -> H.x--;
                    case "U" -> H.y--;
                    case "D" -> H.y++;
                }
                int x = H.x - T.x;
                int y = H.y - T.y;
                if (Math.abs(x) > 1 || Math.abs(y) > 1) {
                    if (x > 1) {
                        T.x++;
                        if (y > 0)
                            T.y++;
                        else if (y < 0)
                            T.y--;
                    }
                    else if (x < -1) {
                        T.x--;
                        if (y > 0)
                            T.y++;
                        else if (y < 0)
                            T.y--;
                    }
                    else if (y > 1) {
                        T.y++;
                        if (x > 0)
                            T.x++;
                        else if (x < 0)
                            T.x--;
                    }
                    else if (y < -1) {
                        T.y--;
                        if (x > 0)
                            T.x++;
                        else if (x < 0)
                            T.x--;
                    }
                }
                if(!T.pos.contains(T))
                    T.pos.add(T.copy());
            }

        }
        System.out.println(T.pos.size());
    }
}
