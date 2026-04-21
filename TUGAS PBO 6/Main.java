import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Pilih Laptop:");
        System.out.println("1. Toshiba");
        System.out.println("2. MacBook");
        System.out.print("Masukkan pilihan: ");

        int pilihan = input.nextInt();
        input.nextLine(); // biar tidak bug

        Laptop laptop;

        if (pilihan == 1) {
            laptop = new Toshiba();
        } else {
            laptop = new MacBook();
        }

        LaptopUser user = new LaptopUser(laptop);

        String perintah;

        do {
            System.out.println("\nMasukkan perintah (ON, OFF, UP, DOWN, EXIT): ");
            perintah = input.nextLine();

            switch (perintah.toUpperCase()) {
                case "ON":
                    user.turnOnLaptop();
                    break;
                case "OFF":
                    user.turnOffLaptop();
                    break;
                case "UP":
                    user.makeLaptopLouder();
                    break;
                case "DOWN":
                    user.makeLaptopSilent();
                    break;
                case "EXIT":
                    System.out.println("Program selesai");
                    break;
                default:
                    System.out.println("Perintah tidak dikenal");
            }

        } while (!perintah.equalsIgnoreCase("EXIT"));

        input.close();
    }
}