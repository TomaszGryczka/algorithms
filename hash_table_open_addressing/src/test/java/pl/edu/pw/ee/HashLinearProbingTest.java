package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

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
    public void should_PutElem_When_HashTableIsFullOfTombstones() {
        // given
        HashTable<String> hashTable = new HashLinearProbing<>(3);
        setTombstonesInHash(hashTable);

        String elem1 = "nothing special";

        // when
        int nOfElemsBeforePut = getNumOfElems(hashTable);
        hashTable.put(elem1);
        int nOfElemsAfterPut = getNumOfElems(hashTable);

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
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

    @Test(expected = IllegalStateException.class)
    public void should_ThrowException_When_TryingToGetElemFromEmptyHash() {
        // given
        HashTable<String> hash = new HashLinearProbing<>();
        String elemToGet = "string";

        // when
        hash.get(elemToGet);

        // then
        assert false;
    }

    @Test(expected = IllegalStateException.class)
    public void should_ThrowException_When_HashTableIsFullOfTombstones() {
        // given
        HashTable<String> hash = new HashLinearProbing<>(3);

        setTombstonesInHash(hash);

        String elemToGet = "string";

        // when
        hash.get(elemToGet);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_ElemDoesNotExistInHashTable() {
        // given
        String elemToGet = "string";
        String elem2 = "stronger";

        HashTable<String> hash = new HashLinearProbing<>(3);

        hash.put(elem2);

        // when
        hash.get(elemToGet);

        // then
        assert false;
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
    public void should_ReturnCorrectElem_When_HashTableHasThreeElems() {
        // given
        HashTable<String> hashTable = new HashLinearProbing<>();

        String elem1 = "nothing special";
        String elem2 = "something special";
        String elem3 = "you are special";

        hashTable.put(elem1);
        hashTable.put(elem2);
        hashTable.put(elem3);

        // when
        String result = hashTable.get(elem2);

        // then
        String expected = elem2;
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnElem_When_HashTableHasEvenNumOfElems() {
        // given
        HashTable<String> hashTable = new HashLinearProbing<>();

        String[] elems = { "aa", "ab", "ac", "ad", "ae", "af" };

        for (int i = 0; i < elems.length; i++) {
            hashTable.put(elems[i]);
        }

        // when
        String result = hashTable.get("ac");

        // then
        String expected = elems[2];
        assertEquals(expected, result);
    }

    @Test
    public void should_ReturnElem_When_HashTableHasOddNumOfElems() {
        // given
        HashTable<String> hashTable = new HashLinearProbing<>();

        String[] elems = { "aa", "ab", "ac", "ad", "ae" };

        for (int i = 0; i < elems.length; i++) {
            hashTable.put(elems[i]);
        }

        // when
        String result = hashTable.get("ac");

        // then
        String expected = elems[2];
        assertEquals(expected, result);
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

    @Test(expected = IllegalStateException.class)
    public void should_ThrowException_When_TryingToDeleteElemFromEmptyHash() {
        // given
        HashTable<String> hash = new HashLinearProbing<>();
        String elemToDelete = "string";

        // when
        hash.delete(elemToDelete);

        // then
        assert false;
    }

    @Test(expected = IllegalStateException.class)
    public void should_ThrowException_When_TryingToDeleteFromHashThatIsFullOfTombstones() {
        // given
        HashTable<String> hash = new HashLinearProbing<>(3);

        setTombstonesInHash(hash);

        String elemToDelete = "string";

        // when
        hash.delete(elemToDelete);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToDeleteElemThatDoesNotExistInHash() {
        // given
        String elemToDelete = "string";
        String elem = "stronger";

        HashTable<String> hash = new HashLinearProbing<>(3);

        hash.put(elem);

        // when
        hash.delete(elemToDelete);

        // then
        assert false;
    }

    @Test
    public void should_DecrementNumberOfElements_When_ElemWasDeleted() {
        // given
        String elemToDelete = "string";
        String elem = "stronger";

        HashTable<String> hash = new HashLinearProbing<>(3);

        hash.put(elem);
        hash.put(elemToDelete);

        // when
        int nOfElemsBeforeDeletion = getNumOfElems(hash);
        hash.delete(elemToDelete);
        int nOfElemsAfterDeletion = getNumOfElems(hash);

        // then
        assertEquals(2, nOfElemsBeforeDeletion);
        assertEquals(1, nOfElemsAfterDeletion);
    }

    @Test
    public void should_getElem_When_PreviousElemWithTheSameHashCodeWasDeleted() {
        // given
        HashTable<IdenticalHashCode> hash = new HashLinearProbing<>(3);

        IdenticalHashCode elem1 = new IdenticalHashCode(12);
        IdenticalHashCode elem2 = new IdenticalHashCode(13);

        hash.put(elem1);
        hash.put(elem2);

        hash.delete(elem1);

        // when
        IdenticalHashCode result = hash.get(elem2);

        // then
        IdenticalHashCode expected = elem2;
        assertEquals(expected, result);
    }

    @Test
    public void should_DeleteElem_When_HashTableHasOneElem() {
        // given
        HashTable<String> hashTable = new HashLinearProbing<>(3);
        String elem1 = "nothing special";
        hashTable.put(elem1);

        // when
        int nOfElemsBeforeDeletion = getNumOfElems(hashTable);
        hashTable.delete(elem1);
        int nOfElemsAfterDeletion = getNumOfElems(hashTable);

        // then
        assertEquals(1, nOfElemsBeforeDeletion);
        assertEquals(0, nOfElemsAfterDeletion);
    }

    @Test
    public void should_DeleteElem_When_HashTableHasTwoElems() {
        // given
        HashTable<String> hashTable = new HashLinearProbing<>(3);
        String elem1 = "nothing special";
        String elem2 = "something special";

        hashTable.put(elem1);
        hashTable.put(elem2);

        // when
        int nOfElemsBeforeDeletion = getNumOfElems(hashTable);
        hashTable.delete(elem2);
        int nOfElemsAfterDeletion = getNumOfElems(hashTable);

        // then
        assertEquals(2, nOfElemsBeforeDeletion);
        assertEquals(1, nOfElemsAfterDeletion);
    }

    private int getNumOfElems(HashTable<?> hash) {
        String fieldNumOfElems = "nElems";
        try {
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
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldSize);
            field.setAccessible(true);

            int size = field.getInt(hash);

            return size;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTombstonesInHash(HashTable<?> hash) {
        String hashElems = "hashElems";
        String delValue = "delValue";

        try {
            Field fieldHash = hash.getClass().getSuperclass().getDeclaredField(hashElems);
            Field fieldDelVal = hash.getClass().getSuperclass().getDeclaredField(delValue);

            fieldHash.setAccessible(true);
            fieldDelVal.setAccessible(true);

            Object array = Array.newInstance(fieldHash.getType().getComponentType(), getSizeOfHashTable(hash));

            for (int i = 0; i < getSizeOfHashTable(hash); i++) {
                Array.set(array, i, fieldDelVal.get(hash));
            }

            fieldHash.set(hash, array);

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private class IdenticalHashCode implements Comparable<IdenticalHashCode> {
        private int id;

        public IdenticalHashCode(int id) {
            this.id = id;
        }

        public boolean equals(IdenticalHashCode o) {
            if (this.id == o.id) {
                return true;
            } else {
                return false;
            }
        }

        public int hashCode() {
            return 123;
        }

        @Override
        public int compareTo(IdenticalHashCode o) {
            if (o == null) {
                return -1;
            } else if (this.equals(o)) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
