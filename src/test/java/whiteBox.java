import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by Sonad on 13.10.17.
 */
public class whiteBox {

    @Test
    public void addEmptyKey() {

        Hash hashTable = new Hash();

        hashTable.add("", "");
        hashTable.add(null, "");

        assertNull(hashTable.get(""));

    }

    @Test
    public void addAndGet() {

        Hash hashTable = new Hash();
        String key = "12";
        String value = "Захаров Ю.С.";

        hashTable.add(key, value);

        LinkedList<Pair> testValues = hashTable.get(key);

        assertEquals(true, testValues.size() == 1);
        assertEquals(key, testValues.getFirst().getKey());

    }

    @Test
    public void createCollision() {

        Hash hashTable = new Hash();
        String key1 = "12"; // createFirstHash(key1) == 149
        String key2 = "31"; // createFirstHash(key2) == 149
        String value = "Захаров Ю.С.";

        hashTable.add(key1, value);
        hashTable.add(key2, value);

        LinkedList<Pair> testValues1 = hashTable.get(key1);
        LinkedList<Pair> testValues2 = hashTable.get(key2);

        assertEquals(true, testValues1.size() == 1);
        assertEquals(true, testValues2.size() == 1);

    }

    @Test
    public void addSameKey() {

        Hash hashTable = new Hash();
        String key = "4014-110080";

        String value = "Захаров2";
        String value2 = "Захаров4";
        String value3 = "Захаров16";

        hashTable.add(key, value);
        hashTable.add(key, value2);
        hashTable.add(key, value3);

        LinkedList<Pair> testValues = hashTable.get(key);

        assertEquals(testValues.size(), 3);
        int i = 2;

        for (Pair pair : testValues) {
            assertEquals(key, pair.getKey());
            assertEquals(pair.getValue(), "Захаров"+i);
            i = (int) Math.pow(i, 2);
        }
    }

    @Test
    public void removeNullElement() {

        Hash hashTable = new Hash();

        assertFalse(hashTable.remove(null));


    }

    @Test
    public void removeList() {

        Hash hashTable = new Hash();
        String key = "12";
        String value = "Захаров Ю.С.";

        hashTable.add(key, value);

        assertTrue(hashTable.remove(key));
        assertFalse(hashTable.remove(key));
        assertNull(hashTable.get(key));

    }

    @Test
    public void removeListWithCollision() {

        Hash hashTable = new Hash();
        String key1 = "12"; // createFirstHash(key1) == 149
        String key2 = "31"; // createFirstHash(key2) == 149
        String value = "Захаров Ю.С.";

        hashTable.add(key1, value);
        hashTable.add(key2, value);

        assertTrue(hashTable.remove(key2));
        assertFalse(hashTable.remove(key2));
        assertNull(hashTable.get(key2));

    }

    @Test
    public void removeNonexistentList() {

        Hash hashTable = new Hash();
        String key = "12";
        String value = "Захаров Ю.С.";

        assertFalse(hashTable.remove(key));

    }


    @Test
    public void removeElementFromListWithCollision() {

        Hash hashTable = new Hash();
        String key = "12";
        String key2 = "31";

        String value = "Захаров2";
        String value2 = "Захаров4";
        String value3 = "Захаров16";

        hashTable.add(key, value);
        hashTable.add(key, value2);
        hashTable.add(key, value3);
        hashTable.add(key2, value);
        hashTable.add(key2, value2);
        hashTable.add(key2, value3);

        hashTable.remove(key2, value2);

        for (Pair pair : hashTable.get(key2)) {
            assertFalse(pair.getValue().equals(value2));
        }

    }

    @Test
    public void removeNonexistentValueFromSingleList() {

        Hash hashTable = new Hash();
        String key = "12";
        String value = "Захаров Ю.С.";

        hashTable.add(key, value);

        assertFalse(hashTable.remove(key, "edsfsdf"));

    }
//
//    @Test
//    public void removeFromSingleListWithValue() {
//
//        Hash hashTable = new Hash();
//        String key = "12";
//        String value = "Захаров Ю.С.";
//
//        hashTable.add(key, value);
//
//        assertTrue(hashTable.remove(key, value));
//
//    }


    @Test
    public void removeElementFromList() {

        Hash hashTable = new Hash();
        String key = "12";

        String value = "Захаров2";
        String value2 = "Захаров4";
        String value3 = "Захаров16";

        hashTable.add(key, value);
        hashTable.add(key, value2);
        hashTable.add(key, value3);

        hashTable.remove(key, value3);

        for (Pair pair : hashTable.get(key)) {
            assertFalse(pair.getValue().equals(value3));
        }

    }

    @Test
    public void constructorTest() {

        Hash hashTable = new Hash();
        assertNotNull(hashTable);
    }

    @Test
    public void rmvNEElementFromMEtList() {

        Hash hashTable = new Hash();
        String key = "12";

        String value = "Захаров2";
        String value2 = "Захаров4";
        String value3 = "Захаров16";

        hashTable.add(key, value);
        hashTable.add(key, value2);
        hashTable.add(key, value3);

        hashTable.remove(key, "wwef");

        LinkedList<Pair> testValues = hashTable.get(key);
        assertEquals(true, testValues.size() == 3);

    }

}
