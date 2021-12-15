package pl.edu.pw.ee;

public class StringUtils {
    public static String emptyString(int size) {
        return new String(new char[size]).replace('\0', ' ');
    }

    public static String leftPad(String text, int length) {
        return String.format("%" + length + "s", text);
    }
    
    public static String rightPad(String text, int length) {
        return String.format("%-" + length + "s", text);
    }
}
