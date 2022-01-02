package pl.edu.pw.ee;

public class Edge implements Comparable<Edge> {
    private final int weight;
    private boolean isIncluded;

    public Edge(int weight) {
        this.weight = weight;
        isIncluded = false;
    }

    @Override
    public int compareTo(Edge otherEdge) {
        if (otherEdge == null) {
            throw new IllegalArgumentException("Edge cannot be null!");
        } else if (weight < otherEdge.weight) {
            return -1;
        } else if (weight > otherEdge.weight) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        if (this.weight != ((Edge) obj).weight) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int primeNum = 31;
        int result = 1;

        result = primeNum * result + weight;

        return result;
    }

    /*

    DO USUNIECIA

    */

    public int getWeight() {
        return weight;
    }

    public void setIncluded(boolean isIncluded) {
        this.isIncluded = isIncluded;
    }

    public boolean isIncluded() {
        return isIncluded;
    }
}
