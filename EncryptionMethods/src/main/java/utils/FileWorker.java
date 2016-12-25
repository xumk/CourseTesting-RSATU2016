package utils;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Алексей on 25.12.2016.
 */
public abstract class FileWorker {
    public static StringBuilder readFile(File file) {
        StringBuilder result = new StringBuilder("");
        if (file != null) {
            try (
                    InputStream fis = new FileInputStream(file);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "UTF-8"))
            ) {
                String line = reader.readLine();
                while (line != null) {
                    result.append(line);
                    line = reader.readLine();
                }
                return result;
            } catch (FileNotFoundException e) {
                Logger.getLogger(FileWorker.class.getName()).log(Level.SEVERE, null, e);
                return new StringBuilder("Файл не найден");
            } catch (IOException e) {
                Logger.getLogger(FileWorker.class.getName()).log(Level.SEVERE, null, e);
                return result;
            }
        }
        return result;
    }

    public static boolean writeFile(File file, String text) {
        if (file != null) {
            try (FileWriter fw = new FileWriter(file)) {
                fw.append(text);
                fw.append("\n");
                fw.flush();
                fw.close();
                return true;
            } catch (Exception ex) {
                Logger.getLogger(FileWorker.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
}
