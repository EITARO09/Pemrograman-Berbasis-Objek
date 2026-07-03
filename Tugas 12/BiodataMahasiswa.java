import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BiodataMahasiswa extends JFrame {
    private final JTextField nimField;
    private final JTextField namaField;
    private final JTextField programStudiField;
    private final JTextArea outputArea;

    public BiodataMahasiswa() {
        setTitle("Aplikasi Biodata Mahasiswa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 380);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Data"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nimLabel = new JLabel("NIM");
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(nimLabel, gbc);

        nimField = new JTextField(28);
        gbc.gridx = 1;
        inputPanel.add(nimField, gbc);

        JLabel namaLabel = new JLabel("Nama");
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(namaLabel, gbc);

        namaField = new JTextField(28);
        gbc.gridx = 1;
        inputPanel.add(namaField, gbc);

        JLabel programStudiLabel = new JLabel("Program Studi");
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(programStudiLabel, gbc);

        programStudiField = new JTextField(28);
        gbc.gridx = 1;
        inputPanel.add(programStudiField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        JButton tampilkanButton = new JButton("Tampilkan");
        JButton resetButton = new JButton("Reset");
        buttonPanel.add(tampilkanButton);
        buttonPanel.add(resetButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(buttonPanel, gbc);

        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        outputArea.setBorder(BorderFactory.createTitledBorder("Output"));

        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        add(inputPanel, BorderLayout.NORTH);
        add(outputScroll, BorderLayout.CENTER);

        tampilkanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanBiodata();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
    }

    private void tampilkanBiodata() {
        String nim = nimField.getText().trim();
        String nama = namaField.getText().trim();
        String programStudi = programStudiField.getText().trim();

        StringBuilder output = new StringBuilder();
        output.append("========== BIODATA MAHASISWA ==========\n\n");
        output.append(String.format("NIM           : %s\n", nim));
        output.append(String.format("Nama          : %s\n", nama));
        output.append(String.format("Program Studi : %s\n", programStudi));

        outputArea.setText(output.toString());
    }

    private void resetForm() {
        nimField.setText("");
        namaField.setText("");
        programStudiField.setText("");
        outputArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BiodataMahasiswa frame = new BiodataMahasiswa();
            frame.setVisible(true);
        });
    }
}
