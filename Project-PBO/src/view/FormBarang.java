package view;

import config.Koneksi;
import model.Barang;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FormBarang extends JFrame {
    private JLabel lblId, lblNama, lblHarga, lblStok, lblCari;
    private JTextField txtId, txtNama, txtHarga, txtStok, txtCari;
    private JButton btnSimpan, btnEdit, btnHapus, btnClear;
    private JTable tabelBarang;
    private JScrollPane scrollPane;
    private DefaultTableModel modelTabel;

    public FormBarang() {
        setTitle("Kelol Data Barang");
        setSize(700,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        JPanel panelInput = new JPanel(new GridLayout(4,2,5,5));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblId = new JLabel("ID Barang:"); txtId = new JTextField();
        lblNama = new JLabel("Nama Barang:"); txtNama = new JTextField();
        lblHarga = new JLabel("Harga:"); txtHarga = new JTextField();
        lblStok = new JLabel("Stok:"); txtStok = new JTextField();

        panelInput.add(lblId); panelInput.add(txtId);
        panelInput.add(lblNama); panelInput.add(txtNama);
        panelInput.add(lblHarga); panelInput.add(txtHarga);
        panelInput.add(lblStok); panelInput.add(txtStok);

        JPanel panelKontrol = new JPanel(new BorderLayout(5,5 ));
        panelKontrol.setBorder(BorderFactory.createEmptyBorder(0, 10,0,10));

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        panelButton.add(btnSimpan); panelButton.add(btnEdit); panelButton.add(btnHapus); panelButton.add(btnClear);

        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblCari = new JLabel("Cari Barang: "); txtCari = new JTextField(15);
        panelCari.add(lblCari); panelCari.add(txtCari);

        panelKontrol.add(panelButton, BorderLayout.WEST);
        panelKontrol.add(panelCari, BorderLayout.EAST);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelInput, BorderLayout.CENTER);
        panelAtas.add(panelKontrol, BorderLayout.SOUTH);

        modelTabel = new DefaultTableModel();
        modelTabel.addColumn("ID Barang"); modelTabel.addColumn("Nama Barang"); modelTabel.addColumn("Harga"); modelTabel.addColumn("Stok");
        tabelBarang = new JTable(modelTabel);
        scrollPane = new JScrollPane(tabelBarang);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Barang"));
        add(scrollPane, BorderLayout.CENTER);

        tampilkanData("");
        btnSimpan.addActionListener(e -> simpanData());
        btnEdit.addActionListener(e -> editData());
        btnHapus.addActionListener(e -> hapusData());
        btnClear.addActionListener(e -> bersihkanForm());

        txtCari.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tampilkanData(txtCari.getText());
            }
        });

        tabelBarang.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int baris = tabelBarang.getSelectedRow();
                if (baris != -1) {
                    txtId.setText(modelTabel.getValueAt(baris, 0).toString());
                    txtId.setEditable(false);
                    txtNama.setText(modelTabel.getValueAt(baris, 1).toString());
                    txtHarga.setText(modelTabel.getValueAt(baris, 2).toString());
                    txtStok.setText(modelTabel.getValueAt(baris, 3).toString());
                }
            }
        });
    }

    private void tampilkanData(String keyword) {
        modelTabel.setRowCount(0);
        String sql = "SELECT * FROM barang WHERE id_barang LIKE ? OR nama_barang LIKE ?";
        try (Connection conn = Koneksi.getKoneksi(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Barang brg = new Barang(
                    rs.getString("id_barang"),
                    rs.getString("nama_barang"),
                    rs.getInt("harga"),
                    rs.getInt("stok")
                );

                modelTabel.addRow(new Object[]{brg.getIdBarang(), brg.getNamaBarang(), brg.getHarga(),brg.getStok()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage());
        }
    }

    private void simpanData() {
        if (txtId.getText().isEmpty() || txtNama.getText().isEmpty() || txtHarga.getText().isEmpty() || txtStok.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua data wajib diisi");
            return;
        }
        String sql = "INSERT INTO barang VALUES (?, ?, ?, ?)";
        try (Connection conn = Koneksi.getKoneksi(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, txtId.getText());
            pst.setString(2, txtNama.getText());
            pst.setInt(3, Integer.parseInt(txtHarga.getText()));
            pst.setInt(4, Integer.parseInt(txtStok.getText()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Disimpan!");
            tampilkanData(""); bersihkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan: " + e.getMessage());
        }
    }

    private void editData() {
        String sql = "UPDATE barang SET nama_barang=?, harga=?, stok=? WHERE id_barang=?";
        try ( Connection conn = Koneksi.getKoneksi(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, txtNama.getText());
            pst.setInt(2, Integer.parseInt(txtHarga.getText()));
            pst.setInt(3, Integer.parseInt(txtStok.getText()));
            pst.setString(4, txtId.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Diperbarui!");
            tampilkanData(""); bersihkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan: " + e.getMessage());
        }
    }

    private void hapusData() {
        int opsi = JOptionPane.showConfirmDialog(this, "Hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (opsi == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM barang WHERE id_barang=?";
            try ( Connection conn = Koneksi.getKoneksi(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, txtNama.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Dihapus!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan: " + e.getMessage());
            }
        }
    }
    
    private void bersihkanForm() {
        txtId.setText(""); txtId.setEditable(true);
        txtNama.setText(""); txtHarga.setText(""); txtStok.setText(""); txtCari.setText("");
        tampilkanData("");
    }
}