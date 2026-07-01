package tokobuku;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

/**
 * FormLogin
 * Form login untuk masuk ke aplikasi Toko Buku.
 */
public class FormLogin extends JFrame {

    private JTextField    txtUsername;
    private JPasswordField txtPassword;
    private JButton        btnLogin, btnKeluar;

    public FormLogin() {
        setTitle("Login - Aplikasi Toko Buku");
        setSize(420, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        JPanel panelUtama = new JPanel();
        panelUtama.setBackground(new Color(52, 73, 94)); // biru gelap elegan
        panelUtama.setLayout(null);
        setContentPane(panelUtama);

        JLabel lblJudul = new JLabel("TOKO BUKU");
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblJudul.setForeground(Color.WHITE);
        lblJudul.setBounds(130, 18, 250, 35);
        panelUtama.add(lblJudul);

        JLabel lblSub = new JLabel("Silakan login untuk melanjutkan");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(Color.WHITE);
        lblSub.setBounds(110, 55, 250, 20);
        panelUtama.add(lblSub);

        JPanel panelForm = new JPanel();
        panelForm.setBackground(Color.WHITE);
        panelForm.setBounds(40, 90, 340, 185);
        panelForm.setLayout(null);
        panelUtama.add(panelForm);

        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblUser.setBounds(30, 25, 100, 25);
        panelForm.add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(30, 50, 280, 30);
        panelForm.add(txtUsername);

        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblPass.setBounds(30, 85, 100, 25);
        panelForm.add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(30, 110, 280, 30);
        panelForm.add(txtPassword);

        btnLogin = new JButton("LOGIN");
        btnLogin.setBackground(new Color(52, 73, 94));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLogin.setBounds(30, 148, 130, 32);
        panelForm.add(btnLogin);

        btnKeluar = new JButton("KELUAR");
        btnKeluar.setBackground(new Color(192, 57, 43));
        btnKeluar.setForeground(Color.WHITE);
        btnKeluar.setFocusPainted(false);
        btnKeluar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnKeluar.setBounds(180, 148, 130, 32);
        panelForm.add(btnKeluar);

        btnLogin.addActionListener(e -> prosesLogin());
        btnKeluar.addActionListener(e -> System.exit(0));
        txtPassword.addActionListener(e -> prosesLogin());
    }

    private void prosesLogin() {
        String username = txtUsername.getText().trim();
        String password = String.valueOf(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Username dan password wajib diisi!",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection konek = Koneksi.getKoneksi()) {
            if (konek == null) return;
            PreparedStatement ps = konek.prepareStatement(
                    "SELECT * FROM tb_user WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this,
                        "Login berhasil! Selamat datang, " + username,
                        "Sukses", JOptionPane.INFORMATION_MESSAGE);
                new FormUtama().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Username atau password salah!",
                        "Gagal Login", JOptionPane.ERROR_MESSAGE);
                txtPassword.setText("");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Kesalahan database:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormLogin().setVisible(true));
    }
}
