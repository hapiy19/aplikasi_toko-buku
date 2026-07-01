package tokobuku;

import java.awt.*;
import javax.swing.*;

/**
 * FormUtama
 * Dashboard / menu utama setelah login.
 */
public class FormUtama extends JFrame {

    public FormUtama() {
        setTitle("Dashboard - Aplikasi Toko Buku");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        // Header
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(new Color(52, 73, 94));
        panelHeader.setPreferredSize(new Dimension(600, 70));

        JLabel lblHeader = new JLabel("  SELAMAT DATANG DI APLIKASI TOKO BUKU");
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblHeader.setForeground(Color.WHITE);
        panelHeader.add(lblHeader, BorderLayout.CENTER);

        // Menu tombol
        JPanel panelMenu = new JPanel(new GridLayout(2, 1, 20, 20));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(60, 100, 60, 100));

        JButton btnDataBuku = new JButton("DATA BUKU");
        btnDataBuku.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDataBuku.setBackground(new Color(39, 174, 96));
        btnDataBuku.setForeground(Color.WHITE);
        btnDataBuku.setFocusPainted(false);

        JButton btnLogout = new JButton("LOGOUT");
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogout.setBackground(new Color(192, 57, 43));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);

        panelMenu.add(btnDataBuku);
        panelMenu.add(btnLogout);

        btnDataBuku.addActionListener(e -> new FormBuku().setVisible(true));

        btnLogout.addActionListener(e -> {
            int konfirmasi = JOptionPane.showConfirmDialog(this,
                    "Apakah Anda yakin ingin logout?",
                    "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                new FormLogin().setVisible(true);
                this.dispose();
            }
        });

        setLayout(new BorderLayout());
        add(panelHeader, BorderLayout.NORTH);
        add(panelMenu, BorderLayout.CENTER);
    }
}
