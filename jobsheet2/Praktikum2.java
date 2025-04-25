package jobsheet2;
import javax.swing.*;


public class Praktikum2 extends JFrame {
    private JTextPane txtPane;
    private JButton btBaca;
    private JButton btSimpan;
    private JFileChooser loadFile;
    private Praktikum2Controller controller;

    public Praktikum2() {
        setTitle("Praktikum 2");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inisialisasi Controller
        controller = new Praktikum2Controller(this);

        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Label Judul
        JLabel titleLabel = new JLabel("Praktikum 2", JLabel.CENTER);
        panel.add(titleLabel);

        // Text Pane untuk menampilkan isi file
        txtPane = new JTextPane();
        txtPane.setEditable(true);
        panel.add(new JScrollPane(txtPane));

        // JFileChooser untuk memilih file
        loadFile = new JFileChooser();

        // Tombol untuk membaca file&simpan
        btBaca = new JButton("Baca File");
        panel.add(btBaca);

        btSimpan = new JButton("Simpan");
        panel.add(btSimpan);

        add(panel);

        // Delegasikan aksi tombol ke Controller
        controller.initController();
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
            new Praktikum2().setVisible(true);
        });
    }
}
