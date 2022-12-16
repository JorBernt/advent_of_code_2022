package com.jb.priv.Day15;

import com.jb.priv.InputReader;

import java.util.*;

public class PartOneAndTwo {
    static List<Range> ranges = new ArrayList<>();
    static List<Point> beacons = new ArrayList<>();

    public static void main(String[] args) {
        var input = InputReader.getInput("src/com/jb/priv/Day15/input.txt");
        List<Point> sensors = new ArrayList<>();
        for (var s : input) {
            var parts = s.split(": ");
            Point sensor = getPoint(parts[0]);
            Point beacon = getPoint(parts[1]);
            long length = (Math.abs(sensor.y() - beacon.y()) + Math.abs(sensor.x() - beacon.x()));
            sensors.add(sensor);
            addRange(sensor.x() - length, sensor.x() + length, sensor.y(), 2000000);
            beacons.add(beacon);
        }
        concatRanges();

        int sum = 0;
        for (var r : ranges) {
            sum += Math.abs(r.end - r.start) + 1;
        }
        Set<Point> beac = new HashSet<>(beacons);
        for (var b : beac)
            if (b.y() == 2000000) {
                for (var r : ranges) {
                    if (b.x() >= r.start && b.x() <= r.end)
                        sum--;
                }
            }

        System.out.println("Part 1: " + sum);
        System.out.println("Starting part two...");
        for (int i = 0; i < 4000000; i++) {
            if(i%1000000==0)
                System.out.println("Calculating...");
            ranges.clear();
            for(int j = 0; j< sensors.size(); j++) {
                var sensor = sensors.get(j);
                var beacon = beacons.get(j);
                long length = (Math.abs(sensor.y() - beacon.y()) + Math.abs(sensor.x() - beacon.x()));
                addRange(sensor.x() - length, sensor.x() + length, sensor.y(), i);
            }
            concatRanges();
            if(ranges.size() != 2)
                continue;
            long min = ranges.stream().mapToLong(Range::end).min().getAsLong();
            long ans = min + 1;
            if(ans >= 0 && ans <= 4000000) {
                ans *= 4000000;
                ans += i;
                System.out.println("Part 2: " + ans);
                return;
            }
        }
    }

    static void addRange(long start, long end, long h, int level) {
        h = Math.abs(h - level);
        start += h;
        end -= h;
        if (start >= end)
            return;

        boolean makeNew = true;
        for (var r : ranges) {
            if (start <= r.start && end >= r.start && end <= r.end) {
                r.start = start;
                makeNew = false;
            }
            if (start >= r.start && start <= r.end && end >= r.end) {
                r.end = end;
                makeNew = false;
            }
            if (start <= r.start && end >= r.end) {
                r.start = start;
                r.end = end;
                makeNew = false;
            }
        }
        if (!makeNew) {
            return;
        }
        ranges.add(new Range(start, end));
    }

    static void concatRanges() {
        while (true) {
            boolean done = true;
            outer:
            for (var r : ranges) {
                for (var rr : ranges) {
                    if (r.equals(rr))
                        continue;
                    if (r.start <= rr.start && r.end >= rr.start && r.end <= rr.end) {
                        rr.start = r.start;
                        ranges.remove(r);
                        done = false;
                        break outer;
                    }
                    if (r.start >= rr.start && r.start <= rr.end && r.end >= rr.end) {
                        rr.end = r.end;
                        ranges.remove(r);
                        done = false;
                        break outer;
                    }
                    if (r.start <= rr.start && r.end >= rr.end) {
                        rr.start = r.start;
                        rr.end = r.end;
                        ranges.remove(r);
                        done = false;
                        break outer;
                    }
                }
            }
            if (done)
                break;
        }
    }

    static Point getPoint(String input) {
        var parts = input.split(", ");
        long x = Long.parseLong(parts[0].replaceAll("[^\\d-]", ""));
        long y = Long.parseLong(parts[1].replaceAll("[^\\d-]", ""));
        return new Point(x, y);
    }

    record Point(long x, long y) {
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
        long start, end;

        public Range(long start, long end) {
            this.start = start;
            this.end = end;
        }

        public long start() {
            return start;
        }

        public long end() {
            return end;
        }

    }
}
