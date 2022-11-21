-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 19 Agu 2019 pada 09.05
-- Versi Server: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
  `kd_sparepart` varchar(10) NOT NULL,
  `harga` int(10) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `no_faktur` varchar(25) NOT NULL,
  `ongkos` int(10) NOT NULL,
  `subtotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_service`
--

INSERT INTO `detail_service` (`kd_sparepart`, `harga`, `jumlah`, `no_faktur`, `ongkos`, `subtotal`) VALUES
('SP0002', 20000, 2, '19081100001', 8000, 48000),
('SP0003', 200000, 2, '19081100001', 10000, 410000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `login`
--

CREATE TABLE `login` (
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `status` varchar(100) NOT NULL,
  `id_login` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `login`
--

INSERT INTO `login` (`username`, `email`, `password`, `status`, `id_login`) VALUES
('ALWAN', 'alwan2710@gmail.com', '123', 'ADMIN', 'LG0001');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mekanik`
--

CREATE TABLE `mekanik` (
  `kd_mekanik` varchar(10) NOT NULL,
  `nm_mekanik` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `no_telepon` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `mekanik`
--

INSERT INTO `mekanik` (`kd_mekanik`, `nm_mekanik`, `alamat`, `no_telepon`) VALUES
('MK0001', 'DASUK', 'LEGOK NYENANG', 988765),
('MK0002', 'AWANG', 'sukabumii', 857987);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pelanggan`
--

CREATE TABLE `pelanggan` (
  `kd_pelanggan` varchar(10) NOT NULL,
  `nm_pelanggan` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `no_telepon` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pelanggan`
--

INSERT INTO `pelanggan` (`kd_pelanggan`, `nm_pelanggan`, `alamat`, `no_telepon`) VALUES
('PL0001', 'ALWAN', 'SUKABUMI', 98765),
('PL0002', 'REVAN', 'SUKABUMI', 9876);

-- --------------------------------------------------------

--
-- Struktur dari tabel `service_motor`
--

CREATE TABLE `service_motor` (
  `no_faktur` varchar(25) NOT NULL,
  `tanggal` date NOT NULL,
  `kd_pelanggan` varchar(10) NOT NULL,
  `kd_mekanik` varchar(10) NOT NULL,
  `no_polisi` varchar(15) NOT NULL,
  `keluhan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `service_motor`
--

INSERT INTO `service_motor` (`no_faktur`, `tanggal`, `kd_pelanggan`, `kd_mekanik`, `no_polisi`, `keluhan`) VALUES
('19081100001', '2019-08-11', 'PL0001', 'MK0002', 'F 3456 QPO', 'perbaiki semuanya');

-- --------------------------------------------------------

--
-- Struktur dari tabel `sparepart`
--

CREATE TABLE `sparepart` (
  `kd_sparepart` varchar(10) NOT NULL,
  `nm_sparepart` varchar(50) NOT NULL,
  `harga` int(10) NOT NULL,
  `stok` int(10) NOT NULL,
  `ongkos` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `sparepart`
--

INSERT INTO `sparepart` (`kd_sparepart`, `nm_sparepart`, `harga`, `stok`, `ongkos`) VALUES
('SP0001', 'BAN LUAR', 175000, 45, 10000),
('SP0002', 'SPION', 20000, 0, 8000),
('SP0003', 'JOK', 200000, 9, 10000),
('SP0004', 'aaa', 60000, 5, 5000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_service`
--
ALTER TABLE `detail_service`
  ADD KEY `kd_sparepart` (`kd_sparepart`),
  ADD KEY `no_faktur` (`no_faktur`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id_login`);

--
-- Indexes for table `mekanik`
--
ALTER TABLE `mekanik`
  ADD PRIMARY KEY (`kd_mekanik`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`kd_pelanggan`);

--
-- Indexes for table `service_motor`
--
ALTER TABLE `service_motor`
  ADD PRIMARY KEY (`no_faktur`),
  ADD KEY `kd_mekanik` (`kd_mekanik`),
  ADD KEY `kd_pelanggan` (`kd_pelanggan`);

--
-- Indexes for table `sparepart`
--
ALTER TABLE `sparepart`
  ADD PRIMARY KEY (`kd_sparepart`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `service_motor`
--
ALTER TABLE `service_motor`
  ADD CONSTRAINT `service_motor_ibfk_1` FOREIGN KEY (`kd_mekanik`) REFERENCES `mekanik` (`kd_mekanik`),
  ADD CONSTRAINT `service_motor_ibfk_2` FOREIGN KEY (`kd_pelanggan`) REFERENCES `pelanggan` (`kd_pelanggan`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
