package pl.edu.pw.ee;

public class Code {
    private String bitCode;

    private Character character;

    public Code(char character, String bitCode) {
        this.bitCode = bitCode;
        this.character = character;
    }

    public String getBitCode() {
        return bitCode;
    }

    public char getCharacter() {
        return character;
    }
}
