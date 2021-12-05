package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

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
}
