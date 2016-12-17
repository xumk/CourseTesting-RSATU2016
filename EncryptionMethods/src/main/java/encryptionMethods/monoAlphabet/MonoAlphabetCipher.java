package encryptionMethods.monoAlphabet;

import encryptionMethods.base.SubstitutionCipher;

/**
 * Класс для кодирования текста моноалфавитным методом Created by Алексей on
 * 25.03.2016.
 */
public class MonoAlphabetCipher extends SubstitutionCipher<Integer> {

    public MonoAlphabetCipher() {
        super();
    }

    /**
     * Создать закрытый алфавит
     *
     * @param key ключ смещения
     */
    @Override
    public void calculationPrivateAlphabet(Integer key) {
        for (int i = 0; i < 32; i++) {
            privateAlphabet[i] = openAlphabet[Math.floorMod(i + key, 32)];
        }
    }
}
