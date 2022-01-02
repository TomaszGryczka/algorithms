package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import pl.edu.pw.ee.services.MinSpanningTree;
import pl.edu.pw.ee.services.PriorityQueue;

public class PrimAlgorithm implements MinSpanningTree {

    private List<Vertex> graph;

    private PriorityQueue pQueue;

    public PrimAlgorithm() {
        graph = new ArrayList<>();
        pQueue = new PriorityQueueListImplementation();
    }

    public String findMST(String pathToFile) {
        String result = "";
        int edgeCounter = 0;

        readFile(pathToFile);

        validateGraphSize();

        List<Edge> edges = graph.get(0).getEdges();
        for (Edge edge : edges) {
            pQueue.put(edge);
        }

        graph.get(0).setIncluded(true);

        while (isDisconnected()) {
            Edge edge = pQueue.pop();

            if (edge == null) {
                break;
            }

            Vertex ver = new Vertex(edge.getSecondVer());
            int id = findVertexId(ver);

            if (!graph.get(id).isIncluded()) {
                edgeCounter++;

                result += edge.toString() + "|";

                edges = graph.get(id).getEdges();
                for (Edge x : edges) {
                    pQueue.put(x);
                }
                graph.get(id).setIncluded(true);
            }
        }

        if (edgeCounter != graph.size() - 1) {
            throw new IllegalArgumentException("Graph is not connected!");
        }

        result = result.substring(0, result.length() - 1);

        return result;
    }

    private boolean isDisconnected() {
        for (Vertex vertex : graph) {
            if (!vertex.isIncluded()) {
                return true;
            }
        }
        return false;
    }

    private void readFile(String pathToFile) {
        validateInputFile(pathToFile);

        try (FileReader fileReader = new FileReader(pathToFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);) {

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                if (!line.contains(" ")) {
                    throw new IllegalArgumentException("Incorrect data file!");
                }

                String[] result = line.split(" ");

                if (result.length == 3) {
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
        } else if (!graph.contains(firstVertex) && !graph.contains(secondVertex)) {
            graph.add(firstVertex);
            graph.get(graph.size() - 1).addEdge(connection);

            graph.add(secondVertex);
        } else if (!graph.contains(firstVertex) && graph.contains(secondVertex)) {
            id = findVertexId(secondVertex);
            graph.get(id).addEdge(connection);

            graph.add(firstVertex);
        } else {
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

    private void validateWeight(String str) {
        if (NumberUtils.isCreatable(str)) {
            if (!(Integer.parseInt(str) >= 0)) {
                throw new IllegalArgumentException("Weight has to be greater or equal to 0!");
            }
        } else {
            throw new IllegalArgumentException("Weight is not a number!");
        }

    }

    private void validateVertices(String firstName, String secondName) {
        if (firstName.equals(secondName)) {
            throw new IllegalArgumentException("Graph should not have loops!");
        }

        for (int i = 0; i < firstName.length(); i++) {
            validateChar(firstName.charAt(i));
        }

        for (int i = 0; i < secondName.length(); i++) {
            validateChar(secondName.charAt(i));
        }
    }

    private void validateChar(char character) {
        if (!((character >= 'A' && character <= 'Z')
                || (character >= 'a' && character <= 'z'))) {
            throw new IllegalArgumentException("Vertices names should contain only A-Z or a-z letters.");
        }
    }

    private void validateInputFile(String pathToFile) {
        File file = new File(pathToFile);

        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("File " + pathToFile + " does not exist!");
        }
    }

    private void validateGraphSize() {
        if (graph.size() == 0) {
            throw new IllegalArgumentException("Incorrect data file!");
        }
    }

    public static void main(String[] args) {
        PrimAlgorithm pa = new PrimAlgorithm();

        // System.out.println(pa.findMST("./small_data.txt"));

        System.out.println(pa.findMST("./small_data.txt"));
    }
}