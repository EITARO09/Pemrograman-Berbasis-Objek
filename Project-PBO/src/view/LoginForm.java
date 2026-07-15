package view;

import config.Koneksi;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class LoginForm extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblUsername, lblPassword, lblTitle;

    public LoginForm() {
        setTitle("Login System");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        lblTitle = new JLabel("LOGIN APLIKASI", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 0,0));
        add(lblTitle, BorderLayout.NORTH);

        JPanel panelInput = new JPanel(new GridLayout(2,2,5,5));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        lblUsername = new JLabel("Username: ");
        txtUsername = new JTextField();
        lblPassword = new JLabel("Password: ");
        txtPassword = new JPasswordField();

        panelInput.add(lblUsername);
        panelInput.add(txtUsername);
        panelInput.add(lblPassword);
        panelInput.add(txtPassword);
        add(panelInput, BorderLayout.CENTER);

        btnLogin = new JButton("Login");
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTombol.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        panelTombol.add(btnLogin);
        add(panelTombol, BorderLayout.SOUTH);

        btnLogin.addActionListener(e -> btnLoginActionPerformed());
    }

    private void btnLoginActionPerformed() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan password harus diisi.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection conn = Koneksi.getKoneksi();
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login berhasil! Selamat datang " + rs.getString("nama_lengkap"));
                new MenuUtamaForm().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password salah.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
