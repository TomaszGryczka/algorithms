package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private final String name;

    private List<Edge> edges;

    private boolean isIncluded;

    public Vertex(String name) {
        edges = new ArrayList<>();

        this.name = name;

        this.isIncluded = false;
    }

    public void addEdge(Edge edge) {
        if(edges.contains(edge)) {
            throw new IllegalArgumentException("Edge has already been added!");
        }

        edges.add(edge);
    }

    /*

    DO USUNIECIA

    */

    /*public void print() {
        System.out.println(name);

        for(int i = 0; i < verts.size(); i++) {
            System.out.println(edges.get(i).getWeight() + " " + verts.get(i).name);
        }

        System.out.println();
    }*/

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
