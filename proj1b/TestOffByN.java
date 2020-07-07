import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offBy5 = new OffByN(5);

    @Test
    public void TestOffByOne() {
        boolean actual = offBy5.equalChars('a', 'a');

        assertFalse("false", actual);

        boolean actual1 = offBy5.equalChars('a', 'e');

        assertFalse("false", actual1);

        boolean actual2 = offBy5.equalChars('f', 'a');

        assertFalse("false", actual2);

        boolean actual3 = offBy5.equalChars('b', 'a');

        assertTrue("true", actual3);
    }
}
