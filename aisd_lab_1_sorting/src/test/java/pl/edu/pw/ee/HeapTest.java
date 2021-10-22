package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class HeapTest {
    private Heap<Double> heap;
    private Random rand;

    private final int seed = 12;

    @Before
    public void setUp() {
        heap = new Heap<>();

        rand = new Random(seed);
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

    @Test
    public void should_ReturnCorrectElem_When_HeapHasThreeElems() {
        // given
        double firstElem = -1.2;
        double secondElem = 4.0;
        double thirdElem = 14.0;

        // when
        heap.put(firstElem);
        heap.put(secondElem);
        heap.put(thirdElem);

        double result = heap.pop();

        // then
        double expected = 14.0;
        assertEquals(expected, result, 0);
    }

    @Test
    public void should_ReturnMaxElem_WhenHeapHasRandomData() {
        //given
        double[] nums = new double[100];

        nums[0] = rand.nextDouble();
        double correctElem = nums[0];

        for(int i = 1; i < 100; i++) {
            nums[i] = rand.nextDouble();
            if(nums[i] > correctElem) {
                correctElem = nums[i];
            }
        }

        //when
        for(int i = 0; i < 100; i++) {
            heap.put(nums[i]);
        }
        double result = heap.pop();

        //then
        assertEquals(correctElem, result, 0);
    }

    
}
