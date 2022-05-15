package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.ee.services.HashTable;

public class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

    private final Elem nil = null;
    private List<Elem> hashElems;
    private int nElem;

    private class Elem {
        private T value;
        private Elem next;

        Elem(T value, Elem nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    public HashListChaining(int size) {
        if(size <= 0) {
            throw new IllegalArgumentException("Elems arrray can not have size less or equal to 0");
        }
        hashElems = new ArrayList<>(size);

        initializeHash(size);
    }

    @Override
    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value can not be null");
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem oldElem = hashElems.get(hashId);
        while (oldElem != nil && !(oldElem.value.compareTo(value) == 0)) {
            oldElem = oldElem.next;
        }
        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems.set(hashId, new Elem(value, hashElems.get(hashId)));
            nElem++;
        }
    }

    @Override
    public T get(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value can not be null");
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems.get(hashId);

        while (elem != nil && !(elem.value.compareTo(value) == 0)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : null;
    }

    public double countLoadFactor() {
        double size = hashElems.size();

        return nElem / size;
    }

    private void initializeHash(int size) {
        for (int i = 0; i < size; i++) {
            hashElems.add(nil);
        }
    }

    private int countHashId(int hashCode) {
        if (hashCode == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("HashCode can not be Integer.MIN_VALUE");
        }

        int n = hashElems.size();
        return Math.abs(hashCode % n);
    }

    @Override
    public void delete(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value can not be null");
        }

        if (nElem == 0) {
            throw new IllegalStateException("Can not delete value from empty hash table");
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems.get(hashId);

        if (elem.value.compareTo(value) == 0) {
            hashElems.set(hashId, elem.next);
            nElem--;
        } else {
            while (elem.next != nil && !(elem.next.value.compareTo(value) == 0)) {
                elem = elem.next;
            }
            if (elem.next != nil) {
                elem.next = elem.next.next;
                nElem--;
            }
        }

    }

    // for tests only

    public int getNElem() {
        return nElem;
    }

    public int getHashIdOfHashCode(int value) {
        return countHashId(value);
    }
}