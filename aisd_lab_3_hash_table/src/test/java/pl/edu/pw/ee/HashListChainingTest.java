package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashListChainingTest {
    HashTable<Double> hashtable = null;

    @Before
    public void setUp() {
        hashtable = new HashListChaining<>(10000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_ItemIsNull() {
        //given
        Double item = null;
        
        //when
        hashtable.add(item);
    }

    @Test
    public void shouldReturnNull_When_HashTableDoesNotContainItem() {
        //given
        Double item = 9.0;

        //when
        Double result = hashtable.get(item);

        //then
        Double expected = null;
        assertEquals(expected, result);
        
    }

    @Test
    public void should_ReturnItem_When_HashTableHasOneItem() {
        //given
        Double item = 10.0;
        hashtable.add(item);

        //when
        double result = hashtable.get(item);

        //then
        double expected = 10;
        assertEquals(expected, result, 0);
    }

    @Test
    public void should_ReturnCorrectItem_When_HashTableHasTwoItems() {
        //given
        Double firstItem = 10.0;
        Double secondItem = -14.9;

        hashtable.add(firstItem);
        hashtable.add(secondItem);

        //when
        double result = hashtable.get(secondItem);

        //then
        double expected = secondItem;
        assertEquals(expected, result, 0);
    }
}
