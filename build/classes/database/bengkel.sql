-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 30, 2020 at 11:59 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
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

--
-- Dumping data for table `detail_service`
--

INSERT INTO `detail_service` (`kd_sparepart`, `harga`, `jumlah`, `no_faktur`, `ongkos`, `subtotal`) VALUES
('SP0002', 20000, 2, '19081100001', 8000, 48000),
('SP0003', 200000, 2, '19081100001', 10000, 410000),
('SP0001', 175000, 4, '20063000001', 10000, 710000),
('SP0004', 60000, 5, '20063000001', 5000, 305000);

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
('ALWAN', 'alwan2710@gmail.com', '123', 'ADMIN', 'LG0001'),
('12', 'puguhlakson@gmail.com', '12', 'Admin', 'LG0002');

-- --------------------------------------------------------

--
-- Table structure for table `mekanik`
--

CREATE TABLE `mekanik` (
  `kd_mekanik` varchar(10) NOT NULL,
  `nm_mekanik` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `no_telepon` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mekanik`
--

INSERT INTO `mekanik` (`kd_mekanik`, `nm_mekanik`, `alamat`, `no_telepon`) VALUES
('MK0001', 'DASUK', 'LEGOK NYENANG', 988765),
('MK0002', 'AWANG', 'sukabumii', 857987),
('MK0003', 'Rudi', 'Bogor', 876435655),
('MK0004', 'Daeng Doer', 'Citereup', 906899768);

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
('PL0002', 'REVAN', 'SUKABUMI', 9876),
('PL0003', 'Rendy', 'Bogor', 896565643);

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
  `keluhan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service_motor`
--

INSERT INTO `service_motor` (`no_faktur`, `tanggal`, `kd_pelanggan`, `kd_mekanik`, `no_polisi`, `keluhan`) VALUES
('19081100001', '2019-08-11', 'PL0001', 'MK0002', 'F 3456 QPO', 'perbaiki semuanya'),
('20063000001', '2020-06-30', 'PL0002', 'MK0001', 'F 4958 LE', 'Tune Up');

-- --------------------------------------------------------

--
-- Table structure for table `sparepart`
--

CREATE TABLE `sparepart` (
  `kd_sparepart` varchar(10) NOT NULL,
  `nm_sparepart` varchar(50) NOT NULL,
  `harga` int(10) NOT NULL,
  `stok` int(10) NOT NULL,
  `ongkos` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sparepart`
--

INSERT INTO `sparepart` (`kd_sparepart`, `nm_sparepart`, `harga`, `stok`, `ongkos`) VALUES
('SP0001', 'Ban Bridegestone', 1750000, 21, 10000),
('SP0002', 'Spion Sein', 200000, 778, 8000),
('SP0003', 'Jok Seet', 200000, 9, 10000),
('SP0005', 'Bummper Depan Toyota M3', 5000000, 69, 200000),
('SP0006', 'Bummper Belakang Toyota M3', 4500000, 57, 200000),
('SP0007', 'Master Rem Brembo Rcs19', 9000000, 35, 250000),
('SP0008', 'Kampas Rem Depan Toyota Z1', 300000, 79, 70000),
('SP0009', 'Kap Kaca depan Polarized', 1600000, 48, 300000),
('SP0010', 'Master Rem IRC', 3200000, 86, 150000);

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
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_service`
--
ALTER TABLE `detail_service`
  ADD CONSTRAINT `detail_service_ibfk_1` FOREIGN KEY (`no_faktur`) REFERENCES `service_motor` (`no_faktur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `service_motor`
--
ALTER TABLE `service_motor`
  ADD CONSTRAINT `service_motor_ibfk_1` FOREIGN KEY (`kd_mekanik`) REFERENCES `mekanik` (`kd_mekanik`),
  ADD CONSTRAINT `service_motor_ibfk_2` FOREIGN KEY (`kd_pelanggan`) REFERENCES `pelanggan` (`kd_pelanggan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
