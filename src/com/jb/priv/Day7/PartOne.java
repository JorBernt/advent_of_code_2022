package com.jb.priv.Day7;

import com.jb.priv.InputReader;

import java.util.ArrayList;
import java.util.List;

class Node {
    String name;
    List<Node> children;
    Node parent;
    long totalSize;

    public Node(String name) {
        this.name = name;
        children = new ArrayList<>();
    }
}

class Parser {
    List<String> input;
    int index = 0;
    int lineIndex = 0;
    String[] currentLine;

    public Parser(String path) {
        input = InputReader.getInput(path);
    }

    public void consume() {
        lineIndex++;
    }

    public String getNextToken() {
        return currentLine[lineIndex++];
    }

    public boolean hasNext() {
        return index < input.size();
    }

    public void nextLine() {
        if (!hasNext())
            return;
        currentLine = input.get(index++).split(" ");
        lineIndex = 0;
    }

    public String peek() {
        return index < input.size() ? input.get(index) : "";
    }


}

public class PartOne {
    public static void main(String[] args) {
        Parser parser = new Parser("src/com/jb/priv/Day7/input.txt");
        Node root = new Node("/");
        parse(root, parser);
        List<Node> dirs = new ArrayList<>();
        getDirs(root, dirs);
        long sum = 0;
        for (var d : dirs) {
            long size = calcSize(d);
            if(size > 100000)
                continue;
            sum += size;
        }
        System.out.println(sum);
    }

    public static long calcSize(Node n) {
        long sum = n.totalSize;
        for(var node : n.children) {
            sum += calcSize(node);
        }
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
