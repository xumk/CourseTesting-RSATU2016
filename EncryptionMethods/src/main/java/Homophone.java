package sample;

import java.util.*;

/**
 * Created by Белый on 11.03.2016.
 */
public class Homophone {


    public String toString(int iteration) {
        if (iteration < 10) return "0" + iteration;
        else return "" + iteration;
    }

    public Character toChar(int code) {
        return (char) code;
    }

    public List<Map<Character, String>> generateAlpha(int nubmer) {
        List<Map<Character, String>> result = new ArrayList<>();
        int iteration = 0;

        for (int i = 0; i < nubmer; i++) {
            int c = 1040;
            Map<Character, String> map = new LinkedHashMap<>();


            map.put(toChar('_'), toString(iteration));
            iteration++;


            for (int j = 0; j < 32; j++) {
                if (c != 1049) {
                    //check
                    map.put(toChar(c), toString(iteration));
                    iteration++;
                    c++;

                } else c++;
            }

            result.add(map);
        }

        return result;
    }


    public int meets(String word, int position) {
        int nubmber = 0;
        char who = word.toCharArray()[position];
        char[] chars = word.substring(0, position).toCharArray();
        for (char aChar : chars) {
            if (aChar == who) nubmber++;
        }
        return nubmber;
    }

    public String delempt(String word) {
        String result = "";
        char[] a = word.toCharArray();
        for (char c : a) {
            if (!(c == ' ')) result += c;
        }
        return result;
    }

    public boolean isNum(char c) {
        return ((c >= '1') && (c <= '9'));
    }


    public String code(String word, int key) {

        List<Map<Character, String>> alpha = generateAlpha(key);
        String result = "";


        word = delempt(word);

        char[] chars = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {

            if (isNum(chars[i]) && isNum(chars[i + 1])) {
                result += Character.toString(chars[i]) + Character.toString(chars[i+1]) + " ";
                i++;
            } else {
                char c = chars[i];
                int m = Math.floorMod(meets(word, i), key);


                if (alpha.size() > m) {
                    Map<Character, String> map = alpha.get(m);
                    if (map.containsKey(c)) result += map.get(c) + " ";
                    else result += c + " ";
                } else result += c + " ";
            }
        }


        return result;
    }


    public Map<String, Character> reverse(List<Map<Character, String>> alpha) {
        Map<String, Character> result = new LinkedHashMap<>();
        for (Map<Character, String> stringMap : alpha) {
            for (Character key : stringMap.keySet()) {
                result.put(stringMap.get(key), key);
            }
        }
        return result;
    }

    public String decode(String word, int key) {
        String result = "";
        Map<String, Character> alpha = reverse(generateAlpha(key));


        String[] code = word.split(" ");
        for (String s : code) {
            if (alpha.containsKey(s)) result += alpha.get(s);
            else result += " " + s + " ";


        }


        return result;
    }

}
