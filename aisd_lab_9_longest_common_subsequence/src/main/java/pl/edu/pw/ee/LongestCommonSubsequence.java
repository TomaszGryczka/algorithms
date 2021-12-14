package pl.edu.pw.ee;

import static pl.edu.pw.ee.Arrow.*;

public class LongestCommonSubsequence {
    private String firstStr;
    private String secondStr;

    private int firstStrLength;
    private int secondStrLength;

    private Matrix fieldMatrix;

    public LongestCommonSubsequence(String firstStr, String secondStr) {
        this.firstStr = firstStr;
        this.secondStr = secondStr;

        firstStrLength = firstStr.length();
        secondStrLength = secondStr.length();

        fieldMatrix = new Matrix(firstStrLength, secondStrLength);
    }

    public String findLCS() {
        setFirstRow();
        setFirstCol();

        return null;
    }

    public void display() {
        for(int i = 0; i < firstStr.length(); i++) {
            for(int j = 0; j < secondStr.length(); j++) {
                if(fieldMatrix.getField(i, j) != null) {
                    System.out.print(fieldMatrix.getField(i, j).getFieldValue() + " ");
                }   
            }
            System.out.println();
        }
    }

    private void setFirstRow() {
        final int firstRow = 0;

        final int addUpLeftVal = 1;
        final int upVal = 0;

        if(firstStr.charAt(firstRow) == secondStr.charAt(0)) {
            fieldMatrix.setField(firstRow, 0, new Field(addUpLeftVal, UPPERLEFT));
        } else {
            fieldMatrix.setField(firstRow, 0, new Field(upVal, UPPER));
        }
        
        for(int j = 1; j < secondStrLength; j++) {
            if(firstStr.charAt(firstRow) == secondStr.charAt(j)) {
                fieldMatrix.setField(firstRow, j, new Field(addUpLeftVal, UPPERLEFT));
            } else {
                int leftVal = fieldMatrix.getField(firstRow, j - 1).getFieldValue();

                if(upVal < leftVal) {
                    fieldMatrix.setField(firstRow, j, new Field(leftVal, LEFT));
                } else {
                    fieldMatrix.setField(firstRow, j, new Field(upVal, UPPER));
                }
            }
        }
    }

    private void setFirstCol() {
        final int firstCol = 0;
        
        final int addUpLeftVal = 1;
        final int leftVal = 0;

        for(int i = 1; i < firstStrLength; i++) {
            if(firstStr.charAt(i) == secondStr.charAt(firstCol)) {
                fieldMatrix.setField(i, firstCol, new Field(addUpLeftVal, UPPERLEFT));
            } else {
                int upVal = fieldMatrix.getField(i - 1, firstCol).getFieldValue();

                fieldMatrix.setField(i, firstCol, new Field(upVal, UPPER));
            }
        }

    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("ABBAABA", "BABBAB");

        lcs.findLCS();
        
        lcs.display();
    }

}
