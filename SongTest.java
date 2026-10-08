import student.TestCase;

/**
 * Test class for Song
 * 
 * @author kvasallo
 * @version Oct 8, 2026
 */
public class SongTest extends TestCase {
    // ~ Fields ................................................................

    private Song song;

    // ~Public Methods ........................................................

    public void setUp() {
        song = new Song("title", "artist", "album", 1.0);
    }


    // ----------------------------------------------------------
    /**
     * Test setTitle method
     * 
     */
    public void testSetTitle() {
        assertEquals("title", song.getTitle());
        song.setTitle("new title");
        assertEquals("new title", song.getTitle());

    }


    // ----------------------------------------------------------
    /**
     * Test getTitle method
     * 
     */
    public void testGetTitle() {
        assertEquals("title", song.getTitle());
    }


    // ----------------------------------------------------------
    /**
     * Test setArtist method
     * 
     */
    public void testSetArtist() {
        assertEquals("artist", song.getArtist());
        song.setArtist("new artist");
        assertEquals("new artist", song.getArtist());
    }


    // ----------------------------------------------------------
    /**
     * Test getArtist method
     * 
     */
    public void testGetArtist() {
        assertEquals("artist", song.getArtist());
    }


    // ----------------------------------------------------------
    /**
     * Test setAlbum method
     * 
     */
    public void testSetAlbum() {
        assertEquals("album", song.getAlbum());
        song.setAlbum("new album");
        assertEquals("new album", song.getAlbum());

    }


    // ----------------------------------------------------------
    /**
     * Test getAlbum method
     * 
     */
    public void testGetAlbum() {
        assertEquals("album", song.getAlbum());
    }


    // ----------------------------------------------------------
    /**
     * Test setDuration method
     * 
     */
    public void testSetDuration() {
        assertEquals(1.0, song.getDuration(), 3);
        song.setDuration(45.0);
        assertEquals(45.0, song.getDuration(), 3);
    }


    // ----------------------------------------------------------
    /**
     * Test getDuration method
     * 
     */
    public void testGetDuration() {
        assertEquals(1.0, song.getDuration(), 3);
    }
    
    // ----------------------------------------------------------
    /**
     * Test toString method
     */
    public void testToString() {
        assertEquals("Sound Title: title Artist: artist Album: album Duration: 1.0", song.toString());
    }

}
