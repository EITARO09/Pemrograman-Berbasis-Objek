-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 14, 2026 at 01:43 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penjualan_db`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tambah_transaksi` (IN `p_id_transaksi` VARCHAR(15), IN `p_id_pelanggan` VARCHAR(10))   BEGIN
    INSERT INTO transaksi(id_transaksi, tanggal, id_pelanggan, total_bayar)
    VALUES (p_id_transaksi, CURDATE(), p_id_pelanggan, 0);
END$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_hitung_subtotal` (`qty` INT, `harga_barang` INT) RETURNS INT(11) DETERMINISTIC BEGIN
    RETURN qty * harga_barang;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` varchar(10) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `harga` int(11) NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `harga`, `stok`) VALUES
('B001', 'Laptop ASUS ROG', 15000000, 10),
('B002', 'Mouse Logitech', 250000, 50);

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_detail` int(11) NOT NULL,
  `id_transaksi` varchar(15) DEFAULT NULL,
  `id_barang` varchar(10) DEFAULT NULL,
  `jumlah` int(11) NOT NULL,
  `subtotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `detail_transaksi`
--
DELIMITER $$
CREATE TRIGGER `trg_kurangi_stok` AFTER INSERT ON `detail_transaksi` FOR EACH ROW BEGIN
    UPDATE barang 
    SET stok = stok - NEW.jumlah 
    WHERE id_barang = NEW.id_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` varchar(10) NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `telepon` varchar(15) DEFAULT NULL,
  `alamat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama_pelanggan`, `telepon`, `alamat`) VALUES
('P001', 'Budi Santoso', '08123456789', 'Sukabumi');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(15) NOT NULL,
  `tanggal` date NOT NULL,
  `id_pelanggan` varchar(10) DEFAULT NULL,
  `total_bayar` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`, `nama_lengkap`, `role`) VALUES
(1, 'admin', 'admin123', 'Admin', 'Admin');

-- --------------------------------------------------------

--
-- Stand-in structure for view `view_laporan_penjualan`
-- (See below for the actual view)
--
CREATE TABLE `view_laporan_penjualan` (
`No_Transaksi` varchar(15)
,`Tanggal` date
,`Pelanggan` varchar(100)
,`Nama_Barang` varchar(100)
,`Qty` int(11)
,`Harga_Satuan` int(11)
,`Subtotal` int(11)
,`Total_Transaksi` int(11)
);

-- --------------------------------------------------------

--
-- Structure for view `view_laporan_penjualan`
--
DROP TABLE IF EXISTS `view_laporan_penjualan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_laporan_penjualan`  AS SELECT `t`.`id_transaksi` AS `No_Transaksi`, `t`.`tanggal` AS `Tanggal`, `p`.`nama_pelanggan` AS `Pelanggan`, `b`.`nama_barang` AS `Nama_Barang`, `dt`.`jumlah` AS `Qty`, `b`.`harga` AS `Harga_Satuan`, `dt`.`subtotal` AS `Subtotal`, `t`.`total_bayar` AS `Total_Transaksi` FROM (((`detail_transaksi` `dt` join `transaksi` `t` on(`dt`.`id_transaksi` = `t`.`id_transaksi`)) left join `pelanggan` `p` on(`t`.`id_pelanggan` = `p`.`id_pelanggan`)) join `barang` `b` on(`dt`.`id_barang` = `b`.`id_barang`)) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_pelanggan` (`id_pelanggan`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE,
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
