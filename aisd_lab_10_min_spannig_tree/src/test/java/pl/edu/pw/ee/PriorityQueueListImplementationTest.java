package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.PriorityQueue;

public class PriorityQueueListImplementationTest {
    private PriorityQueue pQueue;

    @Before
    public void setUp() {
        pQueue = new PriorityQueueListImplementation();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenTryingToPutNullEdge() {
        // given
        Edge edge = null;

        // when
        pQueue.put(edge);

        // then
        assert false;
    }

    @Test
    public void should_PutAndPopEdge_When_PQIsEmpty() {
        // given
        Edge edge = new Edge("a", "b", 10);

        // when
        pQueue.put(edge);

        Edge actual = pQueue.pop();

        // then
        Edge expected = edge;

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnNull_When_TryingToPopFromEmptyPQ() {
        // when
        Edge actual = pQueue.pop();

        // then
        Edge expected = null;

        assertEquals(expected, actual);
        ;
    }

    @Test
    public void should_PutEdges_Then_PopThemInAscendingOrder() {
        // given
        Edge edge1 = new Edge("a", "b", 5);
        Edge edge2 = new Edge("s", "s", 2);
        Edge edge3 = new Edge("r", "r", 6);
        Edge edge4 = new Edge("q", "w", 0);

        pQueue.put(edge1);
        pQueue.put(edge2);
        pQueue.put(edge3);
        pQueue.put(edge4);

        // when
        List<Edge> actuals = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            actuals.add(pQueue.pop());
        }

        // then
        List<Edge> expecteds = new ArrayList<>();
        expecteds.add(edge4);
        expecteds.add(edge2);
        expecteds.add(edge1);
        expecteds.add(edge3);

        assertEquals(expecteds, actuals);
    }

    @Test
    public void should_PutAndPopTwoEdges_When_FirstEdgeIsGreaterThanSecond() {
        // given
        Edge firstEdge = new Edge("a", "b", 10);
        Edge secondEdge = new Edge("b", "c", 5);

        // when
        pQueue.put(firstEdge);
        pQueue.put(secondEdge);

        Edge actualFirst = pQueue.pop();
        Edge actualSecond = pQueue.pop();

        // then
        Edge expectedFirst = secondEdge;
        Edge expectedSecond = firstEdge;

        assertEquals(expectedFirst, actualFirst);
        assertEquals(expectedSecond, actualSecond);
    }

    @Test
    public void should_PutAndPopTwoEdges_When_SecondEdgeIsGreaterThanFirst() {
        // given
        Edge firstEdge = new Edge("a", "b", 5);
        Edge secondEdge = new Edge("b", "c", 15);

        // when
        pQueue.put(firstEdge);
        pQueue.put(secondEdge);

        Edge actualFirst = pQueue.pop();
        Edge actualSecond = pQueue.pop();

        // then
        Edge expectedFirst = firstEdge;
        Edge expectedSecond = secondEdge;

        assertEquals(expectedFirst, actualFirst);
        assertEquals(expectedSecond, actualSecond);
    }
}