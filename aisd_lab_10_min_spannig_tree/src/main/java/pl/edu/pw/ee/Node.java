package pl.edu.pw.ee;

public class Node {
    private final String name;

    private Node parent;

    private int size;

    public Node(String name) {
        this.name = name;
        this.parent = this;

        this.size = 1;
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

        if(!name.equals(((Node)obj).name)) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int result;

        result = name.hashCode();

        return result;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}