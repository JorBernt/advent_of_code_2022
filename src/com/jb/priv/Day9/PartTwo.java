package com.jb.priv.Day9;

import com.jb.priv.InputReader;

import java.util.ArrayList;
import java.util.List;

public class PartTwo {
    public static void main(String[] args) {
        var input = InputReader.getInput("src/com/jb/priv/Day9/input.txt");
        var H = new Knot(0, 0);
        List<Knot> knots = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            knots.add(H.copy());
        }
        Knot tail = knots.get(knots.size() - 1);
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
                Knot m = H;
                for (Knot T : knots) {
                    int x = m.x - T.x;
                    int y = m.y - T.y;
                    if (Math.abs(x) > 1 || Math.abs(y) > 1) {
                        if (x > 1) {
                            T.x++;
                            if (y > 0)
                                T.y++;
                            else if (y < 0)
                                T.y--;
                        } else if (x < -1) {
                            T.x--;
                            if (y > 0)
                                T.y++;
                            else if (y < 0)
                                T.y--;
                        } else if (y > 1) {
                            T.y++;
                            if (x > 0)
                                T.x++;
                            else if (x < 0)
                                T.x--;
                        } else if (y < -1) {
                            T.y--;
                            if (x > 0)
                                T.x++;
                            else if (x < 0)
                                T.x--;
                        }
                    }
                    if (T.equals(tail) && !T.pos.contains(T))
                        T.pos.add(T.copy());
                    m = T;
                }
            }

        }
        System.out.println(tail.pos.size());
    }
}
