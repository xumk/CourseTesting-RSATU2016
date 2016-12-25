package unit.integration;

import encryptionMethods.base.SubstitutionCipher;
import encryptionMethods.bitrevers.BitReversCipher;
import encryptionMethods.monoAlphabet.MonoAlphabetCipher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Алексей on 22.12.2016.
 */
public class IntegrationCipherTest {
    private static SubstitutionCipher<Integer> monoAlphabet;
    private static SubstitutionCipher<String> bitRevers;
    private String actualText;

    @BeforeClass
    public static void test() {
        monoAlphabet = new MonoAlphabetCipher();
        bitRevers = new BitReversCipher();
    }

    @Before
    public void createCipherClass() {
        actualText = "специальныи текст для тестов";
    }

    @Test
    public void integrationCipherEqualsTest() {
        monoAlphabet.calculationPrivateAlphabet(4);
        bitRevers.calculationPrivateAlphabet("32451");
        String expectedText = monoAlphabet.encodeText(actualText);
        expectedText = bitRevers.decodeText(expectedText);
        expectedText = bitRevers.encodeText(expectedText);
        expectedText = monoAlphabet.decodeText(expectedText);
        Assert.assertEquals(expectedText, actualText);
    }

    @Test
    public void  integrationCipherNotEqualsTest() {
        monoAlphabet.calculationPrivateAlphabet(4);
        bitRevers.calculationPrivateAlphabet("32451");
        String testText = monoAlphabet.encodeText(actualText);
        testText = bitRevers.decodeText(testText);
        bitRevers.calculationPrivateAlphabet("52341");
        String expectedText = bitRevers.encodeText(testText);
        Assert.assertNotEquals(expectedText, testText);
    }

    @Test
    public void  integrationDeepCipherEqualsTest() {
        monoAlphabet.calculationPrivateAlphabet(2);
        String expectedText = monoAlphabet.encodeText(actualText);

        bitRevers.calculationPrivateAlphabet("53241");
        expectedText = bitRevers.encodeText(expectedText);
        Assert.assertNotEquals(expectedText, actualText);

        monoAlphabet.calculationPrivateAlphabet(3);
        expectedText = monoAlphabet.decodeText(expectedText);

        bitRevers.calculationPrivateAlphabet("24531");
        expectedText = bitRevers.decodeText(expectedText);
        Assert.assertNotEquals(expectedText, actualText);

        expectedText = bitRevers.encodeText(expectedText);
        expectedText = monoAlphabet.encodeText(expectedText);
        Assert.assertNotEquals(expectedText, actualText);

        bitRevers.calculationPrivateAlphabet("53241");
        expectedText = bitRevers.decodeText(expectedText);

        monoAlphabet.calculationPrivateAlphabet(2);
        expectedText = monoAlphabet.decodeText(expectedText);

        Assert.assertEquals(expectedText, actualText);
    }
}
