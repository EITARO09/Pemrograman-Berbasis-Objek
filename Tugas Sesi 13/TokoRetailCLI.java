import java.sql.*;
import java.util.Scanner;

public class TokoRetailCLI {

    // Konfigurasi Database
    static final String DB_URL = "jdbc:mysql://localhost:3306/toko_retail";
    static final String USER = "root";
    static final String PASS = ""; // Sesuaikan jika XAMPP kamu pakai password

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==================================");
            System.out.println("         MENU TOKO RETAIL         ");
            System.out.println("==================================");
            System.out.println("1. Tampil Semua Data");
            System.out.println("2. Tambah Data");
            System.out.println("3. Cari Data");
            System.out.println("4. Ubah Data");
            System.out.println("5. Hapus Data");
            System.out.println("0. Keluar");
            System.out.println("==================================");
            System.out.print("Pilihan : ");
            
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1": tampilData(); break;
                case "2": tambahData(); break;
                case "3": cariData(); break;
                case "4": ubahData(); break;
                case "5": hapusData(); break;
                case "0": 
                    System.out.println("Keluar dari program. Terima kasih!");
                    System.exit(0);
                default: 
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    static void tampilData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_barang")) {

            System.out.println("\n=========================================================");
            System.out.println("                DAFTAR BARANG TOKO RETAIL                ");
            System.out.println("=========================================================");
            System.out.printf("| %-2s | %-6s | %-20s | %-7s | %-4s |\n", "#", "Kode", "Nama Barang", "Harga", "Stok");
            System.out.println("---------------------------------------------------------");

            int count = 0;
            while (rs.next()) {
                count++;
                String kode = rs.getString("kode");
                String nama = rs.getString("nama_barang");
                int harga = rs.getInt("harga");
                int stok = rs.getInt("stok");
                System.out.printf("| %-2d | %-6s | %-20s | %-7d | %-4d |\n", count, kode, nama, harga, stok);
            }
            System.out.println("=========================================================");
            System.out.println("Total: " + count + " barang\n");

        } catch (SQLException e) {
            System.out.println("Error tampil data: " + e.getMessage());
        }
    }

    static void tambahData() {
        System.out.println("\n--- TAMBAH DATA BARANG ---");
        System.out.print("Masukkan Kode: ");
        String kode = scanner.nextLine();
        System.out.print("Masukkan Nama Barang: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Harga: ");
        int harga = Integer.parseInt(scanner.nextLine());
        System.out.print("Masukkan Stok: ");
        int stok = Integer.parseInt(scanner.nextLine());

        String sql = "INSERT INTO tbl_barang (kode, nama_barang, harga, stok) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, kode);
            pstmt.setString(2, nama);
            pstmt.setInt(3, harga);
            pstmt.setInt(4, stok);
            
            pstmt.executeUpdate();
            System.out.println("Berhasil menambahkan data!");

        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data: " + e.getMessage());
        }
    }

    static void cariData() {
        System.out.println("\n--- CARI DATA BARANG ---");
        System.out.print("Masukkan Kode atau Nama Barang: ");
        String keyword = scanner.nextLine();

        String sql = "SELECT * FROM tbl_barang WHERE kode LIKE ? OR nama_barang LIKE ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n--- HASIL PENCARIAN ---");
            System.out.printf("| %-2s | %-6s | %-20s | %-7s | %-4s |\n", "#", "Kode", "Nama Barang", "Harga", "Stok");
            System.out.println("---------------------------------------------------------");

            int count = 0;
            while (rs.next()) {
                count++;
                String kode = rs.getString("kode");
                String nama = rs.getString("nama_barang");
                int harga = rs.getInt("harga");
                int stok = rs.getInt("stok");
                System.out.printf("| %-2d | %-6s | %-20s | %-7d | %-4d |\n", count, kode, nama, harga, stok);
            }
            if (count == 0) System.out.println("Data tidak ditemukan!");

        } catch (SQLException e) {
            System.out.println("Error cari data: " + e.getMessage());
        }
    }

    static void ubahData() {
        System.out.println("\n--- UBAH DATA BARANG ---");
        System.out.print("Masukkan Kode Barang yang ingin diubah: ");
        String kode = scanner.nextLine();

        System.out.print("Masukkan Nama Barang Baru: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Harga Baru: ");
        int harga = Integer.parseInt(scanner.nextLine());
        System.out.print("Masukkan Stok Baru: ");
        int stok = Integer.parseInt(scanner.nextLine());

        String sql = "UPDATE tbl_barang SET nama_barang=?, harga=?, stok=? WHERE kode=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nama);
            pstmt.setInt(2, harga);
            pstmt.setInt(3, stok);
            pstmt.setString(4, kode);
            
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Berhasil mengubah data!");
            } else {
                System.out.println("Kode barang tidak ditemukan!");
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengubah data: " + e.getMessage());
        }
    }

    static void hapusData() {
        System.out.println("\n--- HAPUS DATA BARANG ---");
        System.out.print("Masukkan Kode Barang yang ingin dihapus: ");
        String kode = scanner.nextLine();

        System.out.print("Yakin ingin menghapus " + kode + "? (y/n): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("y")) {
            String sql = "DELETE FROM tbl_barang WHERE kode=?";
            
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, kode);
                int rowsDeleted = pstmt.executeUpdate();
                
                if (rowsDeleted > 0) {
                    System.out.println("Berhasil menghapus data!");
                } else {
                    System.out.println("Kode barang tidak ditemukan!");
                }
    
            } catch (SQLException e) {
                System.out.println("Gagal menghapus data: " + e.getMessage());
            }
        } else {
            System.out.println("Aksi dibatalkan.");
        }
    }
}