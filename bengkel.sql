-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2022 at 09:36 AM
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

--
-- Dumping data for table `detail_service`
--

INSERT INTO `detail_service` (`kd_sparepart`, `harga`, `jumlah`, `no_faktur`, `ongkos`, `subtotal`) VALUES
('SP0003', 200000, 2, '20221200001', 10000, 410000),
('SP0006', 1233, 2, '20221200002', 1233, 3699),
('SP0001', 175000, 2, '20221200003', 10000, 360000),
('SP0001', 175000, 2, '20221200004', 10000, 360000);

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
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `kd_pelanggan` varchar(10) NOT NULL,
  `kd_mekanik` varchar(10) NOT NULL,
  `no_polisi` varchar(15) NOT NULL,
  `keluhan` text NOT NULL,
  `kd_user` varchar(10) NOT NULL,
  `total` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service_motor`
--

INSERT INTO `service_motor` (`no_faktur`, `tanggal`, `kd_pelanggan`, `kd_mekanik`, `no_polisi`, `keluhan`, `kd_user`, `total`) VALUES
('20221200001', '2022-12-01 07:13:34', 'PL0001', 'MK0002', '123', '12313', 'LG0005', 410000),
('20221200002', '2022-12-01 07:13:38', 'PL0002', 'MK0002', 'test', 'test', 'LG0005', 3699),
('20221200003', '2022-12-01 07:20:03', 'PL0001', 'MK0001', '2', 'dadsa', 'LG0005', 360000),
('20221200004', '2022-12-01 07:24:34', 'PL0002', 'MK0002', 'test', 'dadad', 'LG0005', 360000);

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
('SP0001', 'BAN LUAR', 175000, 39, 10000),
('SP0002', 'SPION', 20000, 0, 8000),
('SP0003', 'JOK', 200000, 7, 10000),
('SP0004', 'aaa', 60000, 5, 5000),
('SP0005', 'Sepda', 12000, 5, 5),
('SP0006', 'gas', 1233, 1231, 1233);

-- --------------------------------------------------------

--
-- Table structure for table `stok`
--

CREATE TABLE `stok` (
  `id_stok` int(11) NOT NULL,
  `kd_sparepart` varchar(10) NOT NULL,
  `qty` int(11) NOT NULL,
  `jenis` varchar(10) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp(),
  `created_by` varchar(10) NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stok`
--

INSERT INTO `stok` (`id_stok`, `kd_sparepart`, `qty`, `jenis`, `tanggal`, `created_by`, `keterangan`) VALUES
(3, 'SP0003', 2, 'OUT', '2022-12-01 06:57:53', 'LG0005', 'Terjual'),
(4, 'SP0006', 2, 'OUT', '2022-12-01 07:04:07', 'LG0005', 'Terjual'),
(5, 'SP0001', 2, 'OUT', '2022-12-01 07:20:03', 'LG0005', 'Terjual'),
(6, 'SP0001', 2, 'OUT', '2022-12-01 07:24:34', 'LG0005', 'Terjual');

--
-- Triggers `stok`
--
DELIMITER $$
CREATE TRIGGER `stokk` AFTER INSERT ON `stok` FOR EACH ROW BEGIN
	IF (NEW.jenis = 'IN') 
    THEN
    UPDATE sparepart SET stok = stok + NEW.qty
    where kd_sparepart = NEW.kd_sparepart;
    ELSE
    UPDATE sparepart SET stok = stok - NEW.qty
    where kd_sparepart = NEW.kd_sparepart;
    END IF;
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_service`
--
ALTER TABLE `detail_service`
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
  ADD PRIMARY KEY (`no_faktur`);

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
  MODIFY `id_stok` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_service`
--
ALTER TABLE `detail_service`
  ADD CONSTRAINT `fk_faktur` FOREIGN KEY (`no_faktur`) REFERENCES `service_motor` (`no_faktur`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
