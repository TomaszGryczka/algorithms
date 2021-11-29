package pl.edu.pw.ee;

import pl.edu.pw.ee.exceptions.NotImplementedException;
import pl.edu.pw.ee.services.HashTable;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private int size;
    private int nElems;
    private T[] hashElems;
    private final double correctLoadFactor;

    private final Deleted delValue = new Deleted();

    private class Deleted implements Comparable<T> {

        @Override
        public int compareTo(T o) {
            if (o == null) {
                return -1;
            } else if (equals(o) == true) {
                return 0;
            } else {
                return -1;
            }
        }

        public boolean equals(T elem) {
            return this == elem;
        }
    }

    HashOpenAdressing() {
        this(2039);
    }

    HashOpenAdressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.correctLoadFactor = 0.75;
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }

        hashElems[hashId] = newElem;
        nElems++;
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil && hashElems[hashId].compareTo(elem) != 0) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }

        if (hashElems[hashId] == nil) {
            throw new IllegalArgumentException("Elem does not exist in hash table!");
        }

        T result = hashElems[hashId];

        return result;
    }

    @Override
    public void delete(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil && hashElems[hashId].compareTo(elem) != 0) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }

        if (hashElems[hashId] == nil) {
            throw new IllegalArgumentException("Elem does not exist in hash table!");
        } else {
            hashElems[hashId] = (T) delValue;
            nElems--;
        }
    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException("Input elem cannot be null!");
        }
    }

    abstract int hashFunc(int key, int i);

    int getSize() {
        return size;
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    private void doubleResize() {
        int prevSize = this.size;
        // System.out.println(size + " - " + nElems);
        this.size *= 2;

        T[] prevHash = hashElems;
        hashElems = (T[]) new Comparable[this.size];

        for (int i = 0; i < prevSize; i++) {
            if (prevHash[i] != nil && delValue.compareTo(prevHash[i]) != 0) {
                // put(prevHash[i]);
                // nElems--;

                int key = prevHash[i].hashCode();
                int idx = 0;
                int hashId = hashFunc(key, idx);

                while (hashElems[hashId] != nil) {
                    idx = (idx + 1) % size;
                    hashId = hashFunc(key, idx);
                }

                hashElems[hashId] = prevHash[i];
            }
        }
        // System.out.println("Liczba elem po: " + nElems);
    }
}