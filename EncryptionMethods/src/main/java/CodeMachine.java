/**
 * Шифровальная машина, инкапсулирует в себе все действия 
 * @author roman
 */
/*
public class CodeMachine {

    private Reader reader = null;
    private int bias;
    private int keys[];
    private final char alph[] = {'_', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К', 'Л', 'М',
        'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Ч', 'Ц', 'Ш', 'Щ', 'Ъ',
        'Ы', 'Ь', 'Э', 'Ю', 'Я'};
    private StringBuffer word = null;
    
    public CodeMachine() {
        reader = new Reader();
        bias = 0;
        word = new StringBuffer();
    }

    public StringBuffer getWord() {
        return word;
    }
    
    
    public void initMachine(){
        keys = reader.readKeys();
        bias = keys.length;
        word.append(reader.readWord());
    }
    
    public void runMachine(){
        reader.getUserPanelAction();
    }
    
    */
/**
     * 2 - закодировать, 1 - декодировать
     * @param option - опция (1,2)
     *//*

    public void coding(int option){
        char newSymbol;
        int newPosition;
        int j = 0;
        for (int i = 0; i < word.length(); i++) {
            if (j >= bias) j = 0;
            int x = getCharPosition(word.charAt(i));
            newPosition = (int) Math.floorMod((int) ((x + Math.pow(-1, option) * keys[j])), 31);
            newSymbol = getCharFromPosition(newPosition);
            setNewSymbol(i, newSymbol);
            j++;
        }
    }
    
    */
/**
     * Вставляем новый символ
     * @param position
     * @param newSybol 
     *//*

    public void setNewSymbol(int position, char newSybol){
        word.setCharAt(position, newSybol);
    }

    */
/**
     * Получает позицию текущего символа
     * @param curChar
     * @return 
     *//*

    public int getCharPosition(char curChar){
        int position = 0;
        int i = 0;
        boolean flag = true;
        
        String s = String.valueOf(alph);
        while(i < s.length() && (flag)){
            
            if (s.charAt(i) == curChar){
                position = i;
                flag = false;
            }
            i++;
        }
        return position;
    }
    
    public char getCharFromPosition(int position){
        return alph[position];
    }
}
*/
