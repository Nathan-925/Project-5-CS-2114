package prj5;

import java.util.Comparator;
import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {

    private ListNode<T> firstNode;
    private ListNode<T> lastNode;
    private int size;
    
    public void add(T value) {
        if(firstNode == null) {
            firstNode = lastNode = new ListNode<>(null, value, null);
            size++;
            return;
        }
        lastNode.setNext(new ListNode<>(lastNode, value, null));
        lastNode = lastNode.getNext();
        size++;
    }
    
    public boolean remove(T value) {
        if(firstNode != null && firstNode.getValue() == value) {
            firstNode = firstNode.getNext();
            firstNode.setPrevious(null);
            size--;
            return true;
        }
        ListNode<T> current = firstNode;
        while(current != null && current.getValue() != value) {
            current = current.getNext();
        }
        if(current == null) {
            return false;
        }
        current.getPrevious().setNext(current.getNext());
        if(current.getNext() != null) {
            current.getNext().setPrevious(current.getPrevious());
        }
        size--;
        return true;
    }
    
    public T get(int index) {
        if(index >= size) {
            throw new IllegalArgumentException(String.format("index %d out of bounds for size %d", index, size));
        }
        ListNode<T> current = firstNode;
        for(int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }
    
    public int indexOf(T value) {
        ListNode<T> current = firstNode;
        int index = 0;
        while(current != null) {
            if(current.getValue().equals(value)) {
                return index;
            }
            index++;
            current = current.getNext();
        }
        return -1;
    }
    
    public int getSize() {
        return size;
    }
    
    public void clear() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private ListNode<T> current = firstNode;
            
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
    
    public void sort(Comparator<T> comparator) {
        ListNode<T> sortNode = firstNode.getNext();
        while(sortNode != null) {
            ListNode<T> current = sortNode;
            sortNode = sortNode.getNext();
            while(current.getPrevious() != null && comparator.compare(current.getValue(), current.getPrevious().getValue()) < 0) {
                T temp = current.getValue();
                current.setValue(current.getPrevious().getValue());
                current.getPrevious().setValue(temp);
                
                current = current.getPrevious();
            }
        }
    }
    
    public String toString() {
        ListNode<T> current = firstNode;
        String s = "";
        while(current != null) {
            s += current.getValue().toString()+" ";
            current = current.getNext();
        }
        return s;
    }
    
    private class ListNode<T> {
        private ListNode<T> nextNode;
        private ListNode<T> previousNode;
        private T value;
        
        public ListNode(ListNode<T> previousNode, T value, ListNode<T> nextNode) {
            this.nextNode = nextNode;
            this.value = value;
            this.previousNode = previousNode;
        }
        
        public T getValue() {
            return value;
        }
        
        public ListNode<T> getNext(){
            return nextNode;
        }
        
        public ListNode<T> getPrevious(){
            return previousNode;
        }
        
        public void setNext(ListNode<T> nextNode) {
            this.nextNode = nextNode;
        }
        
        public void setPrevious(ListNode<T> previousNode) {
            this.previousNode = previousNode;
        }
        
        public void setValue(T value) {
            this.value = value;
        }
    }
    
}
