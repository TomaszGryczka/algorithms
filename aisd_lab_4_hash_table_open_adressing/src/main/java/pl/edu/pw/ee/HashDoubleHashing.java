package pl.edu.pw.ee;

public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    public HashDoubleHashing() {
        super();
    }

    public HashDoubleHashing(int size) {
        super(size);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (key % m + i * (1 + (key % (m - 3)))) % m;

        hash = hash < 0 ? -hash : hash;

        return hash;

        // f(k, i) = (f(k) + i * g(k)) % m
        // f(k)= k % m
        // g(k)= 1 + (k mod (m-3))
    }
}