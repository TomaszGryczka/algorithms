package pl.edu.pw.ee;

public class Edge {
    private final String firstVer;
    private final String secondVer;
    private final int weight;

    public Edge(String firstVer, String secondVer, int weight) {
        this.firstVer = firstVer;
        this.secondVer = secondVer;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return firstVer + "_" + weight + "_" + secondVer;
    }

    public String getFirstVer() {
        return firstVer;
    }

    public String getSecondVer() {
        return secondVer;
    }

    public int getWeight() {
        return weight;
    }
}
