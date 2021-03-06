package pl.edu.pw.ee;

import java.util.Comparator;

import pl.edu.pw.ee.services.PriorityQueue;

public class PriorityQueueListImplementation implements PriorityQueue {
    private Node root;
    private Comparator<Edge> cmp;

    public PriorityQueueListImplementation() {
        this.root = null;

        this.cmp = new WeightComparator();
    }

    private class Node {
        Edge edge;

        Node next;

        Node(Edge edge) {
            this.edge = edge;
        }

        Node(Edge edge, Node next) {
            this.edge = edge;
            this.next = next;
        }

        public boolean equals(Object obj) {
            if(obj == null) {
                return false;
            }
    
            if(this == obj) {
                return true;
            }
    
            if(getClass() != obj.getClass()) {
                return false;
            }
    
            if(!edge.getFirstVer().equals((((Node)obj).edge.getFirstVer()))) {
                return false;
            }

            if(!edge.getSecondVer().equals((((Node)obj).edge.getSecondVer()))) {
                return false;
            }

            if(edge.getWeight() != (((Node)obj).edge.getWeight())) {
                return false;
            }
    
            return true;
        }
    
        public int hashCode() {
            int result = 31;
    
            result += edge.getFirstVer().hashCode();
            result += edge.getSecondVer().hashCode();
            result += edge.getWeight();
    
            return result;
        }
    }

    private class WeightComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            if (o1.getWeight() < o2.getWeight()) {
                return 1;
            } else if (o1.getWeight() > o2.getWeight()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public void put(Edge edge) {
        if (edge == null) {
            throw new IllegalArgumentException("Cannot put null edge!");
        }

        if (root == null || cmp.compare(edge, root.edge) > 0) {
            root = new Node(edge, root);
        } else {
            Node node = new Node(edge);

            Node iter = root;

            while (iter.next != null && cmp.compare(iter.next.edge, edge) >= 0) {
                iter = iter.next;
            }

            if(iter != null && iter.equals(node)) {
                return;
            }

            node.next = iter.next;
            iter.next = node;
        }

    }

    @Override
    public Edge pop() {
        if (root == null) {
            return null;
        }

        Edge result = root.edge;
        root = root.next;

        return result;
    }

    public boolean isEmpty() {
        if(root == null) {
            return true;
        } else {
            return false;
        }
    }
}
