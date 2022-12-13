package com.jb.priv.Day13;

import com.jb.priv.InputReader;

import java.util.ArrayList;
import java.util.List;

public class PartOneAndTwo {
    static int index = 1;

    public static void main(String[] args) {
        var input = InputReader.getInput("src/com/jb/priv/Day13/input.txt");
        int sumPairs = 0;
        List<Packet> packets = new ArrayList<>();
        for (int i = 0, j = 1; i < input.size(); i += 3, j++) {
            Packet a = parsePackets(input.get(i));
            Packet b = parsePackets(input.get(i + 1));
            packets.add(a);
            packets.add(b);
            if (equalsPacket(a, b) != 1) {
                sumPairs += j;
            }
        }
        for (int i = 2; i < 7; i += 4) {
            Packet packet = parsePackets("[[" + i + "]]");
            packet.divider = true;
            packets.add(packet);

        }
        System.out.println("Part 1: " + sumPairs);
        packets.sort(PartOneAndTwo::equalsPacket);
        int n = 1;
        for (int i = 0; i < packets.size(); i++) {
            if (packets.get(i).divider)
                n *= (i + 1);
        }
        System.out.println("Part 2 : " + n);
    }

    public static Packet parsePackets(String in) {
        index = 1;
        return parsePacketsImpl(in);
    }

    public static Packet parsePacketsImpl(String in) {
        List<Data> datas = new ArrayList<>();
        var ca = in.toCharArray();
        for (; index < in.length(); index++) {
            char c = ca[index];
            if (c == ',')
                continue;
            Data data = new Data();

            if (c == ']')
                break;
            if (c == '[') {
                index++;
                data.packet = parsePacketsImpl(in);
            } else {
                StringBuilder sb = new StringBuilder();
                for (int j = index; j < ca.length && ca[j] != ',' && ca[j] != ']'; j++, index++) {
                    sb.append(ca[j]);
                }
                data.value = Integer.parseInt(sb.toString());
                index--;
            }
            datas.add(data);
        }
        return new Packet(datas);
    }

    public static int equalsPacket(Packet p1, Packet p2) {
        if (p1.data.isEmpty())
            return p2.data.isEmpty() ? 0 : -1;
        for (int i = 0; i < p1.data.size() || i < p2.data.size(); i++) {
            if (i >= p1.data.size())
                return -1;
            Data a = p1.data.get(i);
            if (i >= p2.data.size())
                return 1;
            Data b = p2.data.get(i);
            if (a.packet == null & b.packet == null) {
                int c = Integer.compare(a.value, b.value);
                if (c == 0)
                    continue;
                return c;
            }
            int c = equalsPacket(a.packet == null ? new Packet(a.value) : a.packet, b.packet == null ? new Packet(b.value) : b.packet);
            if (c != 0)
                return c;
        }
        return 0;
    }

    static class Data {
        int value = -1;
        Packet packet;

        public Data() {
        }
    }

    static class Packet {
        List<Data> data;
        boolean divider = false;

        public Packet(int value) {
            Data data = new Data();
            data.value = value;
            this.data = new ArrayList<>();
            this.data.add(data);
        }

        public Packet(List<Data> data) {
            this.data = data;
        }
    }
}
