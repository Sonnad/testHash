import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Created by Sonad on 03.10.17.
 */
public class Hash {

    private LinkedList<Pair>[] hashTable;

    public Hash()
    {
        hashTable = new LinkedList[500];

        for (int i = 0; i < 100; i++) {
            this.add("adjfda"+i+"dfsd" , "dslfjsdf");
        }

        for (int i = 0; i < 100; i++) {
            this.get("adjfda"+i+"dfsd");
        }

        for (int i = 0; i < 100; i++) {
            this.remove("adjfda"+i+"dfsd");
        }
    }

    int createFirstHash(final String key) {
        int value = 0;

        for (int i = 0, n = 2; i < key.length(); i++) {
            value += ((int) key.charAt(i) * Math.pow(n, i)) % hashTable.length;
        }

        return value % hashTable.length;
    }

    private int createSecondHash(final String key) {
        int value = 0;

        for (int i = 0, n = 3, s = 3; i < key.length(); i++, s*=2) {
            value += ((int) key.charAt(i) * Math.pow(n, i) + s) % hashTable.length;
        }

        return value % hashTable.length;
    }

    public void add(final String key, final String value)
    {
        if (key == null  || key.equals("")) return;

        int x = createFirstHash(key);
        int y = createSecondHash(key);
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[x] == null) {
                hashTable[x] = new LinkedList<>();
                hashTable[x].add(new Pair(key, value));
                return;
            }
            else {
                if(hashTable[x].getFirst().getKey().equals(key)) {
                    hashTable[x].add(new Pair(key, value));
                    return;
                }

            }
            x = (x+y) % hashTable.length;
        }
    }

    public LinkedList<Pair> get(final String key) {
        int x = createFirstHash(key);
        int y = createSecondHash(key);
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[x] != null) {
                if (hashTable[x].getFirst().getKey().equals(key))
                    return hashTable[x];
            }
            x = (x+y) % hashTable.length;
        }
        return null;
    }

    public boolean remove(final String key, final String value) {
        if (key == null  || key.equals("")) return false;
        int x = createFirstHash(key);
        int y = createSecondHash(key);
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[x] != null) {
                if (hashTable[x].getFirst().getKey().equals(key)) {
                    if (hashTable[x].size() == 1) {
                        if (hashTable[x].getFirst().getValue().equals(value)) {
                            hashTable[x] = null;
                            return true;
                        }
                        else return false;
                    }
                    else return removeFromList(hashTable[x], value);

                }

            }
            x = (x+y) % hashTable.length;
        }
        return false;
    }

    public boolean remove(final String key) {
        if (key == null  || key.equals("")) return false;
        int x = createFirstHash(key);
        int y = createSecondHash(key);
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[x] != null) {
                if (hashTable[x].getFirst().getKey().equals(key)) {
                    hashTable[x] = null;
                    return true;
                }

            }
            x = (x+y) % hashTable.length;
        }
        return false;
    }

    private boolean removeFromList(LinkedList<Pair> list, final String value) {
        ListIterator<Pair> iter = list.listIterator();
        while (iter.hasNext()) {
            Pair pair = iter.next();
            if (pair.getValue().equals(value)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }


}
