package com.mvppoa.adidas.domain;

import java.util.*;

public class NodeHelp {

    private String name;

    private List<NodeHelp> shortestPath;
    private Long distance;

    public NodeHelp(String name) {
        this.name = name;
        this.distance = Long.MAX_VALUE;
        this.shortestPath = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeHelp node = (NodeHelp) o;
        return Objects.equals(name, node.name) &&
            Objects.equals(shortestPath, node.shortestPath) &&
            Objects.equals(distance, node.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "NodeHelp{" +
            "name='" + name + '\'' +
            ", shortestPath=" + shortestPath +
            ", distance=" + distance +
            '}';
    }
}
