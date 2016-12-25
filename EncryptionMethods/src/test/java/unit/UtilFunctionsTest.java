package unit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static utils.UtilFunctions.md5Custom;

/**
 * Created by Алексей on 25.12.2016.
 */
public class UtilFunctionsTest {

    @Test
    public void md5EqualsTesting() {
        String expected = md5Custom("1");
        assertEquals(expected, "c4ca4238a0b923820dcc509a6f75849b");
        expected = md5Custom("md5test");
        assertEquals(expected, "82da61aa724b5d149a9c5dc8682c2a45");
        expected = md5Custom("абра-кадбра, магия какая-то");
        assertEquals(expected, "783e47ca544ddf34ccce81b524adbbe3");
    }

    @Test
    public void md5NullArgument() {
        assertEquals(md5Custom(null), "");
    }
}
