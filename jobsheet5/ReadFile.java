package jobsheet5;
import javax.swing.*;

public class ReadFile extends JFrame {
    private JTextPane txtPane;
    private JButton btBaca;
    private JButton btSimpan;
    private JFileChooser loadFile;
    private ReadFileReader controller;

    public ReadFile() {
        setTitle("Praktikum 5");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        controller = new ReadFileReader(this); // Inisialisasi Controller

        JPanel panel = new JPanel(); // Panel utama
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel("Praktikum 3", JLabel.CENTER); // Label Judul
        panel.add(titleLabel);

        
        txtPane = new JTextPane(); // Text Pane untuk menampilkan isi file
        txtPane.setEditable(true);
        panel.add(new JScrollPane(txtPane));

        
        loadFile = new JFileChooser(); // JFileChooser untuk memilih file

        
        btBaca = new JButton("Baca File"); // Tombol untuk membaca file&simpan
        panel.add(btBaca);

        btSimpan = new JButton("Simpan");
        panel.add(btSimpan);

        add(panel);
        
        controller.initController(); // Delegasikan aksi tombol ke Controller
    }

    // Getter untuk komponen GUI
    public JButton getBtBaca() {
        return btBaca;
    }

    public JButton getBtSimpan() {
        return btSimpan;
    }

    public JFileChooser getLoadFile() {
        return loadFile;
    }

    public JTextPane getTxtPane() {
        return txtPane;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ReadFile().setVisible(true);
        });
    }
}
