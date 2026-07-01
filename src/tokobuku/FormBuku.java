package tokobuku;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * FormBuku
 * Form manajemen data buku toko buku.
 * Field: no, judul buku, pengarang, harga, stok.
 * Fitur: Tambah, Ubah, Hapus, Tampil data (CRUD) + Cari buku.
 */
public class FormBuku extends JFrame {

    private JTextField  txtJudul, txtPengarang, txtHarga, txtStok, txtCari;
    private JTable      tabelBuku;
    private DefaultTableModel model;
    private JButton     btnSimpan, btnUbah, btnHapus, btnBatal, btnCari, btnReset;
    private int         noTerpilih = -1;

    public FormBuku() {
        setTitle("Data Buku - Toko Buku");
        setSize(850, 580);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        tampilkanData("");
    }

    private void initComponents() {
        setLayout(null);

        // ===== Judul halaman =====
        JLabel lblJudul = new JLabel("MANAJEMEN DATA BUKU");
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblJudul.setForeground(new Color(52, 73, 94));
        lblJudul.setBounds(20, 15, 400, 25);
        add(lblJudul);

        // ===== Panel Cari =====
        JPanel panelCari = new JPanel();
        panelCari.setBorder(BorderFactory.createTitledBorder("Cari Buku"));
        panelCari.setBounds(20, 45, 800, 60);
        panelCari.setLayout(null);
        add(panelCari);

        JLabel lblCari = new JLabel("Judul / Pengarang");
        lblCari.setBounds(15, 22, 130, 25);
        panelCari.add(lblCari);

        txtCari = new JTextField();
        txtCari.setBounds(150, 22, 350, 28);
        panelCari.add(txtCari);

        btnCari = new JButton("Cari");
        btnCari.setBackground(new Color(52, 73, 94));
        btnCari.setForeground(Color.WHITE);
        btnCari.setFocusPainted(false);
        btnCari.setBounds(510, 22, 80, 28);
        panelCari.add(btnCari);

        btnReset = new JButton("Tampil Semua");
        btnReset.setFocusPainted(false);
        btnReset.setBounds(600, 22, 120, 28);
        panelCari.add(btnReset);

        // ===== Panel Form Input =====
        JPanel panelForm = new JPanel();
        panelForm.setBorder(BorderFactory.createTitledBorder("Input Data Buku"));
        panelForm.setBounds(20, 115, 800, 140);
        panelForm.setLayout(null);
        add(panelForm);

        // Baris 1: Judul & Pengarang
        JLabel lblJudulBuku = new JLabel("Judul Buku");
        lblJudulBuku.setBounds(15, 25, 100, 25);
        panelForm.add(lblJudulBuku);

        txtJudul = new JTextField();
        txtJudul.setBounds(120, 25, 300, 28);
        panelForm.add(txtJudul);

        JLabel lblPengarang = new JLabel("Pengarang");
        lblPengarang.setBounds(440, 25, 100, 25);
        panelForm.add(lblPengarang);

        txtPengarang = new JTextField();
        txtPengarang.setBounds(545, 25, 235, 28);
        panelForm.add(txtPengarang);

        // Baris 2: Harga & Stok
        JLabel lblHarga = new JLabel("Harga (Rp)");
        lblHarga.setBounds(15, 65, 100, 25);
        panelForm.add(lblHarga);

        txtHarga = new JTextField();
        txtHarga.setBounds(120, 65, 200, 28);
        panelForm.add(txtHarga);

        JLabel lblStok = new JLabel("Stok");
        lblStok.setBounds(340, 65, 60, 25);
        panelForm.add(lblStok);

        txtStok = new JTextField();
        txtStok.setBounds(405, 65, 100, 28);
        panelForm.add(txtStok);

        // Tombol aksi
        btnSimpan = new JButton("Simpan");
        btnSimpan.setBackground(new Color(39, 174, 96));
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFocusPainted(false);
        btnSimpan.setBounds(120, 103, 90, 30);
        panelForm.add(btnSimpan);

        btnUbah = new JButton("Ubah");
        btnUbah.setBackground(new Color(52, 73, 94));
        btnUbah.setForeground(Color.WHITE);
        btnUbah.setFocusPainted(false);
        btnUbah.setBounds(220, 103, 80, 30);
        panelForm.add(btnUbah);

        btnHapus = new JButton("Hapus");
        btnHapus.setBackground(new Color(192, 57, 43));
        btnHapus.setForeground(Color.WHITE);
        btnHapus.setFocusPainted(false);
        btnHapus.setBounds(310, 103, 80, 30);
        panelForm.add(btnHapus);

        btnBatal = new JButton("Batal / Bersihkan");
        btnBatal.setFocusPainted(false);
        btnBatal.setBounds(400, 103, 160, 30);
        panelForm.add(btnBatal);

        // ===== Tabel Data Buku =====
        String[] kolom = {"No", "Judul Buku", "Pengarang", "Harga (Rp)", "Stok"};
        model = new DefaultTableModel(kolom, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabelBuku = new JTable(model);
        tabelBuku.setRowHeight(24);
        tabelBuku.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabelBuku.getColumnModel().getColumn(1).setPreferredWidth(250);
        tabelBuku.getColumnModel().getColumn(2).setPreferredWidth(160);
        tabelBuku.getColumnModel().getColumn(3).setPreferredWidth(110);
        tabelBuku.getColumnModel().getColumn(4).setPreferredWidth(60);

        JScrollPane scrollPane = new JScrollPane(tabelBuku);
        scrollPane.setBounds(20, 265, 800, 270);
        add(scrollPane);

        // ===== Event Listener =====
        btnSimpan.addActionListener(e -> simpanData());
        btnUbah.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnBatal.addActionListener(e -> bersihkanForm());
        btnCari.addActionListener(e -> tampilkanData(txtCari.getText().trim()));
        btnReset.addActionListener(e -> { txtCari.setText(""); tampilkanData(""); });
        txtCari.addActionListener(e -> tampilkanData(txtCari.getText().trim()));
        tabelBuku.getSelectionModel().addListSelectionListener(e -> isiFormDariTabel());
    }

    /** Tampilkan data buku ke tabel. Jika keyword tidak kosong, filter berdasar judul/pengarang */
    private void tampilkanData(String keyword) {
        model.setRowCount(0);
        try (Connection konek = Koneksi.getKoneksi()) {
            if (konek == null) return;
            String sql;
            PreparedStatement ps;
            if (keyword.isEmpty()) {
                sql = "SELECT no, judul, pengarang, harga, stok FROM tb_buku ORDER BY no";
                ps = konek.prepareStatement(sql);
            } else {
                sql = "SELECT no, judul, pengarang, harga, stok FROM tb_buku "
                    + "WHERE judul LIKE ? OR pengarang LIKE ? ORDER BY no";
                ps = konek.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("no"),
                        rs.getString("judul"),
                        rs.getString("pengarang"),
                        String.format("Rp %,.0f", rs.getDouble("harga")),
                        rs.getInt("stok")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menampilkan data: " + e.getMessage());
        }
    }

    private boolean validasiForm() {
        if (txtJudul.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Judul buku wajib diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtPengarang.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pengarang wajib diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            Double.parseDouble(txtHarga.getText().trim().replace(",", "").replace(".", ""));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka! Contoh: 85000", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(txtStok.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Stok harus berupa angka bulat! Contoh: 10", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void simpanData() {
        if (!validasiForm()) return;
        try (Connection konek = Koneksi.getKoneksi()) {
            if (konek == null) return;
            PreparedStatement ps = konek.prepareStatement(
                    "INSERT INTO tb_buku (judul, pengarang, harga, stok) VALUES (?, ?, ?, ?)");
            ps.setString(1, txtJudul.getText().trim());
            ps.setString(2, txtPengarang.getText().trim());
            ps.setDouble(3, Double.parseDouble(txtHarga.getText().trim()));
            ps.setInt(4, Integer.parseInt(txtStok.getText().trim()));
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data buku berhasil disimpan!");
            bersihkanForm();
            tampilkanData("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + e.getMessage());
        }
    }

    private void ubahData() {
        if (noTerpilih == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data pada tabel terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validasiForm()) return;
        try (Connection konek = Koneksi.getKoneksi()) {
            if (konek == null) return;
            PreparedStatement ps = konek.prepareStatement(
                    "UPDATE tb_buku SET judul = ?, pengarang = ?, harga = ?, stok = ? WHERE no = ?");
            ps.setString(1, txtJudul.getText().trim());
            ps.setString(2, txtPengarang.getText().trim());
            ps.setDouble(3, Double.parseDouble(txtHarga.getText().trim()));
            ps.setInt(4, Integer.parseInt(txtStok.getText().trim()));
            ps.setInt(5, noTerpilih);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data buku berhasil diubah!");
            bersihkanForm();
            tampilkanData("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengubah data: " + e.getMessage());
        }
    }

    private void hapusData() {
        if (noTerpilih == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data pada tabel terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Yakin ingin menghapus buku ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi != JOptionPane.YES_OPTION) return;

        try (Connection konek = Koneksi.getKoneksi()) {
            if (konek == null) return;
            PreparedStatement ps = konek.prepareStatement("DELETE FROM tb_buku WHERE no = ?");
            ps.setInt(1, noTerpilih);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data buku berhasil dihapus!");
            bersihkanForm();
            tampilkanData("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage());
        }
    }

    private void isiFormDariTabel() {
        int baris = tabelBuku.getSelectedRow();
        if (baris == -1) return;

        noTerpilih = (int) model.getValueAt(baris, 0);
        txtJudul.setText((String) model.getValueAt(baris, 1));
        txtPengarang.setText((String) model.getValueAt(baris, 2));
        // Hapus format "Rp x.xxx" sebelum diisi ke field harga
        String hargaStr = model.getValueAt(baris, 3).toString()
                .replace("Rp ", "").replace(",", "").trim();
        txtHarga.setText(hargaStr);
        txtStok.setText(String.valueOf(model.getValueAt(baris, 4)));
    }

    private void bersihkanForm() {
        txtJudul.setText("");
        txtPengarang.setText("");
        txtHarga.setText("");
        txtStok.setText("");
        noTerpilih = -1;
        tabelBuku.clearSelection();
    }
}
