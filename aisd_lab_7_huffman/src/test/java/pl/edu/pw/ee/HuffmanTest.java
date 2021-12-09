package pl.edu.pw.ee;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class HuffmanTest {
    private Huffman huffman;

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
        String pathToRootDir = "./";
        boolean compress = true;

        File file = new File("data.txt");
        if (file.exists()) {
            file.delete();
        }

        // when
        huffman.huffman(pathToRootDir, compress);

        // then
        assert false;
    }

    @Test
    public void should_() {
        // given

        // when

        // then

    }
}
