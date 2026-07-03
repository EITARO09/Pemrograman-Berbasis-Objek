import java.io.*;
import java.util.Scanner;

public class CSVWriter {
    public static void main(String[] args) {
        String csvFile = "students.csv";
        Scanner sc = new Scanner(System.in);
        System.out.print("Berapa data yang akan ditambahkan? ");
        int n = Integer.parseInt(sc.nextLine());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {
            for (int i = 0; i < n; i++) {
                System.out.print("Masukkan NIM: ");
                String nim = sc.nextLine();
                System.out.print("Masukkan NAMA: ");
                String nama = sc.nextLine();
                System.out.print("Masukkan UMUR: ");
                String umur = sc.nextLine();
                System.out.print("Masukkan PRODI: ");
                String prodi = sc.nextLine();

                String line = nim + "," + nama + "," + umur + "," + prodi;
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Data berhasil ditambahkan ke " + csvFile);
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
