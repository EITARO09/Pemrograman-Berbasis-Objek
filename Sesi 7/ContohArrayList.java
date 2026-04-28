import java.util.ArrayList;

public class ContohArrayList {
    public static void main(String[] args) {
        System.out.println("=== IMPLEMENTASI ARRAYLIST ===");
        
        // Menggunakan ArrayList untuk menyimpan daftar perlengkapan trip Team IGD
        ArrayList<String> perlengkapanTrip = new ArrayList<>();

        // Menambahkan elemen ke dalam list
        perlengkapanTrip.add("Tenda");
        perlengkapanTrip.add("Peralatan P3K");
        perlengkapanTrip.add("Kamera Dokumentasi");
        perlengkapanTrip.add("Tikar");

        System.out.println("Daftar perlengkapan awal: " + perlengkapanTrip);

        // Mengambil elemen berdasarkan indeks (dimulai dari 0)
        System.out.println("Barang pada index ke-1: " + perlengkapanTrip.get(1));

        // Menghapus elemen dari list
        perlengkapanTrip.remove("Tikar");
        System.out.println("Daftar perlengkapan setelah tikar dikeluarkan: " + perlengkapanTrip);
    }
}