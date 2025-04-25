package coba6;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Praktikum2Controller {
    private Praktikum2 view;
    private List<Integer> list = new ArrayList<>();
    public Praktikum2Controller(Praktikum2 view) {
        this.view = view;
    }

    public void initController() {
        this.view.getBtBaca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proses();
            }
        });

        this.view.getBtSimpan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
    }


    private void proses() {
        JFileChooser loadFile = view.getLoadFile();
        StyledDocument doc = view.getTxtPane().getStyledDocument();
    
        if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
            PushbackReader reader = null;
            try {
                File selectedFile = loadFile.getSelectedFile();
                reader = new PushbackReader(new InputStreamReader(new FileInputStream(selectedFile), "UTF-8"));
                StringBuilder contentBuilder = new StringBuilder();
                int charCount = 0;
                int wordCount = 0;
                int lineCount = 0;
    
                char[] buffer = new char[1024];
                int numRead;
                while ((numRead = reader.read(buffer)) != -1) {
                    String chunk = new String(buffer, 0, numRead);
                    contentBuilder.append(chunk);
                    charCount += chunk.length();
                }
    
                String content = contentBuilder.toString();
                String[] lines = content.split("\r?\n");
                lineCount = lines.length;
                for (String line : lines) {
                    wordCount += line.trim().isEmpty() ? 0 : line.trim().split("\\s+").length;
                }
    
                // Tampilkan konten ke JTextPane
                doc.remove(0, doc.getLength());
                doc.insertString(0, content, null);
    
                // Tambahkan statistik ke bagian akhir teks
                doc.insertString(doc.getLength(), 
                    "\n\n==== Statistik File ====\n" +
                    "Jumlah Baris    : " + lineCount + "\n" +
                    "Jumlah Kata     : " + wordCount + "\n" +
                    "Jumlah Karakter : " + charCount + "\n", null);
    
            } catch (IOException | BadLocationException ex) {
                Logger.getLogger(Praktikum2Controller.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Praktikum2Controller.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }
            }
        }
    }
    

    private void save() {
        JFileChooser loadFile = view.getLoadFile();
        if (JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)) {
            OutputStream os = null;
            try {
                if (!list.isEmpty()) {
                    os = new FileOutputStream(loadFile.getSelectedFile());
                    byte[] dt = new byte[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        dt[i] = list.get(i).byteValue();
                    }
                    os.write(dt);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Praktikum2Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Praktikum2Controller.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (os != null) {
                    try {
                        os.flush();
                        os.close();
                        list.clear();
                    } catch (IOException ex) {
                        Logger.getLogger(Praktikum2Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
