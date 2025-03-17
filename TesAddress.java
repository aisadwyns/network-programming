import java.net.InetAddress;

public class TesAddress {
    public static void main(String[] args) {
        try {
            InetAddress polinema = InetAddress.getByName("www.polinema.ac.id");
            InetAddress polinemaIp = InetAddress.getByName("114.6.41.77");

            InetAddress google = InetAddress.getByName("www.google.com");
            InetAddress googleIp = InetAddress.getByName("172.253.118.106");

            InetAddress youtube = InetAddress.getByName("www.youtube.com");
            InetAddress youtubeIp = InetAddress.getByName("142.251.10.91");

            InetAddress insta = InetAddress.getByName("www.instagram.com");
            InetAddress instaIp = InetAddress.getByName("157.240.208.174");

            System.out.println("IP Address dari Polinema adalah " + polinema.getHostAddress());
            System.out.println("IP Address dari Google adalah " + google.getHostAddress());
            System.out.println("IP Address dari Youtube adalah " + youtube.getHostAddress());
            System.out.println("IP Address dari Instagram adalah " + insta.getHostAddress());

            if (polinema.equals(polinemaIp)) {
                System.out.println("Alamat sama");
            } else {
                System.out.println("Alamat Berbeda");
            }

            if (google.equals(googleIp)) {
                System.out.println("Alamat sama");
            } else {
                System.out.println("Alamat Berbeda");
            }

            if (google.equals(youtubeIp)) {
                System.out.println("Alamat sama");
            } else {
                System.out.println("Alamat Berbeda");
            }
            if (google.equals(instaIp)) {
                System.out.println("Alamat sama");
            } else {
                System.out.println("Alamat Berbeda");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}