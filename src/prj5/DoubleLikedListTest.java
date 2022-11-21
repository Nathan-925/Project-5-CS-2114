package prj5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import student.TestCase;

/**
 * Test class for DoublyLinkedList
 * 
 * @author Nathaniel Dunlap (nathan925)
 * @version 11/13/2022
 *
 */
public class DoubleLikedListTest extends TestCase {

    private DoublyLinkedList<Integer> list;

    /**
     * Sets up the test methods
     */
    public void setUp() {
        list = new DoublyLinkedList<>();
    }


    /**
     * Tests add and remove
     */
    public void testAddAndRemove() {
        ArrayList<Integer> tests = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int n = rand.nextInt(20);
            list.add(n);
            tests.add(n);
        }
        Collections.shuffle(tests);
        while (!tests.isEmpty()) {
            assertTrue(list.remove(tests.get(0)));
            tests.remove(0);
        }
        assertFalse(list.remove(1));
    }


    /**
     * Tests get
     */
    public void testGet() {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        for (int i = 0; i < 20; i++) {
            assertEquals(i, (int)list.get(i));
        }
        IllegalArgumentException exc = null;
        try {
            list.get(21);
        }
        catch (IllegalArgumentException e) {
            exc = e;
        }
        assertNotNull(exc);
    }


    /**
     * Tests indexOf
     */
    public void testIndexOf() {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        for (int i = 0; i < 20; i++) {
            assertEquals(i, (int)list.indexOf(i));
        }
        assertEquals(-1, list.indexOf(100));
    }


    /**
     * Tests size
     */
    public void testSize() {
        for (int i = 0; i < 20; i++) {
            assertEquals(i, list.size());
            list.add(i);
        }
    }


    /**
     * Tests clear
     */
    public void testClear() {
        list.add(1);
        list.clear();
        assertEquals(0, list.size());
    }


    /**
     * Tests toString
     */
    public void testToString() {
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        assertEquals("[0, 1, 2, 3, 4]", list.toString());
    }


    /**
     * Tests sort
     */
    public void testSort() {
        list.add(1);
        list.add(5);
        list.add(2);
        list.add(0);
        list.add(3);
        list.sort((n, m) -> Integer.compare(n, m));
        Iterator<Integer> it = list.iterator();
        Integer prev = it.next();
        while (it.hasNext()) {
            Integer n = it.next();
            assertTrue(prev < n);
        }
    }

}
