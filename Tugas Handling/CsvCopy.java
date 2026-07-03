import java.io.*;

public class CsvCopy {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java CsvCopy source.csv destination.csv");
            return;
        }
        String src = args[0];
        String dst = args[1];

        try (BufferedReader br = new BufferedReader(new FileReader(src));
             BufferedWriter bw = new BufferedWriter(new FileWriter(dst))) {
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Copied " + src + " to " + dst);
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}
