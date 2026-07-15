package view;

import javax.swing.*;
import java.awt.*;

public class MenuUtamaForm extends JFrame {
    private JButton btnBarang, btnKeluar;

    public MenuUtamaForm() {
        setTitle("Menu Utama");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3,1,10,10));

        JLabel lblTitle = new JLabel("MENU UTAMA", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));

        btnBarang = new JButton("Kelola Data Barang (CRUD)");
        btnKeluar = new JButton("Keluar Aplikasi");

        add(btnBarang);
        add(btnKeluar);

        btnBarang.addActionListener(e -> new FormBarang().setVisible(true));
        btnKeluar.addActionListener(e -> {
            int opsi = JOptionPane.showConfirmDialog(this, "Apakah Anda ingin keluar?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (opsi == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
}
