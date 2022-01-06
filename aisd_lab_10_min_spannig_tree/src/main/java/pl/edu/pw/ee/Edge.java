package pl.edu.pw.ee;

public class Edge {
    private final String firstVer;
    private final String secondVer;
    private final int weight;

    private int firstVerTreeNum;
    private int secondVerTreeNum;

    public Edge(String firstVer, String secondVer, int weight) {
        if (firstVer == null || secondVer == null) {
            throw new IllegalArgumentException("Names of vertices cannot be null!");
        }

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

    public int getFirstVerTreeNum() {
        return firstVerTreeNum;
    }

    public void setFirstVerTreeNum(int number) {
        firstVerTreeNum = number;
    }

    public int getSecondVerTreeNum() {
        return secondVerTreeNum;
    }

    public void setSecondVerTreeNum(int number) {
        secondVerTreeNum = number;
    }

    public void setTreeNum(int number) {
        firstVerTreeNum = number;
        secondVerTreeNum = number + 1;
    }

    public void setTreeInvertedNum(int number) {
        firstVerTreeNum = number + 1;
        secondVerTreeNum = number;
    }
}
