package jab.aoc.day5;

import static jab.aoc.Utils.GROUP_SEPARATOR_PATTERN;
import static jab.aoc.Utils.LINE_SEPARATOR_PATTERN;

import jab.aoc.Utils;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Solution for AOC 2022, Day 5
 * https://adventofcode.com/2022/day/5
 *
 */
public class Day5 {

    private static final Pattern SPACE_PATTERN = Pattern.compile(" ");

    // @formatter:off
    private record Command(Integer quantity, Integer from, Integer to) {
        public static Command fromString(String str) {
            var arr = SPACE_PATTERN.split(str);
            return new Command(
                    Integer.parseInt(arr[1]),
                    Integer.parseInt(arr[3]) - 1,
                    Integer.parseInt(arr[5]) - 1);
        }
    }

    private Function<String, List<Command>> recreateCommands = param -> Arrays
        .stream(LINE_SEPARATOR_PATTERN.split(param))
        .map(Command::fromString)
        .toList();
    // @formatter:on

    private static final Pattern pattern2 = Pattern.compile("(?<=\\G.{4})");

    private Function<String, List<Deque<String>>> recreateStacks = param -> {
        //Create LIFO Queue
        var numberOfStacks = Arrays
            .stream(LINE_SEPARATOR_PATTERN.split(param))
            .filter(str -> !str.contains("["))
            .map(str -> str.substring(str.length() - 1))
            .map(Integer::valueOf)
            .findFirst()
            .orElseThrow();

        List<Deque<String>> stacks = new ArrayList<>();
        for (int i = 0; i < numberOfStacks; i++) {
            stacks.add(new ArrayDeque<>());
        }

        //Populate LIFO Queue
        Arrays
            .stream(LINE_SEPARATOR_PATTERN.split(param))
            .filter(str -> str.contains("["))
            .forEach(str -> {
                String[] groups = pattern2.split(str);
                AtomicInteger counter = new AtomicInteger(0);
                Arrays
                    .stream(groups)
                    .forEach(part -> {
                        if (part.contains("[")) {
                            stacks.get(counter.get()).add(part);
                        }
                        counter.incrementAndGet();
                    });
            });

        return stacks;
    };

    private Function<List<Deque<String>>, String> getResult = param ->
        param
            .stream()
            .map(lifo -> lifo.pollFirst().replace("[", "").replace("]", "").trim())
            .reduce("", String::concat);

    private record Tuple(List<Deque<String>> stacks, List<Command> commands) {}

    private Function<String, Tuple> recreateStateFromFile = fileName -> {
        var fileLoaded = Utils.readFileToString(fileName);
        var parts = GROUP_SEPARATOR_PATTERN.split(fileLoaded);

        var stacks = recreateStacks.apply(parts[0]);
        var commands = recreateCommands.apply(parts[1]);

        return new Tuple(stacks, commands);
    };

    public String getPart1Result(String fileName) {
        Tuple state = recreateStateFromFile.apply(fileName);

        //Apply commands over the stacks
        state
            .commands()
            .forEach(command ->
                IntStream
                    .rangeClosed(1, command.quantity())
                    .forEach(i -> {
                        String element = state.stacks().get(command.from()).pollFirst();
                        state.stacks().get(command.to()).addFirst(element);
                    })
            );

        return getResult.apply(state.stacks());
    }

    public String getPart2Result(String fileName) {
        Tuple state = recreateStateFromFile.apply(fileName);

        //Apply commands over the stacks
        state
            .commands()
            .forEach(command -> {
                var elementList = IntStream
                    .rangeClosed(1, command.quantity())
                    .mapToObj(i -> state.stacks().get(command.from()).pollFirst())
                    .toList();

                //TODO Streams doesn´t offer the index
                for (int i = elementList.size(); i-- > 0;) {
                    state.stacks().get(command.to).addFirst(elementList.get(i));
                }
            });

        return getResult.apply(state.stacks());
    }
}
