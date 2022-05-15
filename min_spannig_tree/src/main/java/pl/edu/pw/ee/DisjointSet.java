package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

public class DisjointSet {
    private final List<Node> forest;

    public DisjointSet() {
        forest = new ArrayList<>();
    }
    
    public boolean isDisconnected() {
        validateForestSize();

        final Node root = findRoot(forest.get(0));

        for(int i = 1; i < forest.size(); i++) {
            if(!findRoot(forest.get(i)).equals(root)) {
                return true;
            }
        }

        return false;
    }

    public void addNode(String nodeName) {
        if(nodeName == null) {
            throw new IllegalArgumentException("Node name cannot be null!");
        }
        
        Node newNode = new Node(nodeName);

        if (!forest.contains(newNode)) {
            forest.add(newNode);
        } else {
           return;
        }
    }

    public boolean union(String firstNodeName, String secondNodeName) {
        validateForestSize();

        if(firstNodeName == null || secondNodeName == null) {
            throw new IllegalArgumentException("Name of nodes cannot be null!");
        }

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

        throw new IllegalArgumentException("Cannot find node of given name.");
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

    private void validateForestSize() {
        if(forest.size() < 2) {
            throw new IllegalArgumentException("Illegal size of forest!");
        }
    }
}
