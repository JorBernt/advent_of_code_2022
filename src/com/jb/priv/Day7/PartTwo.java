package com.jb.priv.Day7;

import java.util.List;


public class PartTwo {
    public static void main(String[] args) {
        Parser parser = new Parser("src/com/jb/priv/Day7/input.txt");
        Node root = new Node("/");
        parse(root, parser);
        long totalSize = calcSize(root);
        long smallestToDelete = findSmallestToDelete(root, 70000000 - totalSize);
        System.out.println(smallestToDelete);
    }

    public static long findSmallestToDelete(Node node, long totalsize) {
        long min = node.totalSize;
        for (Node n : node.children) {
            min = Math.min(min, findSmallestToDelete(n, totalsize));
        }
        return totalsize + min >= 30000000 ? min : Long.MAX_VALUE;
    }

    public static long calcSize(Node n) {
        long sum = n.totalSize;
        for (var node : n.children) {
            sum += calcSize(node);
        }
        n.totalSize = sum;
        return sum;
    }

    public static void getDirs(Node node, List<Node> dirs) {
        if (node.totalSize <= 100000 && !node.name.equals("/")) {
            dirs.add(node);
        }
        for (Node n : node.children) {
            getDirs(n, dirs);
        }
    }


    public static void parse(Node root, Parser parser) {
        Node current = null;
        while (parser.hasNext()) {
            parser.nextLine();
            var token = parser.getNextToken();
            if (token.equals("$")) {
                token = parser.getNextToken();
                if (token.equals("cd")) {
                    var path = parser.getNextToken();
                    if (path.equals("..")) {
                        current = current.parent;
                    } else if (path.equals("/")) {
                        current = root;
                    } else {
                        current = findDir(current, path);
                    }
                } else if (token.equals("ls")) {
                    parser.nextLine();
                    while (true) {
                        token = parser.getNextToken();
                        if (token.equals("dir")) {
                            var path = parser.getNextToken();
                            Node dir = new Node(path);
                            current.children.add(dir);
                            dir.parent = current;

                        } else {
                            long size = Long.parseLong(token);
                            current.totalSize += size;
                        }
                        if (parser.peek().startsWith("$") || !parser.hasNext())
                            break;
                        parser.nextLine();
                    }
                }
            }
        }
    }

    public static Node findDir(Node node, String path) {
        for (Node n : node.children) {
            if (n.name.equals(path))
                return n;
        }
        return null;
    }
}
