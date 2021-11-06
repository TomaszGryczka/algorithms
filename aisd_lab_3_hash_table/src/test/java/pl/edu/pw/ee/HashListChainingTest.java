package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HashListChainingTest {
    private HashListChaining<Double> hashtable;

    @Before
    public void setUp() {
        hashtable = new HashListChaining<>(10000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_ItemIsNull() {
        // given
        Double item = null;

        // when
        hashtable.add(item);
    }

    @Test
    public void shouldReturnNull_When_HashTableDoesNotContainItem() {
        // given
        Double item = 9.0;

        // when
        Double result = hashtable.get(item);

        // then
        Double expected = null;
        assertEquals(expected, result);

    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_TryingToGetNullItem() {
        // when
        hashtable.get(null);
    }

    @Test
    public void should_ReturnItem_When_HashTableHasOneItem() {
        // given
        Double item = 102.0;
        hashtable.add(item);

        // when
        double result = hashtable.get(item);

        // then
        double expected = 102;
        assertEquals(expected, result, 0);
    }

    @Test
    public void should_ReturnCorrectItem_When_HashTableHasTwoItems() {
        // given
        Double firstItem = 10.0;
        Double secondItem = -14.9;

        hashtable.add(firstItem);
        hashtable.add(secondItem);

        // when
        double result = hashtable.get(secondItem);

        // then
        double expected = secondItem;
        assertEquals(expected, result, 0);
    }

    @Test
    public void should_NotAddItem_WhenItemExistsInHashTable() {
        // given
        Double firstItem = 1.0;
        Double item = 9.0;
        Double lastItem = 10.2;

        hashtable.add(firstItem);
        hashtable.add(item);
        hashtable.add(lastItem);
        hashtable.add(item);

        // when
        int result = hashtable.getnElem();

        // then
        int expected = 3;
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_HashCodeIsIntegerMIN_VALUE() {
        // given
        int hashCode = Integer.MIN_VALUE;

        // when
        hashtable.getHashIdOfHashCode(hashCode);
    }
}
