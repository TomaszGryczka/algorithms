package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    private double a;
    private double b;

    public HashQuadraticProbing() {
        super();
        this.a = 1;
        this.b = 1;
    }

    public HashQuadraticProbing(int size, double a, double b) {
        super(size);

        validateConstants(a, b);
        this.a = a;
        this.b = b;
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (key + (int) a * i + (int) b * i * i) % m;

        hash = hash < 0 ? -hash : hash;

        return hash;
    }

    private void validateConstants(double constA, double contsB) {
        if (constA == 0 || contsB == 0) {
            throw new IllegalArgumentException("Constant can not be 0!");
        }
    }

}
