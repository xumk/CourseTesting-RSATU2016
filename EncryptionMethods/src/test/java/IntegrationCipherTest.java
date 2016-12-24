import encryptionMethods.base.SubstitutionCipher;
import encryptionMethods.bitrevers.BitReversCipher;
import encryptionMethods.monoAlphabet.MonoAlphabetCipher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Алексей on 22.12.2016.
 */
public class IntegrationCipherTest {
    private SubstitutionCipher<Integer> monoAlphabet;
    private SubstitutionCipher<String> bitRevers;
    private String actualText;

    @Before
    public void createCipherClass() {
        monoAlphabet = new MonoAlphabetCipher();
        bitRevers = new BitReversCipher();
        actualText = "тест текст";
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
        bitRevers.calculationPrivateAlphabet("53241");
        String expectedText = monoAlphabet.encodeText(actualText);
        expectedText = bitRevers.encodeText(expectedText);
        Assert.assertEquals(expectedText, actualText);
        monoAlphabet.calculationPrivateAlphabet(3);
        bitRevers.calculationPrivateAlphabet("24531");
        expectedText = monoAlphabet.decodeText(expectedText);
        expectedText = bitRevers.decodeText(expectedText);
        Assert.assertNotEquals(expectedText, actualText);
        expectedText = monoAlphabet.encodeText(expectedText);
        expectedText = bitRevers.encodeText(expectedText);
        Assert.assertEquals(expectedText, actualText);
        monoAlphabet.calculationPrivateAlphabet(2);
        bitRevers.calculationPrivateAlphabet("53241");
        expectedText = monoAlphabet.decodeText(actualText);
        expectedText = bitRevers.decodeText(expectedText);
        Assert.assertEquals(expectedText, actualText);
    }
}
