import java.io.*;

public class CSVReader {
    public static void main(String[] args) {
        String csvFile = "students.csv";
        String csvSplitBy = ",";
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    System.out.println("Header: " + line);
                    isHeader = false;
                    continue;
                }
                String[] fields = line.split(csvSplitBy);
                System.out.println("NIM: " + fields[0] + ", NAMA: " + fields[1] + ", UMUR: " + fields[2] + ", PRODI: " + fields[3]);
                count++;
            }
            System.out.println("Jumlah baris data: " + count);
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}
