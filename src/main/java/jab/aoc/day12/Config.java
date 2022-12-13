package jab.aoc.day12;

import java.util.*;

public class Config {
    public int col;
    public int row;
    public int goalCol;
    public int goalRow;
    public char[][] mountain;

    public Config(int col, int row, int goalCol, int goalRow, char[][] mountain) {
        this.col = col;
        this.row = row;
        this.goalCol = goalCol;
        this.goalRow = goalRow;
        this.mountain = mountain;
    }

    public ArrayList<Config> getNeighbors() {
        ArrayList<Config> nbrs = new ArrayList<>();
        int here;
        try {
            here = mountain[row][col];
        } catch (ArrayIndexOutOfBoundsException e) {
            return nbrs;
        }
        try {
            if ((here - mountain[row - 1][col]) >= -1) {
                nbrs.add(new Config(col, row - 1, goalCol, goalRow, mountain));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if ((here - mountain[row + 1][col]) >= -1) {
                nbrs.add(new Config(col, row + 1, goalCol, goalRow, mountain));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if ((here - mountain[row][col - 1]) >= -1) {
                nbrs.add(new Config(col - 1, row, goalCol, goalRow, mountain));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if ((here - mountain[row][col + 1]) >= -1) {
                nbrs.add(new Config(col+1, row, goalCol, goalRow, mountain));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        return nbrs;
    }

    public boolean isSolution() {
        return row == goalRow && col == goalCol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Config config = (Config) o;
        return col == config.col && row == config.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }

    @Override
    public String toString() {
        return "Config{" +
                "col=" + col +
                ", row=" + row +
                ", goalCol=" + goalCol +
                ", goalRow=" + goalRow +
                '}';
    }
}
