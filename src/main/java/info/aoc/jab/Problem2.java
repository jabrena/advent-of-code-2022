package info.aoc.jab;

import java.util.List;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem2 {

    private static final Logger logger = LoggerFactory.getLogger(Problem2.class);

    private static String SEPARATOR = " ";

    /**
     * Player 1 options: A for Rock, B for Paper, and C for Scissors
     * Player 2 options: X for Rock, Y for Paper, and Z for Scissors
     * Shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors)
     * Outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won)
     *
     * Examples:
     * In the first round, your opponent will choose Rock (A), and you should choose Paper (Y).
     * This ends in a win for you with a score of 8 (2 because you chose Paper + 6 because you won).
     * In the second round, your opponent will choose Paper (B), and you should choose Rock (X).
     * This ends in a loss for you with a score of 1 (1 + 0).
     * The third round is a draw with both players choosing Scissors,
     * giving you a score of 3 + 3 = 6.
     *
     * Part 2:
     *
     * A Y
     * B X
     * C Z
     *
     * Anyway, the second column says how the round needs to end:
     * X means you need to lose,
     * Y means you need to end the round in a draw, and
     * Z means you need to win.
     *
     * Examples:
     * In the first round, your opponent will choose Rock (A),
     * and you need the round to end in a draw (Y), so you also choose Rock.
     * This gives you a score of 1 + 3 = 4.
     * In the second round, your opponent will choose Paper (B),
     * and you choose Rock so you lose (X) with
     * a score of 1 + 0 = 1.
     * In the third round, you will defeat your opponent's Scissors with Rock for
     * a score of 1 + 6 = 7.
     *
     * @param player1 player1
     * @param player2 player1
     */
    record Match(String player1, String player2) {
        private Integer getShapeScore() {
            if (player2().equals("X")) {
                return 1;
            } else if (player2().equals("Y")) {
                return 2;
            } else if (player2().equals("Z")) {
                return 3;
            } else {
                throw new RuntimeException("Option not identified");
            }
        }

        /**
         * Player 1 options: A for Rock, B for Paper, and C for Scissors
         * Player 2 options: X for Rock, Y for Paper, and Z for Scissors
         * Outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won)
         *
         * @return return
         */
        private Integer getRoundOutcome() {
            if (player1().equals("A") && player2().equals("X")) {
                //Draw
                return 3;
            } else if (player1().equals("A") && player2().equals("Y")) {
                //Win
                return 6;
            } else if (player1().equals("A") && player2().equals("Z")) {
                //Lost
                return 0;
            } else if (player1().equals("B") && player2().equals("X")) {
                //Lost
                return 0;
            } else if (player1().equals("B") && player2().equals("Y")) {
                //Draw
                return 3;
            } else if (player1().equals("B") && player2().equals("Z")) {
                //Win
                return 6;
            } else if (player1().equals("C") && player2().equals("X")) {
                //Win
                return 6;
            } else if (player1().equals("C") && player2().equals("Y")) {
                //Lost
                return 0;
            } else if (player1().equals("C") && player2().equals("Z")) {
                //Draw
                return 3;
            } else {
                throw new RuntimeException("Option not identified");
            }
        }

        public Integer getScore() {
            return getShapeScore() + getRoundOutcome();
        }

        /**
         * Anyway, the second column says how the round needs to end:
         * X means you need to lose,
         * Y means you need to end the round in a draw, and
         * Z means you need to win.
         *
         * Player 1 options: A for Rock, B for Paper, and C for Scissors
         * Player 2 options: X for Rock, Y for Paper, and Z for Scissors
         * Shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors)
         *
         * @return return
         */
        // @formatter:off
        private Integer getShapeScore2() {
            //Lost
            if (player2().matches("X") && player1().matches("A")) {
                //Scissor
                return 3;
            } else if (player2().matches("X") && player1().matches("B")) {
                //Rock
                return 1;
            } else if (player2().matches("X") && player1().matches("C")) {
                //Paper
                return 2;
            //Draw
            } else if (player2().matches("Y") && player1().matches("A")) {
                //Rock
                return 1 + 3;
            } else if (player2().matches("Y") && player1().matches("B")) {
                //Paper
                return 2 + 3;
            } else if (player2().matches("Y") && player1().matches("C")) {
                //Scissor
                return 3 + 3;
            //Win
            } else if (player2().matches("Z") && player1().matches("A")) {
                //Paper
                return 2 + 6;
            } else if (player2().matches("Z") && player1().matches("B")) {
                //Scissor
                return 3 + 6;
            } else if (player2().matches("Z") && player1().matches("C")) {
                //Rock
                return 1 + 6;
            } else {
                throw new RuntimeException("Option not identified");
            }
        }
        // @formatter:on

        public Integer getScore2() {
            return getShapeScore2(); // + getRoundOutcome2();
        }
    }

    public static void main(String[] args) {
        Function<List<String>, Integer> getResult1 = fileContent -> {
            return fileContent
                .stream()
                .map(line -> line.split(SEPARATOR))
                .map(arr -> new Match(arr[0], arr[1]))
                .map(Match::getScore)
                .reduce(0, Integer::sum);
        };

        Function<List<String>, Integer> getResult2 = fileContent -> {
            return fileContent
                .stream()
                .map(line -> line.split(SEPARATOR))
                .map(arr -> new Match(arr[0], arr[1]))
                .map(Match::getScore2)
                .reduce(0, Integer::sum);
        };

        logger.info("Day 2: Rock Paper Scissors");

        var content = Utils.loadFileToList("day2/problem2-input-sample.txt");
        var content2 = Utils.loadFileToList("day2/problem2-input.txt");

        logger.info("Sample");
        logger.info("Result: " + getResult1.apply(content));
        logger.info("Result: " + getResult2.apply(content));

        logger.info("Problem");
        logger.info("Result: " + getResult1.apply(content2));
        logger.info("Result: " + getResult2.apply(content2));
    }
}
