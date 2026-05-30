
public class UtamaTryCatch {
    public static void main(String[] args) {
        Test3 o = new Test3();
        o.methodA();
        try {
            o.methodB();
        } catch (Exception e) {
            System.out.println("Error di Method B");
            System.out.println("Detail error: " + e);
        } finally {
            System.out.println("Ini selalu dicetak");
        }
    }
}
