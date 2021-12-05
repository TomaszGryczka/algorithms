package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

public class RedBlackTreeTest {

    private RedBlackTree<String, String> rbt;

    @Before
    public void setUp() {
        rbt = new RedBlackTree<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToGetNullKey() {
        // when
        String key = null;

        // when
        rbt.get(key);

        // then
        assert false;
    }

    @Test
    public void should_ReturnNull_When_TryingToGetValueFromEmptyRBT() {
        // given
        String key = "K";

        // when
        String actual = rbt.get(key);

        // then
        String expected = null;
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnValue_When_RBTHasOneElem() {
        // given
        String key = "A";
        String value = "A";

        rbt.put(key, value);

        // when
        String actual = rbt.get(key);

        // then
        String expected = "A";

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnValue_When_RootHasOnlyOneChild() {
        // given
        String rootKeyVal = "B";
        String childKeyVal = "A";

        rbt.put(rootKeyVal, rootKeyVal);
        rbt.put(childKeyVal, childKeyVal);

        // when
        String actual = rbt.get(childKeyVal);

        // then
        String expected = "A";
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnValue_When_ValueIsAtDeepestLevelOfRBT() {
        // given
        String[] keysAndVals = { "L", "I", "S", "T", "O", "P", "A", "D" };

        for (int i = 0; i < keysAndVals.length; i++) {
            rbt.put(keysAndVals[i], keysAndVals[i]);
        }

        // when
        String actual = rbt.get(keysAndVals[4]);

        // then
        String expected = "O";
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnValue_When_ValueIsMinAndMaxElemInRBT() {
        // given
        String[] keysAndVals = { "L", "I", "S", "T", "O", "P", "A", "D" };

        for (int i = 0; i < keysAndVals.length; i++) {
            rbt.put(keysAndVals[i], keysAndVals[i]);
        }

        // when
        String actualMin = rbt.get(keysAndVals[6]);
        String actualMax = rbt.get(keysAndVals[3]);

        // then
        String expectedMin = "A";
        String expectedMax = "T";

        assertEquals(expectedMax, actualMax);
        assertEquals(expectedMin, actualMin);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToPutNullKey() {
        // given
        String key = null;
        String value = "aaa";

        // when
        rbt.put(key, value);

        // then
        assert false;
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_TryingToPutNullValue() {
        // given
        String key = "aaa";
        String value = null;

        // when
        rbt.put(key, value);

        // then
        assert false;
    }

    @Test
    public void should_PutElemInRBT_When_RBTIsEmpty() {
        // given
        String key = "K";
        String value = "K";

        // when
        rbt.put(key, value);

        // then
        Node<String, String> node = (Node<String, String>) getRoot(rbt);
        String result = node.getValue();

        String expected = "K";
        assertEquals(expected, result);
    }

    @Test
    public void should_ReplaceValue_When_KeysAreEqual() {
        // given
        String key1 = "A";
        String val1 = "something good";

        String key2 = "A";
        String val2 = "something not good";

        // when
        rbt.put(key1, val1);
        rbt.put(key2, val2);

        String actual = rbt.get(key2);

        // then
        String expected = "something not good";
        assertEquals(expected, actual);
    }

    @Test
    public void should_() {
        // given

        // when

        // then

    }

    

    @Test
    public void should_RotateLeft_When_RBTHasRedRightLink() {
        // given
        String keyVal1 = "K";
        String keyVal2 = "O";

        rbt.put(keyVal1, keyVal1);

        // when
        rbt.put(keyVal2, keyVal2);

        // then
        Node<String, String> node = (Node<String, String>) getRoot(rbt);

        String resultO = node.getValue();
        String resultK = node.getLeft().getValue();

        String expectedK = "K";
        String expectedO = "O";

        assertEquals(expectedK, resultK);
        assertEquals(expectedO, resultO);

        assertTrue(node.isBlack());
        assertTrue(node.getLeft().isRed());
    }

    @Test
    public void should_RotateRightAndChangeColors_When_RBTHasTwoConsecutiveRedLinks() {
        // given
        String keyVal1 = "C";
        String keyVal2 = "B";
        String keyVal3 = "A";

        rbt.put(keyVal1, keyVal1);
        rbt.put(keyVal2, keyVal2);

        // when
        rbt.put(keyVal3, keyVal3);

        // then
        Node<String, String> node = (Node<String, String>) getRoot(rbt);

        String resultB = node.getValue();
        String resultA = node.getLeft().getValue();
        String resultC = node.getRight().getValue();

        String expectedB = "B";
        String expectedA = "A";
        String expectedC = "C";

        assertEquals(expectedA, resultA);
        assertEquals(expectedB, resultB);
        assertEquals(expectedC, resultC);

        assertTrue(node.getLeft().isBlack());
        assertTrue(node.getRight().isBlack());
    }

    @Test
    public void should_ReturnEmptyString_When_TryingToPrintXOrder() {
        // when
        String actualPreOrder = rbt.getPreOrder();
        String actualInOrder = rbt.getInOrder();
        String actualPostOrder = rbt.getPostOrder();

        // then
        String expected = "";
        assertEquals(expected, actualPreOrder);
        assertEquals(expected, actualInOrder);
        assertEquals(expected, actualPostOrder);
    }

    @Test
    public void should_ReturnPreOrderResult_When_RBTHasData() {
        // given
        String[] data = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M" };

        for (int i = 0; i < data.length; i++) {
            rbt.put(data[i], data[i]);
        }

        // when
        String actual = rbt.getPreOrder();

        // then
        String expected = "H:H D:D B:B A:A C:C F:F E:E G:G L:L J:J I:I K:K M:M ";

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnInOrderResult_When_RBTHasData() {
        // given
        String[] data = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M" };

        for (int i = 0; i < data.length; i++) {
            rbt.put(data[i], data[i]);
        }

        // when
        String actual = rbt.getInOrder();

        // then
        String expected = "A:A B:B C:C D:D E:E F:F G:G H:H I:I J:J K:K L:L M:M ";

        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnPostOrderResult_When_RBTHasData() {
        // given
        String[] data = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M" };

        for (int i = 0; i < data.length; i++) {
            rbt.put(data[i], data[i]);
        }

        // when
        String actual = rbt.getPostOrder();

        // then
        String expected = "A:A C:C B:B E:E G:G F:F D:D I:I K:K J:J M:M L:L H:H ";

        assertEquals(expected, actual);
    }

    @Test
    public void should_DeleteMaxValue_When_RBTHasOnlyRoot() {
        // given
        String keyVal = "something different";

        rbt.put(keyVal, keyVal);

        // when
        rbt.deleteMax();

        Node<String, String> actual = getRoot(rbt);

        // then
        Node<String, String> expected = null;

        assertEquals(expected, actual);
    }

    @Test
    public void should_DeleteMaxValue_When_RootHasOnlyOneLessChild() {
        // given
        String keyVal1 = "A";
        String keyVal2 = "B";

        rbt.put(keyVal1, keyVal1);
        rbt.put(keyVal2, keyVal2);

        // when
        rbt.deleteMax();

        String actual = getRoot(rbt).getValue();

        // then
        String expected = "A";

        assertEquals(expected, actual);

    }

    @Test
    public void should_() {
        // given

        // when

        // then

    }

    private Node<String, String> getRoot(RedBlackTree<String, String> rbt) {
        String fieldRoot = "root";

        try {
            Field field = rbt.getClass().getDeclaredField(fieldRoot);
            field.setAccessible(true);

            Node<String, String> node = (Node<String, String>) field.get(rbt);

            return node;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
