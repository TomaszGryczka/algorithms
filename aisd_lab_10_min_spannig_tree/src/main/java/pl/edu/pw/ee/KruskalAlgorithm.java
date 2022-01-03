package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.ee.services.PriorityQueue;

public class KruskalAlgorithm {
    private List<Vertex> graph;

    private PriorityQueue pQueue;

    public KruskalAlgorithm() {
        graph = new ArrayList<>();
        pQueue = new PriorityQueueListImplementation();
    }

    public String findMST(String pathToFile) {
        readFile(pathToFile);
        validateGraphSize();

        String result = "";

        for(int i = 0; i < graph.size(); i++) {
            List<Edge> edges = graph.get(i).getEdges();
            for(Edge edge : edges) {
                pQueue.put(edge);
            }
        }

        pQueue.print();



        return result;
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

        int numCounter = 0;

        final Vertex firstVertex = new Vertex(data[0]);
        final Vertex secondVertex = new Vertex(data[1]);

        final int weight = Integer.parseInt(data[2]);

        final Edge edge = new Edge(data[0], data[1], weight);
        final Edge invertedEdge = new Edge(data[1], data[0], weight);

        int id;

        if (graph.contains(firstVertex) && graph.contains(secondVertex)) {
            id = findVertexId(firstVertex);
            graph.get(id).addEdge(edge);

            id = findVertexId(secondVertex);
            graph.get(id).addEdge(invertedEdge);
        } else if (!graph.contains(firstVertex) && !graph.contains(secondVertex)) {
            graph.add(firstVertex);
            edge.setTreeNum(numCounter);
            graph.get(graph.size() - 1).addEdge(edge);

            

            graph.add(secondVertex);
            invertedEdge.setTreeInvertedNum(numCounter);
            graph.get(graph.size() - 1).addEdge(invertedEdge);

            numCounter += 2;
        } else if (!graph.contains(firstVertex) && graph.contains(secondVertex)) {
            graph.add(firstVertex);
            edge.setTreeNum(numCounter);
            graph.get(graph.size() - 1).addEdge(edge);

            id = findVertexId(secondVertex);
            invertedEdge.setTreeInvertedNum(numCounter);
            graph.get(id).addEdge(invertedEdge);

            numCounter += 2;
        } else {
            id = findVertexId(firstVertex);
            edge.setTreeNum(numCounter);
            graph.get(id).addEdge(edge);

            graph.add(secondVertex);
            invertedEdge.setTreeInvertedNum(numCounter);
            graph.get(graph.size() - 1).addEdge(invertedEdge);
            numCounter += 2;
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
        if (str.matches("-?\\d+")) {
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
