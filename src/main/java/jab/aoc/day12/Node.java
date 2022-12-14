package jab.aoc.day12;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Node {

    private String name;

    private List<Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public List<Node> getShortestPath() {
        return this.shortestPath;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return this.adjacentNodes;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Node && ((Node) o).name.equals(this.name);
    }

    @Override
    public int hashCode() {
        String[] splitStr = name.split(",");
        return name.hashCode() + Integer.parseInt(splitStr[0]) + Integer.parseInt(splitStr[1]);
    }
}
