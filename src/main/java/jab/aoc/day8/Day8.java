package jab.aoc.day8;

import jab.aoc.Day;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day8 implements Day<Integer> {

    enum Edge {
        LEFT,
        RIGHT,
        TOP,
        BOTTON,
    }

    private boolean isVisible(Integer[][] grid, int i, int j, Edge edge) {
        int treeHeight = grid[i][j];
        boolean treeVisible = true;

        int traverseI = 0;
        int traverseJ = 0;
        if (edge == Edge.LEFT) {
            traverseI = 0;
            traverseJ = -1;
        } else if (edge == Edge.RIGHT) {
            traverseI = 0;
            traverseJ = 1;
        } else if (edge == Edge.TOP) {
            traverseI = -1;
            traverseJ = 0;
        } else {
            traverseI = 1;
            traverseJ = 0;
        }
        i = i + traverseI;
        j = j + traverseJ;

        int[] gridSize = new int[] { grid.length, grid[0].length };

        while (i >= 0 && i < gridSize[0] && j >= 0 && j < gridSize[1]) {
            if (grid[i][j] >= treeHeight) {
                treeVisible = false;
                break;
            }
            i = i + traverseI;
            j = j + traverseJ;
        }

        return treeVisible;
    }

    private boolean isVisibleFromOutside(Integer[][] grid, Integer i, Integer j) {
        return (
            isVisible(grid, i, j, Edge.LEFT) ||
            isVisible(grid, i, j, Edge.RIGHT) ||
            isVisible(grid, i, j, Edge.TOP) ||
            isVisible(grid, i, j, Edge.BOTTON)
        );
    }

    @Override
    public Integer getPart1Result(String fileName) {
        Integer[][] grid = MatrixHelper.getMatrix(fileName);

        Integer visibleTrees = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (isVisibleFromOutside(grid, x, y)) {
                    visibleTrees++;
                }
            }
        }
        return visibleTrees;
    }

    private int viewingDistance(int i, int j, Edge edge, Integer[][] grid) {
        int treeHeight = grid[i][j];
        int distance = 0;

        int traverseI = 0;
        int traverseJ = 0;
        if (edge == Edge.LEFT) {
            traverseI = 0;
            traverseJ = -1;
        } else if (edge == Edge.RIGHT) {
            traverseI = 0;
            traverseJ = 1;
        } else if (edge == Edge.TOP) {
            traverseI = -1;
            traverseJ = 0;
        } else {
            traverseI = 1;
            traverseJ = 0;
        }

        i = i + traverseI;
        j = j + traverseJ;
        int[] gridSize = new int[] { grid.length, grid[0].length };
        while (i >= 0 && i < gridSize[0] && j >= 0 && j < gridSize[1]) {
            if (grid[i][j] <= treeHeight) {
                distance = distance + 1;
            }
            if (grid[i][j] >= treeHeight) {
                break;
            }
            i = i + traverseI;
            j = j + traverseJ;
        }

        return distance;
    }

    private int scenicScore(int i, int j, Integer[][] grid) {
        return (
            viewingDistance(i, j, Edge.LEFT, grid) *
            viewingDistance(i, j, Edge.RIGHT, grid) *
            viewingDistance(i, j, Edge.TOP, grid) *
            viewingDistance(i, j, Edge.BOTTON, grid)
        );
    }

    @Override
    public Integer getPart2Result(String fileName) {
        Integer[][] grid = MatrixHelper.getMatrix(fileName);

        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                scores.add(scenicScore(i, j, grid));
            }
        }

        return Collections.max(scores);
    }
}
