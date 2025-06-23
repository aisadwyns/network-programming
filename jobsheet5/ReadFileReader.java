package jobsheet5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReadFileReader {

    public static void main(String[] args) {
            try {
                int desimal;
                char ascii;
                LineNumberReader lineNumberReader = new LineNumberReader(new FileReader("coba.txt"));
                while ((desimal = lineNumberReader.read()) != -1) {
                    ascii = (char) desimal;
                    System.out.println("" + ascii + " at line " + lineNumberReader.getLineNumber());
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReadFileReader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ReadFileReader.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}