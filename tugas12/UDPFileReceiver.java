package tugas12;

import java.io.*;
import java.net.*;

public class UDPFileReceiver {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(2000);
        byte[] buffer = new byte[1024];

        FileOutputStream fos = new FileOutputStream("received_sample.txt");

        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String data = new String(packet.getData(), 0, packet.getLength());
            if (data.equals("END")) {
                break;
            }

            fos.write(packet.getData(), 0, packet.getLength());
        }

        fos.close();
        socket.close();
        System.out.println("File berhasil diterima dan disimpan.");
        
    }
}
