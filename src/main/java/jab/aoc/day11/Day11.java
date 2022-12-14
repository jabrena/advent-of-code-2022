package jab.aoc.day11;

import jab.aoc.Day;
import jab.aoc.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Day11 implements Day<Long> {

    private Monkey createMutableMonkey(List<String> monkeyLines) {
        String[] itemComponents = monkeyLines.get(1).substring(18).split(", ");
        var queue = new LinkedList<>(Arrays.stream(itemComponents).map(Long::parseLong).toList());

        String[] operationArr = monkeyLines.get(2).substring(23).split(" ");
        var operation = operationArr[0];
        var operand = operationArr[1].equals("old") ? 0 : Integer.parseInt(operationArr[1]);
        var divisor = Integer.parseInt(monkeyLines.get(3).substring(21));
        var trueMonkeyId = Integer.parseInt(monkeyLines.get(4).substring(29));
        var falseMonkeyId = Integer.parseInt(monkeyLines.get(5).substring(30));

        return new Monkey(queue, operation, operand, divisor, trueMonkeyId, falseMonkeyId, 0);
    }

    private List<Monkey> getMonkeyList(String fileName) {
        List<String> lines = Utils.readFileToList(fileName);

        var monkeys = new ArrayList<Monkey>();
        for (int i = 0; i < lines.size(); i += 7) {
            monkeys.add(createMutableMonkey(lines.subList(i, i + 6)));
        }
        return monkeys;
    }

    //TODO: Refactor to avoid Mutable Monkeys
    private List<Monkey> processMonkeys(List<Monkey> monkeys, Integer rounds, Boolean flag) {
        //The trick
        Integer lcm = monkeys.stream().map(Monkey::getDivisor).reduce(1, (a, b) -> a * b);

        for (int i = 0; i < rounds; i++) {
            for (Monkey monkey : monkeys) {
                while (!monkey.getItems().isEmpty()) {
                    long worryLevel = monkey.getItems().remove();
                    long operand = monkey.getOperand() > 0 ? monkey.getOperand() : worryLevel;
                    worryLevel =
                        switch (monkey.getOperation()) {
                            case "*" -> (worryLevel * operand) % lcm;
                            case "+" -> (worryLevel + operand) % lcm;
                            default -> throw new RuntimeException("Katakroker");
                        };
                    if (flag) {
                        worryLevel /= 3;
                    }
                    if (worryLevel % monkey.getDivisor() == 0) {
                        monkeys.get(monkey.trueMonkeyId).getItems().add(worryLevel);
                    } else {
                        monkeys.get(monkey.falseMonkeyId).getItems().add(worryLevel);
                    }
                    monkey.setItemsInspected(monkey.getItemsInspected() + 1);
                }
            }
        }

        return monkeys;
    }

    private Long calculateResult(List<Monkey> monkeyList) {
        return monkeyList
            .stream()
            .map(Monkey::getItemsInspected)
            .sorted(Comparator.reverseOrder())
            .limit(2)
            .reduce(1L, (a, b) -> a * b);
    }

    @Override
    public Long getPart1Result(String fileName) {
        //Source
        var monkeyList = getMonkeyList(fileName);

        //Transform
        var monkeyListProcessed = processMonkeys(monkeyList, 20, true);

        //Sink
        return calculateResult(monkeyListProcessed);
    }

    @Override
    public Long getPart2Result(String fileName) {
        //Source
        var monkeyList = getMonkeyList(fileName);

        //Transform
        var monkeyListProcessed = processMonkeys(monkeyList, 10000, false);

        //Sink
        return calculateResult(monkeyListProcessed);
    }
}
