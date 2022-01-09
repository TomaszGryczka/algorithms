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
    private File dataFile;

    @Rule
    public TemporaryFolder dataFolder = new TemporaryFolder();

    @Before
    public void setUp() {
        mst = new PrimAlgorithm();
        dataFile = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_FileIsEmpty() {
        // given
        try {
            dataFile = dataFolder.newFile("./dataFile.txt");

            FileUtils.writeStringToFile(dataFile, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        mst.findMST(dataFile.getAbsolutePath());

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_FileDoesNotExist() {
        // given
        String pathToFile = "idonotexist";

        // when
        mst.findMST(pathToFile);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_FileHasIncorrectLine() {
        // given
        try {
            dataFile = dataFolder.newFile("./dataFile.txt");

            FileUtils.writeStringToFile(dataFile, "a \na z 1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        mst.findMST(dataFile.getAbsolutePath());

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_WeightIsNotANumber() {
        // given
        try {
            dataFile = dataFolder.newFile("./dataFile.txt");

            FileUtils.writeStringToFile(dataFile, "a z b\na z 1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        mst.findMST(dataFile.getAbsolutePath());

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_VertexNameIncludeIllegalCharacters() {
        // given
        try {
            dataFile = dataFolder.newFile("./dataFile.txt");

            FileUtils.writeStringToFile(dataFile, "a# b 12\na z 1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        mst.findMST(dataFile.getAbsolutePath());

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_FileHasNegativeWeight() {
        // given
        try {
            dataFile = dataFolder.newFile("./dataFile.txt");

            FileUtils.writeStringToFile(dataFile, "a b -11");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        mst.findMST(dataFile.getAbsolutePath());

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_GraphIsNotConnected() {
        // given
        try {
            dataFile = dataFolder.newFile("./dataFile.txt");

            FileUtils.writeStringToFile(dataFile, "a b 1\nb c 12\nb c 3\nz d 45");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        System.out.println(mst.findMST(dataFile.getAbsolutePath()));

        // then
        assert false;
    }

    @Test
    public void should_FindMST_When_TwoVerticesHaveTwoCommonEdges() {
        // given
        try {
            dataFile = dataFolder.newFile("./dataFile.txt");

            FileUtils.writeStringToFile(dataFile, "a b 10\nb a 12");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        String actual = mst.findMST(dataFile.getAbsolutePath());

        // then
        String expected = "a_10_b";

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_GraphHasOneVertexConnectedToItself() {
        // given
        try {
            dataFile = dataFolder.newFile("./dataFile.txt");

            FileUtils.writeStringToFile(dataFile, "a a 10");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        mst.findMST(dataFile.getAbsolutePath());

        // then
        assert false;
    }

    @Test
    public void should_FindMST_When_GraphHasTwoConnectedVertices() {
        // given
        try {
            dataFile = dataFolder.newFile("./dataFile.txt");

            FileUtils.writeStringToFile(dataFile, "a b 10");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        String actual = mst.findMST(dataFile.getAbsolutePath());

        // then
        String expected = "a_10_b";

        assertEquals(expected, actual);
    }

    @Test
    public void should_FindMST_When_GraphHasCorrectData() {
        // given
        try {
            dataFile = dataFolder.newFile("./dataFile.txt");

            FileUtils.writeStringToFile(dataFile, "A B 2\nA C 3\nB C 2\nB E 5\nC E 1\nC D 1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        String actual = mst.findMST(dataFile.getAbsolutePath());

        // then
        String expected = "A_2_B|B_2_C|C_1_E|C_1_D";

        assertEquals(expected, actual);
    }

    @Test
    public void should_FindMST_When_GraphHasDataFromExample() {
        // given
        try {
            dataFile = dataFolder.newFile("./dataFile.txt");

            FileUtils.writeStringToFile(dataFile, "A B 3\nA C 5\nA D 7\nB C 1\nC D 1\nD E 7");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        String actual = mst.findMST(dataFile.getAbsolutePath());

        // then
        String expected = "A_3_B|B_1_C|C_1_D|D_7_E";

        assertEquals(expected, actual);
    }

    @Test
    public void should_FindMST_When_GraphIsAList() {
        // given
        try {
            dataFile = dataFolder.newFile("./dataFile.txt");

            FileUtils.writeStringToFile(dataFile, "a b 3\nb c 3\nc d 4\nd e 0");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        String actual = mst.findMST(dataFile.getAbsolutePath());

        // then
        String expected = "a_3_b|b_3_c|c_4_d|d_0_e";

        assertEquals(expected, actual);
    }

    @Test
    public void should_FindMST_When_GraphHasOnlyRootAndLeafs() {
        // given
        try {
            dataFile = dataFolder.newFile("./dataFile.txt");

            FileUtils.writeStringToFile(dataFile, "a b 1\na c 2\na d 3\na e 4");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        String actual = mst.findMST(dataFile.getAbsolutePath());

        // then
        String expected = "a_1_b|a_2_c|a_3_d|a_4_e";

        assertEquals(expected, actual);
    }

    @Test
    public void should_FindMST_When_FileHasInvertedVeritces() {
        // given
        try {
            dataFile = dataFolder.newFile("./dataFile.txt");

            FileUtils.writeStringToFile(dataFile, "v b 1\na c 2\nv d 3\na e 4\ne b 1\nc e 4");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // when
        String actual = mst.findMST(dataFile.getAbsolutePath());

        // then
        String expected = "v_1_b|b_1_e|v_3_d|e_4_a|a_2_c";

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_PathToFileIsNull() {
        // given
        String pathToFile = null;

        // when
        mst.findMST(pathToFile);

        // then
        assert false;
    }
}