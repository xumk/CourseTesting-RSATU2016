import encryptionMethods.base.SubstitutionCipher;
import encryptionMethods.monoAlphabet.MonoAlphabetCipher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Алексей on 19.12.2016.
 */
public class MonoAlphabetCipherTest {
    SubstitutionCipher monoAlphabetCipher;

    @Before
    public void beforeTest() {
        monoAlphabetCipher = new MonoAlphabetCipher();
    }

    @After
    public void afterTest() {
        monoAlphabetCipher = null;
    }

    @Test(expected = NullPointerException.class)
    public void testNullCalculationPrivateAlphabet() {
        monoAlphabetCipher.calculationPrivateAlphabet(null);
    }

    @Test(expected = ClassCastException.class)
    public void testWrongArgumentCalculationPrivateAlphabet() {
        monoAlphabetCipher.calculationPrivateAlphabet("test");
    }

    @Test
    public void testCalculationPrivateAlphabet() {
        char[] actuals = {' ', 'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
        monoAlphabetCipher.calculationPrivateAlphabet(0);
        assertArrayEquals(monoAlphabetCipher.getPrivateAlphabet(), actuals);
        assertArrayEquals(monoAlphabetCipher.getOpenAlphabet(), actuals);
        assertArrayEquals(monoAlphabetCipher.getOpenAlphabet(), monoAlphabetCipher.getOpenAlphabet());
    }

    @Test
    public void testCalculationPrivateAlphabetCaesarCipher() {
        char[] actuals = {'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', ' ', 'а', 'б'};
        monoAlphabetCipher.calculationPrivateAlphabet(3);
        assertArrayEquals(monoAlphabetCipher.getPrivateAlphabet(), actuals);
    }

    @Test
    public void testEncodeText() {
        monoAlphabetCipher.calculationPrivateAlphabet(3);
        assertEquals(monoAlphabetCipher.encodeText("тест"), "хифх");
    }

    @Test
    public void testEncodeAndDecode() {
        monoAlphabetCipher.calculationPrivateAlphabet(30);
        String encode = monoAlphabetCipher.encodeText("тест");
        assertEquals("тест", monoAlphabetCipher.decodeText(encode));
    }
}
