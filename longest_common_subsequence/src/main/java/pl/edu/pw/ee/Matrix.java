package pl.edu.pw.ee;

public class Matrix {
    private Field[] rowMajorOrderMatrix;

    private int rows;
    private int cols;

    public Matrix(int firstStrSize, int secondStrSize) {
        if (firstStrSize < 1 || secondStrSize < 1) {
            throw new IllegalArgumentException("Strings size cannot be lower than 1!");
        }

        rows = firstStrSize;
        cols = secondStrSize;

        rowMajorOrderMatrix = new Field[rows * cols];
    }

    public void setField(int i, int j, Field field) {
        if (field == null) {
            throw new IllegalArgumentException("Field variable cannot be null!");
        }

        if (i < 0 || j < 0 || i > rows || j > cols) {
            throw new IllegalArgumentException("Indices cannot be lower than 0!");
        }

        int index = j + cols * i;

        rowMajorOrderMatrix[index] = field;
    }

    public Field getField(int i, int j) {
        if (i < 0 || j < 0 || i > rows || j > cols) {
            throw new IllegalArgumentException("Indices cannot be lower than 0!");
        }

        int index = j + cols * i;

        return rowMajorOrderMatrix[index];
    }
}