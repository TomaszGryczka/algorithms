package pl.edu.pw.ee;

public class Field implements Comparable<Field> {
    private int fieldValue;

    private Arrow arrow;

    private boolean isPath;

    public Field(int fieldValue, Arrow arrow) {
        if (fieldValue < 0) {
            throw new IllegalArgumentException("Field value cannot be lower than 0!");
        }

        if (arrow == null) {
            throw new IllegalArgumentException("Arrow cannot be null!");
        }

        this.fieldValue = fieldValue;
        this.arrow = arrow;
        isPath = false;
    }

    @Override
    public int compareTo(Field otherField) {
        if (otherField == null) {
            throw new IllegalArgumentException("Field cannot be null!");
        } else if (fieldValue < otherField.fieldValue) {
            return -1;
        } else if (fieldValue > otherField.fieldValue) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        if (this.fieldValue != ((Field) obj).fieldValue) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int primeNum = 31;
        int result = 1;

        result = primeNum * result + fieldValue;

        return result;
    }

    public int getFieldValue() {
        return fieldValue;
    }

    public Arrow getArrow() {
        return arrow;
    }

    public boolean getIsPath() {
        return isPath;
    }

    public void setIsPathTrue() {
        isPath = true;
    }
}