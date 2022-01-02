package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private String name;

    private List<Connection> connections;

    private List<Vertex> verts;
    private List<Edge> edges;

    private boolean isIncluded;

    public Vertex(String name) {
        verts = new ArrayList<>();
        edges = new ArrayList<>();

        connections = new ArrayList<>();

        this.name = name;

        isIncluded = false;
    }

    public void addEdge(Vertex vertex, Edge edge) {
        if(verts.contains(vertex) && edges.contains(edge)) {
            throw new IllegalArgumentException("Edge has already been added!");
        }
        
        verts.add(vertex);
        edges.add(edge);

        Connection con = new Connection(name, vertex.name, edge.getWeight());

        connections.add(con);
    }

    /*

    DO USUNIECIA

    */

    public void print() {
        System.out.println(name);

        for(int i = 0; i < verts.size(); i++) {
            System.out.println(edges.get(i).getWeight() + " " + verts.get(i).name);
        }

        System.out.println();
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

        if (name.compareTo(((Vertex) obj).name) != 0) {
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

    public String getName() {
        return name;
    }

    public boolean isIncluded() {
        return isIncluded;
    }

    public void setIncluded(boolean isIncluded) {
        this.isIncluded = isIncluded;
    }

    public List<Connection> getConnections() {
        return connections;
    }
}
