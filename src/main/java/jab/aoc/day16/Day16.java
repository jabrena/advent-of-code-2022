package jab.aoc.day16;

import jab.aoc.Day;
import jab.aoc.Utils;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day16 implements Day<Long> {

    private static final String regex = List
        .of(
            "Valve (\\w+) has flow rate=(\\d+); ",
            "(?:tunnels lead to valves (\\w+(?:,\\s\\w+)*)|",
            "tunnel leads to valve (\\w+))"
        )
        .stream()
        .collect(Collectors.joining(""));
    private static final Pattern pattern = Pattern.compile(regex);

    private record Valve(String name, Integer rate, List<String> connections) {}

    private record Valve2(String id, int flowRate, List<String> available) {
        private static final Pattern PATTERN = Pattern.compile(
            "Valve (\\w+) has flow rate=(\\d+); \\w+ \\w+ to \\w+ (.+)"
        );

        static Valve2 parse(String line) {
            var matcher = PATTERN.matcher(line);
            if (matcher.matches()) {
                String id = matcher.group(1);
                int rate = Integer.parseInt(matcher.group(2));
                List<String> next = Arrays.asList(matcher.group(3).trim().split(", "));
                return new Valve2(id, rate, next);
            }
            throw new IllegalArgumentException("does not match: " + line);
        }
    }

    @Override
    public Long getPart1Result(String fileName) {
        try {
            ProboscideaVolcanium proboscideaVolcanium = new ProboscideaVolcanium();
            proboscideaVolcanium.solution(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // @formatter:off

        //Source
        var valves = Utils.readFileToList(fileName)
            .stream()
            .map(line -> {
                Matcher matcher = pattern.matcher(line);

                String name = "";
                Integer flowRate = 0;
                List<String> connected = new ArrayList<>();

                if (matcher.find()) {
                    name = matcher.group(1).trim();
                    flowRate = Integer.parseInt(matcher.group(2));
                    if (matcher.group(3) != null) {
                        var list = Arrays.stream(matcher.group(3).split(","))
                                .map(String::trim).toList();
                        connected.addAll(list);
                    } else {
                        var list2 = List.of(matcher.group(4));
                        connected.addAll(list2);
                    }
                }
                return new Valve(name, flowRate, connected);
            })
            .toList();

        // @formatter:on

        valves.stream().forEach(System.out::println);

        HashMap<String, Integer> openedValves = new HashMap<>();
        AtomicReference<String> valveIndex = new AtomicReference<>();
        valveIndex.set("AA");
        Boolean moveFlag = false;

        // @formatter:off

        //Transform
        for (var i = 0; i < 30; i++) {
            System.out.println("== Minute " + (i + 1) + " ==");

            String temp = valveIndex.get();
            System.out.println(temp);
            Valve currentValve = valves.stream()
                    .filter(v -> v.name().equals(temp))
                    .findFirst()
                    .orElseThrow();

            var openedValvesCounter = openedValves.size();
            if (openedValvesCounter == 0) {
                System.out.println("No valves are open.");
            } else {
                //First line
                List<String> keys = new ArrayList<>(openedValves.keySet());
                var rateSum = openedValves.values().stream().mapToInt(d -> d).sum();
                if (keys.size() == 1) {
                    System.out.println("Valve " + keys.get(0) + " is open, releasing "
                            + rateSum + " pressure.");
                } else if (keys.size() == 2) {
                    System.out.println("Valve " + keys.get(0) + " and " + keys.get(1)
                            + " are open, releasing " + rateSum + " pressure.");
                } else {
                    System.out.println("Valve " + String.join(", ", keys)
                                + " are open, releasing " + rateSum + " pressure.");
                }

                if (!moveFlag) {
                    //Second line
                    String temp2 = valveIndex.get();
                    var result = valves
                        .stream()
                        .filter(v -> v.name().equals(temp2))
                        .flatMap(v -> v.connections().stream())
                        //.peek(System.out::println)
                        .filter(str -> !keys.contains(str))
                        .findFirst()
                        .orElseThrow();

                    valveIndex.set(result);
                    System.out.println("You move to valve " + valveIndex + ".");
                    moveFlag = true;
                    continue;
                }
            }

            //Move
            if (currentValve.rate() == 0) { // damaged or jammed or something
                String finalValveIndex = valveIndex.get();
                var result = valves
                    .stream()
                    .filter(v -> v.name().equals(finalValveIndex))
                    .flatMap(v -> v.connections().stream())
                    .filter(str -> {
                        List<String> keys = openedValves.keySet().stream().toList();
                        return keys.size() == keys.stream().filter(k -> k.equals(str)).count();
                    })
                    .findFirst()
                    .orElseThrow();

                valveIndex.set(result);
                System.out.println("You move to valve " + valveIndex + ".");
                continue;
            }

            //Open
            if (currentValve.rate() != 0) {
                System.out.println("You open valve " + valveIndex + ".");
                openedValves.put(valveIndex.get(), currentValve.rate());
                //moveFlag = false;
                continue;
            }
        }

        // @formatter:on

        //Sink
        return openedValves.values().stream().mapToLong(Long::valueOf).sum();
    }

    @Override
    public Long getPart2Result(String fileName) {
        return null;
    }
}
