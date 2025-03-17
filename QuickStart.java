import java.net.InetAddress;
import java.net.UnknownHostException;

public class QuickStart {

    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            InetAddress address1 = InetAddress.getByName( "www.youtube.com");
            InetAddress address2 = InetAddress.getByName( "www.instagram.com");
            InetAddress address3 = InetAddress.getByName("www.polinema.ac.id");
            System.out.println("IP Address dari Google adalah: " + address.getHostAddress());
            System.out.println("IP Address dari YT adalah: " + address1.getHostAddress());
            System.out.println("IP Address dari IG adalah: " + address2.getHostAddress());
            System.out.println("IP Address dari Polinema adalah: " + address3.getHostAddress());

        } catch (UnknownHostException ex) {
            System.out.println("Error: " + ex);
        }
    }
}
    
