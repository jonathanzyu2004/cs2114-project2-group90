import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import student.TestCase;

/**
 * Checks console input and output for Main.
 * Written with assistance from OpenAI Codex.
 * @author Zhaojin Gao
 * @version October 13, 2026
 */
public class MainTest extends TestCase
{
    private InputStream oldInput;
    private PrintStream oldOutput;
    private ByteArrayOutputStream output;

    public void setUp()
    {
        oldInput = System.in;
        oldOutput = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    public void tearDown()
    {
        System.setIn(oldInput);
        System.setOut(oldOutput);
    }

    private void enter(String input)
    {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(new String[0]);
    }

    public void testExitAndMenu()
    {
        enter("0\n");
        assertTrue(output.toString().contains("1. Create a playlist"));
        assertTrue(output.toString().contains("10. Combine two playlists"));
        assertTrue(output.toString().contains("Goodbye!"));
    }

    public void testBadMenuInput()
    {
        enter("hello\n-1\n11\n0\n");
        assertTrue(output.toString().contains("Please enter a number from 0 to 10."));
        assertTrue(output.toString().contains("Goodbye!"));
    }

    public void testEmptyInput()
    {
        enter("");
        assertTrue(output.toString().contains("Goodbye!"));
    }

    public void testNoPlaylistAndEmptyPlaylist()
    {
        enter("3\n2\n1\nEmpty\n3\n6\n7\n8\nMissing\n0\n");
        String text = output.toString();
        assertTrue(text.contains("Create or select a playlist first."));
        assertTrue(text.contains("No playlists yet."));
        assertTrue(text.contains("This playlist is empty."));
        assertTrue(text.contains("No matching songs."));
    }

    public void testAddSearchAndRemove()
    {
        enter("1\nRoad\n4\nHello\nAdele\n25\n4.30\n"
            + "8\nHell\n9\nAdele\n3\n6\n1\n3\n0\n");
        String text = output.toString();
        assertTrue(text.contains("Song added."));
        assertTrue(text.contains("1. Sound Title: Hello Artist: Adele"));
        assertTrue(text.contains("Total duration (minutes.seconds): 4.3"));
        assertTrue(text.contains("Song removed."));
        assertTrue(text.contains("Number of songs: 0"));
        assertFalse(text.contains("No matching songs."));
    }

    public void testInsertAtStartAndEnd()
    {
        enter("1\nMix\n5\n1\nB\nArtist\nAlbum\n1.00\n"
            + "5\n1\nA\nArtist\nAlbum\n2.00\n"
            + "5\n3\nC\nArtist\nAlbum\n3.00\n3\n0\n");
        String text = output.toString();
        assertTrue(text.contains("1. Sound Title: A"));
        assertTrue(text.contains("2. Sound Title: B"));
        assertTrue(text.contains("3. Sound Title: C"));
        assertTrue(text.contains("Number of songs: 3"));
    }

    public void testEditAllFields()
    {
        enter("1\nMix\n4\nOld\nArtist\nAlbum\n1.00\n"
            + "7\n1\n1\nNew\n7\n1\n2\nSinger\n"
            + "7\n1\n3\nRecord\n7\n1\n4\n2.45\n3\n0\n");
        assertTrue(output.toString().contains(
            "Sound Title: New Artist: Singer Album: Record Duration: 2.45"));
    }

    public void testBadSongInput()
    {
        enter("1\nMix\n4\n\n4\nSong\nArtist\nAlbum\n0.00\n"
            + "4\nSong\nArtist\nAlbum\n1.60\n"
            + "5\nabc\n3\n0\n");
        String text = output.toString();
        assertTrue(text.contains("Please enter some text."));
        assertTrue(text.contains("Duration must be greater than zero."));
        assertTrue(text.contains("Enter minutes.seconds from 0.01 to 999.59"));
        assertTrue(text.contains("Please enter a number from 1 to 1."));
        assertTrue(text.contains("Number of songs: 0"));
    }

    public void testInvalidDurationValues()
    {
        String[] values = { "abc", "NaN", "Infinity", "-1", "1000",
            "4.60", "4.301" };
        for (int i = 0; i < values.length; i++)
        {
            output.reset();
            enter("1\nMix\n4\nSong\nArtist\nAlbum\n" + values[i]
                + "\n3\n0\n");
            assertFalse(output.toString().contains("Song added."));
            assertTrue(output.toString().contains("Number of songs: 0"));
        }
    }

    public void testCombineAndSelect()
    {
        enter("1\nFirst\n4\nA\nArtist\nAlbum\n1.40\n"
            + "1\nSecond\n4\nB\nArtist\nAlbum\n2.30\n"
            + "10\n1\n2\nCombined\n3\n7\n1\n1\nChanged\n"
            + "2\n1\n8\nChanged\n3\n0\n");
        String text = output.toString();
        assertTrue(text.contains("Playlists combined."));
        assertTrue(text.contains("Current playlist: Combined"));
        assertTrue(text.contains("Number of songs: 2"));
        assertTrue(text.contains("Total duration (minutes.seconds): 4.1"));
        assertTrue(text.contains("No matching songs."));
        assertTrue(text.contains("Current playlist: First"));
    }

    public void testBadPlaylistSelection()
    {
        enter("1\nFirst\n2\n2\n10\n1\n1\n0\n");
        String text = output.toString();
        assertTrue(text.contains("Please enter a number from 1 to 1."));
        assertTrue(text.contains("Please choose two different playlists."));
        assertTrue(text.contains("Current playlist: First"));
    }

    public void testInputEndsDuringSong()
    {
        enter("1\nMix\n4\nSong\n");
        assertTrue(output.toString().contains("Input ended."));
        assertTrue(output.toString().contains("Goodbye!"));
    }
}
