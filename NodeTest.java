import student.TestCase;


// -------------------------------------------------------------------------
/**
 *  test the Node class
 * 
 *  @author Jonathan Yu
 *  @version Oct 7, 2026
 */
public class NodeTest extends TestCase
{
    //~ Fields ................................................................
    private Node<String> node;
    //~ Constructors ..........................................................

    /**
     * sets up the test environment
     */
    public void setUp() {
        node = new Node<String> ("Node");
    }
    
    //~Public  Methods ........................................................
    /**
     * test for the constructor
     */
    public void testConstructor() {
        assertEquals("Node", node.getData());
        assertNull(node.getNext());
    }
    
    /**
     * tests getData(){
     */
    public void testGetData() {
        assertEquals("Node", node.getData());
    }
    
    /**
     * tests setData
     */
    public void testSetData() {
        node.setData("New");
        assertEquals("New", node.getData());
    }
    
    /**
     * tests getNext().
     */
    public void testGetNext() {
        Node<String> nextNode = new Node<String>("Next");
        
        node.setNext(nextNode);

        assertEquals(nextNode, node.getNext());
    }
    
    /**
     * tests setNext().
     */
    public void testSetNext() {
        Node<String> thirdNode = new Node<String>("Third Node");
        
        node.setNext(thirdNode);

        assertEquals(thirdNode, node.getNext());
    }
    
    
    
}
