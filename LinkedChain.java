/**
 *  Represents a generic linked chain
 *  @param <T> the type of elements stored in the chain
 * 
 *  @author Jonathan Yu
 *  @version Oct 5, 2026
 */

public class LinkedChain<T>
{
    //~ Fields ................................................................
    private Node<T> head;
    private Node<T> tail;
    private int size;
    
    //~ Constructors ..........................................................
    /**
     * Creates an empty linked chain
     */
    public LinkedChain() {
        head = null;
        tail = null;
        size = 0;
    }
    
    //~Public  Methods ........................................................
    /**
     * adds an entry to the beginning of the chain
     * 
     * @param entry the entry to add
     * @return true if the act is successful
     */
    public boolean add(T entry) {
        Node<T> newNode = new Node<T> (entry);
        
        if(isEmpty()) {
            head = newNode;
            tail = newNode;
        }
        
        else {
            newNode.setNext(head);
            head = newNode;
        }
        
        size++;
        return true;
    }
    
    /**
     * Adds an entry to the end of the chain.
     *
     * @param entry the entry to add
     * @return true after the entry is added
     */
    public boolean addToEnd(T entry) {
        Node<T> newNode = new Node<T> (entry);
        
        if(isEmpty()) {
            head = newNode;
            tail = newNode;
        }
        
        else {
            tail.setNext(newNode);
            tail = newNode;
        }
        
        size++;
        return true;
    }
    
    /**
     * Adds an entry at the specified index.
     *
     * @param index the position where the entry should be added
     * @param entry the entry to add
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public void add(int index, T entry) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + size
            );
        }
        
        if (index == 0) {
            add(entry);
            return;
        }
        
        if (index == size) {
            addToEnd(entry);
            return;
        }
        
        Node<T> newNode = new Node<T>(entry);
        Node<T> previous = head;

        for (int i = 0; i < index - 1; i++) {
            previous = previous.getNext();
        }

        newNode.setNext(previous.getNext());
        previous.setNext(newNode);

        size++;
    }
    
    /**
     * Removes and returns the first entry in the chain.
     *
     * @return the entry that was removed
     * @throws IndexOutOfBoundsException if the chain is empty
     */
    public T remove() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException(
                "Cannot remove from an empty chain."
            );
        }

        T removedData = head.getData();
        head = head.getNext();
        size--;

        if (size == 0) {
            tail = null;
        }

        return removedData;
    }
    
    /**
     * Removes the first occurrence of the specified entry.
     *
     * @param entry the entry to remove
     * @return true if the entry was found and removed, false otherwise
     */
    public boolean remove(T entry) {
        if (isEmpty()) {
            return false;
        }

        if (head.getData().equals(entry)) {
            remove();
            return true;
        }

        Node<T> current = head;

        while (current.getNext() != null) {
            if (current.getNext().getData().equals(entry)) {

                if (current.getNext() == tail) {
                    tail = current;
                }

                current.setNext(current.getNext().getNext());
                size--;

                return true;
            }

            current = current.getNext();
        }

        return false;
    }
    
    /**
     * Removes all entries from the chain.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    
    /**
     * Returns the entry at the specified index.
     *
     * @param index the index of the entry to retrieve
     * @return the entry at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public T get(int index) {
        checkIndex(index);

        Node<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.getData();
    }
    
    /**
     * Determines whether the specified entry is contained in the chain.
     *
     * @param entry the entry to search for
     * @return true if the entry is found, false otherwise
     */
    public boolean contains(T entry) {
        Node<T> current = head;

        while (current != null) {
            if (current.getData().equals(entry)) {
                return true;
            }

            current = current.getNext();
        }

        return false;
    }
    
    /**
     * Counts the number of occurrences of the specified entry.
     *
     * @param entry the entry to count
     * @return the number of times the entry occurs in the chain
     */
    public int count(T entry) {
        int count = 0;
        Node<T> current = head;

        while (current != null) {
            if (current.getData().equals(entry)) {
                count++;
            }

            current = current.getNext();
        }

        return count;
    }
    
    /**
     * Returns the number of entries in the chain.
     *
     * @return the number of entries
     */
    public int size() {
        return size;
    }

    /**
     * Determines whether the chain is empty.
     *
     * @return true if the chain is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Returns the entries in the chain as an array.
     *
     * @return an array containing the entries in chain order
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] array = (T[]) new Object[size];

        Node<T> current = head;

        for (int i = 0; i < size; i++) {
            array[i] = current.getData();
            current = current.getNext();
        }

        return array;
    }
    
    /**
     * Returns a readable representation of the chain.
     *
     * @return a string representation of the chain
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        Node<T> current = head;

        while (current != null) {
            result.append(current.getData());

            if (current.getNext() != null) {
                result.append(" -> ");
            }

            current = current.getNext();
        }

        return result.toString();
    }
    
    /**
     * Checks whether an index is valid for accessing an existing entry.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + size
            );
        }
    }
}
