import student.TestCase;


// -------------------------------------------------------------------------
/**
 *  Test the LinkedChain class
 * 
 *  @author Jonathan Yu
 *  @version Oct 7, 2026
 */
public class LinkedChainTest extends TestCase
{
    //~ Fields ................................................................
    private LinkedChain<String> chain; 
    
    //~ Constructors ..........................................................

    /**
     * sets up the test environment
     */
    public void setUp() {
        chain = new LinkedChain<String>();
    }
    
    //~Public  Methods ........................................................
    /**
     * tests the constructor.
     */
    public void testConstructor() {
        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
    }

    /**
     * tests add().
     * @throws PlaylistException 
     */
    public void testAdd() throws PlaylistException{
        assertTrue(chain.add("A"));
        assertTrue(chain.add("B"));

        assertEquals(2, chain.size());
        assertEquals("B", chain.get(0));
        assertEquals("A", chain.get(1));
    }
    
    /**
     * Tests addToEnd().
     * @throws PlaylistException
     */
    public void testAddToEnd() throws PlaylistException{
        assertTrue(chain.addToEnd("A"));
        assertTrue(chain.addToEnd("B"));
        assertTrue(chain.addToEnd("C"));

        assertEquals(3, chain.size());
        assertEquals("A", chain.get(0));
        assertEquals("B", chain.get(1));
        assertEquals("C", chain.get(2));
    }

    /**
     * Tests indexed add at the beginning.
     * @throws PlaylistException
     */
    public void testAddAtBeginning() throws PlaylistException {
        chain.addToEnd("B");
        chain.addToEnd("C");

        chain.add(0, "A");

        assertEquals(3, chain.size());
        assertEquals("A", chain.get(0));
        assertEquals("B", chain.get(1));
        assertEquals("C", chain.get(2));
    }

    /**
     * Tests indexed add in the middle.
     * @throws PlaylistException
     */
    public void testAddInMiddle() throws PlaylistException {
        chain.addToEnd("A");
        chain.addToEnd("C");

        chain.add(1, "B");

        assertEquals(3, chain.size());
        assertEquals("A", chain.get(0));
        assertEquals("B", chain.get(1));
        assertEquals("C", chain.get(2));
    }

    /**
     * Tests indexed add at the end.
     * @throws PlaylistException
     */
    public void testAddAtEnd() throws PlaylistException {
        chain.addToEnd("A");
        chain.addToEnd("B");

        chain.add(2, "C");

        assertEquals(3, chain.size());
        assertEquals("A", chain.get(0));
        assertEquals("B", chain.get(1));
        assertEquals("C", chain.get(2));
    }

    /**
     * Tests indexed add to an empty chain.
     * @throws PlaylistException
     */
    public void testAddToEmptyChain() throws PlaylistException {
        chain.add(0, "A");

        assertEquals(1, chain.size());
        assertEquals("A", chain.get(0));
    }

    /**
     * Tests indexed add with a negative index.
     */
    public void testAddNegativeIndex() {
        try {
            chain.add(-1, "A");
            fail();
        }
        catch (PlaylistException e) {
            assertEquals("Invalid Index", e.getMessage());
        }
    }

    /**
     * Tests indexed add with an index greater than size.
     */
    public void testAddTooLargeIndex() {
        chain.addToEnd("A");

        try {
            chain.add(2, "B");
            fail();
        }
        catch (PlaylistException e) {
            assertEquals("Invalid Index", e.getMessage());
        }
    }
    
    /**
     * Tests indexed add when the index is greater than 1.
     * @throws PlaylistException
     */
    public void testAddInMiddleMultipleSteps() throws PlaylistException {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("D");

        chain.add(2, "C");

        assertEquals(4, chain.size());
        assertEquals("A", chain.get(0));
        assertEquals("B", chain.get(1));
        assertEquals("C", chain.get(2));
        assertEquals("D", chain.get(3));
    }

    /**
     * Tests remove().
     * @throws PlaylistException
     */
    public void testRemove() throws PlaylistException {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertEquals("A", chain.remove());
        assertEquals(2, chain.size());
        assertEquals("B", chain.get(0));
        assertEquals("C", chain.get(1));
    }

    /**
     * Tests remove() until the chain is empty.
     * @throws PlaylistException
     */
    public void testRemoveUntilEmpty() throws PlaylistException {
        chain.addToEnd("A");

        assertEquals("A", chain.remove());
        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
    }

    /**
     * Tests remove() on an empty chain.
     */
    public void testRemoveEmptyChain() {
        try {
            chain.remove();
            fail();
        }
        catch (PlaylistException e) {
            assertEquals("Playlist is empty", e.getMessage());
        }
    }

    /**
     * Tests remove(T) when the entry is the head.
     * @throws PlaylistException
     */
    public void testRemoveHead() throws PlaylistException {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertTrue(chain.remove("A"));

        assertEquals(2, chain.size());
        assertEquals("B", chain.get(0));
        assertEquals("C", chain.get(1));
    }

    /**
     * Tests remove(T) when the entry is in the middle.
     * @throws PlaylistException
     */
    public void testRemoveMiddle() throws PlaylistException {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertTrue(chain.remove("B"));

        assertEquals(2, chain.size());
        assertEquals("A", chain.get(0));
        assertEquals("C", chain.get(1));
    }

    /**
     * Tests remove(T) when the entry is the tail.
     * @throws PlaylistException
     */
    public void testRemoveTail() throws PlaylistException {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertTrue(chain.remove("C"));

        assertEquals(2, chain.size());
        assertEquals("A", chain.get(0));
        assertEquals("B", chain.get(1));
    }

    /**
     * Tests remove(T) when the entry is not found.
     * @throws PlaylistException
     */
    public void testRemoveNotFound() throws PlaylistException {
        chain.addToEnd("A");
        chain.addToEnd("B");

        assertFalse(chain.remove("C"));
        assertEquals(2, chain.size());
    }

    /**
     * Tests remove(T) on an empty chain.
     * @throws PlaylistException
     */
    public void testRemoveEntryFromEmptyChain() throws PlaylistException {
        assertFalse(chain.remove("A"));
        assertTrue(chain.isEmpty());
    }

    /**
     * Tests clear().
     * @throws PlaylistException
     */
    public void testClear() throws PlaylistException {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        chain.clear();

        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
        assertEquals("", chain.toString());
    }

    /**
     * Tests get() with valid indexes.
     * @throws PlaylistException
     */
    public void testGet() throws PlaylistException {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertEquals("A", chain.get(0));
        assertEquals("B", chain.get(1));
        assertEquals("C", chain.get(2));
    }

    /**
     * Tests get() with a negative index.
     */
    public void testGetNegativeIndex() {
        try {
            chain.get(-1);
            fail();
        }
        catch (PlaylistException e) {
            assertEquals("Invalid index", e.getMessage());
        }
    }

    /**
     * Tests get() with an index equal to size.
     */
    public void testGetTooLargeIndex() {
        chain.addToEnd("A");

        try {
            chain.get(1);
            fail();
        }
        catch (PlaylistException e) {
            assertEquals("Invalid index", e.getMessage());
        }
    }

    /**
     * Tests get() on an empty chain.
     */
    public void testGetEmptyChain() {
        try {
            chain.get(0);
            fail();
        }
        catch (PlaylistException e) {
            assertEquals("Invalid index", e.getMessage());
        }
    }

    /**
     * Tests contains().
     */
    public void testContains() {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertTrue(chain.contains("A"));
        assertTrue(chain.contains("B"));
        assertTrue(chain.contains("C"));
        assertFalse(chain.contains("D"));
    }

    /**
     * Tests contains() on an empty chain.
     */
    public void testContainsEmptyChain() {
        assertFalse(chain.contains("A"));
    }

    /**
     * Tests count().
     */
    public void testCount() {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("A");
        chain.addToEnd("C");
        chain.addToEnd("A");

        assertEquals(3, chain.count("A"));
        assertEquals(1, chain.count("B"));
        assertEquals(0, chain.count("D"));
    }

    /**
     * Tests count() on an empty chain.
     */
    public void testCountEmptyChain() {
        assertEquals(0, chain.count("A"));
    }

    /**
     * Tests size().
     * @throws PlaylistException
     */
    public void testSize() throws PlaylistException {
        assertEquals(0, chain.size());

        chain.add("A");
        assertEquals(1, chain.size());

        chain.add("B");
        assertEquals(2, chain.size());

        chain.remove();
        assertEquals(1, chain.size());
    }

    /**
     * Tests isEmpty().
     * @throws PlaylistException
     */
    public void testIsEmpty() throws PlaylistException {
        assertTrue(chain.isEmpty());

        chain.add("A");
        assertFalse(chain.isEmpty());

        chain.remove();
        assertTrue(chain.isEmpty());
    }

    /**
     * Tests toArray() on an empty chain.
     */
    public void testToArrayEmpty() {
        Object[] array = chain.toArray();

        assertEquals(0, array.length);
    }

    /**
     * Tests toArray().
     */
    public void testToArray() {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        Object[] array = chain.toArray();

        assertEquals(3, array.length);
        assertEquals("A", array[0]);
        assertEquals("B", array[1]);
        assertEquals("C", array[2]);
    }

    /**
     * Tests toString() on an empty chain.
     */
    public void testToStringEmpty() {
        assertEquals("", chain.toString());
    }

    /**
     * Tests toString().
     */
    public void testToString() {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertEquals("A -> B -> C", chain.toString());
    }

    /**
     * Tests adding after clearing the chain.
     * @throws PlaylistException
     */
    public void testAddAfterClear() throws PlaylistException {
        chain.addToEnd("A");
        chain.addToEnd("B");

        chain.clear();

        chain.addToEnd("C");

        assertEquals(1, chain.size());
        assertEquals("C", chain.get(0));
        assertEquals("C", chain.toString());
    }
}
