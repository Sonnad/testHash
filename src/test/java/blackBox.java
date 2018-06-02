import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;
/**
 * Created by Sonad on 03.10.17.
 */

public class blackBox {

    @Test
    public void addAndGet() {

        Hash hashTable = new Hash();
        String key = "4014-110080";
        String value = "Захаров Ю.С.";

        hashTable.add(key, value);

        LinkedList<Pair> testValues = hashTable.get(key);

        assertEquals(true, testValues.size() == 1);
        assertEquals(key, testValues.getFirst().getKey());

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

//    @Test
//    public void stressAdd() {
//
//        Hash hashTable = new Hash();
//
//        for (int i = 0; i < 450; i++) {
//            hashTable.add("Element №" + i, "value = " + i);
//        }
//
//        for (int i = 0; i < 450; i++) {
//            assertNotNull("Один из элементов не был добавлен", hashTable.get("Element №" + i));
//        }

   // }

    @Test
    public void addEmptyKey() {

        Hash hashTable = new Hash();

        hashTable.add("", "");

        assertNull(hashTable.get(""));

    }

    @Test
    public void removeOneElementList() {

        Hash hashTable = new Hash();
        String key = "4014-110080";
        String value = "Захаров Ю.С.";

        hashTable.add(key, value);

        assertTrue(hashTable.remove(key, value));
        assertFalse(hashTable.remove(key, value));
        assertNull(hashTable.get(key));

    }

    @Test
    public void removeNullElement() {

        Hash hashTable = new Hash();

        assertFalse(hashTable.remove("null"));


    }

    @Test
    public void removeSeveralElementsList() {

        Hash hashTable = new Hash();
        String key = "4014-110080";

        String value = "Захаров2";
        String value2 = "Захаров4";
        String value3 = "Захаров16";

        hashTable.add(key, value);
        hashTable.add(key, value2);
        hashTable.add(key, value3);

        hashTable.remove(key, value2);

        for (Pair pair : hashTable.get(key)) {
            assertFalse(pair.getValue().equals(value2));
        }

    }

    @Test
    public void removeAllElements() {

        Hash hashTable = new Hash();
        String key = "4014-110080";

        String value = "Захаров2";
        String value2 = "Захаров4";
        String value3 = "Захаров16";

        hashTable.add(key, value);
        hashTable.add(key, value2);
        hashTable.add(key, value3);

        hashTable.remove(key);

        assertNull(hashTable.get(key));

    }


}
