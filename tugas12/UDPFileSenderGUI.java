package tugas12;

import javax.swing.*;
import java.awt.*;
import java.io.File;

// Komponen utama JFrame untuk GUI pengirim file
public class UDPFileSenderGUI extends JFrame {
    // Field untuk menampilkan path file yang dipilih
    private JTextField filePathField;
    // Tombol untuk membuka file explorer
    private JButton browseButton;
    // Tombol untuk mengirim file
    private JButton sendButton;
    // Objek controller untuk menangani logika pengiriman
    private UDPFileSenderController controller;

    public UDPFileSenderGUI() {
        controller = new UDPFileSenderController(); // Inisialisasi controller

        // Set properti dasar GUI
        setTitle("UDP File Sender");
        setSize(500, 120);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Inisialisasi komponen GUI
        filePathField = new JTextField(25);
        filePathField.setEditable(false); // Supaya user tidak bisa edit manual
        browseButton = new JButton("Browse");
        sendButton = new JButton("Send File");

        // Tambah komponen ke frame
        add(new JLabel("File: "));
        add(filePathField);
        add(browseButton);
        add(sendButton);

        // Event handler untuk tombol Browse
        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(); // Dialog pemilih file
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filePathField.setText(selectedFile.getAbsolutePath()); // Tampilkan path di text field
            }
        });

        // Event handler untuk tombol Send File
        sendButton.addActionListener(e -> {
            String path = filePathField.getText(); // Ambil path dari text field
            if (!path.isEmpty()) {
                controller.sendFile(path); // Kirim file lewat controller
            } else {
                JOptionPane.showMessageDialog(this, "Silakan pilih file terlebih dahulu.");
            }
        });

        setVisible(true); // Tampilkan GUI
    }

    // Main method untuk menjalankan aplikasi GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(UDPFileSenderGUI::new);
    }
}
