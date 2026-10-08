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
        Exception error = null;

        try {
            song.setTitle(null);
        }
        catch (PlaylistException e) {
            error = e;
        }
        assertNotNull(error);

        try {
            song.setTitle("new title");
        }
        catch (PlaylistException e) {
            error = e;
        }
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
        Exception error = null;

        try {
            song.setArtist(null);
        }
        catch (PlaylistException e) {
            error = e;
        }
        assertNotNull(error);

        try {
            song.setArtist("new artist");
        }
        catch (PlaylistException e) {
            error = e;
        }
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
        Exception error = null;

        try {
            song.setAlbum(null);
        }
        catch (PlaylistException e) {
            error = e;
        }
        assertNotNull(error);

        try {
            song.setAlbum("new album");
        }
        catch (PlaylistException e) {
            error = e;
        }
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
        Exception error = null;

        try {
            song.setDuration(0.0);
        }
        catch (PlaylistException e) {
            error = e;
        }
        assertNotNull(error);

        try {
            song.setDuration(45.0);
        }
        catch (PlaylistException e) {
            error = e;
        }
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
        assertEquals(
            "Sound Title: title Artist: artist Album: album Duration: 1.0", song
                .toString());
    }

}
