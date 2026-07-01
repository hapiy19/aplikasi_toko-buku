package tokobuku;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * Kelas Koneksi
 * Menghubungkan aplikasi Java dengan database MySQL (XAMPP).
 * Pastikan Apache & MySQL di XAMPP Control Panel sudah Running.
 */
public class Koneksi {

    private static final String HOST     = "localhost";
    private static final String PORT     = "3306";
    private static final String DB_NAME  = "db_toko_buku";
    private static final String USER     = "root";
    private static final String PASSWORD = ""; // default XAMPP kosong

    private static final String URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME
            + "?useSSL=false&serverTimezone=Asia/Jakarta";

    public static Connection getKoneksi() {
        Connection konek = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            konek = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Driver MySQL tidak ditemukan!\n"
                    + "Pastikan mysql-connector-j-x.x.x.jar sudah ditambahkan ke Library project.\n"
                    + "Detail: " + e.getMessage(),
                    "Error Driver", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal terhubung ke database!\n"
                    + "Pastikan XAMPP (Apache & MySQL) sudah running dan database 'db_toko_buku' sudah dibuat.\n"
                    + "Detail: " + e.getMessage(),
                    "Error Koneksi", JOptionPane.ERROR_MESSAGE);
        }
        return konek;
    }
}
