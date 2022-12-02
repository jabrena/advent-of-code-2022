package jab.aoc.day2;

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
 * @param column1 column1
 * @param column2 column1
 */
record Game1(Column1 column1, Column2 column2) {
    private Integer getShapeScore() {
        if (column2() == Column2.ROCK) {
            return 1;
        } else if (column2() == Column2.PAPER) {
            return 2;
        } else if (column2() == Column2.SCISSOR) {
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
        if (column1() == Column1.ROCK && column2() == Column2.ROCK) {
            //Draw
            return 3;
        } else if (column1() == Column1.ROCK && column2() == Column2.PAPER) {
            //Win
            return 6;
        } else if (column1() == Column1.ROCK && column2() == Column2.SCISSOR) {
            //Lost
            return 0;
        } else if (column1() == Column1.PAPER && column2() == Column2.ROCK) {
            //Lost
            return 0;
        } else if (column1() == Column1.PAPER && column2() == Column2.PAPER) {
            //Draw
            return 3;
        } else if (column1() == Column1.PAPER && column2() == Column2.SCISSOR) {
            //Win
            return 6;
        } else if (column1() == Column1.SCISSOR && column2() == Column2.ROCK) {
            //Win
            return 6;
        } else if (column1() == Column1.SCISSOR && column2() == Column2.PAPER) {
            //Lost
            return 0;
        } else if (column1() == Column1.SCISSOR && column2() == Column2.SCISSOR) {
            //Draw
            return 3;
        } else {
            throw new RuntimeException("Option not identified");
        }
    }

    public Integer getScore() {
        return getShapeScore() + getRoundOutcome();
    }
}
