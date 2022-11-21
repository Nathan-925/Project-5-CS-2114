package prj5;

import java.util.Comparator;
import java.util.Iterator;

/**
 * A doubly linked list with generic typing
 * 
 * @author Nathaniel Dunlap (nathan925)
 * @version 11/13/2022
 *
 * @param <T>
 *            the data type the linked list will hold
 */
public class DoublyLinkedList<T> implements Iterable<T> {

    private ListNode firstNode;
    private ListNode lastNode;
    private int size;

    /**
     * Adds a value to the end of the linked list
     * 
     * @param value
     *            the value to add to the list
     */
    public void add(T value) {
        if (firstNode == null) {
            firstNode = lastNode = new ListNode(null, value, null);
            size++;
            return;
        }
        lastNode.setNext(new ListNode(lastNode, value, null));
        lastNode = lastNode.getNext();
        size++;
    }


    /**
     * Removes the given value from the list
     * 
     * @param value
     *            the value to remove
     * @return true if a value was successfully removed
     */
    public boolean remove(T value) {
        if (firstNode != null && firstNode.getValue().equals(value)) {
            firstNode = firstNode.getNext();
            if (firstNode != null) {
                firstNode.setPrevious(null);
            }
            size--;
            return true;
        }
        ListNode current = firstNode;
        while (current != null && !current.getValue().equals(value)) {
            current = current.getNext();
        }
        if (current == null) {
            return false;
        }
        current.getPrevious().setNext(current.getNext());
        if (current.getNext() != null) {
            current.getNext().setPrevious(current.getPrevious());
        }
        size--;
        return true;
    }


    /**
     * Gets the value at a given index
     * 
     * @param index
     *            the index to retrieve
     * @return the value at the given index.
     */
    public T get(int index) {
        if (index >= size) {
            throw new IllegalArgumentException(String.format(
                "index %d out of bounds for size %d", index, size));
        }
        ListNode current = firstNode;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }


    /**
     * Returns the index of a given value
     * 
     * @param value
     *            the value to search for
     * @return the index of the given value or -1 if the value is not in the
     *         list
     */
    public int indexOf(T value) {
        ListNode current = firstNode;
        int index = 0;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return index;
            }
            index++;
            current = current.getNext();
        }
        return -1;
    }


    /**
     * Returns the size of the list
     * 
     * @return the size of the list
     */
    public int size() {
        return size;
    }


    /**
     * Removes all values from the list
     */
    public void clear() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }


    /**
     * Returns an iterator that iterates through the values in the list in order
     * 
     * @return an iterator that iterates through the values in the list in order
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private ListNode current = firstNode;

            @Override
            public boolean hasNext() {
                return current != null;
            }


            @Override
            public T next() {
                T out = current.getValue();
                current = current.getNext();
                return out;
            }

        };
    };


    /**
     * Sorts the list using the given comparator
     * 
     * @param comparator
     *            the comparator to sort by
     */
    public void sort(Comparator<T> comparator) {
        ListNode sortNode = firstNode.getNext();
        while (sortNode != null) {
            ListNode current = sortNode;
            sortNode = sortNode.getNext();
            while (current.getPrevious() != null && comparator.compare(current
                .getValue(), current.getPrevious().getValue()) < 0) {
                T temp = current.getValue();
                current.setValue(current.getPrevious().getValue());
                current.getPrevious().setValue(temp);

                current = current.getPrevious();
            }
        }
    }


    /**
     * Returns a string representation of the list
     * 
     * @return a string representation of the list
     */
    public String toString() {
        ListNode current = firstNode;
        String s = "[";
        while (current != null) {
            s += current.getValue() + ", ";
            current = current.getNext();
        }
        return s.substring(0, s.length() - 2) + "]";
    }

    private class ListNode {
        private ListNode nextNode;
        private ListNode previousNode;
        private T value;

        public ListNode(ListNode previousNode, T value, ListNode nextNode) {
            this.nextNode = nextNode;
            this.value = value;
            this.previousNode = previousNode;
        }


        public T getValue() {
            return value;
        }


        public ListNode getNext() {
            return nextNode;
        }


        public ListNode getPrevious() {
            return previousNode;
        }


        public void setNext(ListNode nextNode) {
            this.nextNode = nextNode;
        }


        public void setPrevious(ListNode previousNode) {
            this.previousNode = previousNode;
        }


        public void setValue(T value) {
            this.value = value;
        }
    }

}
