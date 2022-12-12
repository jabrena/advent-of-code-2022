package jab.aoc.day11;

import jab.aoc.Utils;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public Long runPart1(String fileName) {
        List<Monkey> monkeys = readInput(fileName);
        long monkeyBusiness = getMonkeyBusiness(monkeys, 20, true);
        return monkeyBusiness;
    }

    public Long runPart2(String fileName) {
        List<Monkey> monkeys = readInput(fileName);
        long monkeyBusiness = getMonkeyBusiness(monkeys, 10000, false);
        return monkeyBusiness;
    }

    private long getMonkeyBusiness(List<Monkey> monkeys, int rounds, boolean reduceWorryLevel) {
        int lcm = monkeys
                .stream()
                .map(Monkey::getDivisor)
                .reduce(1, (a, b) -> a * b);
        System.out.println(lcm);

        for (int i = 0; i < rounds; i++) {
            for (Monkey monkey : monkeys) {
                while (!monkey.getItems().isEmpty()) {
                    long worryLevel = monkey.getItems().remove();
                    long operand = monkey.getOperand() > 0 ? monkey.getOperand() : worryLevel;
                    worryLevel = switch (monkey.getOperation()) {
                        case "*" -> (worryLevel * operand) % lcm;
                        case "+" -> (worryLevel + operand) % lcm;
                        default -> throw new RuntimeException("Invalid operation: " + monkey.getOperation());
                    };
                    if (reduceWorryLevel) {
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

        return monkeys
                .stream()
                .map(Monkey::getItemsInspected)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce(1L, (a, b) -> a * b);
    }

    private List<Monkey> readInput(String fileName) {
        List<String> lines = Utils.readFileToList(fileName);

        var monkeys = new ArrayList<Monkey>();
        for (int i = 0; i < lines.size(); i += 7) {
            monkeys.add(readMonkey(lines.subList(i, i + 6)));
        }
        return monkeys;
    }

    private Monkey readMonkey(List<String> monkeyLines) {
        var monkey = new Monkey();

        String[] itemComponents = monkeyLines.get(1).substring(18).split(", ");
        monkey.setItems(new LinkedList<>(Arrays.stream(itemComponents)
                .map(Long::parseLong)
                .toList()));

        String[] operatingComponents = monkeyLines.get(2).substring(23).split(" ");
        monkey.setOperation(operatingComponents[0]);
        monkey.setOperand(operatingComponents[1].equals("old") ? 0 : Integer.parseInt(operatingComponents[1]));

        monkey.setDivisor(Integer.parseInt(monkeyLines.get(3).substring(21)));

        monkey.setTrueMonkeyId(Integer.parseInt(monkeyLines.get(4).substring(29)));
        monkey.setFalseMonkeyId(Integer.parseInt(monkeyLines.get(5).substring(30)));

        return monkey;
    }

    @Data
    private static class Monkey {
        Queue<Long> items;
        String operation;
        int operand;
        int divisor;
        int trueMonkeyId;
        int falseMonkeyId;
        long itemsInspected;
    }
}
