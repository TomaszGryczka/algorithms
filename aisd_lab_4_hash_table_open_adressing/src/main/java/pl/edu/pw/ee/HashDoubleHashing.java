package pl.edu.pw.ee;

public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    public HashDoubleHashing() {
        super();
    }

    public HashDoubleHashing(int size) {
        super(size);

        validateInitSizeOfDoubleHashing(size);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (fFunction(key, m) + i * (2 * gFunction(key, m) + 1)) % m;

        hash = hash < 0 ? -hash : hash;

        return hash;

        /* zamieniłem funkcję z g(k)= 1 + (k mod (m-3)) na 2 * g(k) + 1 */

        // f(k, i) = (f(k) + i * g(k)) % m
        // f(k)= k % m
        // g(k)= 1 + (k mod (m-3))
    }

    private int fFunction(int key, int m) {
        return key % m;
    }

    private int gFunction(int key, int m) {
        return 1 + (key % (m - 3));
    }

    private void validateInitSizeOfDoubleHashing(int size) {
        if (size == 3) {
            throw new IllegalArgumentException("Size can not be 3! - / by 0!");
        }
    }
}