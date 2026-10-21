import java.util.Scanner;

/**
 * Runs the TuneChain console menu.
 * Written with assistance from OpenAI Codex.
 *
 * @author Zhaojin Gao
 * @version October 13, 2026
 */
public class Main
{
    private static Scanner keyboard = new Scanner(System.in);
    private static LinkedChain<Playlist> playlists = new LinkedChain<Playlist>();
    private static Playlist current;

    /**
     * Starts the program.
     * @param args unused command line arguments
     */
    public static void main(String[] args)
    {
        runMenu();
    }

    /** Displays the available choices. */
    public static void displayMenu()
    {
        System.out.println("\nTuneChain");
        if (current != null)
        {
            System.out.println("Current playlist: " + current.getName());
        }
        System.out.println("1. Create a playlist");
        System.out.println("2. Select a playlist");
        System.out.println("3. View songs and total duration");
        System.out.println("4. Add a song");
        System.out.println("5. Insert a song");
        System.out.println("6. Remove a song");
        System.out.println("7. Edit a song");
        System.out.println("8. Search by title");
        System.out.println("9. Search by artist");
        System.out.println("10. Combine two playlists");
        System.out.println("11. Undo last add/remove action");
        System.out.println("0. Exit");
    }

    /**
     * Reads a valid menu choice. End of input also exits the menu.
     * @return a choice from 0 to 11
     */
    public static int readChoice()
    {
        while (true)
        {
            System.out.print("\nEnter your choice: ");
            if (!keyboard.hasNextLine())
            {
                return 0;
            }
            try
            {
                int choice = Integer.parseInt(keyboard.nextLine().trim());
                if (choice >= 0 && choice <= 11)
                {
                    return choice;
                }
            }
            catch (NumberFormatException exception)
            {
                // Show the same message for text and out-of-range numbers.
            }
            System.out.println("Please enter a number from 0 to 11.");
        }
    }

    /** Runs a new session until the user exits. */
    public static void runMenu()
    {
        keyboard = new Scanner(System.in);
        playlists = new LinkedChain<Playlist>();
        current = null;
        boolean running = true;
        while (running)
        {
            displayMenu();
            int choice = readChoice();
            try
            {
                if (choice == 0)
                {
                    running = false;
                }
                else if (choice == 1)
                {
                    current = new Playlist(readText("Playlist name: "));
                    playlists.addToEnd(current);
                    System.out.println("Playlist created.");
                }
                else if (choice == 2)
                {
                    current = choosePlaylist();
                }
                else if (choice == 10)
                {
                    combinePlaylists();
                }
                else if (current == null)
                {
                    System.out.println("Create or select a playlist first.");
                }
                else if (choice == 3)
                {
                    showSongs();
                }
                else if (choice == 4)
                {
                    current.addSong(readSong());
                    System.out.println("Song added.");
                }
                else if (choice == 5)
                {
                    insertSong();
                }
                else if (choice == 6)
                {
                    Song song = chooseSong();
                    current.removeSong(song);
                    System.out.println("Song removed.");
                }
                else if (choice == 7)
                {
                    editSong();
                }
                else if (choice == 8)
                {
                    showMatches(current.searchByTitle(readText("Title: ")));
                }
                else if (choice == 9)
                {
                    showMatches(current.searchByArtist(readText("Artist: ")));
                }
                
                
                else if (choice == 11)
                {
                    current.undoLastAction();
                    System.out.println("Last add/remove action undone.");
                }
            }
            catch (PlaylistException exception)
            {
                System.out.println(exception.getMessage());
            }
        }
        System.out.println("Goodbye!");
    }

    private static String readText(String prompt) throws PlaylistException
    {
        while (true)
        {
            System.out.print(prompt);

            if (!keyboard.hasNextLine())
            {
                throw new PlaylistException("Input ended.");
            }

            String text = keyboard.nextLine().trim();

            if (text.length() > 0)
            {
                return text;
            }

            System.out.println("Please enter some text.");
        }
    }

    private static int readNumber(String prompt, int minimum, int maximum)
        throws PlaylistException
    {
        while (true) {
            String text = readText(prompt);
            try
            {
                int number = Integer.parseInt(text);
                if (number >= minimum && number <= maximum)
                {
                    return number;
                }
            }
            catch (NumberFormatException exception)
            {
                // Invalid input is handled after the catch block
            }
            System.out.println("Please enter a number from " + minimum
                + " to " + maximum + ".");
        }
        
    }


    private static double readDuration() throws PlaylistException
    {
        while (true)
        {
            // The decimal part represents seconds, not a fraction of a minute.
            String text = readText(
                "Duration (minutes.seconds, for example 4.30): ");

            try
            {
                double duration = Double.parseDouble(text);

                if (duration > 0 && duration <= 999.59)
                {
                    int minutes = (int)duration;
                    int seconds = (int)Math.round(
                        (duration - minutes) * 100);

                    // Allow only whole seconds from 0 to 59.
                    if (seconds < 60
                        && Math.abs(duration * 100
                            - Math.round(duration * 100)) < 0.000001)
                    {
                        return duration;
                    }
                }
            }
            catch (NumberFormatException exception)
            {
                // Invalid numeric input is handled below.
            }

            System.out.println(
                "Enter minutes.seconds from 0.01 to 999.59, "
                    + "with seconds from 00 to 59.");
        }
    }


    private static Song readSong() throws PlaylistException
    {
        String title = readText("Title: ");
        String artist = readText("Artist: ");
        String album = readText("Album: ");
        double duration = readDuration();
        return new Song(title, artist, album, duration);
    }

    private static Playlist choosePlaylist() throws PlaylistException
    {
        if (playlists.isEmpty())
        {
            throw new PlaylistException("No playlists yet.");
        }
        for (int i = 0; i < playlists.size(); i++)
        {
            System.out.println((i + 1) + ". " + playlists.get(i).getName());
        }
        int number = readNumber("Playlist number: ", 1, playlists.size());
        return playlists.get(number - 1);
    }

    private static void showSongs() throws PlaylistException
    {
        if (current.getNumberOfSong() == 0)
        {
            System.out.println("This playlist is empty.");
        }
        for (int i = 0; i < current.getNumberOfSong(); i++)
        {
            System.out.println((i + 1) + ". " + current.getSong(i));
        }
        System.out.println("Number of songs: " + current.getNumberOfSong());
        System.out.println("Total duration (minutes.seconds): "
            + current.getTotalDuration());
    }

    private static Song chooseSong() throws PlaylistException
    {
        if (current.getNumberOfSong() == 0)
        {
            throw new PlaylistException("This playlist is empty.");
        }
        showSongs();
        int number = readNumber("Song number: ", 1, current.getNumberOfSong());
        return current.getSong(number - 1);
    }

    private static void insertSong() throws PlaylistException
    {
        showSongs();
        int number = readNumber("Insert at position: ", 1,
            current.getNumberOfSong() + 1);
        Song song = readSong();
        // The existing insertSong method does not accept the end position.
        if (number == current.getNumberOfSong() + 1)
        {
            current.addSong(song);
        }
        else
        {
            current.insertSong(number - 1, song);
        }
        System.out.println("Song inserted.");
    }

    private static void editSong() throws PlaylistException
    {
        Song song = chooseSong();
        System.out.println("1. Title  2. Artist  3. Album  4. Duration");
        int field = readNumber("Field number: ", 1, 4);
        if (field == 1)
        {
            current.editSongTitle(song, readText("New title: "));
        }
        else if (field == 2)
        {
            current.editSongArtist(song, readText("New artist: "));
        }
        else if (field == 3)
        {
            current.editSongAlbum(song, readText("New album: "));
        }
        else
        {
            current.editSongDuration(song, readDuration());
        }
        System.out.println("Song updated.");
    }

    private static void showMatches(LinkedChain<Song> matches)
    {
        if (matches.isEmpty())
        {
            System.out.println("No matching songs.");
        }
        for (int i = 0; i < matches.size(); i++)
        {
            System.out.println((i + 1) + ". " + matches.get(i));
        }
    }

    private static void combinePlaylists() throws PlaylistException
    {
        System.out.println("Choose the first playlist:");
        Playlist first = choosePlaylist();
        System.out.println("Choose the second playlist:");
        Playlist second = choosePlaylist();
        if (first == second)
        {
            throw new PlaylistException("Please choose two different playlists.");
        }
        Playlist combined = new Playlist(readText("New playlist name: "));
        LinkedChain<Song> songs = first.combinePlaylist(second);
        for (int i = 0; i < songs.size(); i++)
        {
            Song song = songs.get(i);
            // Copy each song so edits do not change the original playlists.
            combined.addSong(new Song(song.getTitle(), song.getArtist(),
                song.getAlbum(), song.getDuration()));
        }
        playlists.addToEnd(combined);
        current = combined;
        System.out.println("Playlists combined.");
    }
}
