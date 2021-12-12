package pl.edu.pw.ee;

public class Node implements Comparable<Node> { 
    private int freq;

    private Character character;

    private Node left, right;

    public Node(Node left, Node right, int freq, char character) {
        this.left = left;
        this.right = right;
        
        this.freq = freq;
        this.character = character;
    }

    public Node(Node left, Node right, int freq) {
        this.left = left;
        this.right = right;
        
        this.freq = freq;
        this.character = null;
    }

    @Override
    public int compareTo(Node o) {
        int result = -1;

        if(freq == o.freq) {
            result = 0;
        } else if (freq < o.freq) {
            result = -1;
        } else {
            result = 1;
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        } else if(((Node)o).character.equals(character)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String result = character + " " + Integer.toString(freq);

        return result;
    }

    public String toString(String word) {
        String result = character + "->" + word;

        return result;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public Character getCharacter() {
        return character;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}
