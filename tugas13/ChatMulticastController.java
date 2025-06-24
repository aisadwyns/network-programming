package tugas13;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ChatMulticastController {
    private static final String GROUP_ADDRESS = "234.5.6.7";
    private static final int PORT = 1234;

    @SuppressWarnings("deprecation")
    public void sendMessage(String message) {
        try {
            InetAddress group = InetAddress.getByName(GROUP_ADDRESS);
            MulticastSocket socket = new MulticastSocket();
            socket.joinGroup(group);

            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, group, PORT);
            socket.send(packet);

            socket.leaveGroup(group);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}