package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithmTest {

    private MinSpanningTree mst;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() {
        mst = new PrimAlgorithm();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_FileIsEmpty() {
        // given
        File tempFile = null;

        try {
            tempFile = temporaryFolder.newFile("./tempFile.txt");

            FileUtils.writeStringToFile(tempFile, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        mst.findMST(tempFile.getAbsolutePath());

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_FileHasIncorrectLine() {
        // given
        File tempFile = null;

        try {
            tempFile = temporaryFolder.newFile("./tempFile.txt");

            FileUtils.writeStringToFile(tempFile, "a \na z 1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        mst.findMST(tempFile.getAbsolutePath());

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_WeightIsNotANumber() {
        // given
        File tempFile = null;

        try {
            tempFile = temporaryFolder.newFile("./tempFile.txt");

            FileUtils.writeStringToFile(tempFile, "a z b\na z 1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        mst.findMST(tempFile.getAbsolutePath());

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_VertexNameIncludeIllegalCharacters() {
        // given
        File tempFile = null;

        try {
            tempFile = temporaryFolder.newFile("./tempFile.txt");

            FileUtils.writeStringToFile(tempFile, "a# b 12\na z 1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        mst.findMST(tempFile.getAbsolutePath());

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_FileHasNegativeWeight() {
        // given
        File tempFile = null;

        try {
            tempFile = temporaryFolder.newFile("./tempFile.txt");

            FileUtils.writeStringToFile(tempFile, "a b -11");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        mst.findMST(tempFile.getAbsolutePath());

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_GraphIsNotConnected() {
        // given
        File tempFile = null;

        try {
            tempFile = temporaryFolder.newFile("./tempFile.txt");

            FileUtils.writeStringToFile(tempFile, "a b 1\nb c 12\nb c 3\nz d 45");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        mst.findMST(tempFile.getAbsolutePath());

        // then
        assert false;
    }

    @Test
    public void should_FindMST_When_TwoVerticesHaveTwoCommonEdges() {
        // given
        File tempFile = null;

        try {
            tempFile = temporaryFolder.newFile("./tempFile.txt");

            FileUtils.writeStringToFile(tempFile, "a b 10\nb a 12");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        String actual = mst.findMST(tempFile.getAbsolutePath());

        // then
        String expected = "a_10_b";

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_GraphHasOneVertexConnectedToItself() {
        // given
        File tempFile = null;

        try {
            tempFile = temporaryFolder.newFile("./tempFile.txt");

            FileUtils.writeStringToFile(tempFile, "a a 10");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        mst.findMST(tempFile.getAbsolutePath());

        // then
        assert false;
    }

    @Test
    public void should_FindMST_When_GraphHasTwoConnectedVertices() {
        // given
        File tempFile = null;

        try {
            tempFile = temporaryFolder.newFile("./tempFile.txt");

            FileUtils.writeStringToFile(tempFile, "a b 10");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        String actual = mst.findMST(tempFile.getAbsolutePath());

        // then
        String expected = "a_10_b";

        assertEquals(expected, actual);
    }

    @Test
    public void should_FindMST_When_GraphHasCorrectData() {
        // given
        File tempFile = null;

        try {
            tempFile = temporaryFolder.newFile("./tempFile.txt");

            FileUtils.writeStringToFile(tempFile, "A B 2\nA C 3\nB C 2\nB E 5\nC E 1\nC D 1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        String actual = mst.findMST(tempFile.getAbsolutePath());

        // then
        String expected = "A_2_B|B_2_C|C_1_E|C_1_D";

        assertEquals(expected, actual);
    }

    @Test
    public void should_FindMST_When_GraphHasDataFromExample() {
        // given
        File tempFile = null;

        try {
            tempFile = temporaryFolder.newFile("./tempFile.txt");

            FileUtils.writeStringToFile(tempFile, "A B 3\nA C 5\nA D 7\nB C 1\nC D 1\nD E 7");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        String actual = mst.findMST(tempFile.getAbsolutePath());

        // then
        String expected = "A_3_B|B_1_C|C_1_D|D_7_E";

        assertEquals(expected, actual);
    }
}
