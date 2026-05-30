public class Percobaan1 {
    public static void main(String[] args) {
        int[] a = new int[5];
        try {
            a[5] = 100; // indek di luar batas, akan menghasilkan ArrayIndexOutOfBoundsException
        } catch (Exception e) {
            System.out.println("Terjadi pelanggaran memory");
            System.out.println("Detail error: " + e);
        }
        System.out.println("Selesai Percobaan 1");
    }
}
