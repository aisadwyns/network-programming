public class LirikLagu {

    public static void main(String[] args) {
        String[] lirik = {
            "Temanku semua pada jahat tante",
            "Aku lagi susah mereka gak ada",
            "Coba kalo lagi jaya",
            "Aku dipuja pujanya tante",
            "... Sudah terbiasa terjadi tante",
            "Teman datang ketika lagi butuh saja",
            "Coba kalo lagi susah",
            "Mereka semua menghilang tante"
        };

        try {
            for (String baris : lirik) {
                for (char huruf : baris.toCharArray()) {
                    System.out.print(huruf);
                    Thread.sleep(50); // efek berjalan per huruf
                }
                System.out.println(); // pindah baris
                Thread.sleep(800); // jeda antar baris
            }
        } catch (InterruptedException e) {
            System.out.println("Terjadi gangguan saat memutar lirik.");
        }
    }
}
