package pl.edu.pw.ee.services;

import pl.edu.pw.ee.Edge;

public interface PriorityQueue {
    public void put(Edge edge);
    public Edge pop();
}