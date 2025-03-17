import javax.swing.*;

public class Praktikum1 extends JFrame {
    private JTextPane txtPane;
    private JButton btBaca;
    private JFileChooser loadFile;
    private Praktikum1Controller controller;

    public Praktikum1() {
        setTitle("Praktikum 1");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inisialisasi Controller
        controller = new Praktikum1Controller(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("Praktikum 1", JLabel.CENTER);
        panel.add(titleLabel);
        txtPane = new JTextPane();
        txtPane.setEditable(false);
        panel.add(new JScrollPane(txtPane));
        loadFile = new JFileChooser();
        btBaca = new JButton("Baca File");
        panel.add(btBaca);

        add(panel);

        // Delegasikan aksi tombol ke Controller
        controller.initController();
    }

    // Getter untuk komponen GUI
    public JButton getBtBaca() {
        return btBaca;
    }

    public JFileChooser getLoadFile() {
        return loadFile;
    }

    public JTextPane getTxtPane() {
        return txtPane;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Praktikum1().setVisible(true);
        });
    }
}
