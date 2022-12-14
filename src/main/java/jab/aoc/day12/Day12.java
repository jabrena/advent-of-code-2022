package jab.aoc.day12;

import jab.aoc.Day;
import jab.aoc.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day12 implements Day<Long> {

    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        // @formatter:off

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacencyPair :
                    currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }

        // @formatter:on

        return graph;
    }

    Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    @Override
    public Long getPart1Result(String fileName) {
        List<List<Integer>> matrix = new ArrayList<>();
        int[] start = new int[2];
        int[] end = new int[2];

        //Create Matrix
        try {
            BufferedReader br = Utils.readFileToBufferedReader(fileName);
            int i = 0;
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                List<Integer> matrixRow = new ArrayList<>();
                for (int j = 0; j < currentLine.length(); j++) {
                    switch (currentLine.charAt(j)) {
                        case 'S':
                            start[0] = i;
                            start[1] = j;
                            matrixRow.add(0);
                            break;
                        case 'E':
                            end[0] = i;
                            end[1] = j;
                            matrixRow.add(25);
                            break;
                        default:
                            matrixRow.add(alphabet.indexOf(currentLine.charAt(j)));
                            break;
                    }
                }
                matrix.add(matrixRow);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (var y = 0; y < matrix.size(); y++) {
            for (var x = 0; x < matrix.get(0).size(); x++) {
                System.out.print(matrix.get(y).get(x) + " ");
            }
            System.out.println();
        }

        // transform matrix into a graph
        Graph graph = new Graph();
        Set<Node> nodes = new HashSet<>();
        List<List<Node>> nodesMatrix = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            List<Node> nodesMatrixRow = new ArrayList<>();
            for (int j = 0; j < matrix.get(0).size(); j++) {
                Node node = new Node(i + "," + j);
                nodesMatrixRow.add(node);
            }
            nodesMatrix.add(nodesMatrixRow);
        }

        // @formatter:off

        // assemble adjacencies
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(0).size(); j++) {
                Node node = nodesMatrix.get(i).get(j);
                if (i > 0 && matrix.get(i - 1).get(j) - matrix.get(i).get(j) <= 1) {
                    node.getAdjacentNodes().put(nodesMatrix.get(i - 1).get(j), 1);
                }
                if (j > 0 && matrix.get(i).get(j - 1) - matrix.get(i).get(j) <= 1) {
                    node.getAdjacentNodes().put(nodesMatrix.get(i).get(j - 1), 1);
                }
                if (i < matrix.size() - 1 && matrix.get(i + 1).get(j) - matrix.get(i).get(j) <= 1) {
                    node.getAdjacentNodes().put(nodesMatrix.get(i + 1).get(j), 1);
                }
                if (j < matrix.get(0).size() - 1
                        && matrix.get(i).get(j + 1) - matrix.get(i).get(j) <= 1) {
                    node.getAdjacentNodes().put(nodesMatrix.get(i).get(j + 1), 1);
                }
                nodes.add(node);
            }
        }
        graph.setNodes(nodes);

        Graph dijkstra =
                calculateShortestPathFromSource(graph, nodesMatrix.get(start[0]).get(start[1]));
        Node endNode = new Node("");
        for (Node n : dijkstra.getNodes()) {
            if (n.getName().equals(end[0] + "," + end[1])) {
                endNode = n;
            }
        }

        // @formatter:on

        return Long.valueOf(endNode.getDistance());
    }

    @Override
    public Long getPart2Result(String fileName) {
        List<List<Integer>> matrix = new ArrayList<>();
        List<int[]> starts = new ArrayList<>();
        int[] end = new int[2];

        try {
            BufferedReader br = Utils.readFileToBufferedReader(fileName);
            int i = 0;
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                List<Integer> matrixRow = new ArrayList<>();
                for (int j = 0; j < currentLine.length(); j++) {
                    switch (currentLine.charAt(j)) {
                        case 'a':
                            starts.add(new int[] { i, j });
                            matrixRow.add(0);
                            break;
                        case 'E':
                            end[0] = i;
                            end[1] = j;
                            matrixRow.add(25);
                            break;
                        default:
                            matrixRow.add(alphabet.indexOf(currentLine.charAt(j)));
                            break;
                    }
                }
                matrix.add(matrixRow);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // transform matrix into a graph
        Graph graph = new Graph();
        Set<Node> nodes = new HashSet<>();
        List<List<Node>> nodesMatrix = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            List<Node> nodesMatrixRow = new ArrayList<>();
            for (int j = 0; j < matrix.get(0).size(); j++) {
                Node node = new Node(i + "," + j);
                nodesMatrixRow.add(node);
            }
            nodesMatrix.add(nodesMatrixRow);
        }

        // @formatter:off

        // assemble adjacencies
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(0).size(); j++) {
                Node node = nodesMatrix.get(i).get(j);
                if (i > 0 && matrix.get(i - 1).get(j) - matrix.get(i).get(j) <= 1) {
                    node.getAdjacentNodes().put(nodesMatrix.get(i - 1).get(j), 1);
                }
                if (j > 0 && matrix.get(i).get(j - 1) - matrix.get(i).get(j) <= 1) {
                    node.getAdjacentNodes().put(nodesMatrix.get(i).get(j - 1), 1);
                }
                if (i < matrix.size() - 1 && matrix.get(i + 1).get(j) - matrix.get(i).get(j) <= 1) {
                    node.getAdjacentNodes().put(nodesMatrix.get(i + 1).get(j), 1);
                }
                if (j < matrix.get(0).size() - 1
                        && matrix.get(i).get(j + 1) - matrix.get(i).get(j) <= 1) {
                    node.getAdjacentNodes().put(nodesMatrix.get(i).get(j + 1), 1);
                }
                nodes.add(node);
            }
        }
        graph.setNodes(nodes);

        int shortestDistance = Integer.MAX_VALUE;
        for (int[] start : starts) {
            Graph dijkstra =
                    calculateShortestPathFromSource(graph, nodesMatrix.get(start[0]).get(start[1]));
            Node endNode = new Node("");
            for (Node n : dijkstra.getNodes()) {
                if (n.getName().equals(end[0] + "," + end[1])) {
                    endNode = n;
                }
            }
            int dist = endNode.getDistance();
            if (dist < shortestDistance) {
                shortestDistance = dist;
            }
        }

        // @formatter:on

        return Long.valueOf(shortestDistance);
    }
}
