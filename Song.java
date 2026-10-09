/**
 * Stores information about a song
 * 
 * @author kvasallo
 * @version Oct 8, 2026
 */
public class Song {
    // ~ Fields ................................................................
    private String title;
    private String artist;
    private String album;
    private double duration;

    /**
     * Create a new Song object.
     * 
     * @param title
     *            Title of the song
     * @param artist
     *            Artist of the song
     * @param album
     *            Song album is in
     * @param duration
     *            Length of the song
     */
    // ~ Constructors ..........................................................

    public Song(String title, String artist, String album, double duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    // ~Public Methods ........................................................


    /**
     * Get the current value of title.
     * 
     * @return The value of title for this object.
     */
    public String getTitle() {
        return title;
    }


    // ----------------------------------------------------------
    /**
     * Set the value of title for this object.
     * 
     * @param title
     *            The new value for title.
     * @throws PlaylistException
     */
    public void setTitle(String title) throws PlaylistException {
        isNull("Title", title);
        this.title = title;
    }


    // ----------------------------------------------------------
    /**
     * Get the current value of artist.
     * 
     * @return The value of artist for this object.
     */
    public String getArtist() {
        return artist;
    }


    // ----------------------------------------------------------
    /**
     * Set the value of artist for this object.
     * 
     * @param artist
     *            The new value for artist.
     * @throws PlaylistException
     */
    public void setArtist(String artist) throws PlaylistException {
        isNull("Artist", artist);
        this.artist = artist;
    }


    // ----------------------------------------------------------
    /**
     * Get the current value of album.
     * 
     * @return The value of album for this object.
     */
    public String getAlbum() {
        return album;
    }


    // ----------------------------------------------------------
    /**
     * Set the value of album for this object.
     * 
     * @param album
     *            The new value for album.
     * @throws PlaylistException
     */
    public void setAlbum(String album) throws PlaylistException {
        isNull("Album", album);
        this.album = album;
    }


    // ----------------------------------------------------------
    /**
     * Get the current value of duration.
     * 
     * @return The value of duration for this object.
     */
    public double getDuration() {
        return duration;
    }


    // ----------------------------------------------------------
    /**
     * Set the value of duration for this object.
     * 
     * @param duration
     *            The new value for duration.
     * @throws PlaylistException
     */
    public void setDuration(double duration) throws PlaylistException {
        if (duration <= 0.0) {
            throw new PlaylistException(
                "Duration cannot be less than or equal to 0");
        }
        this.duration = duration;
    }


    public String toString() {
        return "Sound Title: " + title + " Artist: " + artist + " Album: "
            + album + " Duration: " + duration;
    }


    private void isNull(String methodName, Object object)
        throws PlaylistException {
        if (object == null) {
            throw new PlaylistException(methodName + " cannot be null");
        }
    }
}
