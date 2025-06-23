package coba5;
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
            try (BufferedReader reader = new BufferedReader(new FileReader(loadFile.getSelectedFile()))) {
                String data;
                int jumlahBaris = 0;
                int jumlahKata = 0;
                int jumlahKarakter = 0;
    
                doc.remove(0, doc.getLength());
    
                while ((data = reader.readLine()) != null) {
                    doc.insertString(doc.getLength(), data + "\n", null);
                    jumlahBaris++;
                    jumlahKata += data.split("\\s+").length;
                    jumlahKarakter += data.length();
                }
    
                JOptionPane.showMessageDialog(view,
                    "File berhasil dibaca.\nJumlah baris = " + jumlahBaris
                    + "\nJumlah kata = " + jumlahKata
                    + "\nJumlah karakter = " + jumlahKarakter,
                    "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | BadLocationException ex) {
                Logger.getLogger(Praktikum2Controller.class.getName()).log(Level.SEVERE, null, ex);
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
