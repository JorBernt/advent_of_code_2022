package com.jb.priv;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {
    public static List<String> getInput (String path) {
        List<String> input = new ArrayList<>();
        try {
            Scanner in = new Scanner(new FileInputStream(path));
            while (in.hasNextLine()) {
                input.add(in.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return input;
    }
}
