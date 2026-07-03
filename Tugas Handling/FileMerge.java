import java.io.*;

public class FileMerge {
    public static void main(String[] args) {
        String file1 = "file1.txt";
        String file2 = "file2.txt";
        String out = "merged.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {
            for (String f : new String[]{file1, file2}) {
                try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        bw.write(line);
                        bw.newLine();
                    }
                }
            }
            System.out.println("Files merged into " + out);
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}
