import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // ====== INPUT STUDENT ======
        System.out.println("=== INPUT MAHASISWA ===");
        System.out.print("Nama: ");
        String nama = input.nextLine();

        System.out.print("Alamat: ");
        String alamat = input.nextLine();

        Student s = new Student(nama, alamat);

        System.out.print("Jumlah Mata Kuliah: ");
        int jumlahMK = input.nextInt();
        input.nextLine();

        for (int i = 0; i < jumlahMK; i++) {
            System.out.print("Nama MK ke-" + (i+1) + ": ");
            String mk = input.nextLine();

            System.out.print("Nilai: ");
            int nilai = input.nextInt();
            input.nextLine();

            s.addCourseGrade(mk, nilai);
        }

        System.out.println("\n=== DATA MAHASISWA ===");
        System.out.println(s);
        s.printGrades();
        System.out.println("Rata-rata: " + s.getAverageGrade());

        // ====== INPUT TEACHER ======
        System.out.println("\n=== INPUT DOSEN ===");
        System.out.print("Nama: ");
        String namaDosen = input.nextLine();

        System.out.print("Alamat: ");
        String alamatDosen = input.nextLine();

        Teacher t = new Teacher(namaDosen, alamatDosen);

        System.out.print("Jumlah MK diajar: ");
        int mkDosen = input.nextInt();
        input.nextLine();

        for (int i = 0; i < mkDosen; i++) {
            System.out.print("Nama MK: ");
            String mk = input.nextLine();

            if (!t.addCourse(mk)) {
                System.out.println("MK sudah ada!");
            }
        }

        System.out.println("\n=== DATA DOSEN ===");
        System.out.println(t);
        t.printCourses();

        input.close();
    }
}