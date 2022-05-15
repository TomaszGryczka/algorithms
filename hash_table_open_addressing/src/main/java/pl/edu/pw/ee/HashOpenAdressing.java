package pl.edu.pw.ee;

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

        int infLoopCheckIter = 0;

        while (hashElems[hashId] != nil && hashElems[hashId].compareTo(newElem) != 0
                && delValue.compareTo(hashElems[hashId]) != 0) {

            i = (i + 1) % size;
            hashId = hashFunc(key, i);

            infLoopCheckIter++;

            if (infLoopCheckIter > size) {
                infLoopCheckIter = -1;
                break;
            }
        }

        if (infLoopCheckIter == -1) {
            doubleResize();

            put(newElem);

            return;
        }

        if (hashElems[hashId] != nil && hashElems[hashId].compareTo(newElem) == 0) {
            hashElems[hashId] = newElem;
        } else {
            hashElems[hashId] = newElem;
            nElems++;
        }
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);
        validateNumOfElems();

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        int infLoopCheckIter = 0;

        while (hashElems[hashId] != nil && hashElems[hashId].compareTo(elem) != 0) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);

            infLoopCheckIter++;

            if (infLoopCheckIter > size) {
                infLoopCheckIter = -1;
                break;
            }
        }

        if (infLoopCheckIter == -1) {
            doubleResize();

            return get(elem);
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
        validateNumOfElems();

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        int infLoopCheckIter = 0;

        while (hashElems[hashId] != nil && hashElems[hashId].compareTo(elem) != 0) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);

            infLoopCheckIter++;

            if (infLoopCheckIter > size) {
                infLoopCheckIter = -1;
                break;
            }
        }

        if (hashElems[hashId] == nil || infLoopCheckIter == -1) {
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

    private void validateNumOfElems() {
        if (nElems == 0) {
            throw new IllegalStateException("Hash Table is empty!");
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
        int prevSize = hashElems.length;

        this.size *= 2;

        T[] prevHash = hashElems;
        hashElems = (T[]) new Comparable[this.size];

        for (int i = 0; i < prevSize; i++) {
            if (prevHash[i] != nil && delValue.compareTo(prevHash[i]) != 0) {

                int key = prevHash[i].hashCode();
                int idx = 0;
                int hashId = hashFunc(key, idx);

                int infLoopCheckIter = 0;

                while (hashElems[hashId] != nil && hashElems[hashId].compareTo(prevHash[i]) != 0
                        && delValue.compareTo(hashElems[hashId]) != 0) {

                    idx = (idx + 1) % size;
                    hashId = hashFunc(key, idx);

                    infLoopCheckIter++;

                    if (infLoopCheckIter > size) {
                        infLoopCheckIter = -1;
                        break;
                    }
                }

                if (infLoopCheckIter == -1) {
                    hashElems = prevHash;

                    doubleResize();

                    return;
                }

                hashElems[hashId] = prevHash[i];
            }
        }
    }
}