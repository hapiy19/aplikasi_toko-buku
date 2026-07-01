package tokobuku;

import javax.swing.SwingUtilities;

/**
 * Main - Entry point aplikasi Toko Buku
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormLogin().setVisible(true));
    }
}
