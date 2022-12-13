package jab.aoc.day12;

import jab.aoc.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class day12_1 {
    public static void solution(String fileName) {
        //String input = Files.readString(Paths.get("src/day12/input.txt"));
//        String input = Files.readString(Paths.get("src/day12/test.txt"));
        String input = Utils.readFileToString(fileName);
        String[] lines = input.split("\r?\n");

        char[][] mountain = new char[lines.length][lines[0].length()];
        int row = 0;
        int col = 0;
        int endRow = 0;
        int endCol = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            char[] c = new char[line.length()];
            for (int j = 0; j < line.length(); j++) {
                c[j] = line.charAt(j);
                if (c[j] == 'S') {
                    row = i;
                    col = j;
                    c[j] = 'a';
                }
                if (c[j] == 'E') {
                    endRow = i;
                    endCol = j;
                    c[j] = 'z';
                }
            }
            mountain[i] = c;
        }

        for(var y = 0; y < mountain.length; y++) {
            for(var x = 0; x < mountain[0].length; x++) {
                System.out.print(mountain[y][x] + " ");
            }
            System.out.println();
        }

        System.out.println("S " + col + "," + row);
        System.out.println("E " + endCol + "," + endRow);
        Config initialConfig = new Config(col, row, endCol, endRow, mountain);
        var result = bfs(initialConfig);
        System.out.println(result);
    }

    public static int bfs(Config start) {
        Map<Config, Config> predecessor = new HashMap<>();
        predecessor.put(start, null);
        Queue<Config> toVisit = new LinkedList<>();
        toVisit.offer(start);
        Config end = null;

        //Why not finish
        while (!toVisit.isEmpty() && end == null) {
            //Current position
            Config thisNode = toVisit.remove();
            for (Config nbr : thisNode.getNeighbors()) {
                if (!predecessor.containsKey(nbr)) {
                    predecessor.put(nbr, thisNode);
                    toVisit.offer(nbr);
                    if (nbr.isSolution()) {
                        end = nbr;
                    }
                }
            }
        }

        /*
        for(Map.Entry entry:predecessor.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().toString());
        }

         */

        System.out.println(predecessor.size());

        List<Config> path = new LinkedList<>();
        path.add(0, end);
        Config node = predecessor.get(end);
        while(node != null) {
            path.add(0, node);
            node = predecessor.get(node);
        }
        return path.size() - 1;
    }
}
