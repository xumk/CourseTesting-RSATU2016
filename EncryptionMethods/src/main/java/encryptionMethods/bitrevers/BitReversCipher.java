package encryptionMethods.bitrevers;

import encryptionMethods.base.SubstitutionCipher;

/**
 * Класс для кодирования текста методом побитовой перестановки Created by
 * Алексей on 25.03.2016.
 */
public class BitReversCipher extends SubstitutionCipher<String> {
    public BitReversCipher() {
        super();
    }

    /**
     * Вычислить закрытый алфавит по заданому ключу
     *
     * @param key ключ
     */
    @Override
    public void calculationPrivateAlphabet(String key) {
        int[] arrayKey = findPosition(key);
        for (int i = 0; i < 32; i++) {
            StringBuilder stI = new StringBuilder(Integer.toBinaryString(i));
            for (int j = stI.length(); j < 5; j++) {
                stI.insert(0, "0");
            }
            StringBuilder charAlph = new StringBuilder();
            char[] mass = stI.toString().toCharArray();
            for (int anArrayKey : arrayKey) {
                charAlph.append(mass[anArrayKey]);
            }
            int exitI = Integer.parseInt(charAlph.toString(), 2);
            privateAlphabet[i] = openAlphabet[exitI];
        }
    }

    /**
     * Найти в каких позициях произошла перестановка
     *
     * @param arrayKey
     * @return
     */
    private int[] findPosition(String arrayKey) {
        int[] result = new int[arrayKey.length()];
        result[0] = arrayKey.indexOf("1");
        result[1] = arrayKey.indexOf("2");
        result[2] = arrayKey.indexOf("3");
        result[3] = arrayKey.indexOf("4");
        result[4] = arrayKey.indexOf("5");
        return result;
    }
}
