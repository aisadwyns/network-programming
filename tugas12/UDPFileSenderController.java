package tugas12;

import java.io.*;
import java.net.*;
import javax.swing.*;

public class UDPFileSenderController {
    // Method untuk mengirim file lewat UDP
    public void sendFile(String path) {
        try {
            DatagramSocket socket = new DatagramSocket(); // Buat socket UDP
            InetAddress ip = InetAddress.getByName("localhost"); // Tujuan pengiriman
            int port = 2000;

            File file = new File(path); // Buka file yang dipilih
            FileInputStream fis = new FileInputStream(file);

            byte[] buffer = new byte[1024];
            int bytesRead;

            // Baca file per 1024 byte dan kirimkan lewat UDP
            while ((bytesRead = fis.read(buffer)) != -1) {
                DatagramPacket packet = new DatagramPacket(buffer, bytesRead, ip, port);
                socket.send(packet);
            }

            // Kirim sinyal akhir (END) ke receiver
            byte[] endSignal = "END".getBytes();
            socket.send(new DatagramPacket(endSignal, endSignal.length, ip, port));

            fis.close();
            socket.close();

            // Tampilkan notifikasi sukses
            JOptionPane.showMessageDialog(null, "File berhasil dikirim!");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal mengirim file: " + ex.getMessage());
        }
    }
}
