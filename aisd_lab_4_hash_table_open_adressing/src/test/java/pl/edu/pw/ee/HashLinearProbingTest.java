package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import org.junit.Ignore;
import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashLinearProbingTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_InitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        HashTable<Double> hash = new HashLinearProbing<>(initialSize);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_ElemToPutIsNull() {
        // given
        String elemToPut = null;
        HashTable<String> hash = new HashLinearProbing<>();

        // when
        hash.put(elemToPut);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_ElemToGetIsNull() {
        // given
        String elemToGet = null;
        HashTable<String> hash = new HashLinearProbing<>();

        // when
        hash.get(elemToGet);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_ElemToDeleteIsNull() {
        // given
        String elemToDelete = null;
        HashTable<String> hash = new HashLinearProbing<>();

        // when
        hash.delete(elemToDelete);

        // then
        assert false;
    }

    @Test
    public void should_CorrectlyAddNewElem_When_ElemDoesNotExistInHashTable() {
        // given
        HashTable<String> emptyHash = new HashLinearProbing<>();
        String newElem = "nothing special";

        // when
        int nOfElemsBeforePut = getNumOfElems(emptyHash);
        emptyHash.put(newElem);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    @Test
    public void should_CorrectlyReturnElem_When_HashTableHasOneElem() {
        // given
        HashTable<String> hashTable = new HashLinearProbing<>();
        String elem = "nothing special";
        hashTable.put(elem);

        // when
        String result = hashTable.get(elem);

        // then
        String expected = elem;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnCorrectElem_When_HashTableHasTwoElems() {
        // given
        HashTable<String> hashTable = new HashLinearProbing<>();

        String elem1 = "nothing special";
        String elem2 = "something special";

        hashTable.put(elem1);
        hashTable.put(elem2);

        // when
        String result = hashTable.get(elem2);

        // then
        String expected = elem2;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnLastElem_When_HashTableHasEvenNumOfElems() {

    }

    //need a fix
    @Ignore("not implemented yet")
    @Test
    public void should_PutElem_When_HashTableIsFullOfTombstones() {
        //given
        HashTable<String> hashTable = new HashLinearProbing<>(3);
        setHashElems(hashTable);

        String elem1 = "nothing special";

        //when
        int nOfElemsBeforePut = getNumOfElems(hashTable);
        hashTable.put(elem1);
        int nOfElemsAfterPut = getNumOfElems(hashTable);

        //then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    @Test
    public void should_ReplaceElem_When_ElemExistsInHashTable() {
        // given
        String elem = "special string";
        String elem2 = "special string";

        HashTable<String> hashTable = new HashLinearProbing<>();
        hashTable.put(elem);

        // when
        int nOfElemsBeforePut = getNumOfElems(hashTable);
        hashTable.put(elem2);
        int nOfElemsAfterPut = getNumOfElems(hashTable);

        // then
        assertEquals(1, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    @Test
    public void should_ExtendHashTable_WhenLoadFactorIsGreaterThanCorrectLoadFactor() {
        // given
        String elem = "special string";
        String elem2 = "unusual string";

        HashTable<String> hashTable = new HashLinearProbing<>(1);

        // when
        int initialSize = getSizeOfHashTable(hashTable);

        hashTable.put(elem);
        int sizeOfHashBeforeExtension = getSizeOfHashTable(hashTable);

        hashTable.put(elem2);
        int sizeOfHashAfterExtension = getSizeOfHashTable(hashTable);

        // then
        assertEquals(1, initialSize);
        assertEquals(1, sizeOfHashBeforeExtension);
        assertEquals(2, sizeOfHashAfterExtension);
    }

    @Test
    public void should_() {
        // given
        

        // when

        // then

    }

    private int getNumOfElems(HashTable<?> hash) {
        String fieldNumOfElems = "nElems";
        try {
            //System.out.println(hash.getClass().getSuperclass().getName());
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private int getSizeOfHashTable(HashTable<?> hash) {
        String fieldSize = "size";
        try {
            //System.out.println(hash.getClass().getSuperclass().getName());
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldSize);
            field.setAccessible(true);

            int size = field.getInt(hash);

            return size;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    //need a fix
    private void setHashElems(HashTable<?> hash) {
        String hashElems = "hashElems";
        String delValue = "delValue";

        try {
            //System.out.println(hash.getClass().getSuperclass().getName());
            Field fieldHash = hash.getClass().getSuperclass().getDeclaredField(hashElems);
            Field fieldDelVal = hash.getClass().getSuperclass().getDeclaredField(delValue);
            
            fieldHash.setAccessible(true);
            fieldDelVal.setAccessible(true);

            Object array = Array.newInstance(fieldHash.getType().getComponentType(), 3);

            for(int i = 0; i < 3; i++) {
                Array.set(array, i, fieldDelVal.get(hash));
            }

            for(int i = 0; i < 3; i++) {
                System.out.println(Array.get(array, i));
            }

            fieldHash.set(hash, array);

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
