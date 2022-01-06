package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DisjointSetTest {
    private DisjointSet disjointSet;

    @Before
    public void setUp() {
        disjointSet = new DisjointSet();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_CheckConnectionOfEmptyForest() {
        // when
        disjointSet.isDisconnected();

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_CheckConnectionOfForestWithOneTree() {
        // given
        String vertex = "WOW";
        disjointSet.addNode(vertex);

        // when
        disjointSet.isDisconnected();

        // then
        assert false;
    }

    @Test
    public void should_ReturnTrue_When_GraphIsDisconnected() {
        // given
        String vertex1 = "WOW";
        String vertex2 = "OH";

        disjointSet.addNode(vertex1);
        disjointSet.addNode(vertex2);

        // when
        boolean actual = disjointSet.isDisconnected();

        // then
        boolean expected = true;

        assertEquals(expected, actual);;
    }

    @Test
    public void should_ReturnFalse_When_GraphIsConnected() {
        // given
        String vertex1 = "WOW";
        String vertex2 = "OH";

        disjointSet.addNode(vertex1);
        disjointSet.addNode(vertex2);

        disjointSet.union(vertex1, vertex2);

        // when
        boolean actual = disjointSet.isDisconnected();

        // then
        boolean expected = false;

        assertEquals(expected, actual);;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_AddNullNodeName() {
        // given
        String nodeName = null;

        // when
        disjointSet.addNode(nodeName);

        // then
        assert false;
    }
}
