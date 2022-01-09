package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import pl.edu.pw.ee.services.MinSpanningTree;
import pl.edu.pw.ee.services.PriorityQueue;

public class KruskalAlgorithm implements MinSpanningTree {

    private final PriorityQueue pQueue;

    public KruskalAlgorithm() {
        pQueue = new PriorityQueueListImplementation();
    }

    public String findMST(String pathToFile) {
        readFile(pathToFile);

        String result = "";

        Edge poppedEdge;

        final DisjointSet disjointSet = new DisjointSet();

        while ((poppedEdge = pQueue.pop()) != null) {
            final String firstVer = poppedEdge.getFirstVer();
            final String secondVer = poppedEdge.getSecondVer();
            final int edgeWeight = poppedEdge.getWeight();

            disjointSet.addNode(firstVer);
            disjointSet.addNode(secondVer);

            boolean edgeAdded = disjointSet.union(firstVer, secondVer);

            if (edgeAdded) {
                result += firstVer + "_" + edgeWeight + "_" + secondVer + "|";
            }
        }

        if (disjointSet.isDisconnected()) {
            throw new IllegalArgumentException("Graph is disconnected!");
        }

        result = result.substring(0, result.length() - 1);

        return result;
    }

    private void readFile(String pathToFile) {
        validateInputFile(pathToFile);

        try (FileReader fileReader = new FileReader(pathToFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);) {

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                if (!line.contains(" ")) {
                    throw new IllegalArgumentException("Incorrect data file in line: \"" + line + "\"");
                }

                String[] result = line.split(" ");

                if (result.length == 3) {
                    addEdge(result);
                } else {
                    throw new IllegalArgumentException("Incorrect data file in line: \"" + line + "\"");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (pQueue.isEmpty()) {
            throw new IllegalArgumentException("File: " + pathToFile + " cannot be empty!");
        }
    }

    private void addEdge(String[] data) {
        String firstVer = data[0];
        String secondVer = data[1];

        validateWeight(data[2]);

        final int weight = Integer.parseInt(data[2]);

        validateVertices(firstVer, secondVer);

        if (firstVer.compareTo(secondVer) > 0) {
            String tmpVer = firstVer;
            firstVer = secondVer;
            secondVer = tmpVer;
        }

        Edge edgeToPut = new Edge(firstVer, secondVer, weight);

        pQueue.put(edgeToPut);
    }

    private void validateWeight(String weight) {
        if (weight.matches("-?\\d+")) {
            if (!(Integer.parseInt(weight) >= 0)) {
                throw new IllegalArgumentException("Weight: \"" + weight + "\" is not greater or equal to 0!");
            }
        } else {
            throw new IllegalArgumentException("Weight: \"" + weight + "\" is not a integer number!");
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
        if (pathToFile == null) {
            throw new IllegalArgumentException("Path to file cannot be null!");
        }

        File file = new File(pathToFile);

        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("File " + "\"" + pathToFile + "\"" + " does not exist!");
        }
    }
}
