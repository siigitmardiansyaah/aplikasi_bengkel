-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 04 Des 2022 pada 16.37
-- Versi server: 10.4.25-MariaDB
-- Versi PHP: 8.0.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bengkel`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_service`
--

CREATE TABLE `detail_service` (
  `id_detail` int(11) NOT NULL,
  `id_sparepart` int(11) NOT NULL,
  `harga` int(10) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `id_service` int(11) NOT NULL,
  `ongkos` int(10) NOT NULL,
  `subtotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_service`
--

INSERT INTO `detail_service` (`id_detail`, `id_sparepart`, `harga`, `jumlah`, `id_service`, `ongkos`, `subtotal`) VALUES
(2, 3, 45000, 2, 4, 5000, 95000),
(5, 3, 45000, 2, 7, 5000, 95000),
(6, 3, 45000, 2, 8, 5000, 95000),
(7, 3, 45000, 2, 9, 5000, 95000),
(8, 2, 75000, 2, 9, 5000, 155000),
(9, 3, 45000, 2, 10, 5000, 95000),
(10, 2, 75000, 2, 10, 5000, 155000),
(11, 3, 45000, 2, 11, 5000, 95000),
(12, 3, 45000, 2, 12, 5000, 95000),
(13, 3, 45000, 2, 13, 5000, 95000),
(14, 3, 45000, 2, 14, 5000, 95000),
(15, 3, 45000, 2, 15, 5000, 95000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `login`
--

CREATE TABLE `login` (
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `status` varchar(100) NOT NULL,
  `id_login` varchar(10) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `login`
--

INSERT INTO `login` (`username`, `email`, `password`, `status`, `id_login`, `nama`) VALUES
('user', '123', '123', 'Gudang', 'LG0003', ''),
('nasra', 'test@mail.com', 'test', 'Kasir', 'LG0004', ''),
('sigit', 'mail.com', 'test', 'Admin', 'LG0005', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mekanik`
--

CREATE TABLE `mekanik` (
  `id_mekanik` int(11) NOT NULL,
  `kd_mekanik` varchar(10) NOT NULL,
  `nm_mekanik` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `no_telepon` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `mekanik`
--

INSERT INTO `mekanik` (`id_mekanik`, `kd_mekanik`, `nm_mekanik`, `alamat`, `no_telepon`) VALUES
(1, 'MK0001', 'Sura', 'Bekasi', '123132'),
(2, 'MK0002', 'Heri', 'GAS', '1231231');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` int(11) NOT NULL,
  `kd_pelanggan` varchar(10) NOT NULL,
  `nm_pelanggan` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `no_telepon` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `kd_pelanggan`, `nm_pelanggan`, `alamat`, `no_telepon`) VALUES
(1, 'PL0001', 'Cipung', 'Bekasi', 812313123),
(2, 'PL0002', 'Danii', 'Bekasi', 13131);

-- --------------------------------------------------------

--
-- Struktur dari tabel `service_motor`
--

CREATE TABLE `service_motor` (
  `id_service` int(11) NOT NULL,
  `no_faktur` varchar(25) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp(),
  `kd_pelanggan` int(10) NOT NULL,
  `kd_mekanik` int(10) NOT NULL,
  `no_polisi` varchar(15) NOT NULL,
  `keluhan` text NOT NULL,
  `kasir` varchar(10) NOT NULL,
  `total` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `service_motor`
--

INSERT INTO `service_motor` (`id_service`, `no_faktur`, `tanggal`, `kd_pelanggan`, `kd_mekanik`, `no_polisi`, `keluhan`, `kasir`, `total`) VALUES
(4, '20221200001', '2022-12-05 07:40:34', 2, 2, '123123', '123131', 'LG0003', 95000),
(7, '20221200002', '2022-12-04 07:40:40', 1, 2, 'B 0001 FKH', 'TEST', 'LG0003', 250000),
(8, '20221200003', '2022-12-04 07:41:07', 1, 2, 'B 0001 C', 'dadas', 'LG0003', 250000),
(9, '20221200004', '2022-12-04 07:41:07', 1, 1, '123', '123', 'LG0003', 250000),
(10, '20221200005', '2022-12-04 07:41:07', 1, 2, 'dsada', 'dasdad', 'LG0003', 250000),
(11, '20221200006', '2022-12-04 07:41:07', 1, 2, '12', '12', 'LG0003', 95000),
(12, '20221200006', '2022-12-04 07:41:07', 1, 2, '12', '12', 'LG0003', 95000),
(13, '20221200007', '2022-12-04 07:41:07', 1, 1, '1231321', '13213213', 'LG0003', 95000),
(14, '20221200007', '2022-12-04 07:41:07', 1, 1, '1231321', '13213213', 'LG0003', 95000),
(15, '20221200008', '2022-12-04 07:41:07', 1, 1, '12', '12', 'LG0003', 95000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `sparepart`
--

CREATE TABLE `sparepart` (
  `id_sparepart` int(11) NOT NULL,
  `kd_sparepart` varchar(10) NOT NULL,
  `nm_sparepart` varchar(50) NOT NULL,
  `harga` float NOT NULL,
  `stok` int(10) NOT NULL DEFAULT 0,
  `ongkos` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `sparepart`
--

INSERT INTO `sparepart` (`id_sparepart`, `kd_sparepart`, `nm_sparepart`, `harga`, `stok`, `ongkos`) VALUES
(1, 'SP0001', 'Ban', 150000, 0, 10000),
(2, 'SP0002', 'Spion Nmax', 75000, 0, 5000),
(3, 'SP0003', 'Oli 1 Liter', 45000, 8, 5000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `stok`
--

CREATE TABLE `stok` (
  `id_stok` int(11) NOT NULL,
  `id_sparepart` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `jenis` varchar(10) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp(),
  `created_by` varchar(10) NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `stok`
--

INSERT INTO `stok` (`id_stok`, `id_sparepart`, `qty`, `jenis`, `tanggal`, `created_by`, `keterangan`) VALUES
(1, 1, 5, 'IN', '2022-12-02 02:31:19', 'sigit', ''),
(2, 1, 2, 'OUT', '2022-12-02 02:32:11', 'sigit', 'Kadaluarsa'),
(3, 1, 2, 'OUT', '2022-12-02 03:10:43', 'sigit', 'Terjual'),
(4, 1, 2, 'OUT', '2022-12-02 03:10:47', 'sigit', 'Terjual'),
(5, 1, 3, 'OUT', '2022-12-02 03:18:33', 'sigit', 'Terjual'),
(6, 2, 3, 'OUT', '2022-12-02 03:42:07', 'sigit', 'Terjual'),
(7, 3, 2, 'OUT', '2022-12-02 03:48:29', 'sigit', 'Terjual'),
(8, 3, 2, 'OUT', '2022-12-02 03:48:33', 'sigit', 'Terjual'),
(9, 3, 2, 'OUT', '2022-12-02 03:48:50', 'sigit', 'Terjual'),
(10, 3, 2, 'OUT', '2022-12-02 07:45:14', 'sigit', 'Terjual'),
(11, 3, 2, 'OUT', '2022-12-02 07:46:57', 'sigit', 'Terjual'),
(12, 3, 2, 'OUT', '2022-12-02 07:51:01', 'sigit', 'Terjual'),
(13, 2, 2, 'OUT', '2022-12-02 07:51:37', 'sigit', 'Terjual'),
(14, 3, 2, 'OUT', '2022-12-02 07:58:18', 'sigit', 'Terjual'),
(15, 2, 2, 'OUT', '2022-12-02 07:58:18', 'sigit', 'Terjual'),
(16, 3, 2, 'OUT', '2022-12-02 08:23:54', 'sigit', 'Terjual'),
(17, 3, 2, 'OUT', '2022-12-02 08:26:24', 'sigit', 'Terjual'),
(18, 3, 2, 'OUT', '2022-12-02 08:27:41', 'sigit', 'Terjual'),
(19, 3, 2, 'OUT', '2022-12-02 08:27:51', 'sigit', 'Terjual'),
(20, 3, 2, 'OUT', '2022-12-02 08:29:30', 'sigit', 'Terjual'),
(21, 3, 2, 'OUT', '2022-12-02 08:30:43', 'sigit', 'Terjual');

--
-- Trigger `stok`
--
DELIMITER $$
CREATE TRIGGER `stok_trigger` AFTER INSERT ON `stok` FOR EACH ROW BEGIN
	IF (NEW.jenis = 'IN') 
    THEN
    UPDATE sparepart SET stok = stok + NEW.qty
    where id_sparepart = NEW.id_sparepart;
    ELSE
    UPDATE sparepart SET stok = stok - NEW.qty
    where id_sparepart = NEW.id_sparepart;
    END IF;
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `detail_service`
--
ALTER TABLE `detail_service`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `fk_sparepart1` (`id_sparepart`),
  ADD KEY `fk_service` (`id_service`);

--
-- Indeks untuk tabel `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id_login`);

--
-- Indeks untuk tabel `mekanik`
--
ALTER TABLE `mekanik`
  ADD PRIMARY KEY (`id_mekanik`);

--
-- Indeks untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indeks untuk tabel `service_motor`
--
ALTER TABLE `service_motor`
  ADD PRIMARY KEY (`id_service`),
  ADD KEY `kd_meka` (`kd_mekanik`),
  ADD KEY `fk_pelanggan` (`kd_pelanggan`);

--
-- Indeks untuk tabel `sparepart`
--
ALTER TABLE `sparepart`
  ADD PRIMARY KEY (`id_sparepart`);

--
-- Indeks untuk tabel `stok`
--
ALTER TABLE `stok`
  ADD PRIMARY KEY (`id_stok`),
  ADD KEY `fk_sparepart` (`id_sparepart`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `detail_service`
--
ALTER TABLE `detail_service`
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT untuk tabel `mekanik`
--
ALTER TABLE `mekanik`
  MODIFY `id_mekanik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `service_motor`
--
ALTER TABLE `service_motor`
  MODIFY `id_service` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT untuk tabel `sparepart`
--
ALTER TABLE `sparepart`
  MODIFY `id_sparepart` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `stok`
--
ALTER TABLE `stok`
  MODIFY `id_stok` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `detail_service`
--
ALTER TABLE `detail_service`
  ADD CONSTRAINT `fk_service` FOREIGN KEY (`id_service`) REFERENCES `service_motor` (`id_service`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_sparepart1` FOREIGN KEY (`id_sparepart`) REFERENCES `sparepart` (`id_sparepart`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `service_motor`
--
ALTER TABLE `service_motor`
  ADD CONSTRAINT `fk_pelanggan` FOREIGN KEY (`kd_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `kd_meka` FOREIGN KEY (`kd_mekanik`) REFERENCES `mekanik` (`id_mekanik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `stok`
--
ALTER TABLE `stok`
  ADD CONSTRAINT `fk_sparepart` FOREIGN KEY (`id_sparepart`) REFERENCES `sparepart` (`id_sparepart`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
