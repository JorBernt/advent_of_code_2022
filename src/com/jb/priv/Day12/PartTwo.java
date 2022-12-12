package com.jb.priv.Day12;

import com.jb.priv.InputReader;

import java.util.*;

public class PartTwo {
    public static void main(String[] args) {
        var input = InputReader.getInput("src/com/jb/priv/Day12/input.txt");
        Node start = null, goal = null;
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                Node n = new Node(j, i, input.get(i).charAt(j));
                nodes.add(n);
                if (n.height == 'S') {
                    start = n;
                    n.height = 'a';
                }
                if (n.height == 'E') {
                    goal = n;
                    n.height = 'z';
                }

            }
        }
        for (Node n : nodes) {
            n.addAdjacent(nodes);
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(goal);
        goal.path = 0;
        List<Node> visited = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            if (visited.contains(n))
                continue;
            visited.add(n);
            for (Node a : n.adjacent) {
                a.path = Math.min(n.path + 1, a.path);
            }
            queue.addAll(n.adjacent);
        }
        int min = Integer.MAX_VALUE;
        for (Node n : nodes) {
            if (n.height == 'a')
                min = Math.min(min, n.path);
        }
        System.out.println(min);
    }

    static class Node {
        int x, y;
        int path = Integer.MAX_VALUE;
        char height;
        List<Node> adjacent = new ArrayList<>();

        public Node(int x, int y, char height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        public void addAdjacent(List<Node> nodes) {
            for (Node n : nodes) {
                if (n.equals(this))
                    continue;
                if (n.height < height - 1)
                    continue;
                if ((n.x == x - 1 && n.y == y) || (n.x == x + 1 && n.y == y) || (n.y == y - 1 && n.x == x) || (n.y == y + 1 && n.x == x)) {
                    adjacent.add(n);
                }

            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y && height == node.height;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, height);
        }
    }
}
