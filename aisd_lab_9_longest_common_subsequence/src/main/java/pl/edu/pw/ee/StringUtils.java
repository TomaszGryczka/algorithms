package pl.edu.pw.ee;

public class StringUtils {
    public static String emptyString(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size cannot be lower than 1!");
        }

        return new String(new char[size]).replace('\0', ' ');
    }

    public static String leftPad(String text, int length) {
        if (length < 1) {
            throw new IllegalArgumentException("Length cannot be lower than 1!");
        }

        if (text == null) {
            throw new IllegalArgumentException("Tet string cannot be null!");
        }

        return String.format("%" + length + "s", text);
    }

    public static String rightPad(String text, int length) {
        if (length < 1) {
            throw new IllegalArgumentException("Length cannot be lower than 1!");
        }

        if (text == null) {
            throw new IllegalArgumentException("Tet string cannot be null!");
        }

        return String.format("%-" + length + "s", text);
    }

    public static String getSpecialCharacter(char character) {
        switch (character) {
            case '\'':
                return "\\'";
            case '\"':
                return "\\\"";
            case '\\':
                return "\\\\";
            case '\f':
                return "\\f";
            case '\r':
                return "\\r";
            case '\n':
                return "\\n";
            case '\b':
                return "\\b";
            case '\t':
                return "\\t";
            default:
                return character + "";
        }
    }

    public static void validateStrings(String firstStr, String secondStr) {
        if (firstStr == null || secondStr == null) {
            throw new IllegalArgumentException("Strings cannot be null!");
        }

        if (firstStr.length() == 0 || secondStr.length() == 0) {
            throw new IllegalArgumentException("Strings length has to be greater than 0!");
        }
    }
}