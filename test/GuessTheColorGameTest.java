import static org.junit.Assert.assertEquals;

import com.cs2340.cs2340.ColorGame;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Guess the Color game.
 */
public class GuessTheColorGameTest {
    private static final int TIMEOUT = 300;

    private ColorGame game;

    @Before
    public void initialize() {
        game = new ColorGame();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {

    }
}
