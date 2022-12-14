package jab.aoc.day11;

import java.util.Queue;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Monkey {

    Queue<Long> items;
    String operation;
    int operand;
    int divisor;
    int trueMonkeyId;
    int falseMonkeyId;
    long itemsInspected;
}
