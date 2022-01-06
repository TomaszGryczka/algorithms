package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

public class DisjointSet {
    private final List<Node> forest;

    public DisjointSet() {
        forest = new ArrayList<>();
    }

    public boolean isDisconnected() {
        Node root = findRoot(forest.get(0));

        for(int i = 1; i < forest.size(); i++) {
            if(!findRoot(forest.get(i)).equals(root)) {
                return true;
            }
        }

        return false;
    }

    public void addNode(String nodeName) {
        Node newNode = new Node(nodeName);

        if (!forest.contains(newNode)) {
            forest.add(newNode);
        } else {
           return;
        }
    }

    public boolean union(String firstNodeName, String secondNodeName) {
        //zabezpieczyć

        Node firstNode = findNode(firstNodeName);
        Node secondNode = findNode(secondNodeName);
        
        firstNode = findRoot(firstNode);
        secondNode = findRoot(secondNode);

        if (firstNode.equals(secondNode)) {
            return false;
        }

        if (firstNode.getSize() < secondNode.getSize()) {
            Node tmpNode = firstNode;
            firstNode = secondNode;
            secondNode = tmpNode;
        }

        secondNode.setParent(firstNode);
        firstNode.setSize(firstNode.getSize() + secondNode.getSize());

        return true;
    }

    private Node findNode(String nodeName) {
        for(int i = 0; i < forest.size(); i++) {
            if(forest.get(i).getName().equals(nodeName)) {
                return forest.get(i);
            }
        }

        throw new IllegalArgumentException("Node");
    }

    private Node findRoot(Node nodeToFind) {
        Node root = nodeToFind;

        while (root.getParent() != root) {
            root = root.getParent();
        }

        while (nodeToFind.getParent() != root) {
            Node parent = nodeToFind.getParent();
            nodeToFind.setParent(root);
            nodeToFind = parent;
        }

        return root;
    }
}
