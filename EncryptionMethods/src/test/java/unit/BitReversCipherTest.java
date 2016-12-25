package unit;

import encryptionMethods.base.SubstitutionCipher;
import encryptionMethods.bitrevers.BitReversCipher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Алексей on 19.12.2016.
 */
public class BitReversCipherTest {
    private SubstitutionCipher bitReversCipher;

    @Before
    public void beforeTest() {
        bitReversCipher = new BitReversCipher();
    }

    @After
    public void afterTest() {
        bitReversCipher = null;
    }

    @Test
    public void testCalculationPrivateAlphabetEqualsOpenAlphabet() {
        char[] actuals = {' ', 'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
        bitReversCipher.calculationPrivateAlphabet("12345");
        assertArrayEquals(bitReversCipher.getPrivateAlphabet(), actuals);
        assertArrayEquals(bitReversCipher.getOpenAlphabet(), actuals);
        assertArrayEquals(bitReversCipher.getOpenAlphabet(), bitReversCipher.getOpenAlphabet());
    }

    @Test
    public void testCalculationPrivateAlphabet() {
        char[] actuals = {' ', 'р', 'з', 'ш', 'г', 'ф', 'м', 'ь', 'б', 'т', 'к', 'ъ', 'е', 'ц', 'о', 'ю', 'а', 'с', 'и', 'щ', 'д', 'х', 'н', 'э', 'в', 'у', 'л', 'ы', 'ж', 'ч', 'п', 'я'};
        bitReversCipher.calculationPrivateAlphabet("54321");
        assertArrayEquals(bitReversCipher.getPrivateAlphabet(), actuals);
    }

    @Test
    public void testFirstElementPrivateAlphabetAtAnyKey() {
        bitReversCipher.calculationPrivateAlphabet("54321");
        assertEquals(bitReversCipher.getPrivateAlphabet()[0], ' ');
        bitReversCipher.calculationPrivateAlphabet("53241");
        assertEquals(bitReversCipher.getPrivateAlphabet()[0], ' ');
        bitReversCipher.calculationPrivateAlphabet("13425");
        assertEquals(bitReversCipher.getPrivateAlphabet()[0], ' ');
        bitReversCipher.calculationPrivateAlphabet("31245");
        assertEquals(bitReversCipher.getPrivateAlphabet()[0], ' ');
    }

    @Test
    public void testLastElementPrivateAlphabetAtAnyKey() {
        bitReversCipher.calculationPrivateAlphabet("54321");
        assertEquals(bitReversCipher.getPrivateAlphabet()[31], 'я');
        bitReversCipher.calculationPrivateAlphabet("53241");
        assertEquals(bitReversCipher.getPrivateAlphabet()[31], 'я');
        bitReversCipher.calculationPrivateAlphabet("13425");
        assertEquals(bitReversCipher.getPrivateAlphabet()[31], 'я');
        bitReversCipher.calculationPrivateAlphabet("31245");
        assertEquals(bitReversCipher.getPrivateAlphabet()[31], 'я');
    }

    @Test
    public void testEncode() {
        bitReversCipher.calculationPrivateAlphabet("54321");
        assertEquals(bitReversCipher.encodeText("тест"), "имси");
    }

    @Test
    public void testEncodeDecode() {
        bitReversCipher.calculationPrivateAlphabet("54321");
        String encode = bitReversCipher.encodeText("тест");
        assertEquals(bitReversCipher.decodeText(encode), "тест");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testWrongValueForCalculationPrivateAlphabet() {
        bitReversCipher.calculationPrivateAlphabet("00000");
    }

    @Test(expected = ClassCastException.class)
    public void testWrongArgumentForCalculationPrivateAlphabet() {
        bitReversCipher.calculationPrivateAlphabet(1213234);
    }

    @Test
    public void testWrongCharInEncodeAndDecodeText() {
        bitReversCipher.calculationPrivateAlphabet("54321");
        Assert.assertEquals(bitReversCipher.encodeText("tttt"), "Недопустимый символ. Шифрование не возможно");
        Assert.assertEquals(bitReversCipher.encodeText("й"), "Недопустимый символ. Шифрование не возможно");
        Assert.assertEquals(bitReversCipher.encodeText("ё"), "Недопустимый символ. Шифрование не возможно");
        Assert.assertEquals(bitReversCipher.encodeText("-"), "Недопустимый символ. Шифрование не возможно");
        Assert.assertEquals(bitReversCipher.encodeText("+"), "Недопустимый символ. Шифрование не возможно");
        Assert.assertEquals(bitReversCipher.decodeText("tttt"), "Недопустимый символ. Расшифровка не возможно");
        Assert.assertEquals(bitReversCipher.decodeText("й"), "Недопустимый символ. Расшифровка не возможно");
        Assert.assertEquals(bitReversCipher.decodeText("ё"), "Недопустимый символ. Расшифровка не возможно");
        Assert.assertEquals(bitReversCipher.decodeText("-"), "Недопустимый символ. Расшифровка не возможно");
        Assert.assertEquals(bitReversCipher.decodeText("+"), "Недопустимый символ. Расшифровка не возможно");
    }
}
