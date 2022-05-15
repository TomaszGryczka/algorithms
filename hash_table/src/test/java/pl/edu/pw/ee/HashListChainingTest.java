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
    public void should_ThrowIllegalArgumentException_When_CreatingHashTableWithSizeLessThan0() {
        // given
        int size = -10;

        // when
        hashtable = new HashListChaining<>(size);
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
        // given
        Double item = null;

        // when
        hashtable.get(item);
    }

    @Test
    public void should_ReturnItem_When_HashTableHasOneItem() {
        // given
        Double item = 102.0;
        hashtable.add(item);

        // when
        double result = hashtable.get(item);

        // then
        double expected = item;
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
        int result = hashtable.getNElem();

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

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_TryingToDeleteNullItem() {
        // given
        Double item = null;

        // when
        hashtable.delete(item);
    }

    @Test(expected = IllegalStateException.class)
    public void should_ThrowIllegalStateException_When_TryingToDeleteFromEmptyHashTable() {
        // given
        double item = 3;

        // when
        hashtable.delete(item);
    }

    @Test
    public void should_DeleteValue_When_HashtableHasOneValue() {
        // given
        double item = 4.0;
        hashtable.add(item);

        // when
        hashtable.delete(item);

        Double result = hashtable.get(item);

        // then
        Double expected = null;
        assertEquals(expected, result);
    }

    @Test
    public void should_DeleteValue_When_ValueIsLastElementInLinkedList() {
        // given
        int size = 1;
        hashtable = new HashListChaining<>(size);

        double firstItem = 3.0;
        double lastItem = -1.2;
        hashtable.add(lastItem);
        hashtable.add(firstItem);

        // when
        hashtable.delete(lastItem);

        Double result = hashtable.get(lastItem);

        // then
        Double expected = null;
        assertEquals(expected, result);

    }

    @Test
    public void should_DeleteValue_When_ValueIsFirstElementInLinkedList() {
        // given
        int size = 1;
        hashtable = new HashListChaining<>(size);

        double firstItem = 3.0;
        double lastItem = -1.2;
        hashtable.add(lastItem);
        hashtable.add(firstItem);

        // when
        hashtable.delete(firstItem);

        Double result = hashtable.get(firstItem);

        // then
        Double expected = null;
        assertEquals(expected, result);
    }

    @Test
    public void should_DeleteValue_When_ValueIsMiddleElementInLinkedList() {
        // given
        int size = 1;
        hashtable = new HashListChaining<>(size);

        double itemToDelete = 2;

        double[] items = { 0, 1, 2, 3, 4 };
        for (int i = 0; i < items.length; i++) {
            hashtable.add(items[i]);
        }

        // when
        hashtable.delete(itemToDelete);

        Double result = hashtable.get(itemToDelete);

        int numOfItems = hashtable.getNElem();

        // then
        Double expected = null;
        assertEquals(expected, result);

        int expectedNumOfItems = 4;
        assertEquals(expectedNumOfItems, numOfItems);
    }

    @Test
    public void should_IncrementNElem_When_ItemWasAddedToHashTable() {
        // given
        double item = 3.4;

        // when
        hashtable.add(item);
        int result = hashtable.getNElem();

        // then
        int expected = 1;
        assertEquals(expected, result);
    }

    @Test
    public void should_DecrementNElem_When_ValueWasDeletedFromHashTable() {
        // given
        double firstItem = 3.4;
        double secondItem = 1.2;
        hashtable.add(firstItem);
        hashtable.add(secondItem);

        // when
        hashtable.delete(firstItem);
        int result = hashtable.getNElem();

        // then
        int expected = 1;
        assertEquals(expected, result);
    }
}