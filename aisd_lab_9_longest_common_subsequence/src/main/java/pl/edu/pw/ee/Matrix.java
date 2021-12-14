package pl.edu.pw.ee;

public class Matrix {
    private Field[] rowMajorOrderMatrix;

    private int rows;
    private int cols;

    public Matrix(int firstStrSize, int secondStrSize) {
        rows = firstStrSize;
        cols = secondStrSize;
        
        rowMajorOrderMatrix = new Field[rows * cols];
    }

    public void setField(int i, int j, Field field) {
        int index = j + cols * i;

        rowMajorOrderMatrix[index] = field;
    }

    public Field getField(int i, int j) {
        int index = j + cols * i;
        
        return rowMajorOrderMatrix[index];
    }
}