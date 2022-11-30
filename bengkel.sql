-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2022 at 09:34 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

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
-- Table structure for table `detail_service`
--

CREATE TABLE `detail_service` (
  `kd_sparepart` varchar(10) NOT NULL,
  `harga` int(10) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `no_faktur` varchar(25) NOT NULL,
  `ongkos` int(10) NOT NULL,
  `subtotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `status` varchar(100) NOT NULL,
  `id_login` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `email`, `password`, `status`, `id_login`) VALUES
('user', '123', '123', 'Gudang', 'LG0003'),
('nasra', 'test@mail.com', 'test', 'Kasir', 'LG0004'),
('sigit', 'mail.com', 'test', 'Admin', 'LG0005');

-- --------------------------------------------------------

--
-- Table structure for table `mekanik`
--

CREATE TABLE `mekanik` (
  `kd_mekanik` varchar(10) NOT NULL,
  `nm_mekanik` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `no_telepon` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mekanik`
--

INSERT INTO `mekanik` (`kd_mekanik`, `nm_mekanik`, `alamat`, `no_telepon`) VALUES
('MK0001', 'DASUK', 'LEGOK NYENANG', '988765'),
('MK0002', 'AWANG', 'sukabumii', '9823131232');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `kd_pelanggan` varchar(10) NOT NULL,
  `nm_pelanggan` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `no_telepon` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`kd_pelanggan`, `nm_pelanggan`, `alamat`, `no_telepon`) VALUES
('PL0001', 'ALWAN', 'SUKABUMI', 98765),
('PL0002', 'REVAN', 'SUKABUMI', 9876);

-- --------------------------------------------------------

--
-- Table structure for table `service_motor`
--

CREATE TABLE `service_motor` (
  `no_faktur` varchar(25) NOT NULL,
  `tanggal` date NOT NULL,
  `kd_pelanggan` varchar(10) NOT NULL,
  `kd_mekanik` varchar(10) NOT NULL,
  `no_polisi` varchar(15) NOT NULL,
  `keluhan` text NOT NULL,
  `kd_user` varchar(10) NOT NULL,
  `total` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sparepart`
--

CREATE TABLE `sparepart` (
  `kd_sparepart` varchar(10) NOT NULL,
  `nm_sparepart` varchar(50) NOT NULL,
  `harga` float NOT NULL,
  `stok` int(10) NOT NULL,
  `ongkos` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sparepart`
--

INSERT INTO `sparepart` (`kd_sparepart`, `nm_sparepart`, `harga`, `stok`, `ongkos`) VALUES
('SP0001', 'BAN LUAR', 175000, 43, 10000),
('SP0002', 'SPION', 20000, 0, 8000),
('SP0003', 'JOK', 200000, 9, 10000),
('SP0004', 'aaa', 60000, 5, 5000),
('SP0005', 'Sepda', 12000, 2, 5),
('SP0006', 'gas', 1233, 1233, 1233);

-- --------------------------------------------------------

--
-- Table structure for table `stok`
--

CREATE TABLE `stok` (
  `id_stok` int(11) NOT NULL,
  `kd_sparepart` varchar(10) NOT NULL,
  `qty` int(11) NOT NULL,
  `jenis` varchar(10) NOT NULL,
  `tanggal` datetime NOT NULL,
  `created_by` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Indexes for table `stok`
--
ALTER TABLE `stok`
  ADD PRIMARY KEY (`id_stok`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `stok`
--
ALTER TABLE `stok`
  MODIFY `id_stok` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_service`
--
ALTER TABLE `detail_service`
  ADD CONSTRAINT `fk_faktur` FOREIGN KEY (`no_faktur`) REFERENCES `service_motor` (`no_faktur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_sparepart` FOREIGN KEY (`kd_sparepart`) REFERENCES `sparepart` (`kd_sparepart`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `service_motor`
--
ALTER TABLE `service_motor`
  ADD CONSTRAINT `service_motor_ibfk_1` FOREIGN KEY (`kd_mekanik`) REFERENCES `mekanik` (`kd_mekanik`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `service_motor_ibfk_2` FOREIGN KEY (`kd_pelanggan`) REFERENCES `pelanggan` (`kd_pelanggan`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
