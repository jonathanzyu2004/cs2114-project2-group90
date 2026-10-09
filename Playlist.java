
// -------------------------------------------------------------------------
/**
 * This class represents a LinkedChain of Songs
 * 
 * @author Jonathan Yu
 * @version Oct 8, 2026
 */
public class Playlist
{
    // ~ Fields ................................................................
    private String name;
    private LinkedChain<Song> songs;

    // ~ Constructors ..........................................................
    /**
     * creates a new playlist with the provided name.
     * 
     * @param name
     *            the name of the playlist
     */
    public Playlist(String name)
    {
        this.name = name;

        this.songs = new LinkedChain<Song>();
    }


    // ~Public Methods ........................................................
    /**
     * gets the name of the playlist
     * 
     * @return the name of the playlist
     */
    public String getName()
    {
        return this.name;
    }


    /**
     * add a song to the playlist
     * 
     * @throws PlaylistException
     */
    public void addSong(Song song)
        throws PlaylistException
    {
        checkSongValidity(song);
        songs.addToEnd(song);
    }


    /**
     * Inserts a song at a specific position
     * 
     * @param index
     *            the position we want to insert the new song
     * @param song
     *            the new song to add
     * @throws PlaylistException
     */
    public void insertSong(int index, Song song)
        throws PlaylistException
    {
        checkSongValidity(song);
        checkIndexValidity(index);

        LinkedChain<Song> updated = new LinkedChain<Song>();

        for (int i = 0; i < index; i++)
        {
            updated.addToEnd(songs.get(i));
        }

        updated.addToEnd(song);

        for (int i = index; i < songs.size(); i++)
        {
            updated.addToEnd(songs.get(i));
        }

        songs = updated;
    }


    /**
     * gets a song from a specific position
     * 
     * @return the song at the specified position
     * @throws PlaylistException
     */
    public Song getSong(int index)
        throws PlaylistException
    {
        checkIndexValidity(index);

        return (songs.get(index));
    }


    /**
     * Update a song's title
     * 
     * @param song
     *            the song we want to edit
     * @paran newTitle the new title
     * @throws PlaylistException
     */
    public void editSongTitle(Song song, String newTitle)
        throws PlaylistException
    {
        Song target = findSong(song);
        target.setTitle(newTitle);
    }


    /**
     * Update a song's artist
     * 
     * @param song
     *            the song we want to edit
     * @param newArtist
     *            the new artist
     * @throws PlaylistException
     */
    public void editSongArtist(Song song, String newArtist)
        throws PlaylistException
    {
        Song target = findSong(song);
        target.setArtist(newArtist);
    }


    /**
     * Update a song's album
     * 
     * @param song
     *            the song we want to edit
     * @param newAlbum
     *            the album we want to edit
     * @throws PlaylistException
     */
    public void editSongAlbum(Song song, String newAlbum)
        throws PlaylistException
    {
        Song target = findSong(song);
        target.setAlbum(newAlbum);
    }


    /**
     * Update a song's duration
     * 
     * @throws PlaylistException
     */
    public void editSongDuration(int index, String newDuration)
        throws PlaylistException
    {
        checkIndexValidity(index);
        songs.get(index).setTitle(newDuration);
    }


    /**
     * remove the first matching song
     * 
     * @param song
     *            the song to remove
     * @return the removed song, or null if not found
     * @throws PlaylistException
     */
    public Song removeSong(Song song)
        throws PlaylistException
    {
        checkSongValidity(song);

        for (int i = 0; i < songs.size(); i++)
        {
            Song curr = songs.get(i);

            if (curr.equals(song))
            {
                songs.remove(curr);
                return curr;
            }
        }

        return null;
    }


    /**
     * gets the number of songs in the playlist
     * 
     * @return the number of songs
     */
    public int getNumberOfSong()
    {
        return songs.size();
    }


    /**
     * calculates the total duration of all songs.
     * 
     * @return total duration in minutes.seconds format
     */
    public double getTotalDuration()
    {
        int totalSeconds = 0;

        for (int i = 0; i < songs.size(); i++)
        {
            totalSeconds += toSeconds(songs.get(i).getDuration());
        }

        return secondsToDuration(totalSeconds);
    }


    /**
     * searches songs by artist
     * 
     * @param artist
     *            the artist to search for
     * @return a chain containing matching songs
     */
    public LinkedChain<Song> searchByArtist(String artist)
    {
        LinkedChain<Song> result = new LinkedChain<Song>();

        for (int i = 0; i < songs.size(); i++)
        {
            Song song = songs.get(i);

            if (song.getArtist().equals(artist))
            {
                result.addToEnd(song);
            }
        }

        return result;
    }


    /**
     * searches songs by title
     * 
     * @param title
     *            the title to search for
     * @return a chain containing matching songs
     */
    public LinkedChain<Song> searchByTitle(String title)
    {
        LinkedChain<Song> result = new LinkedChain<Song>();

        for (int i = 0; i < songs.size(); i++)
        {
            Song song = songs.get(i);

            if (song.getTitle().equals(title))
            {
                result.addToEnd(song);
            }
        }

        return result;
    }


    /**
     * Combines two playlist into one
     * 
     * @param playlist1
     *            the first playlist
     * @param playlist2
     *            the second playlist
     * @return a new linkedchian containing both playlists
     * @throws PlaylistException
     */
    public LinkedChain<Song> combinePlaylist(
        LinkedChain<Song> playlistA,
        LinkedChain<Song> playlistB)
        throws PlaylistException
    {
        if (playlistA == null || playlistB == null)
        {
            throw new PlaylistException("Playlist cannot be null");
        }

        LinkedChain<Song> combined = new LinkedChain<Song>();

        for (int i = 0; i < playlistA.size(); i++)
        {
            combined.addToEnd(playlistA.get(i));
        }

        for (int i = 0; i < playlistB.size(); i++)
        {
            combined.addToEnd(playlistB.get(i));
        }

        return combined;
    }


    /**
     * returns a String version of the playlist
     * 
     * @return the playlist name and songs
     */
    @Override
    public String toString()
    {
        return name + ": " + songs.toString();
    }


    /**
     * helper method to check for Song input validity
     * 
     * @throws PlaylistException
     *             if the song input is null
     */
    private void checkSongValidity(Song song)
        throws PlaylistException
    {
        if (song == null)
        {
            throw new PlaylistException("Song cannot be null");
        }
    }


    /**
     * checks if the index is valid
     * 
     * @param index
     *            the index we want to check
     * @throws PlaylistException
     */
    private void checkIndexValidity(int index)
        throws PlaylistException
    {
        if (index < 0 || index > songs.size())
        {

            throw new PlaylistException("Invalid Index");
        }
    }


    /**
     * converts the duration to seconds
     * 
     * @param duration
     *            the duration we want to convert to seconds
     * @return the total number of seconds
     */
    private int toSeconds(double duration)
    {
        int minutes = (int)duration;
        int seconds = (int)Math.round((duration - minutes) * 100);

        return minutes * 60 + seconds;
    }


    /**
     * converts total seconds into minutes.seconds format
     * 
     * @param totalSeconds
     *            the duration in seconds
     * @return duration in minutes.seconds format
     */
    private double secondsToDuration(int totalSeconds)
    {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        return minutes + (seconds / 100.0);
    }


    /**
     * helper method, find a specific song
     * 
     * @param song
     *            the song we want to find
     * @return the song if it was found
     * @throws PlaylistException
     */
    private Song findSong(Song song)
        throws PlaylistException
    {
        if (song == null)
        {
            throw new PlaylistException("Song cannot be null");

        }

        for (int i = 0; i < songs.size(); i++)
        {
            Song cur = songs.get(i);
            if (cur.equals(song))
            {
                return cur;
            }
        }

        throw new PlaylistException("Song not found");
    }
}
