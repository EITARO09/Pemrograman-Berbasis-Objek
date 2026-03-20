import java.util.Scanner;
import java.util.ArrayList;

class Mahasiswa {
    String nim, nama, grade, status;
    int nilai;

    Mahasiswa(String nim, String nama, int nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
        
        // Logika penentuan Grade
        if (nilai >= 80 && nilai <= 100) {
            this.grade = "A";
            this.status = "Lulus";
        } else if (nilai >= 70) {
            this.grade = "B";
            this.status = "Lulus";
        } else if (nilai >= 60) {
            this.grade = "C";
            this.status = "Lulus";
        } else if (nilai >= 50) {
            this.grade = "D";
            this.status = "Tidak Lulus";
        } else if (nilai >= 0 && nilai < 50) {
            this.grade = "E";
            this.status = "Tidak Lulus";
        } else {
            this.grade = "Error";
        }
    }
}

public class HitungNilaiMahasiswa {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Mahasiswa> listMahasiswa = new ArrayList<>();
        
        System.out.print("Masukkan jumlah mahasiswa: ");
        int jumlah = input.nextInt();
        input.nextLine(); // Membersihkan buffer

        for (int i = 0; i < jumlah; i++) {
            System.out.println("\nData Mahasiswa ke-" + (i + 1));
            System.out.print("NIM   : "); String nim = input.nextLine();
            System.out.print("Nama  : "); String nama = input.nextLine();
            System.out.print("Nilai : "); int nilai = input.nextInt();
            input.nextLine();

            if (nilai < 0 || nilai > 100) {
                System.out.println("Input nilai anda salah");
                i--; // Ulangi input untuk mahasiswa yang sama
                continue;
            }
            listMahasiswa.add(new Mahasiswa(nim, nama, nilai));
        }

        System.out.println("\n==========================================");
        double totalNilai = 0;
        int lulus = 0, tdkLulus = 0, gradeA = 0, gradeB = 0, gradeC = 0, gradeD = 0;
        String namaLulus = "", namaTdkLulus = "", namaA = "", namaB = "", namaD = "";

        for (Mahasiswa m : listMahasiswa) {
            System.out.println("NIM   : " + m.nim);
            System.out.println("Nama  : " + m.nama);
            System.out.println("Nilai : " + m.nilai);
            System.out.println("Grade : " + m.grade);
            System.out.println("==========================================");

            totalNilai += m.nilai;
            if (m.status.equals("Lulus")) {
                lulus++;
                namaLulus += m.nama + ", ";
            } else {
                tdkLulus++;
                namaTdkLulus += m.nama + ", ";
            }

            if (m.grade.equals("A")) { gradeA++; namaA += m.nama + ", "; }
            else if (m.grade.equals("B")) { gradeB++; namaB += m.nama + ", "; }
            else if (m.grade.equals("D")) { gradeD++; namaD += m.nama + ", "; }
        }

        // Tampilkan Statistik
        System.out.println("Jumlah Mahasiswa : " + listMahasiswa.size());
        System.out.println("Jumlah Mahasiswa yg Lulus : " + lulus + " yaitu " + membersihkanKoma(namaLulus));
        System.out.println("Jumlah Mahasiswa yg Tidak Lulus : " + tdkLulus + " yaitu " + membersihkanKoma(namaTdkLulus));
        System.out.println("Jumlah Mahasiswa dengan Nilai A = " + gradeA + " yaitu " + membersihkanKoma(namaA));
        System.out.println("Jumlah Mahasiswa dengan Nilai B = " + gradeB + " yaitu " + membersihkanKoma(namaB));
        System.out.println("Jumlah Mahasiswa dengan Nilai D = " + gradeD + " yaitu " + membersihkanKoma(namaD));
        System.out.printf("Rata-rata nilai mahasiswa adalah : %.2f\n", (totalNilai / listMahasiswa.size()));
    }

    // Fungsi pembantu untuk merapikan tampilan nama (menghapus koma di akhir)
    public static String membersihkanKoma(String teks) {
        if (teks.endsWith(", ")) {
            return teks.substring(0, teks.length() - 2);
        }
        return teks.isEmpty() ? "-" : teks;
    }
}