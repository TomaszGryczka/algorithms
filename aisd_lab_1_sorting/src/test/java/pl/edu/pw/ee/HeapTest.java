package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HeapTest {
    private Heap<Double> heap;

    @Before
    public void setUp() {
        heap = new Heap<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_ItemIsNull() {
        // given
        Double item = null;

        // when
        heap.put(item);
    }

    @Test(expected = IllegalStateException.class)
    public void should_ThrowIllegalStateException_When_HeapIsNull() {
        // when
        heap.pop();
    }

    @Test
    public void should_ReturnElem_When_HeapHasOneElem() {
        // given
        double elem = 4.0;

        // when
        heap.put(elem);
        double result = heap.pop();

        // then
        double expected = 4.0;
        assertEquals(expected, result, 0);
    }

    @Test
    public void should_ReturnCorrectElem_When_HeapHasTwoElems() {
        // given
        double firstElem = -1.2;
        double secondElem = 4.0;

        // when
        heap.put(firstElem);
        heap.put(secondElem);

        double result = heap.pop();

        // then
        double expected = 4.0;
        assertEquals(expected, result, 0);
    }

    
}
