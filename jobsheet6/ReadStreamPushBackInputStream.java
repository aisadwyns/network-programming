package jobsheet6;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadStreamPushBackInputStream {

    public static void main(String[] args) {
         PushbackInputStream stream = new PushbackInputStream(System.in);
                System.out.print("Masukan karakter apapun: ");
                try {
                    int read = stream.read();
                    stream.unread(read);
                    int read2 = stream.read();
                    System.out.print("" + (char) read);
                    System.out.print("" + (char) read2);                
                } catch (IOException ex) {
                    Logger.getLogger(ReadStreamPushBackInputStream.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
}