package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {

    List<Vertex> graph;

    public PrimAlgorithm() {
        graph = new ArrayList<>();
    }

    private class WeightComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            if (o1.getWeight() > o2.getWeight()) {
                return 1;
            } else if (o1.getWeight() < o2.getWeight()) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    public String findMST(String pathToFile) {
        boolean loop = true;

        String result = "";

        readFile(pathToFile);

        if(graph.size() == 0) {
            throw new IllegalArgumentException("Incorrect data file!");
        }

        /*
         * for (int i = 0; i < graph.size(); i++) {
         * graph.get(i).print();
         * }
         */

        Comparator<Edge> cmp = new WeightComparator();

        List<Edge> connections = graph.get(0).getEdges();

        graph.get(0).setIncluded(true);

        connections.sort(cmp);

        /*for (int m = 0; m < connections.size(); m++) {
            System.out.print(connections.get(m).toString() + " ");
            
        }
        System.out.println();*/

        while (isUndone()) {
            loop = true;

            for (int i = 0; i < connections.size(); i++) {
                for (int j = 0; j < graph.size(); j++) {
                    if (connections.get(i).getSecondVer().equals(graph.get(j).getName())) {
                        if (!graph.get(j).isIncluded()) {
                            result += connections.remove(i).toString() + "|";

                            List<Edge> newConnections = graph.get(j).getEdges();

                            for (int m = 0; m < newConnections.size(); m++) {
                                connections.add(newConnections.get(m));
                            }

                            connections.sort(cmp);

                            /*for (int m = 0; m < connections.size(); m++) {
                                System.out.print(connections.get(m).toString() + " ");
                                
                            }
                            System.out.println();*/

                            i = 0;

                            graph.get(j).setIncluded(true);

                            loop = false;
                        }
                    }
                }
            }

            if (loop == true) {
                throw new IllegalArgumentException("Graph is not connected!");
            }
        }

        result = result.substring(0, result.length() - 1);

        // System.out.println(result);

        return result;
    }

    private boolean isUndone() {

        for (int i = 0; i < graph.size(); i++) {
            if (!graph.get(i).isIncluded()) {
                return true;
            }
        }

        return false;
    }

    private void readFile(String pathToFile) {
        try (FileReader fileReader = new FileReader(pathToFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);) {

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                if (!line.contains(" ")) {
                    throw new IllegalArgumentException("Incorrect data file!");
                }

                String[] result = line.split(" ");

                //System.out.println(result.length);

                if(result.length == 3) {
                    addEdge(result);
                } else {
                    throw new IllegalArgumentException("Incorrect data file!");
                }
                

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addEdge(String[] data) {
        validateVertices(data[0], data[1]);
        validateWeight(data[2]);

        Vertex firstVertex = new Vertex(data[0]);
        Vertex secondVertex = new Vertex(data[1]);
        
        int weight = Integer.parseInt(data[2]);

        Edge connection = new Edge(data[0], data[1], weight);

        int id;

        if (graph.contains(firstVertex) && graph.contains(secondVertex)) {
            id = findVertexId(firstVertex);
            graph.get(id).addEdge(connection);

            id = findVertexId(secondVertex);
        } else if (!graph.contains(firstVertex) && !graph.contains(secondVertex)) {
            graph.add(firstVertex);
            graph.get(graph.size() - 1).addEdge(connection);

            graph.add(secondVertex);
        } else if (!graph.contains(firstVertex) && graph.contains(secondVertex)) {
            id = findVertexId(secondVertex);
            graph.get(id).addEdge(connection);

            graph.add(firstVertex);
        } else if (graph.contains(firstVertex) && !graph.contains(secondVertex)) {
            id = findVertexId(firstVertex);
            graph.get(id).addEdge(connection);

            graph.add(secondVertex);
        }
    }

    private int findVertexId(Vertex vertex) {
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).equals(vertex)) {
                return i;
            }
        }

        throw new IllegalArgumentException("Grpah does not have vertex of this name!");
    }

    private boolean validateWeight(String str) {
        try {
            if(Integer.parseInt(str) < 0) {
                throw new IllegalArgumentException("Weight cannot be smaller than 0!");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Weight should be a number greater or equal to 0!");
        }

        if (Integer.parseInt(str) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private void validateVertices(String firstName, String secondName) {
        if(firstName.equals(secondName)) {
            throw new IllegalArgumentException("Graph should not have loops!");
        }

        for (int i = 0; i < firstName.length(); i++) {
            if (!((firstName.charAt(i) >= 'A' && firstName.charAt(i) <= 'Z')
                    || (firstName.charAt(i) >= 'a' && firstName.charAt(i) <= 'z'))) {
                throw new IllegalArgumentException("Vertices names should contain only A-Z or a-z letters.");
            }
        }

        for (int i = 0; i < secondName.length(); i++) {
            if (!((secondName.charAt(i) >= 'A' && secondName.charAt(i) <= 'Z')
                    || (secondName.charAt(i) >= 'a' && secondName.charAt(i) <= 'z'))) {
                throw new IllegalArgumentException("Vertices names should contain only A-Z or a-z letters.");
            }
        }
    }

    public static void main(String[] args) {
        PrimAlgorithm pa = new PrimAlgorithm();

        System.out.println(pa.findMST("./small_data.txt"));
    }
}
