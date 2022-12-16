package com.jb.priv.Day15;

import com.jb.priv.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PartOne {
    public static void main(String[] args) {
        var input = InputReader.getInput("src/com/jb/priv/Day15/input.txt");
        List<Point> sensors = new ArrayList<>();
        List<Point> beacons = new ArrayList<>();
        for (var s : input) {
            var parts = s.split(": ");
            Point sensor = getPoint(parts[0]);
            Point beacon = getPoint(parts[1]);
            sensors.add(sensor);
            beacons.add(beacon);
        }
    }


    static Point getPoint(String input) {
        var parts = input.split(", ");
        int x = Integer.parseInt(parts[0].replaceAll("\\D", ""));
        int y = Integer.parseInt(parts[1].replaceAll("\\D", ""));
        return new Point(x, y);
    }

    record Point(int x, int y) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
