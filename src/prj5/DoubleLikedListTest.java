package prj5;

import java.util.Iterator;
import student.TestCase;

public class DoubleLikedListTest extends TestCase {

    private DoublyLinkedList<Integer> list;
    
    public void setUp() {
        list = new DoublyLinkedList<>();
    }
    
    public void testSort() {
        list.add(1);
        list.add(5);
        list.add(2);
        list.add(0);
        list.add(3);
        System.out.println(list);
        list.sort((n, m) -> Integer.compare(n, m));
        Iterator<Integer> it = list.iterator();
        Integer prev = it.next();
        System.out.println(prev);
        while(it.hasNext()) {
            Integer n = it.next();
            System.out.println(list.toString());
            assertTrue(prev < n);
        }
    }
    
}
