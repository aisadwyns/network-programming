package jobsheet8;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class FormMahasiswaApp extends JFrame {
    private JTextField tfNim, tfNama, tfJurusan, tfProdi, tfIpk;
    private JButton btnSimpan, btnUpdate, btnDelete, btnClear;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Mahasiswa> dataList;
    private final String FILE_NAME = "mahasiswa.obj";

    public FormMahasiswaApp() {
        setTitle("Form Mahasiswa");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Form Input
        JPanel formPanel = new JPanel(new GridLayout(10, 1, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));

        tfNim = new JTextField();
        tfNama = new JTextField();
        tfJurusan = new JTextField();
        tfProdi = new JTextField();
        tfIpk = new JTextField();

        formPanel.add(new JLabel("NIM:")); formPanel.add(tfNim);
        formPanel.add(new JLabel("Nama:")); formPanel.add(tfNama);
        formPanel.add(new JLabel("Jurusan:")); formPanel.add(tfJurusan);
        formPanel.add(new JLabel("Prodi:")); formPanel.add(tfProdi);
        formPanel.add(new JLabel("IPK:")); formPanel.add(tfIpk);

        // Panel Tombol
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // Table dan ScrollPane
        String[] columnNames = {"NIM", "Nama", "Jurusan", "Prodi", "IPK"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Daftar Mahasiswa"));
        bottomPanel.add(tableScrollPane, BorderLayout.CENTER);
        bottomPanel.setPreferredSize(new Dimension(400, 300));

        // Tambahkan ke Frame
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Load dan tampilkan data
        dataList = loadData();
        tampilkanData();

        btnSimpan.addActionListener(e -> simpan());
        btnUpdate.addActionListener(e -> update());
        btnDelete.addActionListener(e -> delete());
        btnClear.addActionListener(e -> clear());
        

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    tfNim.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    tfNama.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    tfJurusan.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    tfProdi.setText(tableModel.getValueAt(selectedRow, 3).toString());
                    tfIpk.setText(tableModel.getValueAt(selectedRow, 4).toString());
                }
            }
        });
        
        
    }

    private void tampilkanData() {
        tableModel.setRowCount(0); // Clear table
        for (Mahasiswa m : dataList) {
            Object[] row = {
                m.getNim(),
                m.getNama(),
                m.getJurusan(),
                m.getProdi(),
                m.getIpk()
            };
            tableModel.addRow(row);
        }
    }

    private void simpan() {
        Mahasiswa m = getInput();
        if (m != null && !nimSudahAda(m.getNim())) {
            dataList.add(m);
            saveData();
            tampilkanData();
            clear();
        } else {
            JOptionPane.showMessageDialog(this, "NIM sudah ada atau input tidak valid.");
        }
    }

    private void update() {
        Mahasiswa m = getInput();
        if (m != null) {
            for (int i = 0; i < dataList.size(); i++) {
                if (dataList.get(i).getNim().equals(m.getNim())) {
                    dataList.set(i, m);
                    saveData();
                    tampilkanData();
                    clear();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Data dengan NIM tidak ditemukan.");
        }
    }

    private void delete() {
        String nim = tfNim.getText().trim();
        if (!nim.isEmpty()) {
            dataList.removeIf(m -> m.getNim().equals(nim));
            saveData();
            tampilkanData();
            clear();
        } else {
            JOptionPane.showMessageDialog(this, "klik data dulu dong");
        }
    }

    private void clear() {
        tfNim.setText("");
        tfNama.setText("");
        tfJurusan.setText("");
        tfProdi.setText("");
        tfIpk.setText("");
    }

    private Mahasiswa getInput() {
        try {
            String nim = tfNim.getText().trim();
            String nama = tfNama.getText().trim();
            String jurusan = tfJurusan.getText().trim();
            String prodi = tfProdi.getText().trim();
            double ipk = Double.parseDouble(tfIpk.getText().trim());

            if (nim.isEmpty() || nama.isEmpty() || jurusan.isEmpty() || prodi.isEmpty()) {
                return null;
            }
            return new Mahasiswa(nim, nama, jurusan, prodi, ipk);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Input tidak valid.");
            return null;
        }
    }

    private boolean nimSudahAda(String nim) {
        return dataList.stream().anyMatch(m -> m.getNim().equals(nim));
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(dataList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Mahasiswa> loadData() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                return (List<Mahasiswa>) ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormMahasiswaApp app = new FormMahasiswaApp();
            app.setVisible(true);
        });
    }

    public static class Mahasiswa implements Serializable {
        private String nim, nama, jurusan, prodi;
        private double ipk;

        public Mahasiswa(String nim, String nama, String jurusan, String prodi, double ipk) {
            this.nim = nim;
            this.nama = nama;
            this.jurusan = jurusan;
            this.prodi = prodi;
            this.ipk = ipk;
        }

        public String getNim() { return nim; }
        public String getNama() { return nama; }
        public String getJurusan() { return jurusan; }
        public String getProdi() { return prodi; }
        public double getIpk() { return ipk; }

        public void setNama(String nama) { this.nama = nama; }
        public void setJurusan(String jurusan) { this.jurusan = jurusan; }
        public void setProdi(String prodi) { this.prodi = prodi; }
        public void setIpk(double ipk) { this.ipk = ipk; }
    }
}
