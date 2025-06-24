package jobsheet13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ChatClient {
    @SuppressWarnings("deprecation")
    public static void main(String args[]) throws Exception {
        // Membuat MulticastSocket di port 1234
        MulticastSocket chat = new MulticastSocket(1234);

        // Gabung ke group multicast dengan IP 234.5.6.7
        InetAddress group = InetAddress.getByName("234.5.6.7");
        chat.joinGroup(group);

        // Ambil pesan dari input user
        String msg = "";
        System.out.println("Type a message for the server:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        msg = br.readLine();

        // Kirim pesan ke group multicast
        DatagramPacket data = new DatagramPacket(msg.getBytes(), msg.length(), group, 1234);
        chat.send(data);

        // Keluar dari group dan tutup socket
        chat.leaveGroup(group);  // disarankan untuk meninggalkan grup sebelum close
        chat.close();
    }
}
