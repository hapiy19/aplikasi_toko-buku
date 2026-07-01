-- =====================================================
-- DATABASE: db_toko_buku
-- Aplikasi: Manajemen Data Buku Toko Buku
-- Tools: XAMPP (MySQL/MariaDB) + phpMyAdmin
-- =====================================================

CREATE DATABASE IF NOT EXISTS db_toko_buku;
USE db_toko_buku;

-- =====================================================
-- TABEL 1: tb_user
-- Untuk menyimpan akun login admin
-- =====================================================
CREATE TABLE tb_user (
    id_user INT(11) NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    level VARCHAR(20) NOT NULL DEFAULT 'admin',
    PRIMARY KEY (id_user),
    UNIQUE KEY username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data default login (username: admin, password: admin123)
INSERT INTO tb_user (username, password, level) VALUES
('admin', 'admin123', 'admin');

-- =====================================================
-- TABEL 2: tb_buku
-- Tabel utama: no, judul, pengarang, harga, stok
-- =====================================================
CREATE TABLE tb_buku (
    no INT(11) NOT NULL AUTO_INCREMENT,
    judul VARCHAR(150) NOT NULL,
    pengarang VARCHAR(100) NOT NULL,
    harga DECIMAL(10,2) NOT NULL DEFAULT 0,
    stok INT(11) NOT NULL DEFAULT 0,
    tanggal_input DATE NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY (no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Contoh data buku
INSERT INTO tb_buku (judul, pengarang, harga, stok) VALUES
('Laskar Pelangi', 'Andrea Hirata', 85000, 15),
('Bumi Manusia', 'Pramoedya Ananta Toer', 95000, 10),
('Negeri 5 Menara', 'Ahmad Fuadi', 78000, 20),
('Perahu Kertas', 'Dee Lestari', 72000, 12),
('Dilan 1990', 'Pidi Baiq', 65000, 25),
('Harry Potter dan Batu Bertuah', 'J.K. Rowling', 120000, 8),
('Sherlock Holmes', 'Arthur Conan Doyle', 90000, 6),
('Rich Dad Poor Dad', 'Robert Kiyosaki', 110000, 18);
