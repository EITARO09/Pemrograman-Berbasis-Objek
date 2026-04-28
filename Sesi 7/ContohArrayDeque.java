import java.util.ArrayDeque;

public class ContohArrayDeque {
    public static void main(String[] args) {
        System.out.println("=== IMPLEMENTASI ARRAYDEQUE ===");
        
        // Menggunakan ArrayDeque untuk antrean notifikasi sistem SAFETAG
        ArrayDeque<String> antrianNotifikasi = new ArrayDeque<>();

        // Menambahkan notifikasi baru ke dalam antrean (masuk dari belakang)
        antrianNotifikasi.addLast("Notif: Barang didekatkan ke sensor");
        antrianNotifikasi.addLast("Notif: Lokasi GPS diperbarui");
        antrianNotifikasi.addLast("Notif: Baterai tersisa 20%");
        
        System.out.println("Antrian notifikasi saat ini: \n" + antrianNotifikasi);
        System.out.println();

        // Menyelipkan notifikasi prioritas/urgent di urutan paling depan
        antrianNotifikasi.addFirst("URGENT: Barang terdeteksi menjauh dari jangkauan!");
        System.out.println("Antrian setelah ada notifikasi darurat: \n" + antrianNotifikasi);
        System.out.println();

        // Mengambil dan langsung menghapus notifikasi urutan pertama untuk diproses sistem
        String diproses = antrianNotifikasi.pollFirst();
        System.out.println("Notifikasi yang sedang diproses: " + diproses);
        System.out.println("Sisa antrean notifikasi: \n" + antrianNotifikasi);
    }
}