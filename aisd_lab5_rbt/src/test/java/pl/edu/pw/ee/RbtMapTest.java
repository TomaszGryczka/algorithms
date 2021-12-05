package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.MapInterface;

public class RbtMapTest {

    private MapInterface<String, String> rbtMap;

    @Before
    public void setUp() {
        rbtMap = new RbtMap<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToSetNullValue() {
        // given
        String key = "KEY";
        String nullVal = null;

        // when
        rbtMap.setValue(key, nullVal);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToSetNullKey() {
        // given
        String nullKey = null;
        String val = "VALUE";

        // when
        rbtMap.setValue(nullKey, val);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToSetNullKeyAndNullValue() {
        // given
        String nullKey = null;
        String val = null;

        // when
        rbtMap.setValue(nullKey, val);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToGetNullKey() {
        // given
        String nullKey = null;

        // when
        rbtMap.getValue(nullKey);

        // then
        assert false;
    }

    @Test
    public void should_ReturnNull_When_TryingToGetValueFromEmptyMap() {
        // given
        String key = "K";

        // when
        String actual = rbtMap.getValue(key);

        // then
        String expected = null;
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnValue_When_MapHasOneElem() {
        // given
        String key = "A";
        String value = "A";

        rbtMap.setValue(key, value);

        // when
        String actual = rbtMap.getValue(key);

        // then
        String expected = "A";

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnValue_When_MapHasTwoElems() {
        // given
        String firstKeyVal = "B";
        String secondKeyVal = "A";

        rbtMap.setValue(firstKeyVal, firstKeyVal);
        rbtMap.setValue(secondKeyVal, secondKeyVal);

        // when
        String actual = rbtMap.getValue(secondKeyVal);

        // then
        String expected = "A";
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnValue_When_MapHasThreeElems() {
        // given
        String firstKeyVal = "B";
        String secondKeyVal = "A";
        String thirdKeyVal = "C";

        rbtMap.setValue(firstKeyVal, firstKeyVal);
        rbtMap.setValue(secondKeyVal, secondKeyVal);
        rbtMap.setValue(thirdKeyVal, thirdKeyVal);

        // when
        String actual = rbtMap.getValue(thirdKeyVal);

        // then
        String expected = "C";
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnNull_When_ElemDoesNotExistInMap() {
        // given
        String elemKeyVal = "C";
        String otherElemKeyVal = "A";

        rbtMap.setValue(otherElemKeyVal, otherElemKeyVal);

        // when
        String actual = rbtMap.getValue(elemKeyVal);

        // then
        String expected = null;

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnValue_When_ValueIsMinAndMaxElemInMap() {
        // given
        String[] keysAndVals = { "L", "I", "S", "T", "O", "P", "A", "D" };

        for (int i = 0; i < keysAndVals.length; i++) {
            rbtMap.setValue(keysAndVals[i], keysAndVals[i]);
        }

        // when
        String actualMin = rbtMap.getValue(keysAndVals[6]);
        String actualMax = rbtMap.getValue(keysAndVals[3]);

        // then
        String expectedMin = "A";
        String expectedMax = "T";

        assertEquals(expectedMax, actualMax);
        assertEquals(expectedMin, actualMin);
    }

    @Test
    public void should_SetAndThenGetElemInMap_When_MapIsEmpty() {
        // given
        String key = "K";
        String value = "K";

        // when
        rbtMap.setValue(key, value);
        String actual = rbtMap.getValue(key);

        // then
        String expected = "K";
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReplaceValue_When_KeysAreEqual() {
        // given
        String key1 = "A";
        String val1 = "something good";

        String key2 = "A";
        String val2 = "something not good";

        // when
        rbtMap.setValue(key1, val1);
        rbtMap.setValue(key2, val2);

        String actual = rbtMap.getValue(key2);

        // then
        String expected = "something not good";
        assertEquals(expected, actual);
    }

    @Test
    public void should_SetAndThenGetAllValues_When_DataIsRandom() {
        // given
        RbtMap<Integer, Double> map = new RbtMap<>();

        int seed = 123;
        int dataSize = 10000;

        Random rand = new Random(seed);

        Integer[] keys = new Integer[dataSize];
        Double[] vals = new Double[dataSize];

        for (int i = 0; i < dataSize; i++) {
            keys[i] = rand.nextInt();
            vals[i] = rand.nextDouble();
        }

        // when
        for (int i = 0; i < dataSize; i++) {
            map.setValue(keys[i], vals[i]);
        }

        Double[] results = new Double[dataSize];

        for (int i = 0; i < dataSize; i++) {
            results[i] = map.getValue(keys[i]);
        }

        // then
        Double[] expected = vals;

        assertArrayEquals(expected, results);
    }
}
