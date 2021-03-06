package pl.edu.pw.ee;

import static pl.edu.pw.ee.Color.BLACK;
import static pl.edu.pw.ee.Color.RED;

public class RedBlackTree<K extends Comparable<K>, V> {

    private Node<K, V> root;

    private int counter = 1;

    public int getCounter() {
        return counter;
    }

    public V get(K key) {
        validateKey(key);
        Node<K, V> node = root;

        V result = null;

        while (node != null) {

            if (shouldCheckOnTheLeft(key, node)) {
                node = node.getLeft();

            } else if (shouldCheckOnTheRight(key, node)) {
                node = node.getRight();

            } else {
                result = node.getValue();
                break;
            }
        }
        return result;
    }

    public void put(K key, V value) {
        counter = 0;

        validateParams(key, value);

        counter++;
        root = put(root, key, value);

        root.setColor(BLACK);
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private boolean shouldCheckOnTheLeft(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void validateParams(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Input params (key, value) cannot be null.");
        }
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value);
        }

        if (isKeyBiggerThanNode(key, node)) {

            counter++;
            putOnTheRight(node, key, value);

        } else if (isKeySmallerThanNode(key, node)) {

            counter++;
            putOnTheLeft(node, key, value);

        } else {
            node.setValue(value);
        }

        node = reorganizeTree(node);

        return node;
    }

    private boolean isKeyBiggerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(Node<K, V> node, K key, V value) {
        Node<K, V> rightChild = put(node.getRight(), key, value);
        node.setRight(rightChild);
    }

    private boolean isKeySmallerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(Node<K, V> node, K key, V value) {
        Node<K, V> leftChild = put(node.getLeft(), key, value);
        node.setLeft(leftChild);
    }

    private Node<K, V> reorganizeTree(Node<K, V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private Node<K, V> rotateLeftIfNeeded(Node<K, V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private Node<K, V> rotateRightIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> head = node.getLeft();
        node.setLeft(head.getRight());
        head.setRight(node);

        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private void changeColorsIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(Node<K, V> node) {
        node.setColor(RED);
        node.getLeft().setColor(BLACK);
        node.getRight().setColor(BLACK);
    }

    private boolean isBlack(Node<K, V> node) {
        return !isRed(node);
    }

    private boolean isRed(Node<K, V> node) {
        return node == null
                ? false
                : node.isRed();
    }

    public void deleteMax() {
        if (root == null) {
            throw new IllegalStateException("Red Black Tree is empty!");
        }

        root = deleteMax(root);

        if (root != null) {
            root.setColor(BLACK);
        }
    }

    private Node<K, V> deleteMax(Node<K, V> node) {
        if (node.getLeft() != null) {
            if (node.getLeft().isRed()) {
                node = rotateRight(node);
            }
        }

        if (node.getRight() == null) {
            return null;
        }

        if (!node.getRight().isRed() && (node.getRight().getLeft() == null || !node.getRight().getLeft().isRed())) {
            reverseColors(node);

            if (node.getLeft() != null && node.getLeft().getLeft() != null && node.getLeft().getLeft().isRed()) {
                node = rotateRight(node);
                reverseColors(node);
            }
        }

        node.setRight(deleteMax(node.getRight()));

        if (node.getRight() != null) {
            if (node.getRight().isRed()) {
                node = rotateLeft(node);
            }
        }

        if (node.getLeft() != null) {
            if (node.getLeft().isRed() && node.getLeft().getLeft() != null && node.getLeft().getLeft().isRed()) {
                node = rotateRight(node);
            }
        }

        if (node.getLeft() != null && node.getRight() != null) {
            if (node.getLeft().isRed() && node.getRight().isRed()) {
                changeColors(node);
            }
        }

        return node;
    }

    private void reverseColors(Node<K, V> node) {
        if (node != null) {
            if (node.isRed()) {
                node.setColor(BLACK);
            } else {
                node.setColor(RED);
            }
        }

        if (node.getLeft() != null) {
            if (node.getLeft().isRed()) {
                node.getLeft().setColor(BLACK);
            } else {
                node.getLeft().setColor(RED);
            }
        }

        if (node.getRight() != null) {
            if (node.getRight().isRed()) {
                node.getRight().setColor(BLACK);
            } else {
                node.getRight().setColor(RED);
            }
        }
    }

    public String getPreOrder() {
        return truncateStr(getPreOrder(root));
    }

    public String getInOrder() {
        return truncateStr(getInOrder(root));
    }

    public String getPostOrder() {
        return truncateStr(getPostOrder(root));
    }

    private String getPreOrder(Node<K, V> node) {
        if (node == null) {
            return "";
        }

        String result = node.getKey().toString() + ":";
        result += node.getValue().toString() + " ";

        result += getPreOrder(node.getLeft());
        result += getPreOrder(node.getRight());

        return result;
    }

    private String getInOrder(Node<K, V> node) {
        if (node == null) {
            return "";
        }

        String result = "";

        result += getInOrder(node.getLeft());

        result += node.getKey().toString() + ":";
        result += node.getValue().toString() + " ";

        result += getInOrder(node.getRight());

        return result;
    }

    private String getPostOrder(Node<K, V> node) {
        if (node == null) {
            return "";
        }

        String result = "";

        result += getPostOrder(node.getLeft());
        result += getPostOrder(node.getRight());

        result += node.getKey().toString() + ":";
        result += node.getValue().toString() + " ";

        return result;
    }

    private String truncateStr(String stringToTruncate) {
        String result = stringToTruncate;

        if(stringToTruncate.length() > 0) {
            result = stringToTruncate.substring(0, stringToTruncate.length() - 1);
        } 

        return result;
    }   
}