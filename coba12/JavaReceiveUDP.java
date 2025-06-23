package coba12;

import java.net.*;
import java.io.*;

public class JavaReceiveUDP {
    public static void main(String[] args) {
        try {
            // Buat buffer untuk menampung data yang masuk
            byte[] buffer = new byte[65536]; 
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

            // Socket di port 2134
            DatagramSocket ds = new DatagramSocket(2134);
            System.out.println("Menunggu data dari pengirim...");

            // Terima data
            ds.receive(incoming);

            // Ambil isi data dan ubah ke String
            byte[] data = incoming.getData();
            String s = new String(data, 0, incoming.getLength());

            // Tampilkan alamat pengirim dan isi pesan
            System.out.println("Port " + incoming.getPort() + " dari " + incoming.getAddress() + " mengirim pesan:");
            System.out.println(s);

            ds.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
