package pl.edu.pw.ee;

import static pl.edu.pw.ee.Arrow.LEFT;
import static pl.edu.pw.ee.Arrow.UPPER;
import static pl.edu.pw.ee.Arrow.UPPERLEFT;

public class LongestCommonSubsequence {
    private final String firstStr;
    private final String secondStr;

    private final int firstStrLength;
    private final int secondStrLength;

    private Matrix fieldMatrix;

    public LongestCommonSubsequence(String topStr, String leftStr) {
        StringUtils.validateStrings(topStr, leftStr);

        this.firstStr = leftStr;
        this.secondStr = topStr;

        firstStrLength = firstStr.length();
        secondStrLength = secondStr.length();

        fieldMatrix = new Matrix(firstStrLength, secondStrLength);
    }

    public String findLCS() {
        setFirstRow();
        setFirstCol();
        setRest();

        return findPath();
    }

    public void display() {
        final int maxVal = fieldMatrix.getField(firstStrLength - 1, secondStrLength - 1).getFieldValue();
        final int maxNumOfChars = String.valueOf(maxVal).length() + 1;

        final String empty = StringUtils.emptyString(maxNumOfChars);
        final String gap = StringUtils.emptyString(maxNumOfChars - 1);

        String firstLine = "    " + gap;
        String secondLine = "   0";

        Field currentField;

        for (int j = 0; j < secondStrLength; j++) {
            String character = StringUtils.getSpecialCharacter(secondStr.charAt(j));
            firstLine += character.charAt(0) != secondStr.charAt(j) ? character + gap.substring(0, gap.length() - 1)
                    : character + gap;
            secondLine += gap + "0";
        }

        System.out.println(firstLine);
        System.out.println(secondLine);

        for (int i = 0; i < firstStrLength; i++) {
            firstLine = "  ";
            secondLine = StringUtils.leftPad(StringUtils.getSpecialCharacter(firstStr.charAt(i)), 2) + " 0";

            for (int j = 0; j < secondStrLength; j++) {
                currentField = fieldMatrix.getField(i, j);

                if (currentField.getIsPath()) {
                    if (currentField.getArrow() == UPPERLEFT) {
                        firstLine += StringUtils.rightPad(gap + " \\", maxNumOfChars);
                        secondLine += StringUtils.leftPad(" " + currentField.getFieldValue(), maxNumOfChars);
                    } else if (currentField.getArrow() == UPPER) {
                        firstLine += StringUtils.leftPad(gap + "  ^", maxNumOfChars);
                        secondLine += StringUtils.leftPad(" " + currentField.getFieldValue(), maxNumOfChars);
                    } else {
                        firstLine += empty;
                        secondLine += StringUtils.leftPad("<" + currentField.getFieldValue(), maxNumOfChars);
                    }
                } else {
                    firstLine += empty;
                    secondLine += StringUtils.leftPad(" " + currentField.getFieldValue(), maxNumOfChars);
                }

            }
            System.out.println(firstLine);
            System.out.println(secondLine);
        }
    }

    private String findPath() {
        String result = "";

        int j = secondStrLength - 1;
        int i = firstStrLength - 1;

        while (i >= 0 && j >= 0) {
            Field currentField = fieldMatrix.getField(i, j);

            currentField.setIsPathTrue();

            if (currentField.getFieldValue() == 0) {
                break;
            } else if (currentField.getArrow() == UPPERLEFT) {
                result = secondStr.charAt(j) + result;
                j--;
                i--;
            } else if (currentField.getArrow() == UPPER) {
                i--;
            } else {
                j--;
            }
        }

        return result;
    }

    private void setFirstRow() {
        final int firstRow = 0;

        final int upLeftVal = 0;
        final int upVal = 0;

        if (firstStr.charAt(firstRow) == secondStr.charAt(0)) {
            fieldMatrix.setField(firstRow, 0, new Field(upLeftVal + 1, UPPERLEFT));
        } else {
            fieldMatrix.setField(firstRow, 0, new Field(upVal, UPPER));
        }

        for (int j = 1; j < secondStrLength; j++) {
            if (firstStr.charAt(firstRow) == secondStr.charAt(j)) {
                fieldMatrix.setField(firstRow, j, new Field(upLeftVal + 1, UPPERLEFT));
            } else {
                int leftVal = fieldMatrix.getField(firstRow, j - 1).getFieldValue();

                if (upVal < leftVal) {
                    fieldMatrix.setField(firstRow, j, new Field(leftVal, LEFT));
                } else {
                    fieldMatrix.setField(firstRow, j, new Field(upVal, UPPER));
                }
            }
        }
    }

    private void setFirstCol() {
        final int firstCol = 0;

        final int upLeftVal = 0;

        for (int i = 1; i < firstStrLength; i++) {
            if (firstStr.charAt(i) == secondStr.charAt(firstCol)) {
                fieldMatrix.setField(i, firstCol, new Field(upLeftVal + 1, UPPERLEFT));
            } else {
                int upVal = fieldMatrix.getField(i - 1, firstCol).getFieldValue();

                fieldMatrix.setField(i, firstCol, new Field(upVal, UPPER));
            }
        }

    }

    private void setRest() {
        for (int i = 1; i < firstStrLength; i++) {
            for (int j = 1; j < secondStrLength; j++) {
                if (firstStr.charAt(i) == secondStr.charAt(j)) {
                    int upLeftVal = fieldMatrix.getField(i - 1, j - 1).getFieldValue();

                    fieldMatrix.setField(i, j, new Field(upLeftVal + 1, UPPERLEFT));
                } else {
                    int upVal = fieldMatrix.getField(i - 1, j).getFieldValue();
                    int leftVal = fieldMatrix.getField(i, j - 1).getFieldValue();

                    if (upVal < leftVal) {
                        fieldMatrix.setField(i, j, new Field(leftVal, LEFT));
                    } else {
                        fieldMatrix.setField(i, j, new Field(upVal, UPPER));
                    }
                }
            }
        }
    }   
}
