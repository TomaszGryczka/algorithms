package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;

public class HuffmanTest {
    private Huffman huffman;

    private final String dataFileName = "data.txt";
    private final String encodedFileName = "encoded.txt";
    private final String decodedFileName = "decoded.txt";
    private final String treeConfigFileName = "treeConfigFile.txt";
    private final String pathToRootDir = "./";

    private final boolean compress = true;
    private final boolean decompress = false;

    @Before
    public void setUp() {
        huffman = new Huffman();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_RootDirDoesNotExist() {
        // given
        String pathToRootDir = "special root dir";
        boolean compress = true;

        // when
        huffman.huffman(pathToRootDir, compress);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_RootDirIsNotDirectory() {
        // given
        File file = new File("./output.txt");

        String pathToRootDir = "./output.txt";
        boolean compress = true;

        // when
        huffman.huffman(pathToRootDir, compress);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_DataFileDoesNotExist() {
        // given
        File file = new File(dataFileName);
        if (file.exists()) {
            file.delete();
        }

        // when
        huffman.huffman(pathToRootDir, compress);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_DataFileIsEmpty() {
        // given
        File file = new File(dataFileName);

        try {
            file.createNewFile();
            new PrintWriter(dataFileName).close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        huffman.huffman(pathToRootDir, compress);

        // then
        assert false;
    }

    @Test
    public void should_EncodeAndDecode_When_FileHasOneChar() {
        // given
        File dataFile = new File(dataFileName);

        String data = "A";

        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeData(pathToRootDir + dataFileName, data);

        // when
        int numOfBits = huffman.huffman(pathToRootDir, compress);
        int numOfChars = huffman.huffman(pathToRootDir, decompress);

        String encodedFileData = readFileData(pathToRootDir + encodedFileName);
        String decodedFileData = readFileData(pathToRootDir + decodedFileName);

        // then
        int expectedNumOfBits = 0;
        int expectedNumOfChars = 1;

        assertEquals(expectedNumOfBits, numOfBits);
        assertEquals(expectedNumOfChars, numOfChars);
        assertEquals("", encodedFileData);
        assertEquals(data, decodedFileData);
    }

    @Test
    public void should_EncodeAndDecode_When_FileHasTwoChars() {
        // given
        File dataFile = new File(dataFileName);

        String data = "AB";

        try {
            dataFile.createNewFile();
            new PrintWriter(dataFileName).close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeData(pathToRootDir + dataFileName, data);

        // when
        int numOfBits = huffman.huffman(pathToRootDir, compress);
        int numOfChars = huffman.huffman(pathToRootDir, decompress);

        String decodedFileData = readFileData(pathToRootDir + decodedFileName);

        // then
        int expectedNumOfBits = 2;
        int expectedNumOfChars = 2;

        assertEquals(expectedNumOfBits, numOfBits);
        assertEquals(expectedNumOfChars, numOfChars);
        assertEquals(data, decodedFileData);
    }

    @Test
    public void should_EncodeAndDecode_When_FileHasThreeChars() {
        // given
        File dataFile = new File(dataFileName);

        String data = "ABA";

        try {
            dataFile.createNewFile();
            new PrintWriter(dataFileName).close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeData(pathToRootDir + dataFileName, data);

        // when
        huffman.huffman(pathToRootDir, compress);
        int numOfChars = huffman.huffman(pathToRootDir, decompress);

        String decodedFileData = readFileData(pathToRootDir + decodedFileName);

        // then
        int expectedNumOfChars = 3;

        assertEquals(expectedNumOfChars, numOfChars);
        assertEquals(data, decodedFileData);
    }

    @Test
    public void should_EncodeAndDecode_When_FileHasOnlyNumericalData() {
        // given
        File dataFile = new File(dataFileName);

        String data = "1234";

        try {
            dataFile.createNewFile();
            new PrintWriter(dataFileName).close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeData(pathToRootDir + dataFileName, data);

        // when
        huffman.huffman(pathToRootDir, compress);
        int numOfChars = huffman.huffman(pathToRootDir, decompress);

        String decodedFileData = readFileData(pathToRootDir + decodedFileName);

        // then
        int expectedNumOfChars = 4;

        assertEquals(expectedNumOfChars, numOfChars);
        assertEquals(data, decodedFileData);

    }

    @Test
    public void should_EncodeAndDecode_When_FileHasSpecialCharacters() {
        // given
        String pathToRootDir = "./";

        File dataFile = new File(dataFileName);

        String data = "eśąćźMBANK";

        try {
            dataFile.createNewFile();
            new PrintWriter(dataFileName).close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeData(pathToRootDir + dataFileName, data);

        // when
        huffman.huffman(pathToRootDir, compress);
        int numOfChars = huffman.huffman(pathToRootDir, decompress);

        String decodedFileData = readFileData(pathToRootDir + decodedFileName);

        // then
        int expectedNumOfChars = 10;
        assertEquals(expectedNumOfChars, numOfChars);
        assertEquals(data, decodedFileData);

    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_TreeConfigFileIsBroken() {
        // given
        File treeConfigFile = new File(treeConfigFileName);

        String treeConfigFileData = "aaaaaa";

        try {
            treeConfigFile.createNewFile();
            new PrintWriter(treeConfigFile).close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeData(pathToRootDir + treeConfigFileName, treeConfigFileData);

        // when
        huffman.huffman(pathToRootDir, decompress);

        // then
        assert false;

    }

    @Test
    public void should_Decode_When_ThereWasNoEncoding() {
        // given
        File decodedFile = new File(decodedFileName);
        File encodedFile = new File(encodedFileName);
        File treeConfigFile = new File(treeConfigFileName);

        String encodedText = "C”Ü";
        String treeConfigData = "22\nG 1 o 2 t 1   1 y 1 u 1 ! 1 ";
        String text = "Got you!";
         

        try{
            encodedFile.createNewFile();
            treeConfigFile.createNewFile();

            writeData(treeConfigFileName, treeConfigData);
            writeData(encodedFileName, encodedText);

            decodedFile.createNewFile();
            new PrintWriter(decodedFile).close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        int expectedNumOfChars = 8;

        int numOfChars = huffman.huffman(pathToRootDir, decompress);

        String decodedText = readFileData(decodedFileName);

        // then
        assertEquals(text, decodedText);
        assertEquals(expectedNumOfChars, numOfChars);

    }

    private String readFileData(String pathToFile) {

        String result = "";

        try (FileReader fileReader = new FileReader(pathToFile, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(fileReader);) {

            String line;

            while ((line = reader.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void writeData(String pathToFile, String data) {

        try (FileWriter fileWriter = new FileWriter(pathToFile, Charset.forName("UTF-8"));
                BufferedWriter writer = new BufferedWriter(fileWriter);) {

            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}