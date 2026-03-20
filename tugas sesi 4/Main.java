class Bank {
    // Overloading 1: Transfer sesama bank
    public void transferUang(int jumlah, String rekeningTujuan) {
        System.out.println("Status: Berhasil transfer Rp" + jumlah + " ke rekening " + rekeningTujuan + " (Sesama Bank)");
    }

    // Overloading 2: Transfer antar bank
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        System.out.println("Status: Berhasil transfer Rp" + jumlah + " ke rekening " + rekeningTujuan + " di Bank " + bankTujuan);
    }

    // Overloading 3: Transfer dengan berita
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan, String berita) {
        System.out.println("Status: Berhasil transfer Rp" + jumlah + " ke " + bankTujuan + " [" + rekeningTujuan + "]");
        System.out.println("Berita: " + berita);
    }

    public void sukuBunga() {
        System.out.println("Suku Bunga standar adalah 3%");
    }
}

// --- BAGIAN 2: CLASS TURUNAN (METHOD OVERRIDING & BONUS CHALLENGE) ---

class BankBNI extends Bank {
    @Override
    public void sukuBunga() {
        System.out.println("Suku Bunga dari BNI adalah : 4%");
    }

    @Override
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        // Bonus Challenge: Biaya transfer (Gratis jika ke sesama BNI, 6500 jika bank lain)
        int biaya = bankTujuan.equalsIgnoreCase("BNI") ? 0 : 6500;
        System.out.println("[BNI Transfer Mode]");
        System.out.println("Tujuan      : " + bankTujuan + " (" + rekeningTujuan + ")");
        System.out.println("Nominal     : Rp" + jumlah);
        System.out.println("Biaya Admin : Rp" + biaya);
        System.out.println("Total Bayar : Rp" + (jumlah + biaya));
    }
}

class BankBCA extends Bank {
    @Override
    public void sukuBunga() {
        System.out.println("Suku Bunga dari BCA adalah : 4.5%");
    }

    @Override
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        // Bonus Challenge: Biaya transfer (BCA ke bank lain biasanya 2500 via BI-FAST)
        int biaya = bankTujuan.equalsIgnoreCase("BCA") ? 0 : 2500;
        System.out.println("[BCA Transfer Mode]");
        System.out.println("Tujuan      : " + bankTujuan + " (" + rekeningTujuan + ")");
        System.out.println("Nominal     : Rp" + jumlah);
        System.out.println("Biaya Admin : Rp" + biaya);
        System.out.println("Total Bayar : Rp" + (jumlah + biaya));
    }
}

// --- BAGIAN 3: CLASS UTAMA (UNTUK PENGUJIAN) ---

public class Main {
    public static void main(String[] args) {
        System.out.println("========== DEMO METHOD OVERLOADING ==========");
        Bank bankUmum = new Bank();
        
        // Menguji 3 variasi transfer (Overloading)
        bankUmum.transferUang(50000, "11122233");
        System.out.println("---------------------------------------------");
        bankUmum.transferUang(100000, "44455566", "MANDIRI");
        System.out.println("---------------------------------------------");
        bankUmum.transferUang(75000, "77788899", "BRI", "Bayar Bakso");
        bankUmum.sukuBunga();

        System.out.println("\n========== DEMO METHOD OVERRIDING ==========");
        
        // Menguji Bank BNI
        BankBNI bni = new BankBNI();
        bni.sukuBunga();
        bni.transferUang(200000, "998877", "BCA"); // Uji biaya admin (Bonus)

        System.out.println("---------------------------------------------");

        // Menguji Bank BCA
        BankBCA bca = new BankBCA();
        bca.sukuBunga();
        bca.transferUang(150000, "665544", "BNI"); // Uji biaya admin (Bonus)
    }
}