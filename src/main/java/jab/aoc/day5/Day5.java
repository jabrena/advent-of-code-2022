package jab.aoc.day5;

import static jab.aoc.Utils.GROUP_SEPARATOR;
import static jab.aoc.Utils.LINE_SEPARATOR;

import jab.aoc.Utils;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Day5 {

    // @formatter:off
    private record Command(Integer quantity, Integer from, Integer to) {
        public static Command fromArray(String[] arr) {
            return new Command(
                    Integer.parseInt(arr[1]),
                    Integer.parseInt(arr[3]) - 1,
                    Integer.parseInt(arr[5]) - 1);
        }
    }

    private Function<String, List<Command>> recreateCommands = param -> {
        return Arrays
            .stream(param.split(LINE_SEPARATOR))
            .map(str -> str.split(" "))
            .map(Command::fromArray)
            .toList();
    };
    // @formatter:on

    private Function<String, List<Deque<String>>> recreateStacks = param -> {
        //Create LIFO Queue
        var numberOfStacks = Arrays
            .stream(param.split(LINE_SEPARATOR))
            .filter(str -> !str.contains("["))
            .map(str -> str.substring(str.length() - 1))
            .map(Integer::valueOf)
            .findFirst()
            .orElseThrow();

        List<Deque<String>> stacks = new ArrayList<>();
        for (int i = 0; i < numberOfStacks; i++) {
            stacks.add(new ArrayDeque<>());
        }

        //Populate Create LIFO Queue
        Arrays
            .stream(param.split(LINE_SEPARATOR))
            .filter(str -> str.contains("["))
            .forEach(str -> {
                String[] groups = str.split("(?<=\\G.{" + 4 + "})");
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
        var parts = fileLoaded.split(GROUP_SEPARATOR);

        var stacks = recreateStacks.apply(parts[0]);
        var commands = recreateCommands.apply(parts[1]);

        return new Tuple(stacks, commands);
    };

    public String getPart1Result(String fileName) {
        Tuple state = recreateStateFromFile.apply(fileName);

        //Apply commands over the stacks
        state
            .commands()
            .stream()
            .forEach(command ->
                IntStream
                    .rangeClosed(1, command.quantity())
                    .forEach(i -> {
                        String element = state.stacks().get(command.from()).pollFirst();
                        state.stacks().get(command.to).addFirst(element);
                    })
            );

        return getResult.apply(state.stacks());
    }

    public String getPart2Result(String fileName) {
        Tuple state = recreateStateFromFile.apply(fileName);

        //Apply commands over the stacks
        state
            .commands()
            .stream()
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
