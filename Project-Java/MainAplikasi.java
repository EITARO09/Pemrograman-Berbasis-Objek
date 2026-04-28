import java.util.ArrayList;
import java.util.List;

// 1. Interface
interface OperasiSistem {
    void tampilkanProfil();
}

// 2. Abstract Class (Hanya tulisan 'class', tanpa 'public')
abstract class Anggota implements OperasiSistem {
    private String nim;
    private String nama;

    public Anggota(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
    }

    public String getNim() { return nim; }
    public String getNama() { return nama; }

    public abstract String dapatkanPeran();
}

// 3. Inheritance & Polymorphism
class Pengurus extends Anggota {
    private String divisi;

    public Pengurus(String nim, String nama, String divisi) {
        super(nim, nama);
        this.divisi = divisi;
    }

    @Override
    public String dapatkanPeran() {
        return "Pengurus Divisi " + divisi;
    }

    @Override
    public void tampilkanProfil() {
        System.out.println("NIM: " + getNim() + " | Nama: " + getNama() + " | Peran: " + dapatkanPeran());
    }
}

// 4. Generic Class & Collection
class DatabaseAnggota<T extends Anggota> {
    private List<T> daftar = new ArrayList<>();

    public void tambahData(T entitas) {
        daftar.add(entitas);
    }

    public void cetakSemuaData() {
        for (T entitas : daftar) {
            entitas.tampilkanProfil();
        }
    }
}

// 5. Main Class (Wajib 'public')
public class MainAplikasi {
    public static void main(String[] args) {
        DatabaseAnggota<Pengurus> dbPengurus = new DatabaseAnggota<>();

        // Menggunakan data fiktif
        Pengurus p1 = new Pengurus("1102001", "Budi Santoso", "Pengembangan Perangkat Lunak");
        Pengurus p2 = new Pengurus("1102002", "Siti Aminah", "Hubungan Masyarakat");

        dbPengurus.tambahData(p1);
        dbPengurus.tambahData(p2);

        System.out.println("=== SISTEM INFORMASI PENGURUS ===");
        dbPengurus.cetakSemuaData();
    }
}