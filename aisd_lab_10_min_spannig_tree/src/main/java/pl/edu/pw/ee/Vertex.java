package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private final String name;

    private List<Edge> edges;

    private boolean isIncluded;

    public Vertex(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Name cannot be null!");
        }

        edges = new ArrayList<>();

        this.name = name;

        this.isIncluded = false;
    }

    public void addEdge(Edge edge) {
        if(edge == null) {
            throw new IllegalArgumentException("Edge cannot be null!");
        }

        edges.add(edge);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        if (!name.equals(((Vertex) obj).name)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;

        result = name.hashCode();

        return result;
    }

    public boolean isIncluded() {
        return isIncluded;
    }

    public void setIncluded(boolean isIncluded) {
        this.isIncluded = isIncluded;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}