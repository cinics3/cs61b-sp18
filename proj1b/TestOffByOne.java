import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    // Uncomment this class once you've created your CharacterComparator interface and OffByOne class.

    @Test
    public void TestOffByOne() {
        boolean actual = offByOne.equalChars('a', 'a');

        assertFalse("false", actual);

        boolean actual1 = offByOne.equalChars('a', 'e');

        assertFalse("false", actual1);

        boolean actual2 = offByOne.equalChars('f', 'a');

        assertFalse("false", actual2);

        boolean actual3 = offByOne.equalChars('b', 'a');

        assertTrue("true", actual3);
    }
}
