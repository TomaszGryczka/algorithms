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
        String node = "WOW";
        disjointSet.addNode(node);

        // when
        disjointSet.isDisconnected();

        // then
        assert false;
    }

    @Test
    public void should_ReturnTrue_When_GraphIsDisconnected() {
        // given
        String firstNode = "WOW";
        String secondNode = "OH";

        disjointSet.addNode(firstNode);
        disjointSet.addNode(secondNode);

        // when
        boolean actual = disjointSet.isDisconnected();

        // then
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnFalse_When_GraphIsConnected() {
        // given
        String firstNode = "WOW";
        String secondNode = "OH";

        disjointSet.addNode(firstNode);
        disjointSet.addNode(secondNode);

        disjointSet.union(firstNode, secondNode);

        // when
        boolean actual = disjointSet.isDisconnected();

        // then
        boolean expected = false;

        assertEquals(expected, actual);
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

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_UnionGraphWithoutTrees() {
        // given
        String firstNodeName = "aaa";
        String secondNodeName = "beeee";

        // when
        disjointSet.union(firstNodeName, secondNodeName);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_UnionNotExistingNodes() {
        // given
        String firstNodeName = "aaa";
        String secondNodeName = "beeee";

        String addedNode1 = "uuu";
        String addedNode2 = "llll";

        disjointSet.addNode(addedNode1);
        disjointSet.addNode(addedNode2);

        // when
        disjointSet.union(firstNodeName, secondNodeName);

        // then
        assert false;
    }

    @Test
    public void should_MergeNodes_When_NodesAreNotConnected() {
        // given
        String firstNode = "WOW";
        String secondNode = "OH";

        disjointSet.addNode(firstNode);
        disjointSet.addNode(secondNode);

        // when
        boolean actualBeforeUnion = disjointSet.isDisconnected();
        disjointSet.union(firstNode, secondNode);
        boolean actualAfterUnion = disjointSet.isDisconnected();

        // then
        boolean expectedBeforeUnion = true;
        boolean expectedAfterUnion = false;

        assertEquals(expectedBeforeUnion, actualBeforeUnion);
        assertEquals(expectedAfterUnion, actualAfterUnion);
    }

    @Test
    public void should_LeaveNodesConnected_When_NodesAreConnected() {
        // given
        String firstNode = "WOW";
        String secondNode = "OH";

        disjointSet.addNode(firstNode);
        disjointSet.addNode(secondNode);

        disjointSet.union(firstNode, secondNode);

        // when
        boolean actualBeforeUnion = disjointSet.isDisconnected();
        disjointSet.union(firstNode, secondNode);
        boolean actualAfterUnion = disjointSet.isDisconnected();

        // then
        boolean expectedBeforeUnion = false;
        boolean expectedAfterUnion = false;

        assertEquals(expectedBeforeUnion, actualBeforeUnion);
        assertEquals(expectedAfterUnion, actualAfterUnion);
    }
}