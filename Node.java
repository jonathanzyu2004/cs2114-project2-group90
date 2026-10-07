
/**
 *  Represents one node in a linked chain
 *  @param <T>
 * 
 *  @author Jonathan Yu
 *  @version Oct 2, 2026
 */
public class Node<T> {
    private T data;
    private Node<T> next;
    
    /**
     * Creates a node containing the specified data.
     * @param data the data to store in the node
     * 
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
    
    /**
     * Returns the data stored in this node.
     *
     * @return the data stored in the node
     */
    public T getData() {
        return this.data;
    }
    
    /**
     * Sets the data stored in this node.
     *
     * @param data the new data
     */
    public void setData(T data) {
        this.data = data;
    }
    
    /**
     * Returns the next node in the chain.
     *
     * @return the next node, or null if this is the last node
     */
    public Node<T> getNext() {
        return this.next;
    }
    
    /**
     * Sets the next node in the chain.
     *
     * @param next the node to follow this node
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}