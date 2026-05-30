public class Percobaan3 {
    public static void main(String[] args) {
        int bil = 10;
        try {
            System.out.println(bil / 0);
        } catch (Exception e) {
            System.out.println("Ini menghandle error yang terjadi");
            System.out.println("Detail error: " + e);
        }
        System.out.println("Selesai Percobaan 3");
    }
}
