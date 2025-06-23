package coba12;

import java.net.*;

public class JavaSenderUDP {
    public static void main(String[] args) {
        try {
            // Alamat tujuan (localhost = komputer sendiri)
            InetAddress ia = InetAddress.getByName("localhost");
            int port = 2134;

            // Pesan yang ingin dikirim
            String message = "oi oi Pesan ini dari UDP";
            byte[] buffer = message.getBytes();

            // Buat paket dengan data, panjang data, IP tujuan, dan port tujuan
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length, ia, port);

            // Buat socket untuk mengirim (tanpa port tetap)
            DatagramSocket sender = new DatagramSocket();

            // Kirim paket
            sender.send(dp);
            System.out.println("Pesan berhasil dikirim ke server.");

            // Tutup socket
            sender.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
