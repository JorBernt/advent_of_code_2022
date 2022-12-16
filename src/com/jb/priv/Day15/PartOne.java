package com.jb.priv.Day15;

import com.jb.priv.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PartOne {
    static List<Range> ranges = new ArrayList<>();

    public static void main(String[] args) {
        var input = InputReader.getInput("src/com/jb/priv/Day15/input.txt");
        List<Point> sensors = new ArrayList<>();
        List<Point> beacons = new ArrayList<>();
        for (var s : input) {
            var parts = s.split(": ");
            Point sensor = getPoint(parts[0]);
            Point beacon = getPoint(parts[1]);
            int x = beacon.x() - sensor.x();
            addRange(beacon.x(), (Math.abs(x) + 1) * 2 + 1, beacon.y());
            sensors.add(sensor);
            beacons.add(beacon);
        }
        int sum = 0;
        for (var r : ranges) {
            sum += Math.abs(r.end - r.start);
        }
        System.out.println(sum);
    }

    static void addRange(int start, int end, int h) {
        h -= 10;
        h /= 2;
        start -= h;
        end -= h;
        for (var r : ranges) {
            if (r.start < start && end < r.end) {
                r.start = start;
                return;
            }
            if (r.start > start && end > r.end) {
                r.end = end;
                return;
            }
        }
        ranges.add(new Range(start, end));
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

    static class Range {
        int start, end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
