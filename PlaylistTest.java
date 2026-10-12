import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  Test class for Playlist
 * 
 *  @author Jonathan Yu
 *  @version Oct 9, 2026
 */
public class PlaylistTest extends TestCase
{
    //~ Fields ................................................................
    private Playlist playlist;
    private Song song1;
    private Song song2;
    private Song song3;
    
    //~ Constructors ..........................................................
    /**
     * Sets up the test environment
     * Constructor
     */
    public void setUp() {
        playlist = new Playlist("Favorite");
        
        song1 = new Song("Beat It", "Michael Jackson", "Thriller", 4.18);
        
        song2 = new Song("Billie Jean", "Michael Jackson", "Thriller", 4.54);
        
        song3 = new Song("P.Y.T", "Michael Jackson", "Thriller", 3.59);
    }
    //~Public  Methods ........................................................
    /**
     * test getName()
     */
    public void testGetName() {
        assertTrue(playlist.getName().equals("Favorite"));
    }
    
    /**
     * test addSong() and getSong()
     * invalid input
     */
    public void testAddandGetSong1() {
        Exception thrown;
        Song songNull = null;
        try {
            playlist.addSong(songNull);
        }
        
        catch(PlaylistException e){
            thrown = e;
            assertNotNull(thrown);
            assertTrue(thrown instanceof PlaylistException);
            assertEquals(thrown.getMessage(), "Song cannot be null");
        }
        
    }
    
    /**
     * test addSong() and getSong()
     * valid input
     * @throws PlaylistException if the input is invalid
     */
    public void testAddandGetSong2() throws PlaylistException{
        playlist.addSong(song1);
        assertEquals(song1, playlist.getSong(0));
        
        playlist.addSong(song2);
        assertEquals(song2, playlist.getSong(1));
     
        playlist.addSong(song3);
        assertEquals(song3, playlist.getSong(2));
        
        assertEquals(playlist.getNumberOfSong(), 3);
    }
    
    /**
     * test insertSong()
     * invalid input()
     * @throws PlaylistException 
     */
    public void testInsertSong1()  throws PlaylistException{
        playlist.addSong(song1);
        
        Exception thrown;
        try {
            playlist.insertSong(-1, song2);
            
        }
        
        catch (PlaylistException e) {
            thrown = e;
            assertNotNull(thrown);
            assertEquals(thrown.getMessage(), "Invalid Index");
        }
        
        playlist.addSong(song2);
        
        try {
            playlist.insertSong(1, null);
            
        }
        
        catch (PlaylistException e) {
            thrown = e;
            assertNotNull(thrown);
            assertEquals(thrown.getMessage(), "Song cannot be null");
        }
    }
    
    /**
     * test insertSong()
     * valid input()
     * @throws PlaylistException 
     */
    public void testInsertSong2()  throws PlaylistException{
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);
        
        Song song4 = new Song("Bad", "Michael Jackson", "Bad", 4.07);
        
        playlist.insertSong(1,song4);
        
        assertEquals(4, playlist.getNumberOfSong());
        assertEquals(song4, playlist.getSong(1));
    }
    
    /**
     * test editSongTitle()
     * @throws PlaylistException 
     * 
     */
    public void testEditSongTitle() throws PlaylistException {
        playlist.addSong(song1);
        Exception thrown;
       
        try {
            playlist.editSongTitle(song1, null);
        }
        
        catch(PlaylistException e) {
            thrown = e;
            assertNotNull(thrown);
            assertEquals(thrown.getMessage(), "Title cannot be null");
        }
        
        playlist.editSongTitle(song1, "Human Nature");
        
        assertEquals(playlist.getSong(0).getTitle(), "Human Nature");
        
    }
    
    /**
     * test editSongArtist()
     * @throws PlaylistException 
     * 
     */
    public void testEditSongArtist() throws PlaylistException {
        playlist.addSong(song1);
        
        playlist.editSongArtist(song1, "Jackson5");
        
        assertEquals(playlist.getSong(0).getArtist(), "Jackson5");
        
    }
    
    /**
     * test editSongDuration()
     * @throws PlaylistException 
     * 
     */
    public void testEditSongDuration() throws PlaylistException {
        playlist.addSong(song1);
        
        playlist.editSongDuration(song1, 4.50);
        
        assertEquals(playlist.getSong(0).getDuration(), 4.50, 0.00);
        
    }
    
    /**
     * test removeSong()
     * @throws PlaylistException 
     */
    public void testRemoveSong() throws PlaylistException {
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);
        
        Song removed = playlist.removeSong(song1);
        
        assertEquals(removed, song1);
        assertEquals(playlist.getNumberOfSong(), 2);
        assertEquals(playlist.getSong(0), song2);
        playlist.addSong(song1);
        
        Exception thrown;
        try {
            Song removed2 = playlist.removeSong(null);
            assertNull(removed2);
            
        }
        
        catch (PlaylistException e) {
            thrown = e;
            assertNotNull(thrown);
            assertEquals(thrown.getMessage(), "Song cannot be null");
            
        }
        
        Song song4 = new Song("Man in the Mirror", "Michael Jackson", "Bad", 5.04);
        Song removed3 = playlist.removeSong(song4);
        assertNull(removed3);
        
    }
    
    
    /**
     * test getTotalDuration
     * @throws PlaylistException 
     */
    public void testGetTotalDuration() throws PlaylistException {
        assertEquals(0.0, playlist.getTotalDuration(), 0.00);
        playlist.addSong(song1);
        assertEquals(4.18, playlist.getTotalDuration(), 0.00);
        
        playlist.addSong(song2);
        playlist.addSong(song3);
        
        assertEquals(13.11, playlist.getTotalDuration(), 0.00);
        
        
    }
    
    /**
     * test searchByArtist()
     * @throws PlaylistException 
     */
    public void testSearchByArtist() throws PlaylistException {
        playlist.addSong(song1);
        playlist.addSong(song2);
        
        Song song4 = new Song("Sweer Caroline", "Neil Diamond", "Brother Love's Travelling Salvation Show", 3.24);
        playlist.addSong(song4);
        
        LinkedChain<Song> result = playlist.searchByArtist("Neil Diamond");
        assertEquals(result.size(), 1);
        assertEquals(song4, result.get(0));
        
        LinkedChain<Song> result2 = playlist.searchByArtist("Michael Jackson");
        assertEquals(result2.size(), 2);
        assertEquals(song1, result2.get(0));
        assertEquals(song2, result2.get(1));
        
        LinkedChain<Song> result3 = playlist.searchByArtist("Ed Sheeran");
        assertEquals(result3.size(), 0);
        assertTrue(result3.isEmpty());
        
        
    }
    
    /**
     * test searchByTitle()
     * @throws PlaylistException 
     */
    public void testSearchByTitle() throws PlaylistException {
        song1 = new Song("Love Story", "Taylor Swift", "Fearless", 3.55);
        song2 = new Song("Love Yourself", "Justin Bieber", "Purpose", 3.53);
        song3 = new Song("Love Me Like You Do", "Ellie Goulding", "Fifty Shades of Grey", 4.10);
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);
        
        LinkedChain<Song> result = playlist.searchByTitle("Love Story");
        assertEquals(1, result.size());
        assertEquals(song1, result.get(0));
        
        LinkedChain<Song> result2 = playlist.searchByTitle("Love");
        assertEquals(3, result2.size());
        assertEquals(song1, result2.get(0));
        assertEquals(song2, result2.get(1));
        assertEquals(song3, result2.get(2));
        
        LinkedChain<Song> result3 = playlist.searchByTitle("Man");
        assertEquals(0, result3.size());
        assertTrue(result3.isEmpty());
    }
    
    /**
     * test combinedPlaylist()
     * @throws PlaylistException 
     * 
     */
    public void testCombinedPlaylist() throws PlaylistException {
        playlist.addSong(song1);
        
        Playlist playlistB = null;
        
        Exception thrown;
        try {
            LinkedChain<Song> combined = playlist.combinePlaylist(playlistB);
        }
        
        catch(PlaylistException e) {
            thrown = e;
            assertNotNull(thrown);
            assertEquals(thrown.getMessage(), "Playlist cannot be null");
        }
        
        playlistB = new Playlist("Second");
        
        playlistB.addSong(song2);
        playlistB.addSong(song3);
        
        LinkedChain<Song> combined = playlist.combinePlaylist(playlistB);
        assertEquals(3, combined.size());
        assertEquals(song1, combined.get(0));
        assertEquals(song2, combined.get(1));
        assertEquals(song3, combined.get(2));
    }
    
    /**
     * test toString()
     * @throws PlaylistException 
     */
    public void testToString() throws PlaylistException {
        assertEquals("Favorite: []", playlist.toString());
        
        playlist.addSong(song1);
        playlist.addSong(song2);
        
        String expected = "Favorite: [" 
            + song1.toString() + ", "
            + song2.toString() + "]";

        assertEquals(expected, playlist.toString());
    }
}
