package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashListChainingTest {
    HashTable<Double> hashtable = null;

    @Before
    public void setUp() {
        hashtable = new HashListChaining<>(1);
    }

    @Test
    public void should_AddValue_and_ReturnIt() {
        hashtable.add(10.0);
        System.out.println(hashtable.get(10.0));
    }
}
